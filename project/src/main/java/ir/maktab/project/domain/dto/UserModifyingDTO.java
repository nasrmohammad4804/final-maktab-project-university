package ir.maktab.project.domain.dto;

import ir.maktab.project.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserModifyingDTO {

    private String firstName;
    private String lastName;
    private UserType userType;
}
