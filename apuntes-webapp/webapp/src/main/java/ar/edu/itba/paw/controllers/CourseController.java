package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.CourseDTO;
import ar.edu.itba.paw.dtos.CourseListDTO;
import ar.edu.itba.paw.dtos.DocumentListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.builders.CourseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Component
@Path("/v1/courses")
public class CourseController {

    private final CourseService cs;

    private final DocumentService ds;

    private final ClientService clientService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public CourseController(CourseService cs, DocumentService ds, ClientService clientService) {
        this.cs = cs;
        this.ds = ds;
        this.clientService = clientService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listCourses(@DefaultValue("") @QueryParam("term") String term) {
        List<Course> courses;
        if (term.isEmpty()) {
            courses = cs.getAll();
        } else {
            courses = cs.findByTerm(term);
        }

        return Response.ok(new CourseListDTO(courses)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) throws Http404Exception {
        final Course course = cs.findById(id);
        if (course != null) {
            return Response.ok(new CourseDTO(course)).build();
        } else {
            throw new Http404Exception("Course not found");
        }
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final CourseDTO courseDTO) throws HttpException, ValidationException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        if (cs.findByCode(courseDTO.getCode()) != null) {
            throw new ValidationException(1, "A course with the same code already exists.", "code");
        }

        validateCourse(courseDTO);

        final Course course = cs.create(
                new CourseBuilder()
                        .setName(courseDTO.getName())
                        .setCode(courseDTO.getCode())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(course.getCourseid())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{id}/documents")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listDocuments(@PathParam("id") final long id) throws Http404Exception {
        final List<Document> documents = ds.findByCourseId(id);
        if (documents != null) {
            return Response.ok(new DocumentListDTO(documents)).build();
        } else {
            throw new Http404Exception("Course not found");
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final CourseDTO courseDTO) throws HttpException, ValidationException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        final Course course = cs.findById(id);

        if (course == null) {
            throw new Http404Exception("Course not found");
        }

        validateCourse(courseDTO);

        cs.update(
                id,
                new CourseBuilder()
                        .setName(courseDTO.getName())
                        .setCode(courseDTO.getCode())
                        .createModel()
        );

        return Response.ok(new CourseDTO(cs.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        cs.delete(id);
        return Response.noContent().build();
    }

    private void validateCourse(final CourseDTO courseDTO) throws ValidationException {

        if (courseDTO.getName() == null || courseDTO.getName().isEmpty()) {
            throw new ValidationException(1, "Name can't be empty", "name");
        }

        if (courseDTO.getCode() == null || courseDTO.getCode().isEmpty()) {
            throw new ValidationException(2, "Code can't be empty", "code");
        }
    }
}
