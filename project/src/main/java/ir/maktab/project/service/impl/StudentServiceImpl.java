package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.User;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.repository.StudentRepository;
import ir.maktab.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> dontExistOnCourse(Set<Student> users,String checker) {
        return repository.dontExistOnCourse(users,checker ).stream()
                .filter(x -> x.getRegisterState().equals(RegisterState.CONFIRM))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Student> findWithId(Long id) {
        return repository.findWithId(id);
    }
}
