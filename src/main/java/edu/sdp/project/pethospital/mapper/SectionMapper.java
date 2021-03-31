package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Section;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionMapper {
    @Delete("delete from section where sectionId=#{sectionId}")
    int deleteById(Integer sectionId);

    @Options(useGeneratedKeys = true,keyProperty = "sectionId")
    @Insert("insert into section (sectionName) values(#{sectionName})")
    int insert(Section section);

    @Select("select * from section")
    List<Section> selectAllUser();
    @Select("select * from section where sectionName=#{sectionName}")
    Section selectByName(String sectionName);
    @Select("select * from section where sectionId=#{sectionId}")
    Section selectById(Integer sectionId);

    @Update("update section set sectionName=#{sectionName},recDescrip=#{recDescrip},docDexcrip=#{docDescrip},assisDescrip=#{assisDescrip} where sectionId=#{sectionId}")
    int updateByModel(Section section);
    @Update("update section set sectionImageUrl=#{sectionImageUrl} where sectionId=#{sectionId}")
    int updateImageUrlById(int sectionId,String sectionImageUrl);
}