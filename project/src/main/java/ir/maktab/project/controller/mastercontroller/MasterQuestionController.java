package ir.maktab.project.controller.mastercontroller;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Question;
import ir.maktab.project.domain.dto.QuestionDTO;
import ir.maktab.project.mapper.QuestionMapper;
import ir.maktab.project.service.ExamQuestionService;
import ir.maktab.project.service.ExamService;
import ir.maktab.project.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/master")
@RequiredArgsConstructor
public class MasterQuestionController {

    @Autowired
    private final QuestionMapper questionMapper;

    @Autowired
    private final QuestionService questionService;

    @Autowired
    private final ExamService examService;

    @Autowired
    private final ExamQuestionService examQuestionService;

    @ResponseBody
    @GetMapping(value = "/all-question-bank")
    public ResponseEntity<List<QuestionDTO>> getExam(@RequestParam("title") String title) {
        return ResponseEntity.ok().body(questionMapper.convertEntitiesToQuestionDTO(questionService.findByTitle(title)));
    }

    @PostMapping(value = "/add-question-bank")
    public String addExam(@CookieValue("examId") Long examId, @RequestParam("questionId") Long questionId) {
        Exam exam = examService.findWithId(examId);
        Question  question = questionService.findById(questionId);
        if (questionService.checkExamHasQuestion(exam, question)) {
            return "master/question/QuestionAlreadyExists";
        }

        examQuestionService.addExamQuestionFromQuestionBank(exam, question);
        return "master/question/resultAddQuestionFromQuestionBank";
    }

    @GetMapping(value = "/new-question")
    public String addNewQuestion() {

        return "master/question/addNewQuestion";
    }

    @GetMapping(value = "/new-descriptive-question")
    public String addNewDescriptiveQuestion() {

        return "master/question/addNewDescriptiveQuestion";
    }

    @PostMapping(value = "/new-descriptive-question")
    public String addNewDescriptiveQuestion(ExamQuestion examQuestion, @CookieValue("examId") Long examId) {

        Exam exam = examService.findWithId(examId);

        examQuestionService.addNewDescriptiveQuestion(exam, examQuestion);
        return "master/question/resultAddNewQuestion";
    }

    @GetMapping(value = "/new-test-question")
    public String addNewTestQuestion() {
        return "master/question/addNewTestQuestion";
    }

    @PostMapping(value = "/new-test-question", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> addNewTestQuestion(@RequestBody Map<String, ?> map, @CookieValue("examId") Long examId) {

        examQuestionService.addNewTestQuestion(examService.findWithId(examId), map);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/result-add-test-question")
    public String showResultOfAddNewTestQuestion() {
        return "master/question/resultAddNewQuestion";
    }

    @GetMapping(value = "/question-all")
    public String showAllQuestionOfExam(@CookieValue("examId") Long examId, Model model) {

        Exam exam = examService.findWithId(examId);
        model.addAttribute("exam", exam);
        return "master/question/showAllQuestionExam";
    }

    /*
    * this api for master can be set score for designed question in exam
    * */
    @PutMapping(value = "/change-score")
    public ResponseEntity<String> changeScore(@RequestBody List<Double> scores, @CookieValue("examId") Long examId) {

        questionService.changeScore(examService.findWithId(examId), scores);
        return ResponseEntity.ok("scores successfully updated");
    }

}
