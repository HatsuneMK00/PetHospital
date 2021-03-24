package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer quesid);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer quesid);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}