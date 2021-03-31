package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Medicine;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineMapper {
    @Delete("delete from medicine where medId=#{medId}")
    int deleteById(Integer medId);

    @Options(useGeneratedKeys = true,keyProperty = "medId")
    @Insert("insert into medicine (medName,medDescrip) values(#{medName},#{medDescrip})")
    int insert(Medicine medicine);

    @Select("select * from medicine")
    List<Medicine> selectAllUser();
    @Select("select * from medicine where medName=#{medName}")
    Medicine selectByName(String medName);
    @Select("select * from medicine where medId=#{medId}")
    Medicine selectById(Integer medId);

    @Update("update medicine set medName=#{medName},medDescrip=#{medDescrip} where medId=#{medId}")
    int updateByModel(Medicine medicine);
}