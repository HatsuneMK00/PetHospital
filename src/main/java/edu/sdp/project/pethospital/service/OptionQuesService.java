package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.mapper.OptionQuesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OptionQuesService {
    private final OptionQuesMapper optionQuesMapper;

    public OptionQuesService(OptionQuesMapper optionQuesMapper) {
        this.optionQuesMapper = optionQuesMapper;
    }


    public List<Question> getQuesByOptionId(int testOptionId){
        return optionQuesMapper.selectQuesByOptionId(testOptionId);
    }
    public int addQuesToOption(int testOptionId,List<Integer> quesIds){
        int count = 0;
        for (Integer quesId : quesIds) {
            if(optionQuesMapper.insert(testOptionId,quesId)>0) count++;
        }
        return count;
    }
    public int deleteQuesFromOption(int testOptionId,List<Integer> quesIds){
        int count = 0;
        for (Integer quesId : quesIds) {
            if(optionQuesMapper.delete(testOptionId,quesId)>0) count++;
        }
        return count;
    }
}
