package ir.maktab.project.domain;

import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Manager extends User{

    @Builder
    public Manager(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive, Role role,String phoneNumber) {
        super(firstName, lastName, userName, password, registerState, isActive, role);
        this.setPhoneNumber(phoneNumber);
    }
}
