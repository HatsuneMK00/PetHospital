package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Exam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamMapper {
    @Delete("delete from exam where examId=#{examId}")
    int deleteById(Integer examId);

    @Options(useGeneratedKeys = true,keyProperty = "examId")
    @Insert("insert into exam (examName,examDescrip) values(#{examName},#{examDescrip})")
    int insert(Exam exam);

    @Select("select * from exam")
    List<Exam> selectAllUser();
    @Select("select * from exam where examName=#{examName}")
    Exam selectByName(String examName);
    @Select("select * from exam where examId=#{examId}")
    Exam selectById(Integer examId);

    @Update("update exam set examName=#{examName},examDescrip=#{examDescrip} where examId=#{examId}")
    int updateByModel(Exam exam);


}