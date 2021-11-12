package ir.maktab.project.controller;

import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserModifyingDTO;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.domain.enumeration.UserType;
import ir.maktab.project.mapper.UserMapper;
import ir.maktab.project.service.RoleService;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    @ResponseBody
    @PostMapping(value = "/change-profile")
    public ResponseEntity<String> changeProfile(@ModelAttribute("user") UserModifyingDTO updatedUser, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");

        if (user.getRole().getName().equals("master")) {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());

            if (updatedUser.getUserType().equals(UserType.STUDENT)) {

                user.setRole(roleService.findRoleByName("student"));
                userService.changeProfile(user, "Student");

            } else userService.save(user);

        } else {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());

            if (updatedUser.getUserType().equals(UserType.MASTER)) {
                user.setRole(roleService.findRoleByName("master"));
                userService.changeProfile(user, "Master");

            } else userService.save(user);

        }
        return ResponseEntity.status(HttpStatus.OK).body("this user successfully update>");
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
    public String findPageForUser(HttpServletRequest request) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByUserName(userName).get();

        HttpSession session = request.getSession();
        session.setAttribute("myUser", user);

        if (user.getRole().getName().equals("manager"))
            return "manager/managerPanel";

        else if (user.getRole().getName().equals("master"))
            return "forward:/master/panel";

        else
            return "student/studentPanel";
    }

    @GetMapping(value = "/register")
    public String register(@ModelAttribute("error") String attribute, Model model) {
        model.addAttribute("error");
        return "user/register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("user") User user, RedirectAttributes attributes) {

        if (userService.findUserByUserName(user.getUserName()).isPresent()) {

            attributes.addAttribute("error", "occurred");
            return "redirect:/register";

        }

        if (user.getUserType().equals(UserType.MASTER)) {

            Master master = Master.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).build();

            master.setRole(roleService.findRoleByName("master"));
            userService.save(master);

            return "master/masterPanel";
        } else {
            Student student = Student.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).build();
            student.setRole(roleService.findRoleByName("student"));
            userService.save(student);

            return "student/studentPanel";
        }
    }
}
