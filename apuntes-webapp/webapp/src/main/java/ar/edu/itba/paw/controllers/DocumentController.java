package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.DocumentDTO;
import ar.edu.itba.paw.dtos.DocumentListDTO;
import ar.edu.itba.paw.dtos.ReviewListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.DocumentBuilder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Base64;
import java.util.List;

@Component
@Path("/v1/documents")
public class DocumentController {

    private final DocumentService ds;

    private final ClientService cs;

    private final CourseService courseService;

    private final ReviewService rs;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public DocumentController(DocumentService ds, ClientService cs, CourseService courseService, ReviewService rs) {
        this.ds = ds;
        this.cs = cs;
        this.courseService = courseService;
        this.rs = rs;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listDocuments() {
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

    /**
     * Ejemplo de como subir un archivo. POST!
     * BODY:
     * {
     * "courseid":1,
     * "subject": "asuntoooooo",
     * "documentName": "nombre del archivo.pdf",
     * "data": "ZGF0b3MgZGVsIGFyY2hpdm8=",
     * "description": "descripcoiiooono"
     * }
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final DocumentDTO documentDTO) throws HttpException, ValidationException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final Course course = courseService.findById(documentDTO.getCourseid());

        if (course == null) {
            throw new ValidationException(1, "Course not found", "courseId");
        }

        validateDocument(documentDTO);

        final Document document = ds.create(
                new DocumentBuilder()
                        .setUser(client)
                        .setCourse(course)
                        .setSubject(documentDTO.getSubject())
                        .setDocumentName(documentDTO.getDocumentName())
                        .setDocumentSize(documentDTO.getDocumentSize())
                        .setData(Base64.getDecoder().decode(documentDTO.getData()))
                        .setDescription(documentDTO.getDescription())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(document.getDocumentId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{id}/reviews")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listReviews(@PathParam("id") final long id) throws Http404Exception {
        final List<Review> reviews = rs.findByFileId(id);
        if (reviews != null) {
            return Response.ok(new ReviewListDTO(reviews)).build();
        } else {
            throw new Http404Exception("Document not found");
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final DocumentDTO documentDTO) throws HttpException, ValidationException {

        final Document document = ds.findById(id);

        if (document == null) {
            throw new Http404Exception("Document not found");
        }

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        if (client.getClientId() != document.getUser().getClientId()) {
            throw new Http403Exception();
        }

        final Course course = courseService.findById(documentDTO.getCourseid());

        if (document.getUser().getClientId() != client.getClientId()) {
            throw new Http403Exception();
        }

        validateDocument(documentDTO);

        ds.update(
                id,
                new DocumentBuilder()
                        .setUser(client)
                        .setCourse(course)
                        .setSubject(documentDTO.getSubject())
                        .setDocumentName(documentDTO.getDocumentName())
                        .setDocumentSize(documentDTO.getDocumentSize())
                        .setData(Base64.getDecoder().decode(documentDTO.getData()))
                        .setDescription(documentDTO.getDescription())
                        .createModel()
        );

        return Response.ok(new DocumentDTO(ds.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) throws HttpException {

        final Document document = ds.findById(id);

        if (document == null) {
            throw new Http404Exception("Document not found");
        }

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        if (client.getClientId() != document.getUser().getClientId()) {
            throw new Http403Exception();
        }

        ds.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("{id}/download")
    @Produces(value = {MediaType.APPLICATION_OCTET_STREAM})
    public Response downloadFile(@PathParam("id") long id) throws HttpException {

        final Document document = ds.findById(id);

        if (document == null) {
            throw new Http404Exception("Document not found");
        }

        final InputStream inp = new ByteArrayInputStream(document.getData());

        StreamingOutput stream = output -> {
            try {
                IOUtils.copy(inp, output);
            } catch (Exception e) {
                throw new WebApplicationException(e);
            }
        };

        return Response.ok(stream).header("content-disposition", String.format("attachment; filename=\"%s\";", document.getDocumentName())).build();
    }

    @GET
    @Path("/{id}/open")
    public Response openFile(@PathParam("id") long id) throws HttpException {

        final Document document = ds.findById(id);

        if (document == null) {
            throw new Http404Exception("Document not found");
        }

        if (!FilenameUtils.getExtension(document.getDocumentName()).equals("pdf")) {
            return downloadFile(id);
        }

        final InputStream inp = new ByteArrayInputStream(document.getData());

        StreamingOutput stream = output -> {
            try {
                IOUtils.copy(inp, output);
            } catch (Exception e) {
                throw new WebApplicationException(e);
            }
        };

        String contentType = "";

        if (document.getDocumentName().endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (document.getDocumentName().endsWith(".jpg") || document.getDocumentName().endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else {
            return downloadFile(id);
        }

        return Response.ok(stream)
                .header("content-disposition", String.format("inline; filename=\"%s\";", document.getDocumentName()))
                .header("content-type", "application/pdf")
                .build();
    }

    private void validateDocument(final DocumentDTO documentDTO) throws ValidationException {

        if (documentDTO.getSubject() == null || documentDTO.getSubject().isEmpty()) {
            throw new ValidationException(1, "Subject can't be empty", "subject");
        }

        if (documentDTO.getDocumentName() == null || documentDTO.getDocumentName().isEmpty()) {
            throw new ValidationException(2, "Document name can't be empty", "documentName");
        }

        if (documentDTO.getDescription() == null || documentDTO.getDescription().isEmpty()) {
            throw new ValidationException(3, "Description can't be empty", "description");
        }
    }
}
