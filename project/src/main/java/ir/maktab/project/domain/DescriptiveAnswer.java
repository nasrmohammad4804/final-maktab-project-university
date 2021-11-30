package ir.maktab.project.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptiveAnswer extends Answer {

    @Lob
    private String answerText;
}
