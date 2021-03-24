package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Case;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseMapper {
    int deleteByPrimaryKey(Integer caseid);

    int insert(Case record);

    int insertSelective(Case record);

    Case selectByPrimaryKey(Integer caseid);

    int updateByPrimaryKeySelective(Case record);

    int updateByPrimaryKey(Case record);
}