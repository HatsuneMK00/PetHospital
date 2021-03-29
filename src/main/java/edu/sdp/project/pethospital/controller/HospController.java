package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.HospRecord;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.HospService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
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
}
