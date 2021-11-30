package ir.maktab.project.domain;

import com.fasterxml.jackson.annotation.*;
import ir.maktab.project.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Inheritance
@DiscriminatorColumn(name = Question.QUESTION_TYPE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Question extends BaseEntity<Long> {

    public static final String QUESTION_TYPE = "question_type";
    public static final String QUESTION_ID = "question_id";
    private static final String QUESTION_TEXT = "question_text";

    @Column(name = QUESTION_TEXT)
    protected String questionText;

    @Column(nullable = false)
    protected String title;

    @Builder
    public Question(String questionText, String title) {
        this.questionText = questionText;
        this.title = title;
    }

    public Question(Long aLong) {
        super(aLong);
    }

    @Builder
    public Question(String questionText, String title, List<Answer> answerList) {
        this.questionText = questionText;
        this.title = title;
        this.answerList = answerList;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    protected List<Answer> answerList=new ArrayList<>();
}
