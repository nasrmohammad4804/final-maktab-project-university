package ir.maktab.project.domain.dto;

import ir.maktab.project.domain.embeddable.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestAnswerDTO {
    private List<Option> options = new ArrayList<>();
    private Long questionId;
}
