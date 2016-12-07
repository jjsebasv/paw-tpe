package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.Secured;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.CourseDTO;
import ar.edu.itba.paw.dtos.CourseListDTO;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.Course;
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
@Path("/api/v1/courses")
public class CourseController {

    private final CourseService cs;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public CourseController(CourseService cs) {
        this.cs = cs;
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
    @Secured({ClientRole.ROLE_ADMIN})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final CourseDTO courseDTO) {

        final Course course = cs.create(
                new CourseBuilder()
                        .setName(courseDTO.getName())
                        .setCode(courseDTO.getCode())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(course.getCourseid())).build();
        return Response.created(uri).build();
    }

    @PUT
    @Secured({ClientRole.ROLE_ADMIN})
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final CourseDTO courseDTO) throws Http404Exception {

        final Course course = cs.findById(id);

        if (course == null) {
            throw new Http404Exception("Course not found");
        }

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
    @Secured({ClientRole.ROLE_ADMIN})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        cs.delete(id);
        return Response.noContent().build();
    }
}
