package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.TestOption;
import org.springframework.stereotype.Repository;

@Repository
public interface TestOptionMapper {
    int deleteByPrimaryKey(Integer testoptionid);

    int insert(TestOption record);

    int insertSelective(TestOption record);

    TestOption selectByPrimaryKey(Integer testoptionid);

    int updateByPrimaryKeySelective(TestOption record);

    int updateByPrimaryKey(TestOption record);
}