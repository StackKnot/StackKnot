package com.codeup.stackknot.Spring.Twilio.service;

import com.codeup.stackknot.Spring.Twilio.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.models.Whiteboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;

@Service("mailService")
public class EmailService {


    @Autowired
    SendGrid sendGrid;

    public Response sendemail(EmailRequest emailrequest)
    {

        Mail mail = new Mail(new Email("stackknot@gmail.com"), "Message from: " + emailrequest.getSubject(), new Email(emailrequest.getFrom()), new Content("text/plain", emailrequest.getBody()));

        mail.setReplyTo(new Email("stackknot@gmail.com"));

        Request request = new Request();

        Response response = null;

        try {
            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            response=this.sendGrid.api(request);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return response;
    }
}
