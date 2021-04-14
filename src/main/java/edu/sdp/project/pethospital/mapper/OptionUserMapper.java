package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.OptionUser;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OptionUserMapper {
    @Select("select * from optionuser where testOptionId=#{testOptionId} and userId=#{userId}")
    OptionUser selectBy2Ids(int testOptionId,int userId);

    @Select("select userId from optionuser where testOptionId=#{testOptionId}")
    List<Integer> selectUserIdsByOptionId(int testOptionId);

    @Select("select testOptionId from optionuser where userId=#{userId}")
    List<Integer> selectOptionIdsByUserId(int userId);

    @Select("select userId,account,name,password,role from user NATURAL join optionuser where testOptionId=#{testOptionId}")
    List<User> selectUsersByTestOptionId(int testOptionId);

    @Select("select testOptionId,testOptionName,goal,startDate,selectNum,judgeNum,qaNum,totalScore,selectTag,judgeTag,qaTag,duration  from testoption NATURAL join optionuser where userId=#{userId}")
    List<TestOption> selectOptionsByUserId(int userId);
    @Insert("insert into optionuser(testOptionId,userId) values(#{testOptionId},#{userId})")
    int insert(OptionUser optionUser);

    @Delete("delete from optionuser where testOptionId=#{testOptionId} and userId=#{userId}")
    int delete(int testOptionId,int userId);
}
