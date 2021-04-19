package edu.sdp.project.pethospital.service;


import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.mapper.TestOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestOptionService {
    private final TestOptionMapper testOptionMapper;

    public TestOptionService(TestOptionMapper testOptionMapper) {
        this.testOptionMapper = testOptionMapper;
    }

    public List<TestOption> getAllOptions(){
        return testOptionMapper.selectAllTestOptions();
    }
    public TestOption getOptionById(int testOptionId){
        return testOptionMapper.selectById(testOptionId);
    }
    public boolean checkId(int testOptionId){
        TestOption exist = testOptionMapper.selectById(testOptionId);
        return exist!=null;
    }
    public int addOption(TestOption option){
        TestOption testOption = testOptionMapper.selectByName(option.getTestOptionName());
        if(testOption!=null) return 0;
        int result = testOptionMapper.insert(option);
        if(result>0) return option.getTestOptionId();
        return result;
    }
    public int updateTestOption(TestOption testOption){
        return testOptionMapper.updateByModel(testOption);
    }
    public int deleteOptionById(int testOptionId){
        return testOptionMapper.deleteById(testOptionId);
    }

}
