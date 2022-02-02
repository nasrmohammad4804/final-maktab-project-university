package ir.maktab.project.domain;

import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
public class Master extends User{


    public static final String MASTER_ID="master_id";

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "master")
    private final List<Course> courses=new ArrayList<>();

    @Builder
    public Master(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive,String phoneNumber) {
        super(firstName, lastName, userName, password, registerState, isActive,phoneNumber);
    }

}
