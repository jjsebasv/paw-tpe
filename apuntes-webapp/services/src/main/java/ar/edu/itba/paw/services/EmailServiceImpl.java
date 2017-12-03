package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.EmailService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private static final String USERNAME = "apuntespaw@gmail.com";
    private static final String PASSWORD = "12345qwerty";
    private static final int MAX_CONNECT_ATTEMPTS = 3;

    private static final String REGISTERED_EMAIL_SUBJECT = "Apuntes PAW: Bienvenido!";
    private static final String REGISTERED_EMAIL_BODY = "<p>Hola %s!,</p>" +
            "<p>Bienvenido a Apuntes PAW! Esperamos que puedas aprovechar la" +
            " página, y contribuir con los archivos que tengas</p>" +
            "<p>Saludos!<br/>Equipo de Apuntes PAW</p>";

    private static final String PASSWORD_RESET_EMAIL_SUBJECT = "Apuntes PAW: Su contrasena ha sido restaurada";
    private static final String PASSWORD_RESET_EMAIL_BODY = "<p>Hola %s!,</p>" +
            "<p>Alguien ha restaurado la contrasena de la cuenta asociada a este email. Si no" +
            " fuiste vos, te pedimos que la restaures nuevamente, o nos contactes a la brevedad.</p>" +
            "<p>Saludos!<br/>Equipo de Apuntes PAW</p>";

    private static final String COMMENT_EMAIL_SUBJECT = "Apuntes PAW: Han comentado en un archivo tuyo!";
    private static final String COMMENT_EMAIL_BODY = "<p>Hola %s!,</p>" +
            "<p>Alguien ha comentado en tu archivo llamado %s. A continuacion puedes ver lo que han escrito:</p>" +
            "<p>%s</p>" +
            "<p>Saludos!<br/>Equipo de Apuntes PAW</p>";


    @Autowired
    private ClientService cs;

    private Transport transport = null;
    private MimeMessage message;

    public EmailServiceImpl() {

        LOGGER.info("Authenticating with SMTP server.");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        for (int tries = 0; tries < MAX_CONNECT_ATTEMPTS; tries++) {
            try {
                transport.connect(USERNAME, PASSWORD);

                if (transport.isConnected()) {
                    LOGGER.info("Connected to SMTP server.");
                    break;
                }
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                LOGGER.debug(e.toString());
                LOGGER.error("Connection to SMTP server failed. Attempt {}/{}", tries + 1, MAX_CONNECT_ATTEMPTS);
            }
        }

        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(USERNAME));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRegisteredEmail(Client to) {
        sendMailToClient(to, REGISTERED_EMAIL_SUBJECT, String.format(REGISTERED_EMAIL_BODY, to.getName()));
    }

    @Override
    public void sendPasswordResetEmail(Client to) {
        sendMailToClient(to, PASSWORD_RESET_EMAIL_SUBJECT, String.format(PASSWORD_RESET_EMAIL_BODY, to.getName()));
    }

    @Override
    public void sendNewCommentEmail(Client to, Document document, Review review) {
        sendMailToClient(to, COMMENT_EMAIL_SUBJECT, String.format(COMMENT_EMAIL_BODY, to.getName(), document.getSubject(), review.getReview()));
    }

    private static final int MAX_EMAILS_TRIES = 1;

    @Override
    public void sendMailToClient(Client to, String subject, String content) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {

            int tries;

            for (tries = 0; tries < MAX_EMAILS_TRIES; tries++) {
                try {
                    LOGGER.info("Sending email. Attempt {}/{}", tries + 1, MAX_CONNECT_ATTEMPTS);
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.getEmail()));
                    message.setSubject(subject);
                    message.setText(content, "utf-8", "html");
                    transport.send(message, InternetAddress.parse(to.getEmail()));
                    break;
                } catch (MessagingException e) {
                    LOGGER.debug(e.toString());
                    LOGGER.error("Sending failed. Attempt {}/{}", tries + 1, MAX_CONNECT_ATTEMPTS);
                }
            }
            if (tries == MAX_EMAILS_TRIES) {
                LOGGER.error("Failed to send email.");
            }
        });
    }

}
