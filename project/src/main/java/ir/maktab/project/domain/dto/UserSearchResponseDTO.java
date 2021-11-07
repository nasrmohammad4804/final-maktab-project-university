package ir.maktab.project.domain.dto;

import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponseDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private RegisterState registerState;
}
