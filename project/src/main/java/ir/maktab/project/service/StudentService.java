package ir.maktab.project.service;

import ir.maktab.project.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface StudentService {

     List<Student> dontExistOnCourse( Set<Student> users,String checker);

    Optional<Student> findById(Long id);

     Optional<Student> findWithId(Long id);

}
