package ir.maktab.project.domain;

import ir.maktab.project.base.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor

@Setter
@Getter
@ToString
public class Role extends BaseEntity<String,Long> {

    public static final String ROLE_ID="role_id";
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
