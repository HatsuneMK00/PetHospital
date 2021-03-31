package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.mapper.MedicineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MedicineService {
    private final MedicineMapper medicineMapper;

    public MedicineService(MedicineMapper medicineMapper) {
        this.medicineMapper = medicineMapper;
    }

    public List<Medicine> getAllMedicines(){
        return medicineMapper.selectAllUser();
    }
    public Medicine getMedicine(int medId){
        return medicineMapper.selectById(medId);
    }
    public int addMedicine(Medicine medicine){
        Medicine exist = medicineMapper.selectByName(medicine.getMedName());
        if(exist!=null) return 0;
        return medicineMapper.insert(medicine);
    }
    public int changeMedicine(Medicine medicine){
        return medicineMapper.updateByModel(medicine);
    }
    public int deleteMedicine(int medId){
        return medicineMapper.deleteById(medId);
    }
    public boolean checkId(int medId){
        Medicine medicine = medicineMapper.selectById(medId);
        return medicine!=null;
    }
}
