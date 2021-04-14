package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Question> getAllQuesByTag(String tag){
        return questionMapper.selectByTag(tag);
    }
    public List<Question> getAllQuesByType(String type){
        return questionMapper.selectQuestionByType(type);
    }
    public List<Question> getAllQuesbyTypeAndTag(String type,String tag){
        return questionMapper.selectQuestionByTagAndType(type,tag);
    }
    public List<Question> getQuesByTagSearch(String param ,String tag){
        if (param.equals("")) {
            return questionMapper.selectByTag(tag);
        }else return questionMapper.selectByTagAndSearch(param,tag);
    }
    public List<Question> getQuesByTypeSearch(String param,String type){
        if (param.equals("")) {
            return questionMapper.selectQuestionByType(type);
        }else return questionMapper.selectByTypeAndSearch(param,type);
    }
    public List<Question> getQuesByTypeTagSearch(String param,String type,String tag){
        if (param.equals("")) {
            return questionMapper.selectQuestionByTagAndType(tag,type);
        }else return questionMapper.selectByTagAndTypeAndSearch(param,tag,type);
    }
    public List<Question> getQuesBySearch(String searchParam){
        if (searchParam.equals("")) {
            return questionMapper.selectAllQues();
        } else if (searchParam.startsWith("qid:")) {
            String param = searchParam.split(":")[1];
            String[] ids = param.split(",");
            ArrayList<Question> result = new ArrayList<>();
            for (String id : ids) {
                result.add(questionMapper.selectById(Integer.valueOf(id.trim())));
            }
            return result;
        } else {
            List<Question> result = questionMapper.selectQuestionByDescripMatch(searchParam);
            return result;
        }
    }
    public List<Question> unionSearch(Map params){
        if(params.containsKey("search")&&params.containsKey("tag")&&params.containsKey("type"))
            return getQuesByTypeTagSearch(params.get("search").toString(),params.get("type").toString(),params.get("tag").toString());
        else if(params.containsKey("search")&&params.containsKey("tag"))
            return getQuesByTagSearch(params.get("search").toString(),params.get("tag").toString());
        else if(params.containsKey("search")&&params.containsKey("type"))
            return getQuesByTypeSearch(params.get("search").toString(),params.get("type").toString());
        else if(params.containsKey("type")&&params.containsKey("tag"))
            return getAllQuesbyTypeAndTag(params.get("type").toString(),params.get("tag").toString());
        else if(params.containsKey("type"))
            return getAllQuesByType(params.get("type").toString());
        else if(params.containsKey("tag"))
            return getAllQuesByTag(params.get("tag").toString());
        else if(params.containsKey("search"))
            return getQuesBySearch(params.get("search").toString());
        return null;
    }
    public int addQuestion(Question question){
        Question exist = questionMapper.selectByDescrip(question.getDescrip());
        if(exist!=null) return 0;
        int result = questionMapper.insert(question);
        if(result >0) return question.getQuesId();
        return result;
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
    public String setImageUrl(int quesId,String imageUrl){
        String[] temp = imageUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = questionMapper.updateImageById(quesId, realUrl);
        if (result == 1)
            return realUrl;
        else
            return null;
    }
    public boolean checkId(int quesId){
        Question question = questionMapper.selectById(quesId);
        return question!=null;
    }

}
