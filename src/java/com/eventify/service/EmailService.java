package com.eventify.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class EmailService {

    public void enviarCorreoHtml(String destinatario, String asunto, String cuerpoHtml) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dev.contact.test@gmail.com", "inwafihmdoicruca");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tu_correo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            message.setContent(cuerpoHtml, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Correo enviado con éxito");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
