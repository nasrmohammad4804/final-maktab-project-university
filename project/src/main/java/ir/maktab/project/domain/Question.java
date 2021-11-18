package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Inheritance
@DiscriminatorColumn(name = Question.QUESTION_TYPE)
public class Question extends BaseEntity<Long> {

    public static final String QUESTION_TYPE = "question_type";
    public static final String QUESTION_ID = "question_id";
    private static final String QUESTION_TEXT = "question_text";

    @Column(name = QUESTION_TEXT)
    private String questionText;

    @Column(nullable = false)
    private String title;

    @Builder
    public Question(String questionText, String title) {
        this.questionText = questionText;
        this.title = title;
    }

    @Builder
    public Question(String questionText, String title, Answer answer) {
        this.questionText = questionText;
        this.title = title;
        this.answer = answer;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "question")
    private Answer answer;
}
