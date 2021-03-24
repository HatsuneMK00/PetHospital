package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Vaccine;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineMapper {
    int deleteByPrimaryKey(Integer vacid);

    int insert(Vaccine record);

    int insertSelective(Vaccine record);

    Vaccine selectByPrimaryKey(Integer vacid);

    int updateByPrimaryKeySelective(Vaccine record);

    int updateByPrimaryKey(Vaccine record);
}