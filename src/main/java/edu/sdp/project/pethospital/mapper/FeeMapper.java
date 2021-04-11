package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Fee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeMapper {
    @Delete("delete from fee where feeId=#{feeId}")
    int deleteById(Integer feeId);

    @Options(useGeneratedKeys = true,keyProperty = "feeId")
    @Insert("insert into fee (feeName,feePrice,feeDescrip) values(#{feeName},#{feePrice},#{feeDescrip})")
    int insert(Fee fee);

    @Select("select * from fee where feeId=#{feeId}")
    Fee selectById(int feeId);
    @Select("select * from fee where feeName=#{feeName}")
    Fee selectByName(String feeName);
    @Select("select * from fee")
    List<Fee> selectAllFees();

    @Update("update fee set feeName=#{feeName},feePrice=#{feePrice},feeDescrip=#{feeDescrip} where feeId=#{feeId}")
    int updateByModel(Fee fee);
}