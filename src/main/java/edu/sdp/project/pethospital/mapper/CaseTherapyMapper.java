package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CaseTherapy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseTherapyMapper {
    @Delete("delete from casetherapy where caseTherapyId=#{caseTherapyId}")
    int deleteById(Integer caseTherapyId);

    @Options(useGeneratedKeys = true,keyProperty = "caseTherapyId")
    @Insert("insert into casetherapy (therapyDescrip,therapyImageUrl,therapyVideoUrl) values(#{therapyDescrip},#{therapyImageUrl},#{therapyVideoUrl})")
    int insert(CaseTherapy record);

    @Select("select * from casetherapy where caseTherapyId=#{caseTherapyId}")
    CaseTherapy selectById(Integer caseTherapyId);
    @Select("select * from casetherapy where therapyDescrip=#{therapyDescrip}")
    CaseTherapy selectByDescrip(String therapyDescrip);

    @Update("update casetherapy set caseTherapyId=#{caseTherapyId},therapyDescrip=#{therapyDescrip},therapyImageUrl=#{therapyImageUrl},therapyVideoUrl=#{therapyVideoUrl} where caseTherapyId=#{caseTherapyId}")
    int updateByModel(CaseTherapy record);

    @Update("update casetherapy set therapyDescrip=#{therapyDescrip} where caseTherapyId=#{caseTherapyId}")
    int updateDescripById(int caseTherapyId,String therapyDescrip);

    @Update("update casetherapy set therapyImageUrl=#{therapyImageUrl} where caseTherapyId=#{caseTherapyId}")
    int updateImageUrlById(int caseTherapyId,String therapyImageUrl);

    @Update("update casetherapy set therapyVideoUrl=#{therapyVideoUrl} where caseTherapyId=#{caseTherapyId}")
    int updateVideoUrlById(int caseTherapyId,String therapyVideoUrl);
}