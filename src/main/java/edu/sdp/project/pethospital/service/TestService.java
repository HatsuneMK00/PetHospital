package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.mapper.OptionUserMapper;
import edu.sdp.project.pethospital.mapper.QuestionMapper;
import edu.sdp.project.pethospital.mapper.TestMapper;
import edu.sdp.project.pethospital.mapper.TestOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class TestService {
    private final TestMapper testMapper;
    private final TestOptionMapper testOptionMapper;
    private final OptionUserMapper optionUserMapper;
    private final QuestionMapper questionMapper;

    public TestService(TestMapper testMapper, TestOptionMapper testOptionMapper, OptionUserMapper optionUserMapper, QuestionMapper questionMapper) {
        this.testMapper = testMapper;
        this.testOptionMapper = testOptionMapper;
        this.optionUserMapper = optionUserMapper;
        this.questionMapper = questionMapper;
    }
    public List<Test> getAllTests(){
        return testMapper.selectAllTest();
    }
    public List<Test> getAllTestsByUser(int userId){
        return testMapper.selectByUserId(userId);
    }
    public List<Test> getAllTestsByOptionId(int testOptionId){
        return testMapper.selectByTestOptionId(testOptionId);
    }
    public boolean checkDate(int testOptionId){
        long now = System.currentTimeMillis();
        TestOption option = testOptionMapper.selectById(testOptionId);
        long start  = option.getStartDate().getTime();
        long duration = (long)option.getDuration()*60*1000;
        log.info(duration+"");
        log.info(start+"");
        log.info(now+"");
        return now>start&&now<(start+duration);
    }
    public Test getTestById(int testId){
        return testMapper.selectById(testId);
    }
    public int addTest(int userId, int testOptionId){
        Test test = new Test();
        test.setUserId(userId);
        test.setTestOptionId(testOptionId);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        test.setBeginDate(new Date());
        if(testMapper.insert(test)>0) return test.getTestId();
        return 0;
    }
    public int deleteTestById(int testId){
        return testMapper.deleteById(testId);
    }
    public int changeTest(Test test){
        return testMapper.updateByModel(test);
    }
    public boolean checkId(int testId){
        Test test = testMapper.selectById(testId);
        return test!=null;
    }
    public boolean checkTest(int userId,int testOptionId){
        Test test = testMapper.selectByUerIdOptionId(userId,testOptionId);
        return test!=null;
    }
    public List<Question> getNewTest(int userId, int testOptionId){
        TestOption option = testOptionMapper.selectById(testOptionId);
        List<Question> result = new ArrayList<>();
        List<Question> tem=null;
        tem = getQess("select",option.getSelectNum(),option.getSelectTag());
        if(tem!=null) result.addAll(tem);
        tem = getQess("judge",option.getJudgeNum(),option.getJudgeTag());
        if(tem!=null) result.addAll(tem);
        tem = getQess("qa",option.getQaNum(),option.getQaTag());
        if(tem!=null) result.addAll(tem);
        return result;
    }
    List<Question> getQess(String type,int num,String tag){
        List<Question> tem = null;
        if(num==0) return null;
        if(tag!=null&&!tag.equals(""))
            tem = questionMapper.selectQuestionByTagAndType(type,tag);
        else tem = questionMapper.selectQuestionByType(type);
        if(tem==null) return null;
        if(tem.size()<num) return null;
        if(tem.size()==num) return tem;
        HashSet<Integer> set = new HashSet<>();
        Random r = new Random();
        while(set.size()<num){
            int next = r.nextInt(tem.size());
            set.add(next);
        }
        List<Question> result = new ArrayList<>();
        for (Integer integer : set) {
            result.add(tem.get(integer));
        }
        return result;
    }
}
