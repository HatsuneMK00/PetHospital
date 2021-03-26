package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.CaseDiag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseDiagMapper {
    @Delete("delete from casediag where caseDiagId=#{caseDiagId}")
    int deleteById(Integer caseDiagId);

    @Options(useGeneratedKeys = true,keyProperty = "caseDiagId")
    @Insert("insert into casediag (diagDescrip,diagImageUrl,diagVideoUrl) values(#{diagDescrip},#{diagImageUrl},#{diagVideoUrl})")
    int insert(CaseDiag record);

    @Select("select * from casediag where caseDiagId=#{caseDiagId}")
    CaseDiag selectById(Integer caseDiagId);
    @Select("select * from casediag where diagDescrip=#{diagDescrip}")
    CaseDiag selectByDescrip(String diagDescrip);

    @Update("update casetherapy set therapyDescrip=#{therapyDescrip},therapyImageUrl=#{therapyImageUrl},therapyVideoUrl=#{therapyVideoUrl} where caseTherapyId=#{caseTherapyId}")
    int updateByModel(CaseDiag record);

    @Update("update casetherapy set therapyDescrip=#{therapyDescrip} where caseTherapyId=#{caseTherapyId}")
    int updateDescripById(int caseTherapyId,String therapyDescrip);

    @Update("update casetherapy set therapyImageUrl=#{therapyImageUrl} where caeTherapyId=#{caseTherapyId}")
    int updateImageUrlById(int caseTherapyId,String therapyImageUrl);

    @Update("update casetherapy set therapyVideoUrl=#{therapyVideoUrl} where caseTherapyId=#{caseTherapyId}")
    int updateVideoUrlById(int caseTherapyId,String therapyVideoUrl);
}