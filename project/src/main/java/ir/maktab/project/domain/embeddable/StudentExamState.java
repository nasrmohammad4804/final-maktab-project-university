package ir.maktab.project.domain.embeddable;

import ir.maktab.project.domain.enumeration.ExamState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamState {

    private Long examId;

    @Enumerated(value = EnumType.STRING)
    private ExamState examState;

}
