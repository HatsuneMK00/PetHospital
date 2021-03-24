package edu.sdp.project.pethospital.mapper;

import edu.sdp.project.pethospital.entity.Section;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionMapper {
    int deleteByPrimaryKey(Integer sectionid);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer sectionid);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
}