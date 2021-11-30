package ir.maktab.project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamWithStudentsDTO {

    private List<UserSearchResponseDTO> studentList;
    private ExamDTO examDTO;
}
