package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.HospRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospRecordMapper {
    @Delete("delete from hospitalize where hosId=#{hosId}")
    int deleteById(Integer hosId);

    @Options(useGeneratedKeys = true,keyProperty = "hosId")
    @Insert("insert into hospitalize (hosAnimalName,disease) values(#{hosAnimalName},#{disease})")
    int insert(HospRecord hospRecord);

    @Select("select * from hospitalize where hosId=#{hosId}")
    HospRecord selectById(Integer hosId);
    @Select("select * from hospitalize")
    List<HospRecord> selectAllHospRecord();

    @Update("update hospitalize set hosAnimalName=#{hosAnimalName}, disease=#{disease}")
    int updateByModel(HospRecord hospRecord);
}