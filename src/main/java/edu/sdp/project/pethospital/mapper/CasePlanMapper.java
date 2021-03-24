package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CasePlan;
import org.springframework.stereotype.Repository;

@Repository
public interface CasePlanMapper {
    int deleteByPrimaryKey(Integer caseplanid);

    int insert(CasePlan record);

    int insertSelective(CasePlan record);

    CasePlan selectByPrimaryKey(Integer caseplanid);

    int updateByPrimaryKeySelective(CasePlan record);

    int updateByPrimaryKey(CasePlan record);
}