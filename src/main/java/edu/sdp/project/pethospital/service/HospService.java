package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.HospRecord;
import edu.sdp.project.pethospital.mapper.HospRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class HospService {
    private final HospRecordMapper hospRecordMapper;

    public HospService(HospRecordMapper hospRecordMapper) {
        this.hospRecordMapper = hospRecordMapper;
    }
    public HospRecord getHospRecord(int hosId){
        return hospRecordMapper.selectById(hosId);
    }
    public List<HospRecord> getAllHospRecord(){
        return hospRecordMapper.selectAllHospRecord();
    }
    public int deleteHospRecord(int hosId){
        return hospRecordMapper.deleteById(hosId);
    }
    public int addHospRecord(String hosAnimalName, String disease, Timestamp inDate){
        HospRecord hospRecord = new HospRecord();
        hospRecord.setHosAnimalName(hosAnimalName);
        hospRecord.setDisease(disease);
        hospRecord.setInDate(inDate);
        return hospRecordMapper.insert(hospRecord);
    }
    public int updateHospRecord(HospRecord hospRecord){
        return hospRecordMapper.updateByModel(hospRecord);
    }
    public boolean checkId(int hosId){
        HospRecord hospRecord = hospRecordMapper.selectById(hosId);
        return hospRecord!=null;
    }
}
