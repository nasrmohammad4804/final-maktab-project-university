package ir.maktab.project.controller;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller

public class ManagerController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user-all")
    public String showAllUser(Model model) {

        model.addAttribute("allUser", userService.findAllUserExceptManager());
        return "user/showAllUser";
    }

    @PostMapping(value = "/check-confirmation")
    @ResponseBody
    public String checkConfirmation(@RequestParam("id") Long id) {

        Optional<User> userOptional = userService.findById(id);

        userOptional.get().setRegisterState(RegisterState.CONFIRM);
        userService.update(userOptional.get());
        return "user successfully confirmed";

    }
    @PreAuthorize("hasRole('manager')")
    @GetMapping(value = "/manager-panel")
    public String managerPanel(){
        return "manager/managerPanel";
    }

}
