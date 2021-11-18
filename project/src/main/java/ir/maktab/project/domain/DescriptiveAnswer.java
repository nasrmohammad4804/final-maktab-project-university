package ir.maktab.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DescriptiveAnswer extends Answer{

    @Lob
    private String answerText;

}
