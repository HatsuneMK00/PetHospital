package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.config.PHServerConfig;
import edu.sdp.project.pethospital.entity.CaseDiag;
import edu.sdp.project.pethospital.mapper.CaseDiagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaseDiagService {
    private final CaseDiagMapper caseDiagMapper;

    public CaseDiagService(CaseDiagMapper caseDiagMapper) {
        this.caseDiagMapper = caseDiagMapper;
    }
    public CaseDiag getCaseDiagById(int caseDiagId){
        return caseDiagMapper.selectById(caseDiagId);
    }
    public int addCaseDiag(String descrip){
        CaseDiag caseDiag = new CaseDiag();
        caseDiag.setDiagDescrip(descrip);
        CaseDiag exist = caseDiagMapper.selectByDescrip(descrip);
        if(exist!=null) return 0;
        caseDiagMapper.insert(caseDiag);
        return caseDiagMapper.selectByDescrip(descrip).getCaseDiagId();
    }
    public String getDiagDescrip(int caseDiagId){
        return caseDiagMapper.selectDescripById(caseDiagId);
    }
    public int changeCaseDiag(CaseDiag caseDiag){
        return caseDiagMapper.updateByModel(caseDiag);
    }
    public int deleteCaseDiag(int caseDiagId){
        return caseDiagMapper.deleteById(caseDiagId);
    }
    public String setImageUrl(int caseDiagId, String imageUrl){
        String[] temp = imageUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/images/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseDiagMapper.updateImageUrlById(caseDiagId, realUrl);
        if (result == 1)
            return realUrl;
        else
            return null;
    }
    public String setVideoUrl(int caseDiagId,String videoUrl){
        String[] temp = videoUrl.split("/");
        String realUrl = PHServerConfig.server + ":" + PHServerConfig.port + "/videos/";
        log.info(PHServerConfig.port);
        realUrl = realUrl + temp[temp.length - 1];
        int result = caseDiagMapper.updateVideoUrlById(caseDiagId, realUrl);
        if (result == 1)
            return realUrl;
        else
            return null;
    }
    public int setDescrip(int caseDiagId,String descrip){
        return caseDiagMapper.updateDescripById(caseDiagId,descrip);
    }
    public boolean checkId(int caseDiagId){
        CaseDiag caseDiag = caseDiagMapper.selectById(caseDiagId);
        return caseDiag!=null;
    }
}
