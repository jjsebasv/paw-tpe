package ar.edu.itba.paw.interfaces;


import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;

public interface EmailService {

    void sendMailToClient(Client to, String subject, String content);

    void sendRegisteredEmail(Client to);

    void sendPasswordResetEmail(Client to);

    void sendNewCommentEmail(Client to, Document document, Review review);

}
