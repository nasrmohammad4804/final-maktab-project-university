package ir.maktab.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ExamQuestion extends Question{

    private double score;

    public ExamQuestion(Long id) {
        super(id);
    }

    public ExamQuestion(String questionText, String title) {
       super(questionText,title);
    }

    public ExamQuestion(String questionText, String title, List<Answer> answerList) {
        super(questionText, title, answerList);
    }

    @ManyToOne
    @JoinColumn(name = Exam.EXAM_ID)
    @JsonBackReference
    private Exam exam;

    @Transient
    private Answer masterAnswer;
}