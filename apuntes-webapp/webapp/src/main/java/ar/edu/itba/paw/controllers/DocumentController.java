package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.DocumentDTO;
import ar.edu.itba.paw.dtos.DocumentListDTO;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.builders.DocumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Component
@Path("/api/v1/documents")
public class DocumentController {

    private final DocumentService ds;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public DocumentController(DocumentService ds) {
        this.ds = ds;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listCourses() {
        final List<Document> documents = ds.getAll();
        return Response.ok(new DocumentListDTO(documents)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) {
        final Document document = ds.findById(id);
        if (document != null) {
            return Response.ok(new DocumentDTO(document)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/")
    //FIXME Ver que mediatype tiene que aceptar
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(final DocumentDTO documentDTO) {

        //FIXME Obtener los objetos a partir de los ids
        //FIXME Autenticacion y errores
        final Document document = ds.create(
                new DocumentBuilder()
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
