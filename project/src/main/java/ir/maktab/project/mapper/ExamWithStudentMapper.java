package ir.maktab.project.mapper;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.dto.ExamWithStudentsDTO;

import java.util.List;


public interface ExamWithStudentMapper extends ExamMapper {

    ExamWithStudentsDTO convertExamWithStudentsToExamWithStudentDTO(Exam exam, List<Student> students);
}
