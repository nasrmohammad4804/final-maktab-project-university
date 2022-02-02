package ir.maktab.project.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U extends CharSequence,ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    @Column(columnDefinition = "tinyint(1)")
    protected Boolean isDeleted;

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    public BaseEntity(ID id) {
        this.id = id;
    }
}
