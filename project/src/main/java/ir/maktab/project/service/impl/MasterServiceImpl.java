package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.repository.MasterRepository;
import ir.maktab.project.service.AnswerService;
import ir.maktab.project.service.MasterService;
import ir.maktab.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MasterServiceImpl extends BaseServiceImpl<Master,Long,String, MasterRepository> implements MasterService {

    @Autowired
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }



    public MasterServiceImpl(MasterRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void scoreToAnswer(List<DescriptiveAnswerDTO> dtos, Long studentId, Long examId) {

        List<DescriptiveAnswer> descriptiveAnswers = studentService.findAllDescriptiveAnswerOfStudentExam(studentId, examId);
        descriptiveAnswers.forEach(descriptiveAnswer -> {
            dtos.forEach(dto -> {
                if(dto.getQuestionId().equals(descriptiveAnswer.getQuestion().getId()) && dto.getScore()!=descriptiveAnswer.getScore() ){

                    descriptiveAnswer.setScore(dto.getScore());
                }
            });
        });
    }

    @Override
    public Class<Master> entityClass() {
        return Master.class;
    }
}
