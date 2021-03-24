package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CaseConsult;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseConsultMapper {
    int deleteByPrimaryKey(Integer caseconsultid);

    int insert(CaseConsult record);

    int insertSelective(CaseConsult record);

    CaseConsult selectByPrimaryKey(Integer caseconsultid);

    int updateByPrimaryKeySelective(CaseConsult record);

    int updateByPrimaryKey(CaseConsult record);
}