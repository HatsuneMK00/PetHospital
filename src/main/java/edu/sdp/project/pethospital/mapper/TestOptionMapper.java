package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.TestOption;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestOptionMapper {
    @Delete("delete from testoption where testOptionId=#{testOptionId}")
    int deleteById(Integer testOptionId);

    @Options(useGeneratedKeys = true,keyProperty = "testOptionId")
    @Insert("insert into testoption (testOptionName,goal,startDate,selectNum,judgeNum,qaNum,totalScore,selectTag,judgeTag,qaTag,duration) "+
    "values(#{testOptionName},#{goal},#{startDate},#{selectNum},#{judgeNum},#{qaNum},#{totalScore},#{selectTag},#{judgeTag},#{qaTag},#{duration})")
    int insert(TestOption testOption);

    @Select("select * from testoption")
    List<TestOption> selectAllTestOptions();

    @Select("select * from testoption where testOptionId=#{testOptionId}")
    TestOption selectById(Integer testOptionID);

    @Select("select * from testoption where testOptionName=!{testOptionName}")
    TestOption selectByName(String testOptionName);

    @Update("update testoption set testOptionName=#{testOptionName},goal=#{goal},startDate=#{startDate},selectNum=#{selectName},judgeNum=#{judgeName},qaNum=#{qaNum},totalScore=#{totalScore},selectTag=#{selectTag},judgeTag=#{judge},qaTag=#{qaTag},duration=#{duration} where testOptionId=#{testOptionId}")
    int updateByModel(TestOption testOption);
}