package ir.maktab.project.service.impl;

import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.service.AnswerService;
import ir.maktab.project.service.MasterService;
import ir.maktab.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MasterServiceImpl implements MasterService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AnswerService answerService;

    @Override
    @Transactional
    public void scoreToAnswer(List<DescriptiveAnswerDTO> dtos, Long studentId, Long examId) {

        List<DescriptiveAnswer> descriptiveAnswers = studentService.findAllDescriptiveAnswerOfStudentExam(studentId, examId);
        descriptiveAnswers.forEach(descriptiveAnswer -> {
            dtos.forEach(dto -> {
                if(dto.getQuestionId().equals(descriptiveAnswer.getQuestion().getId()) && dto.getScore()!=descriptiveAnswer.getScore() ){

                    descriptiveAnswer.setScore(dto.getScore());
                        answerService.saveOrUpdate(descriptiveAnswer);
                }
            });
        });
    }
}
