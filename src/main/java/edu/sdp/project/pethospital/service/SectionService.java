package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.Section;
import edu.sdp.project.pethospital.mapper.SectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SectionService {
    private final SectionMapper sectionMapper;

    public SectionService(SectionMapper sectionMapper) {
        this.sectionMapper = sectionMapper;
    }

    public List<Section> getAllSections(){
        return sectionMapper.selectAllUser();
    }
    public Section getSection(int sectionId){
        return sectionMapper.selectById(sectionId);
    }
    public int addSection(Section section){
        Section exist = sectionMapper.selectByName(section.getSectionName());
        if(exist!=null) return 0;
        int result = sectionMapper.insert(section);
        if(result>0) return section.getSectionId();
        return result;
    }
    public int changeSection(Section section){
        return sectionMapper.updateByModel(section);
    }
    public int deleteSection(int sectionId){
        return sectionMapper.deleteById(sectionId);
    }
    public boolean checkId(int sectionId){
        Section section = sectionMapper.selectById(sectionId);
        return section!=null;
    }
    public String setImageUrl(int sectionId,String image){
        String[] temp = image.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = sectionMapper.updateImageUrlById(sectionId, realUrl);
        if (result == 1)
            return realUrl;
        else
            return null;
    }
}
