package ir.maktab.project.mapper.impl;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.dto.ExamDTO;
import ir.maktab.project.domain.dto.ExamWithStudentsDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import ir.maktab.project.mapper.ExamWithStudentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamWithStudentMapperImpl implements ExamWithStudentMapper {

    @Override
    public ExamDTO convertEntityToExamDTO(Exam exam) {
        if (exam == null)
            return null;

        return ExamDTO.builder().startTime(exam.getStartTime()).endTime(exam.getEndTime())
                .title(exam.getTitle()).id(exam.getId()).build();
    }

    @Override
    public ExamWithStudentsDTO convertExamWithStudentsToExamWithStudentDTO(Exam exam, List<Student> students) {

        if (students == null)
            return null;

        List<UserSearchResponseDTO> studentsDTOList = new ArrayList<>();
        ExamDTO dto = convertEntityToExamDTO(exam);
        for (Student student : students)
            studentsDTOList.add(studentToUserSearchResponseDTO(student));

        return new ExamWithStudentsDTO(studentsDTOList, dto);
    }

    private UserSearchResponseDTO studentToUserSearchResponseDTO(Student student) {
        if (student == null)
            return null;

        return UserSearchResponseDTO.builder().userName(student.getUserName())
                .id(student.getId()).firstName(student.getFirstName()).lastName(student.getLastName()).build();
    }


}
