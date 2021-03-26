package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.CaseTherapy;
import edu.sdp.project.pethospital.mapper.CaseTherapyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaseTherapyService {
    private final CaseTherapyMapper caseTherapyMapper;

    public CaseTherapyService(CaseTherapyMapper caseTherapyMapper) {
        this.caseTherapyMapper = caseTherapyMapper;
    }

    public CaseTherapy getCaseTherapyById(int caseTherapyId){
        return caseTherapyMapper.selectById(caseTherapyId);
    }

    public int deleteCaseTherapy(int caseTherapyId){
        return caseTherapyMapper.deleteById(caseTherapyId);
    }
    public int addCaseTherapy(String descrip){
        CaseTherapy caseTherapy = new CaseTherapy();
        caseTherapy.setTherapyDescrip(descrip);
        CaseTherapy exist = caseTherapyMapper.selectByDescrip(descrip);
        if(exist!=null) return 0;
        return caseTherapyMapper.insert(caseTherapy);
    }
    public int changeCaseTherapy(CaseTherapy caseTherapy){
        return caseTherapyMapper.updateByModel(caseTherapy);
    }
    public int setImageUrl(int caseTherapyId, String imageUrl){
        String[] temp = imageUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseTherapyMapper.updateImageUrlById(caseTherapyId, realUrl);
        if (result == 1)
            return 200;
        else
            return 500;
    }
    public int setVideoUrl(int caseTherapyId,String videoUrl){
        String[] temp = videoUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/videos/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseTherapyMapper.updateVideoUrlById(caseTherapyId, realUrl);
        if (result == 1)
            return 200;
        else
            return 500;
    }
    public int setDescrip(int caseTherapyId,String descrip){
        return caseTherapyMapper.updateDescripById(caseTherapyId,descrip);
    }
    public boolean checkId(int caseTherapyId){
        CaseTherapy caseTherapy = caseTherapyMapper.selectById(caseTherapyId);
        return caseTherapy!=null;
    }
}
