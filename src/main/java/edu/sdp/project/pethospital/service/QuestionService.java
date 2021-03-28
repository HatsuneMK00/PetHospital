package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionService {
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }
    public List<Question> getAllQues(){
        return questionMapper.selectAllQues();
    }
    public int addQuestion(Question question){
        Question exist = questionMapper.selectByDescrip(question.getDescrip());
        if(exist!=null) return 0;
        return questionMapper.insert(question);
    }
    public Question getQuestionById(int quesId){
        return questionMapper.selectById(quesId);
    }
    public int deleteQuestionById(int quesId){
        return questionMapper.deleteById(quesId);
    }
    public int updateQuestion(Question question){
        return questionMapper.updateByModel(question);
    }
    public int setImageUrl(int quesId,String imageUrl){
        String[] temp = imageUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = questionMapper.updateImageById(quesId, realUrl);
        if (result == 1)
            return 200;
        else
            return 500;
    }
    public boolean checkId(int quesId){
        Question question = questionMapper.selectById(quesId);
        return question!=null;
    }
}
