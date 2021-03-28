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
        if(checkId(option.getTestOptionId())) return 0;
        return testOptionMapper.insert(option);
    }
    public int updateTestOption(TestOption testOption){
        return testOptionMapper.updateByModel(testOption);
    }
    public int deleteOptionById(int testOptionId){
        return testOptionMapper.deleteById(testOptionId);
    }

}
