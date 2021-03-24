package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CaseResult;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseResultMapper {
    int deleteByPrimaryKey(Integer caseresultid);

    int insert(CaseResult record);

    int insertSelective(CaseResult record);

    CaseResult selectByPrimaryKey(Integer caseresultid);

    int updateByPrimaryKeySelective(CaseResult record);

    int updateByPrimaryKey(CaseResult record);
}