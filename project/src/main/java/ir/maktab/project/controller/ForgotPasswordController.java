package ir.maktab.project.controller;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.ResetPasswordDTO;
import ir.maktab.project.event.ForgotPasswordEvent;
import ir.maktab.project.exception.UserNotFoundException;
import ir.maktab.project.service.EmailService;
import ir.maktab.project.service.UserService;
import ir.maktab.project.util.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @GetMapping(value = "/forgot-password")
    public String showForgotPasswordForm() {

        return "forgotPasswordForm";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model, HttpServletRequest request) {
        String token = RandomString.make(30);
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;

            publisher.publishEvent(new ForgotPasswordEvent(resetPasswordLink,email));
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            return "user/login";

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "forgotPasswordForm";
        }


    }


    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {

        Optional<User> user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user.isEmpty()) {
            model.addAttribute("message", "invalid token");
            return "message";
        }
        return "resetPasswordForm";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(ResetPasswordDTO resetPasswordDTO, Model model) {

        Optional<User> user = userService.getByResetPasswordToken(resetPasswordDTO.getMyToken());
        model.addAttribute("title", "reset your password");

        if (user.isEmpty()) {
            model.addAttribute("message", "invalid token");
            return "resetPasswordForm";
        } else {
            userService.updatePassword(user.get(), resetPasswordDTO.getPassword());
            model.addAttribute("message", "You have successfully changed your password");
        }
        model.addAttribute("changePasswordMessage","you have successfully changed password");
        return "user/login";

    }
}
