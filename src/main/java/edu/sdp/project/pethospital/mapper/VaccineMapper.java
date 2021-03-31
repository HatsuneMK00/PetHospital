package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Vaccine;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineMapper {
    @Delete("delete from vaccine where vacId=#{vacId}")
    int deleteById(Integer vacId);

    @Options(useGeneratedKeys = true,keyProperty = "vacId")
    @Insert("insert into vaccine (vacName,vacDescrip) values(#{vacName},#{vacDescrip})")
    int insert(Vaccine vaccine);

    @Select("select * from vaccine")
    List<Vaccine> selectAllUser();
    @Select("select * from vaccine where vacName=#{vacName}")
    Vaccine selectByName(String vacName);
    @Select("select * from vaccine where vacId=#{vacId}")
    Vaccine selectById(Integer vacId);

    @Update("update vaccine set vacName=#{vacName},vacDescrip=#{vacDescrip} where vacId=#{vacId}")
    int updateByModel(Vaccine vaccine);
}