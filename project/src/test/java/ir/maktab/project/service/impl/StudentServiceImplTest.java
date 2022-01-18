package ir.maktab.project.service.impl;

import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.TestAnswer;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.TestAnswerDTO;
import ir.maktab.project.domain.embeddable.Option;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.repository.StudentRepository;
import ir.maktab.project.service.ExamQuestionService;
import ir.maktab.project.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ExamQuestionService examQuestionService;

    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(studentRepository);
        ((StudentServiceImpl)studentService).setExamQuestionService(examQuestionService);

        student = Student.builder().firstName("javad")
                .lastName("zare")
                .userName("javad1234").registerState(RegisterState.CONFIRM)
                .build();
    }

    @Test
    void saveTestAnswer() {
        addTestAnswer();

        TestAnswerDTO dto=new TestAnswerDTO(createOption(),4L);
        when(examQuestionService.findById(anyLong())).thenReturn(createFakeExamQuestion());
        studentService.saveTestAnswer(student,4L,dto);
        assertThat(student.getAnswerList().get(0).getScore())
                .isNotZero();
    }

    private void addTestAnswer() {

        student.getAnswerList().add(TestAnswer.builder().question(new ExamQuestion(4L)).options(createOption()).build());

    }
    private ExamQuestion createFakeExamQuestion(){


        ExamQuestion examQuestion= new ExamQuestion("2^3/2+1","multiple & pow");
        examQuestion.setScore(3L);
        examQuestion.getAnswerList().add(
                new TestAnswer(new ExamQuestion(4L),createOption())
        );
        return examQuestion;
    }
    private List<Option> createOption(){
        List<Option> options=new LinkedList<>();
        options.add(new Option(true,"14"));
        options.add(new Option(false,"11"));
        options.add(new Option(false,"8"));
        options.add(new Option(false,"23"));
        return options;
    }

    @Test
    void saveDescriptiveAnswer() {

        addDescriptiveAnswer();
        String newAnswerText = "data";
        DescriptiveAnswerDTO dto = new DescriptiveAnswerDTO(newAnswerText, 1L, 23);

        studentService.saveDescriptiveAnswer(student, dto);
        student.getAnswerList().stream().filter(answer -> answer.getQuestion().getId().equals(dto.getQuestionId()))
                .findFirst().ifPresent(answer -> {
            assertThat(((DescriptiveAnswer) answer).getAnswerText())
                    .isEqualTo(newAnswerText);
        });
    }

    private void addDescriptiveAnswer() {
        student.getAnswerList().add(DescriptiveAnswer.builder().answerText("32-1=31").question(new ExamQuestion(1L)).build());
        student.getAnswerList().add(DescriptiveAnswer.builder().answerText("2*5+1=11").question(new ExamQuestion(2L)).build());
        student.getAnswerList().add(DescriptiveAnswer.builder().answerText("2^4-1=15").question(new ExamQuestion(3L)).build());
    }
}