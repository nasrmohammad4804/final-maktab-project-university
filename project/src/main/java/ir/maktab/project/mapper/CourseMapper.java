package ir.maktab.project.mapper;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.dto.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface CourseMapper {
    List<CourseDTO> convertEntitiesToCourseDTO(List<Course> courses);
}
