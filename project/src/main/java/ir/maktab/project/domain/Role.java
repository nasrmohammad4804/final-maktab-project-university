package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor

@Setter
@Getter
@ToString
public class Role extends BaseEntity<Long> {

    public static final String ROLE_ID="role_id";
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
