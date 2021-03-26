package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Case;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseMapper {
    @Delete("delete from case where caseId = #{caseId}")
    int deleteById(Integer caseId);

    @Options(useGeneratedKeys = true,keyProperty = "caseId")
    @Insert("insert into case (caseName, caseConsultId,caseDiagId, caseTherapyId) values (#{caseName}, #{caseConsultId},#{caseDiagId}, #{caseTherapyId})")
    int insert(Case record);

    @Select("select * from case where caseId=#{caseId}")
    Case selectById(Integer caseId);

    @Select("select * from case where caseName=#{caseName}")
    Case selectByName(String caseName);

    @Update("update case set caseName=#{caseName},caseConsultId=#{caseConsultId},caseDiagId=#{caseDiagId},caseTherapyId=#{caseTherapyId} where caseId=#{caseId}")
    int updateByModel(Case record);
    @Update("update case set caseName=#{caseName} where caseId=#{caseId}")
    int updateCaseNameById(int caseId,String caseName);

    @Select("select * from case")
    List<Case> selectAllCases();

    @Select("select caseConsultId from case where caseId = #{caseId}")
    int selectCaseConsultIdById(int caseId);

    @Select("select caseDiagId from case where caseId=#{caseId}")
    int selectCaseDiagIdById(int caseId);

    @Select("select caseTherapyId from case where caseId=#{caseId}")
    int selectCaseTherapyIdById(int caseId);

    @Update("update case set caseConsultId=#{caseConsultId} where caseId=#{caseId}")
    int updateConsultIdById(int caseId,int caseConsultId);

    @Update("update case set caseDiagId=#{caseDiagId} where caseId=#{caseId}")
    int updateDiagIdById(int caseId,int caseConsultId);

    @Update("update case set caseTherapyId=#{caseTherapyId} where caseId=#{caseId}")
    int updateTherapyIdById(int caseId,int caseTherapyId);
}