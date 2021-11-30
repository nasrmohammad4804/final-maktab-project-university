package ir.maktab.project.service;

import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MasterService {

    void scoreToAnswer(List<DescriptiveAnswerDTO> dtos, Long studentId, Long examId);

}
