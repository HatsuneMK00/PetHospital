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
    @Select("select diagDescrip from casediag where caseDiagId=#{caseDiagId}")
    String selectDescripById(int caseDiagId);

    @Update("update casediag set diagDescrip=#{diagDescrip},diagImageUrl=#{diagImageUrl},diagVideoUrl=#{diagVideoUrl} where caseDiagId=#{caseDiagId}")
    int updateByModel(CaseDiag record);

    @Update("update casediag set diagDescrip=#{diagDescrip} where caseDiagId=#{caseDiagId}")
    int updateDescripById(int caseDiagId,String diagDescrip);

    @Update("update casediag set diagImageUrl=#{diagImageUrl} where caeDiagId=#{caseDiagId}")
    int updateImageUrlById(int caseDiagId,String diagImageUrl);

    @Update("update casediag set diagVideoUrl=#{diagVideoUrl} where caseDiagId=#{caseDiagId}")
    int updateVideoUrlById(int caseDiagId,String diagVideoUrl);
}