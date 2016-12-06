package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.DocumentDTO;
import ar.edu.itba.paw.dtos.DocumentListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.builders.DocumentBuilder;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@Component
@Path("/api/v1/documents")
public class DocumentController {

    private final DocumentService ds;

    private final ClientService cs;

    private final CourseService courseService;

    @Context
    private UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Autowired
    public DocumentController(DocumentService ds, ClientService cs, CourseService courseService) {
        this.ds = ds;
        this.cs = cs;
        this.courseService = courseService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listCourses() {
        final List<Document> documents = ds.getAll();
        return Response.ok(new DocumentListDTO(documents)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) throws Http404Exception {
        final Document document = ds.findById(id);
        if (document != null) {
            return Response.ok(new DocumentDTO(document)).build();
        } else {
            throw new Http404Exception("Document not found");
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@FormDataParam("file") InputStream fileInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileMetaData,
                           @FormDataParam("document") @Valid final DocumentDTO documentDTO) throws IOException {

        final Principal principal = securityContext.getUserPrincipal();
        final String username = principal.getName();
        final Client client = cs.findByUsername(username);
        final Course course = courseService.findById(documentDTO.getCourseid());

        final Document document = ds.create(
                new DocumentBuilder()
                        .setUser(client)
                        .setCourse(course)
                        .setSubject(documentDTO.getSubject())
                        .setDocumentName(documentDTO.getDocumentName())
                        .setDocumentSize(documentDTO.getDocumentSize())
                        .setData(IOUtils.toByteArray(fileInputStream))
                        .setDescription(documentDTO.getDescription())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(document.getDocumentId())).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        ds.delete(id);
        return Response.noContent().build();
    }

}
