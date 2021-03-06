package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Cas;
import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.mapper.CaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CaseService {
    private final CaseMapper caseMapper;

    public CaseService(CaseMapper caseMapper) {
        this.caseMapper = caseMapper;
    }

    public Cas getCaseById(int caseId){
        return caseMapper.selectById(caseId);
    }
    public int getCaseConsultId(int caseId){
        return caseMapper.selectCaseConsultIdById(caseId);
    }
    public int getCaseDiagId(int caseId){
        return caseMapper.selectCaseDiagIdById(caseId);
    }
    public int getCaseTherapyId(int caseId){
        return caseMapper.selectCaseTherapyIdById(caseId);
    }
    public int deleteCaseById(int caseId){
        return caseMapper.deleteById(caseId);
    }
    public List<Cas> traverseCases(){
        return caseMapper.selectAllCases();
    }
    public int changeCaseByModel(Cas record){
        return caseMapper.updateByModel(record);
    }
    public int setCaseTag(int caseId,String caseTag){
        return caseMapper.updateCaseTag(caseId,caseTag);
    }
    public List<Cas> getCasesByTag(String tag){
        return caseMapper.selectCasesByTag(tag);
    }
    public List<Cas> getCasesBySearch(String searchParam){
        if (searchParam.equals("")) {
            return caseMapper.selectAllCases();
        } else if (searchParam.startsWith("cid:")) {
            String param = searchParam.split(":")[1];
            String[] ids = param.split(",");
            ArrayList<Cas> result = new ArrayList<>();
            for (String id : ids) {
                result.add(caseMapper.selectById(Integer.valueOf(id.trim())));
            }
            return result;
        } else {
            List<Cas> result = caseMapper.selectCasesByNameMatch(searchParam);
            return result;
        }
    }
    public int changeCaseConsultId(int caseId,int caseConsultId){
        return caseMapper.updateConsultIdById(caseId,caseConsultId);
    }
    public int changeCaseDiagId(int caseId,int caseDiagId){
        return caseMapper.updateDiagIdById(caseId,caseDiagId);
    }
    public int changeCaseTherapyId(int caseId,int caseTherapyId){
        return caseMapper.updateTherapyIdById(caseId,caseTherapyId);
    }
    public int setCaseName(int caseId,String caseName){
        return caseMapper.updateCaseNameById(caseId,caseName);
    }
    public boolean checkId(int caseId){
        Cas record = caseMapper.selectById(caseId);
        return record!=null;
    }
    public int addCase(String caseName){
        Cas record = new Cas();
        record.setCaseName(caseName);
        Cas exist = caseMapper.selectByName(caseName);
        if(exist!=null) return 0;
        caseMapper.insert(record);
        return caseMapper.selectByName(caseName).getCaseId();
    }
}
