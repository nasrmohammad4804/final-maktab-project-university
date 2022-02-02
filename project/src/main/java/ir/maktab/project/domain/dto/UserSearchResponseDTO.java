package ir.maktab.project.domain.dto;

import ir.maktab.project.base.domain.BaseEntity;
import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class UserSearchResponseDTO  {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private RegisterState registerState;

    public UserSearchResponseDTO(Long id, String firstName, String lastName, String userName, RegisterState registerState) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.registerState = registerState;
    }
}
