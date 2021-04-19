package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.mapper.PaperQuesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaperQuesService {
    private final PaperQuesMapper paperQuesMapper;

    public PaperQuesService(PaperQuesMapper paperQuesMapper) {
        this.paperQuesMapper = paperQuesMapper;
    }


    public List<Question> getQuesByPaperId(int paperId){
        return paperQuesMapper.selectQuesByPaperId(paperId);
    }
    public int addQuesToPaper(int paperId,List<Integer> quesIds){
        int count = 0;
        for (Integer quesId : quesIds) {
            if(paperQuesMapper.insert(paperId,quesId)>0) count++;
        }
        return count;
    }
    public int deleteQuesFromPaper(int paperId,List<Integer> quesIds){
        int count = 0;
        for (Integer quesId : quesIds) {
            if(paperQuesMapper.delete(paperId,quesId)>0) count++;
        }
        return count;
    }
    public int deleteQuesByPaperId(int paperId){
        return paperQuesMapper.deleteByPaperId(paperId);
    }
}
