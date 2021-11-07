package ir.maktab.project.mapper;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserOfCourseDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {

    List<UserSearchResponseDTO> convertEntitiesToDTOList(List<User> users);
    List<User> convertDTOListTOEntities(List<UserSearchResponseDTO> dtos);

    List<UserOfCourseDTO>  convertEntitiesToUserOfCourseDTO(List<User> users);

    UserOfCourseDTO convertEntityTOUserOfCourseDTO(User user);
}
