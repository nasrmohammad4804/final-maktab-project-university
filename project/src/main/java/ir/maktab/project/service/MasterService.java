package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MasterService extends BaseService<Master,String,Long> {

    void scoreToAnswer(List<DescriptiveAnswerDTO> dtos, Long studentId, Long examId);

}
