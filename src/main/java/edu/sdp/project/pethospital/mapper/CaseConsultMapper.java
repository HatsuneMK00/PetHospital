package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CaseConsult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseConsultMapper {
    @Delete("delete from caseconsult where caseConsultId=#{caseConsultId}")
    int deleteById(Integer caseConsultId);

    @Options(useGeneratedKeys = true,keyProperty = "caseConsultId")
    @Insert("insert into caseconsult (consultDescrip, consultImageUrl,consultVideoUrl) values (#{consultDescrip}, #{consultImageUrl},#{consultVideoUrl})")
    int insert(CaseConsult record);

    @Select("select * from caseconsult where caseConsultId = #{caseConsultId}")
    CaseConsult selectById(Integer caseConsultId);
    @Select("select consultImageUrl from caseconsult where caseConsultId=#{caseConsultId}")
    String selectImageUrlById(int caseConsultId);
    @Select("select consultVideoUrl from caseconsult where caseConsultId=#{caseConsultId}")
    String selectVideoUrlById(int caseConsultId);
    @Select("select * from caseconsult where consultDescrip=#{consultDescrip}")
    CaseConsult selectByDescrip(String consultDescrip);

    @Update("update caseconsult set consultDescrip = #{consultDescrip},consultImageUrl=#{consultImageUrl},consultVideoUrl=#{consultVideoUrl} where caseConsultId=#{caseConsultId}")
    int updateByModel(CaseConsult record);

    @Update("update caseconsult set consultDescrip = #{consultDescrip} where caseConsultId=#{caseConsultId}")
    int updateDescripById(int caseConsultId,String consultDescrip);

    @Update("update caseconsult set consultImageUrl=#{consultImageUrl} where caseConsultId=#{caseConsultId}")
    int updateImageUrlById(int caseConsultId,String consultImageUrl);

    @Update("update caseconsult set consultVideoUrl=#{consultVideoUrl} where caseConsultId=#{caseConsultId}")
    int updateVideoUrlById(int caseConsultId,String consultVideoUrl);
}