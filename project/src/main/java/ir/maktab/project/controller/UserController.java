package ir.maktab.project.controller;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import ir.maktab.project.mapper.UserMapper;
import ir.maktab.project.service.RecaptchaService;
import ir.maktab.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RecaptchaService captchaService;

    @PreAuthorize("hasRole('manager')")
    @GetMapping(value = "/change-profile")
    public String changeProfile(@RequestParam("id") Long id, Model model, HttpServletRequest request) {
        User user = userService.findById(id).get();

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (user.getRole().getName().equals("master"))
            model.addAttribute("userType", "MASTER");

        else model.addAttribute("userType", "STUDENT");

        return "user/changeProfile";
    }

    @PreAuthorize("hasRole('manager')")

    @PostMapping(value = "/change-profile")
    public String changeProfile(@ModelAttribute("user") User updatedUser, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");

        userService.changeProfile(user,updatedUser);
        return "user/resultOfEditProfile";
    }

    @PreAuthorize("hasRole('manager')")
    @GetMapping(value = "/confirm-user/{id}")
    public String confirmUser(@PathVariable("id") Long id, Model model) {

        Optional<User> optionalUser = userService.findById(id);
        model.addAttribute("unapprovedUser", optionalUser.get());

        if (optionalUser.get().getRole().getName().equals("master"))
            model.addAttribute("userType", "master");

        else model.addAttribute("userType", "student");

        return "user/confirmUser";
    }

    @PostMapping(value = "/search-users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserSearchResponseDTO> searchingUsers(@RequestBody UserSearchRequestDTO dto) {

        return mapper.convertEntitiesToDTOList(userService.searchUser(dto));

    }

    @GetMapping(value = "/login-user")
    @PreAuthorize("hasAnyRole('master','manager','student')")
    public String findPageForUser(HttpServletRequest request) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByUserName(userName).get();

        HttpSession session = request.getSession();
        session.setAttribute("myUser", user);
        session.setMaxInactiveInterval(60*12);

        if (user.getRole().getName().equals("manager"))
            return "manager/managerPanel";

        else if (user.getRole().getName().equals("master"))
            return "forward:/master/panel";

        else
            return "redirect:/student/panel";
    }

    @GetMapping(value = "/register")
    public String register(@ModelAttribute("error") String attribute, Model model) {
        model.addAttribute("error");
        return "user/register";
    }
    @GetMapping(value ="/login")
    public String login(){
        return "user/login";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("user") User user, RedirectAttributes attributes, @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                           HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                captchaService.verifyRecaptcha(ip, recaptchaResponse);

        if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
            attributes.addAttribute("error",captchaVerifyMessage);
            return "redirect:/register";
        }

        if (userService.isRegisterValid(user.getUserName())) {

            attributes.addAttribute("error", "this userName already exists");
            return "redirect:/register";
        }
        return userService.register(user);
    }
}
