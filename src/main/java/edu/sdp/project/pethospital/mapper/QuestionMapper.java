package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {

    @Delete("delete from ques where quesId=#{quesId}")
    int deleteById(Integer quesId);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into ques (type,descrip,answer,score,image,tag) values(#{type},#{descrip},#{answer},#{score},#{image},#{tag})")
    int insert(Question question);

    @Select("select * from ques where quesId=#{quesId}")
    Question selectById(Integer quesId);
    @Select("select * from ques")
    List<Question> selectAllQues();
    @Select("select * from ques where descrip=#{descrip}")
    Question selectByDescrip(String descrip);

    @Update("update ques set type=#{type},descrip=#{descrip},answer=#{answer},score=#{score},image=#{image},tag=#{tag} where quesId=#{quesId}")
    int updateByModel(Question question);

    @Update("update ques set image = #{image} where quesId=#{quesId}")
    int updateImageById(int quesId,String image);

}