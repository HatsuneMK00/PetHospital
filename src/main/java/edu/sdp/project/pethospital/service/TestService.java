package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.mapper.TestMapper;
import edu.sdp.project.pethospital.mapper.TestOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestService {
    private final TestMapper testMapper;
    private final TestOptionMapper testOptionMapper;

    public TestService(TestMapper testMapper, TestOptionMapper testOptionMapper) {
        this.testMapper = testMapper;
        this.testOptionMapper = testOptionMapper;
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
    public Test getTestById(int testId){
        return testMapper.selectById(testId);
    }
    public int addTest(Test test){
        return testMapper.insert(test);
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
//    public List<Question> getNewTest(int userId,int testOptionId){
//        TestOption testOption = testOptionMapper.selectById(testOptionId);
//        List<Question> selects = null;
//        List<Question> judges = null;
//        List<Question> qas = null;
//    }
}
