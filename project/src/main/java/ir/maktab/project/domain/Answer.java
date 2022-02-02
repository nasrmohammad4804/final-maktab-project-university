package ir.maktab.project.domain;

import com.fasterxml.jackson.annotation.*;
import ir.maktab.project.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = Answer.ANSWER_TYPE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Answer extends BaseEntity<String,Long> {

    public static final String ANSWER_TYPE = "answer_type";
    public static final String ANSWER_ID = "answer_id";
    protected double score;

    public Answer(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = Question.QUESTION_ID)
    protected Question question;

    @ManyToOne
    @JoinColumn(name = Student.STUDENT_ID)
    @JsonBackReference(value = "student")
    protected Student student;

    public Answer(Long id) {
        super(id);
    }
}
