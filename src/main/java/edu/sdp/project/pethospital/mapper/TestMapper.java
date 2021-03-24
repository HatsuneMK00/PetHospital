package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.entity.TestKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
    int deleteByPrimaryKey(TestKey key);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(TestKey key);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}