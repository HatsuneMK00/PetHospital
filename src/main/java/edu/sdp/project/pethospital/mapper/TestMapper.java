package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Test;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {
    @Delete("delete from test where testId=#{testId}")
    int deleteById(int testId);

    @Options(useGeneratedKeys = true,keyProperty = "testId")
    @Insert("insert into test (userId,testOptionId,beginDate) values(#{userId},#{testOptionId},#{beginDate})")
    int insert(Test test);

    @Select("select * from test where testId=#{testId}")
    Test selectById(int testId);
    @Select("select * from test where userId=#{userId}")
    List<Test> selectByUserId(int userId);
    @Select("select * from test where testOptionId=#{testOptionId}")
    List<Test> selectByTestOptionId(int testOptionId);
    @Select("select * from test")
    List<Test> selectAllTest();

    @Update("update test set testId=#{testId},userId=#{userId},testOptionId=#{testOptionId},beginDate=#{beginDate},endDate=#{endDate},score=#{score}")
    int updateByModel(Test record);
}