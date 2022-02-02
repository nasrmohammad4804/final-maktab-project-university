package ir.maktab.project.controller;

import ir.maktab.project.domain.Answer;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.TestAnswerDTO;
import ir.maktab.project.domain.embeddable.StudentExamState;
import ir.maktab.project.service.CourseService;
import ir.maktab.project.service.ExamQuestionService;
import ir.maktab.project.service.ExamService;
import ir.maktab.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("student")
@RequiredArgsConstructor

public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @GetMapping(value = "/panel")
    public String showPanel(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("myUser");
        Student myStudent = studentService.findWithId(student.getId());

        model.addAttribute("student", myStudent);
        return "student/studentPanel";

    }

    @GetMapping(value = "/course")
    public String showCourse(@RequestParam("id") Long courseId, Model model, HttpServletRequest request,HttpServletResponse response) {

        HttpSession session = request.getSession();
        Cookie cookie=new Cookie("courseId",String.valueOf(courseId));
        cookie.setMaxAge(60*60*12);
        response.addCookie(cookie);

        Student student = (Student) session.getAttribute("myUser");
        model.addAttribute("student", studentService.findWithAllStudentExamState(student.getId()).get());

        model.addAttribute("course", courseService.findWithId(courseId).get());
        return "student/allExam";
    }

    /*this is page of participate in exam for is active student if already dont attend
     * */
    @GetMapping("/participate-exam")
    public String participateInExam(@RequestParam("id") Long examId, Model model) {

        model.addAttribute("exam", examService.findById(examId));

        return "student/participateInExam";
    }


    @GetMapping("/continue-exam")
    public String continueOfExam(@RequestParam("id") Long examId,Model model) {


        model.addAttribute("exam", examService.findById(examId));
        return "student/continueExam";
    }

    @GetMapping(value = "/exam/mode")
    public String showEndModeExam(@RequestParam("id") Long examId, Model model) {
        model.addAttribute("exam", examService.findById(examId));
        return "student/examStatus";
    }


    @PutMapping(value = "/change-exam-state")
    public ResponseEntity<Void> changeExamStateOfStudent(@RequestBody StudentExamState state, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("myUser");
        Optional<Student> optionalStudent = studentService.findWithAllStudentExamState(student.getId());


        studentService.changeExamState(optionalStudent.get(), state);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all-question/{id}")
    public ResponseEntity<List<ExamQuestion>> getAllQuestionOfExam(@PathVariable("id") Long examId) {

        List<ExamQuestion> examQuestions = examQuestionService.getAllQuestion(examId);
        examQuestions.forEach(examQuestion -> examQuestion.setMasterAnswer(examQuestion.getAnswerList().get(0)));
        return ResponseEntity.ok(examQuestions);
    }

    @PostMapping(value = "/add-test-answer")
    public ResponseEntity<Void> addTestAnswerOfExamQuestion(@RequestBody TestAnswerDTO testAnswer, @RequestParam("questionId") Long examQuestionId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("myUser");
        Optional<Student> optionalStudent = studentService.findWithAllAnswerList(student.getId());

        studentService.saveTestAnswer(optionalStudent.get(), examQuestionId, testAnswer);


        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add-descriptive-answer")
    public ResponseEntity<Void> addDescriptiveAnswerOfExamQuestion(@RequestBody DescriptiveAnswerDTO descriptiveAnswerDTO, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("myUser");
        Optional<Student> optionalStudent = studentService.findWithAllAnswerList(student.getId());
        studentService.saveDescriptiveAnswer(optionalStudent.get(), descriptiveAnswerDTO);
        return ResponseEntity.ok().build();

    }
    @GetMapping(value = "/all-answers/{examId}")
    public ResponseEntity<List<Answer>> getAllAnswerOfExam(@PathVariable("examId") Long examId,HttpServletRequest request){

        HttpSession session = request.getSession();
        Student student =(Student) session.getAttribute("myUser");

        return ResponseEntity.ok(
                studentService.findAllAnswerOfStudentExam(student.getId(),examId)
        );

    }


}
