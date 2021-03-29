package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.HospRecord;
import edu.sdp.project.pethospital.mapper.HospRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
