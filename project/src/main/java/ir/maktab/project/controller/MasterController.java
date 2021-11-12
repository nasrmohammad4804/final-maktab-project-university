package ir.maktab.project.controller;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Master;
import ir.maktab.project.exception.ExamNotFoundException;
import ir.maktab.project.mapper.UserMapper;
import ir.maktab.project.service.CourseService;
import ir.maktab.project.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Predicate;

@Controller
@RequestMapping(value = "/master")
public class MasterController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private ExamService examService;

    @GetMapping(value = "/panel")
    public String getMasterPanel(HttpServletRequest request, Model model, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session = request.getSession();
        Master user = (Master) session.getAttribute("myUser");

        model.addAttribute("allCourse", mapper.convertEntitiesToCourseDTO(courseService.findByMaster(user)));
        return "master/masterPanel";
    }

    @GetMapping(value = "/show-course")
    public String getAllExamOfCourse(@RequestParam("id") Long id, Model model) {
        Optional<Course> course = courseService.findWithId(id);
        model.addAttribute("course", course.get());

        return "master/showAllExam";
    }

    @GetMapping(value = "/create-exam/{id}")
    public String createExam(@ModelAttribute("myError") String error, @PathVariable("id") Long courseId, Model model) {


        model.addAttribute("courseId", courseId);

        return "master/createExam";
    }

    @PostMapping(value = "/create-exam")
    public String createExam(@ModelAttribute("exam") Exam exam, RedirectAttributes attributes, @RequestParam("courseId") Long courseId) {

        Optional<Course> course = courseService.findWithId(courseId);
        if (LocalDateTime.now().isAfter(exam.getStartTime()) || exam.getStartTime().toLocalTime().isAfter(exam.getEndTime())
                || exam.getStartTime().toLocalDate().isAfter(course.get().getCourseFinishedDate())) {

            attributes.addAttribute("myError", "start time is not valid");
            return "redirect:/master/create-exam/" + courseId;
        }

        courseService.addExam(course.get(), exam);

        return "master/resultOfAddExam";
    }

    @GetMapping(value = "/change-examBeingHeld")
    public String changeExamIsBeingHeld(@ModelAttribute("error") String error, @RequestParam("examId") Long examId, Model model) {

        Exam exam = examService.findById(examId);
        model.addAttribute("exam", exam);

        return "master/changeExamIsBeingHeld";
    }

    @PostMapping(value = "/change-examBeingHeld")
    public String changeExamIsBeingHeld(Exam updatedExam, RedirectAttributes attributes) {

       Exam exam= examService.findById(updatedExam.getId());
        if (!updatedExam.getEndTime().isAfter(LocalTime.now())) {
            attributes.addAttribute("error", "your newTime is not valid");
            return "redirect:/master/change-examBeingHeld?examId=" + updatedExam.getId();
        }

        examService.updateExamIsBeingHeld(exam, updatedExam);
        return "master/resultOfChangeExamIsBeingHeld";
    }

    @DeleteMapping(value = "/delete-exam/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteExam(@PathVariable("id") Long examId) {

        Exam exam = examService.findById(examId);
        examService.deleteExam(exam);

        return ResponseEntity.ok("exam successfully deleted");
    }

    @GetMapping(value = "/change-examNotStarted")
    public String changeExamNotStarted(@RequestParam("id") Long examId, Model model) {
        model.addAttribute("examId", examId);
        return "master/changeExamNotStarted";
    }

    @GetMapping(value = "/check/{id}")
    @ResponseBody
    public ResponseEntity<String> checkMasterIdIsValid(@PathVariable("id") Long id) {

       examService.findById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/update-examNotStarted/{examId}")

    public String changeExamNotStarted(Model model, @PathVariable("examId") Long id, @ModelAttribute("error") String error) {

        Exam exam=examService.findById(id);
        model.addAttribute("exam", exam);

        return "master/updateExamNotStarted";
    }

    @PostMapping(value = "/update-examNotStarted")
    public String changeExamNotStarted(Exam updatedExam, RedirectAttributes attributes) {

       Exam exam= examService.findById(updatedExam.getId());

        LocalDateTime currentTime = LocalDateTime.now();
        Predicate<LocalDateTime> predicate = (currentTime::isAfter);
        Predicate<LocalDateTime> finalTest = predicate.or(x -> x.toLocalDate().isAfter(exam.getCourse().getCourseFinishedDate()));

        if (finalTest.test(updatedExam.getStartTime()) || updatedExam.getStartTime().toLocalTime().isAfter(updatedExam.getEndTime())) {
            attributes.addAttribute("error", "your time is not valid");
            return "redirect:/master/update-examNotStarted/" + updatedExam.getId();
        }

        examService.updateExamNotStarted(exam, updatedExam);
        return "master/resultOfChangeExamNotStarted";
    }
}
