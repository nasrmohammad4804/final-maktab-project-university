package ir.maktab.project.event.listener;

import ir.maktab.project.event.ForgotPasswordEvent;
import ir.maktab.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordEventListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void sendResetPasswordEmail(ForgotPasswordEvent event){
        emailService.sendEmail(event.getEmail(),event.getUrl());
    }
}
