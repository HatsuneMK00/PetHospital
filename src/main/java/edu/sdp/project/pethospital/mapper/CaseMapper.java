package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Cas;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseMapper {
    @Delete("delete from cas where caseId = #{caseId}")
    int deleteById(Integer caseId);

    @Options(useGeneratedKeys = true,keyProperty = "caseId")
    @Insert("insert into cas (caseName, caseConsultId,caseDiagId, caseTherapyId) values (#{caseName}, #{caseConsultId},#{caseDiagId}, #{caseTherapyId})")
    int insert(Cas record);

    @Select("select * from cas where caseId=#{caseId}")
    Cas selectById(Integer caseId);

    @Select("select * from cas where caseName=#{caseName}")
    Cas selectByName(String caseName);

    @Update("update cas set caseName=#{caseName},caseConsultId=#{caseConsultId},caseDiagId=#{caseDiagId},caseTherapyId=#{caseTherapyId} where caseId=#{caseId}")
    int updateByModel(Cas record);
    @Update("update cas set caseName=#{caseName} where caseId=#{caseId}")
    int updateCaseNameById(int caseId,String caseName);

    @Select("select * from cas")
    List<Cas> selectAllCases();

    @Select("select caseConsultId from cas where caseId = #{caseId}")
    int selectCaseConsultIdById(int caseId);

    @Select("select caseDiagId from cas where caseId=#{caseId}")
    int selectCaseDiagIdById(int caseId);

    @Select("select caseTherapyId from cas where caseId=#{caseId}")
    int selectCaseTherapyIdById(int caseId);

    @Update("update cas set caseConsultId=#{caseConsultId} where caseId=#{caseId}")
    int updateConsultIdById(int caseId,int caseConsultId);

    @Update("update cas set caseDiagId=#{caseDiagId} where caseId=#{caseId}")
    int updateDiagIdById(int caseId,int caseDiagId);

    @Update("update cas set caseTherapyId=#{caseTherapyId} where caseId=#{caseId}")
    int updateTherapyIdById(int caseId,int caseTherapyId);
}