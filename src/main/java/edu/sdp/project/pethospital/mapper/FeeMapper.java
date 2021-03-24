package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Fee;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeMapper {
    int deleteByPrimaryKey(Integer feeid);

    int insert(Fee record);

    int insertSelective(Fee record);

    Fee selectByPrimaryKey(Integer feeid);

    int updateByPrimaryKeySelective(Fee record);

    int updateByPrimaryKey(Fee record);
}