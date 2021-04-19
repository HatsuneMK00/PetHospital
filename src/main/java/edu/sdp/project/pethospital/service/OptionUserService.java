package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.OptionUser;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.mapper.OptionUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OptionUserService {
    private final OptionUserMapper optionUserMapper;

    public OptionUserService(OptionUserMapper optionUserMapper) {
        this.optionUserMapper = optionUserMapper;
    }
    public boolean checkId(int testOptionId,int userId){
        OptionUser exist = optionUserMapper.selectBy2Ids(testOptionId,userId);
        return exist!=null;
    }
    public List<Integer> getUserIdsByOptionId(int testOptionId){
        return optionUserMapper.selectUserIdsByOptionId(testOptionId);
    }
    public List<Integer> getOptionsIdByUserId(int userId){
        return optionUserMapper.selectOptionIdsByUserId(userId);
    }
    public List<TestOption> getOptionsByUserId(int userId){
        return optionUserMapper.selectOptionsByUserId(userId);
    }
    public List<User> getUsersByOptionId(int testOptionId){
        return optionUserMapper.selectUsersByTestOptionId(testOptionId);
    }
    public int addUserOption(int testOptionId,int userId){
        if(checkId(testOptionId,userId)) return 0;
        return optionUserMapper.insert(new OptionUser(testOptionId,userId));
    }
    public int addUserOptions(int testOptionId,List<Integer> userIds){
        int count = 0;
        for (Integer userId : userIds) {
            if(!checkId(testOptionId,userId)){
                if(optionUserMapper.insert(new OptionUser(testOptionId,userId))>0) count++;
            }
        }
        return count;
    }
    public int deleteUserOptions(int testOptionId,List<Integer> userIds){
        int count = 0;
        for (Integer userId : userIds) {
            if(!checkId(testOptionId,userId)){
                if((optionUserMapper.delete(testOptionId,userId))>0) count++;
            }
        }
        return count;
    }
    public int deleteUserByOptionId(int testOptionId){
        return optionUserMapper.deleteByTestOptionId(testOptionId);
    }

}
