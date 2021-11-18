package ir.maktab.project.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ExamQuestion extends Question{

    private double score;

    public ExamQuestion(String questionText, String title, Answer answer) {
        super(questionText, title, answer);
    }
}
