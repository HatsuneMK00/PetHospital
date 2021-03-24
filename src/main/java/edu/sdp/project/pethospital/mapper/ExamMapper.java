package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamMapper {
    int deleteByPrimaryKey(Integer examid);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examid);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}