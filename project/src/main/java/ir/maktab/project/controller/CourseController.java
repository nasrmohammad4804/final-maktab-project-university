package ir.maktab.project.controller;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.User;
import ir.maktab.project.mapper.UserMapper;
import ir.maktab.project.service.CourseService;
import ir.maktab.project.service.StudentService;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/create")
    public String createCourse(@ModelAttribute("error") String error) {
        return "course/addCourse";
    }


    @GetMapping(value = "/confirm/{id}")
    public String confirmOfAddCourse(@PathVariable("id") Long id, HttpServletRequest request) {
        User user = userService.findById(id);

        HttpSession session = request.getSession();
        Course course = (Course) session.getAttribute("course");

        course.setMaster((Master) user);
        courseService.saveOrUpdate(course);

        return "course/resultOfAddCourse";
    }

    @GetMapping(value = "/all")
    public String getAllUserOfCourse(Model model) {
        model.addAttribute("allCourse", courseService.findAll());
        return "course/showAllCourse";
    }


    @GetMapping(value = "/show/{id}")
    public String showCourse(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Course course = courseService.findById(id);
        List<User> users = new ArrayList<>(course.getStudents());


        HttpSession session = request.getSession();
        session.setAttribute("courseId", course.getId());

        model.addAttribute("allStudentOfCourse", mapper.convertEntitiesToUserOfCourseDTO(users));
        model.addAttribute("master", mapper.convertEntityTOUserOfCourseDTO(course.getMaster()));
        return "course/showAllUserOfCourse";
    }


    @ResponseBody
    @DeleteMapping(value = "/delete-student/{student-id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("student-id") Long studentId, HttpServletRequest request) {

        HttpSession session = request.getSession();

        Long courseId = (Long) session.getAttribute("courseId");

        if (courseId == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("<p style=background-color:red>your are dont allow delete student without selecting course</p> ");

        Course course = courseService.findById(courseId);

        Student student = studentService.findWithId(studentId);

        courseService.deleteUser(course, student);

        return ResponseEntity.status(HttpStatus.OK).body
                ("you are successfully delete " + student.getUserName());
    }

    @GetMapping(value = "/select-student")
    public String showAllStudentNotExistInCourse(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Long courseId = (Long) session.getAttribute("courseId");

        Course course = courseService.findById(courseId);

        String checker = course.getStudents().isEmpty() ? "" : "data";

        List<Student> students = studentService.dontExistOnCourse(course.getStudents(), checker);
        if (students.isEmpty())
            return "user/userNotFounding";

        model.addAttribute("students", students);
        return "course/selectStudentForCourse";
    }

    @GetMapping(value = "/confirm-student")
    public String confirmOfAddStudentToCourse(@RequestParam("id") Long id, HttpServletRequest request) {

        HttpSession session = request.getSession();

        Long courseId = (Long) session.getAttribute("courseId");
        Course course = courseService.findById(courseId);

        courseService.addStudent(course, studentService.findById(id));

        return "course/resultAddStudent";
    }

    @PostMapping(value = "/find-master")

    public String findAll(@ModelAttribute("course") Course course, HttpServletRequest request, Model model, RedirectAttributes attributes) {

        if (courseService.checkCreateCourse(course)) {
            attributes.addAttribute("error", "your time is not valid");
            return "redirect:/course/create";
        }

        HttpSession session = request.getSession();
        session.setAttribute("course", course);

        List<User> users = userService.findAllMaster();

        if (users.isEmpty())
            return "user/userNotFounding";

        model.addAttribute("users", mapper.convertEntitiesToDTOList(users));

        return "manager/selectMaster";
    }
}
