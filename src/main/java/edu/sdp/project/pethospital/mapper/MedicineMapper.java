package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Medicine;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineMapper {
    int deleteByPrimaryKey(Integer medid);

    int insert(Medicine record);

    int insertSelective(Medicine record);

    Medicine selectByPrimaryKey(Integer medid);

    int updateByPrimaryKeySelective(Medicine record);

    int updateByPrimaryKey(Medicine record);
}