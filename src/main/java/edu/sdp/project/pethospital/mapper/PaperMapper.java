package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Paper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperMapper {
    @Select("select * from paper")
    List<Paper> selectAllPaper();
    @Select("select * from paper where paperId=#{paperId}")
    Paper selectById(int paperId);
    @Select("select * from paper where paperName = #{paperName}")
    Paper selectByName(String paperName);

    @Options(useGeneratedKeys = true,keyProperty = "paperId")
    @Insert("insert into paper (paperName) values(#{paperName})")
    int insert(Paper paper);

    @Update("update paper set paperName=#{paperName} where paperId=#{paperId}")
    int updatePaperName(int paperId,String paperName);

    @Delete("delete from paper where paperId=#{paperId}")
    int deleteById(int paperId);
}
