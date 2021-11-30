package ir.maktab.project.service;

import ir.maktab.project.domain.*;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.TestAnswerDTO;
import ir.maktab.project.domain.embeddable.StudentExamState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface StudentService {

    List<Student> dontExistOnCourse(Set<Student> users, String checker);

    Optional<Student> findById(Long id);

    Student findWithId(Long id);

    Optional<Student> findWithAllStudentExamState(Long id);

    void changeExamState(Student student, StudentExamState studentExamState);

    Optional<Student> findWithAllAnswerList(Long id);

    void saveTestAnswer(Student student, Long examQuestionId, TestAnswerDTO testAnswerDTO);

    void saveDescriptiveAnswer(Student student, Long examQuestionId, DescriptiveAnswerDTO descriptiveAnswerDTO);

    List<Answer> findAllAnswerOfStudentExam(Long studentId, Long examId);

    List<DescriptiveAnswer> findAllDescriptiveAnswerOfStudentExam(Long studentId, Long examId);

    List<Student> findAllStudentWitchParticipatedInExam(Long examId);
}
