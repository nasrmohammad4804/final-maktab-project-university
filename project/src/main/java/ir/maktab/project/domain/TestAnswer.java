package ir.maktab.project.domain;

import ir.maktab.project.domain.embeddable.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class TestAnswer extends Answer {

    private static final String OPTION_TEST_ANSWER = "answer_option";
    private static final String TEST_ANSWER = "test_answer";


    @ElementCollection
    @JoinTable(name = OPTION_TEST_ANSWER)
    private List<Option> options = new ArrayList<>();

    public TestAnswer(List<Option> options) {
        this.options = options;
    }
}
