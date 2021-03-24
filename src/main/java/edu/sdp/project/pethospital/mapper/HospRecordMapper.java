package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.HospRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface HospRecordMapper {
    int deleteByPrimaryKey(Integer hosid);

    int insert(HospRecord record);

    int insertSelective(HospRecord record);

    HospRecord selectByPrimaryKey(Integer hosid);

    int updateByPrimaryKeySelective(HospRecord record);

    int updateByPrimaryKey(HospRecord record);
}