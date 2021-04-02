package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Vaccine;
import edu.sdp.project.pethospital.mapper.VaccineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VaccineService {
    private final VaccineMapper vaccineMapper;

    public VaccineService(VaccineMapper vaccineMapper) {
        this.vaccineMapper = vaccineMapper;
    }


    public List<Vaccine> getAllVaccines(){
        return vaccineMapper.selectAllUser();
    }
    public Vaccine getVaccine(int vacId){
        return vaccineMapper.selectById(vacId);
    }
    public int addVaccine(Vaccine vaccine){
        Vaccine exist = vaccineMapper.selectByName(vaccine.getVacName());
        if(exist!=null) return 0;
        int result = vaccineMapper.insert(vaccine);
        if(result>0) return vaccine.getVacId();
        return result;
    }
    public int changeVaccine(Vaccine vaccine){
        return vaccineMapper.updateByModel(vaccine);
    }
    public int deleteVaccine(int vacId){
        return vaccineMapper.deleteById(vacId);
    }
    public boolean checkId(int vacId){
        Vaccine vaccine = vaccineMapper.selectById(vacId);
        return vaccine!=null;
    }
}
