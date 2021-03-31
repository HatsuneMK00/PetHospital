package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.HospRecord;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.HospService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class HospController {
    private final HospService hospService;

    public HospController(HospService hospService) {
        this.hospService = hospService;
    }

    @ResponseBody
    @GetMapping("/admin/structure/hospitalize/{hosId}")
    ResponseMsg fetchHospRecord(@PathVariable("hosId") int hosId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        HospRecord hospRecord = hospService.getHospRecord(hosId);
        if(hospRecord!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",hospRecord);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/structure/hospitalize")
    ResponseMsg fetchAllHospRecords(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<HospRecord> result = hospService.getAllHospRecord();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    @ResponseBody
    @PutMapping("/admin/structure/hospitalize")
    ResponseMsg addHospRecord(@RequestParam("hosAnimalName") String hosAnimalName, @RequestParam("disease") String disease, @RequestParam("inDate")Timestamp inDate){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(hospService.addHospRecord(hosAnimalName,disease,inDate)>0) msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @PostMapping("/admin/structure/hospitalize/{hosId}")
    ResponseMsg updateHospRecord(@PathVariable("hosId") int hosId, @RequestBody Map param){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        HospRecord hospRecord = hospService.getHospRecord(hosId);
        if(hospRecord==null) return msg;
        if(hospService.updateHospRecord(hospRecord)>0) msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @DeleteMapping("/admin/structure/hospitalize")
    ResponseMsg deleteHospRecord(@RequestParam("hosId") int hosId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!hospService.checkId(hosId)) return msg;
        if(hospService.deleteHospRecord(hosId)>0) msg.setStatus(200);
        return msg;
    }
}
