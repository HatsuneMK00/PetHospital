package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.OptionQues;
import edu.sdp.project.pethospital.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionQuesMapper {
    @Select("select * from optionques")
    List<OptionQues> selectAll();
    @Select("select * from optionques where testOptionId=#{testOptionId} and quesId=#{quesId}")
    OptionQues selectBy2Id(int testOptionId,int quesId);
    @Select("select quesId,type,descrip,answer,score,image,tag from optionques NATURAL join ques where testOptionId=#{testOptionId}")
    List<Question> selectQuesByOptionId(int testOptionId);
    @Insert("insert into optionques(testOptionId,quesId) values(#{testOptionId},#{quesId})")
    int insert(int testOptionId,int quesId);
    @Delete("delete from optionques where testOptionId=#{testOptionId} and quesId=#{quesId}")
    int delete(int testOptionId,int quesId);
}
