package ir.maktab.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/unauthorized")
public class UnauthorizedController {

    @GetMapping
    public String getUnauthorizedPage(){
        return "accessDenied";
    }
}
