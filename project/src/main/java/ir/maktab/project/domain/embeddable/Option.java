package ir.maktab.project.domain.embeddable;

import ir.maktab.project.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Option implements Serializable {


    public static final String OPTION = "option";

    @Column(columnDefinition = "tinyint(1)")
    private Boolean isAnswered;

    private String content;


}
