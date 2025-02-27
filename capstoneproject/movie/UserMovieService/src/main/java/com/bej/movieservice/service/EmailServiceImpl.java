package com.bej.movieservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender emailSender;


    public void sendThankYouEmail(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            message.setSubject("Thank You for Signing Up!");
            message.setText("Dear User,\n\nThank you for signing up in our application. We are excited to have you on board!\n\nBest regards,\nThe Application Team");
            emailSender.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
