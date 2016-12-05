package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.ReviewDTO;
import ar.edu.itba.paw.dtos.ReviewListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.ReviewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@Component
@Path("/api/v1/reviews")
public class ReviewController {

    private final ReviewService rs;

    @Context
    private UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    private final ClientService cs;
    private final DocumentService ds;

    @Autowired
    public ReviewController(ReviewService rs, ClientService cs, DocumentService ds) {
        this.rs = rs;
        this.cs = cs;
        this.ds = ds;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listReviews() {
        final List<Review> reviews = rs.getAll();
        return Response.ok(new ReviewListDTO(reviews)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) {
        final Review review = rs.findById(id);
        if (review != null) {
            return Response.ok(new ReviewDTO(review)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(final ReviewDTO reviewDTO) {

        final Principal principal = securityContext.getUserPrincipal();
        final String username = principal.getName();
        final Client client = cs.findByUsername(username);
        final Document document = ds.findById(reviewDTO.getFileid());

        //FIXME Autenticacion y errores
        final Review review = rs.create(
                new ReviewBuilder()
                        .setRanking(reviewDTO.getRanking())
                        .setReview(reviewDTO.getReview())
                        .setFile(document)
                        .setUser(client)
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(review.getReviewid())).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        rs.delete(id);
        return Response.noContent().build();
    }
}
