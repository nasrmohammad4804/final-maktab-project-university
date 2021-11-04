package ir.maktab.project.domain;

import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private static final String STUDENT_CODE = "student_code";
    private static final String FATHER_NAME = "father_name" ;

    @Column(name = FATHER_NAME)
    private String fatherName;

    @Column(name = STUDENT_CODE)
    private String studentCode;

    @Builder
    public Student(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive) {
        super(firstName, lastName, userName, password, registerState, isActive);
    }

    @ManyToMany(mappedBy = "students")
    private Set<Course> courseSet=new HashSet<>();
}
