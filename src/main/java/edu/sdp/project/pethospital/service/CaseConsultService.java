package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.CaseConsult;
import edu.sdp.project.pethospital.mapper.CaseConsultMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaseConsultService {
    private final CaseConsultMapper caseConsultMapper;

    public CaseConsultService(CaseConsultMapper caseConsultMapper) {
        this.caseConsultMapper = caseConsultMapper;
    }
    public CaseConsult getConsultById(int caseConsultId){
        return caseConsultMapper.selectById(caseConsultId);
    }
    public String getConsultImageUrl(int caseConsultId){
        return caseConsultMapper.selectImageUrlById(caseConsultId);
    }
    public String getConsultVideoUrl(int caseConsultId){
        return caseConsultMapper.selectVideoUrlById(caseConsultId);
    }
    public int setImageUrl(int caseConsultId, String imageUrl){
        String[] temp = imageUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseConsultMapper.updateImageUrlById(caseConsultId, realUrl);
        if (result == 1)
            return 200;
        else
            return 500;
    }
    public int setVideoUrl(int caseConsultId,String videoUrl){
        String[] temp = videoUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/videos/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseConsultMapper.updateVideoUrlById(caseConsultId, realUrl);
        if (result == 1)
            return 200;
        else
            return 500;
    }
    public int deleteCaseConsult(int caseConsultId){
        return caseConsultMapper.deleteById(caseConsultId);
    }
    public int addCaseConsult(String consultDescrip){
        CaseConsult caseConsult = new CaseConsult();
        caseConsult.setConsultDescrip(consultDescrip);
        CaseConsult exist = caseConsultMapper.selectByDescrip(consultDescrip);
        if(exist!=null) return 0;
        return caseConsultMapper.insert(caseConsult);
    }
    public int setDescrip(int caseConsultId,String descrip){
        return caseConsultMapper.updateDescripById(caseConsultId,descrip);
    }
    public int changeCaseConsult(CaseConsult caseConsult){
        return caseConsultMapper.updateByModel(caseConsult);
    }
    public boolean checkId(int caseConsultId){
        CaseConsult caseConsult = caseConsultMapper.selectById(caseConsultId);
        return caseConsult!=null;
    }
}
