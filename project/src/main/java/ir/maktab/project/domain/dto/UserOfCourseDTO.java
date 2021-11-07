package ir.maktab.project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOfCourseDTO {

    private String userName;
    private String firstName;
    private String lastName;
    private Long id;
    private RoleDTO role;
}
