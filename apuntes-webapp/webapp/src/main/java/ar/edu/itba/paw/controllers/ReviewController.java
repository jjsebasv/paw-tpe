package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.ReviewDTO;
import ar.edu.itba.paw.dtos.ReviewListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.ReviewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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
    public Response getById(@PathParam("id") final long id) throws Http404Exception {
        final Review review = rs.findById(id);
        if (review != null) {
            return Response.ok(new ReviewDTO(review)).build();
        } else {
            throw new Http404Exception("Review not found");
        }
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final ReviewDTO reviewDTO) throws HttpException, ValidationException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final Document document = ds.findById(reviewDTO.getFileid());

        final boolean userHasReviewed = rs.findByFileId(reviewDTO.getFileid())
                .stream()
                .anyMatch(review -> review.getUser().getClientId() == reviewDTO.getUserid());

        if (userHasReviewed) {
            throw new ValidationException(3, "You already reviewed this document", "fileid");
        }

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

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final ReviewDTO reviewDTO) throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final Review review = rs.findById(id);

        if (review == null) {
            throw new Http404Exception("Review not found");
        }

        if (client.getClientId() != review.getUser().getClientId()) {
            throw new Http403Exception();
        }

        final Document document = ds.findById(reviewDTO.getFileid());

        if (review.getUser().getClientId() != client.getClientId()) {
            throw new Http403Exception();
        }

        rs.update(
                id,
                new ReviewBuilder()
                        .setRanking(reviewDTO.getRanking())
                        .setReview(reviewDTO.getReview())
                        .setFile(document)
                        .setUser(client)
                        .createModel()
        );

        return Response.ok(new ReviewDTO(rs.findById(id))).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final Review review = rs.findById(id);

        if (review == null) {
            throw new Http404Exception("Review not found");
        }

        if (client.getClientId() != review.getUser().getClientId()) {
            throw new Http403Exception();
        }

        rs.delete(id);
        return Response.noContent().build();
    }
}
