package ir.maktab.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public static final String UNIVERSITY_EMAIL="mnasr13804804@gmail.com";

    public void sendEmail(String email, String link) {


        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);


            helper.setTo(email);
            helper.setText(getContent(link), true);
            helper.setSubject(getSubject());

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private String getSubject() {
        return "Here's the link to reset your password";
    }

    private String getContent(String link) {
        return
                "<p>Hello,</p>"
                        + "<p>You have requested to reset your password.</p>"
                        + "<p>Click the link below to change your password:</p>"
                        + "<p><a href=\"" + link + "\">Change my password</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you do remember your password, "
                        + "or you have not made the request.</p>";
    }

}

