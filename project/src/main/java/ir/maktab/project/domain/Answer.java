package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
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
@DiscriminatorColumn(name =Answer.ANSWER_TYPE )
public class Answer extends BaseEntity<Long> {

    public static final String ANSWER_TYPE="answer_type";


    protected double score;

    @OneToOne
    @JoinColumn(name = Question.QUESTION_ID)
    protected Question question;
}
