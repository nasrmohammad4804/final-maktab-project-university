package ir.maktab.project.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(columnDefinition = "tinyint(1)")
    private Boolean isDeleted;

    public BaseEntity(ID id) {
        this.id = id;
    }
}
