package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.mapper.FeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FeeService {
    private final FeeMapper feeMapper;

    public FeeService(FeeMapper feeMapper) {
        this.feeMapper = feeMapper;
    }
    public List<Fee> getAllFees(){
        return feeMapper.selectAllFees();
    }
    public Fee getFee(int feeId){
        return feeMapper.selectById(feeId);
    }
    public int addFee(Fee fee){
        Fee exist = feeMapper.selectByName(fee.getFeeName());
        if(exist!=null) return 0;
        int result = feeMapper.insert(fee);
        if(result>0) return fee.getFeeId();
        return result;
    }
    public int updateFee(Fee fee){
        return feeMapper.updateByModel(fee);
    }
    public int deleteFee(int feeId){
        return feeMapper.deleteById(feeId);
    }
    public boolean checkId(int feeId){
        Fee fee = feeMapper.selectById(feeId);
        return fee!=null;
    }
}
