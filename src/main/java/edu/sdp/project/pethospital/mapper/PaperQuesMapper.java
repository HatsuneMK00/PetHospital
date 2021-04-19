package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.PaperQues;
import edu.sdp.project.pethospital.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperQuesMapper {
    @Select("select * from paperques")
    List<PaperQues> selectAll();
    @Select("select * from paperques where paperId=#{paperId} and quesId=#{quesId}")
    PaperQues selectBy2Id(int paperId, int quesId);
    @Select("select quesId,type,descrip,answer,score,image,tag from paperques NATURAL join ques where paperId=#{paperId}")
    List<Question> selectQuesByPaperId(int paperId);
    @Insert("insert into paperques(paperId,quesId) values(#{paperId},#{quesId})")
    int insert(int paperId,int quesId);
    @Delete("delete from paperques where paperId=#{paperId} and quesId=#{quesId}")
    int delete(int paperId,int quesId);
    @Delete("delete from paperques where paperId=#{paperId}")
    int deleteByPaperId(int paperId);
}
