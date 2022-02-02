package ir.maktab.project.controller;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.service.UserService;
import ir.maktab.project.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class ManagerController {


    public static final  int DEFAULT_PAGE_SIZE=5;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user-all")
    public String showAllUser(Model model) {

       return findPaginated(1,"first_name","asc",model);
    }

    @PostMapping(value = "/check-confirmation")

    public String checkConfirmation(@RequestParam("id") Long id) {

        User  user = userService.findById(id);

        user.setRegisterState(RegisterState.CONFIRM);
        userService.saveOrUpdate(user);
        return "user/resultOfEditRegisterState";

    }
    @PreAuthorize("hasRole('manager')")
    @GetMapping(value = "/manager-panel")
    public String managerPanel(){
        return "manager/managerPanel";
    }

    @GetMapping("/user-all/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNumber,@RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, Model model){

        Page<User> paginated = userService.findPaginated(pageNumber, DEFAULT_PAGE_SIZE, sortField, sortDir);
        List<User> users= paginated.getContent();
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",paginated.getTotalPages());
        model.addAttribute("totalItems",paginated.getTotalElements());
        model.addAttribute("allUser",users);

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", Utility.getReverseSortDir(sortDir));
        return "user/showAllUser";
    }

}
