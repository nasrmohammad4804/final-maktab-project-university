package ir.maktab.project.domain.dto;

import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchResponseDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private RegisterState registerState;
}
