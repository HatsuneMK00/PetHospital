package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
    @ResponseBody
    @GetMapping("/admin/structure/medicine")
    ResponseMsg fetchAllMedicine(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Medicine> result = medicineService.getAllMedicines();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/structure/medicine/{medId}")
    ResponseMsg fetchMedicine(@PathVariable("medId") int medId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Medicine medicine = medicineService.getMedicine(medId);
        if(medicine!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",medicine);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/structure/medicine")
    ResponseMsg addMedicine(@RequestParam("medName") String medName,@RequestParam("medDescrip") String medDescrip){
        ResponseMsg msg =new ResponseMsg();
        msg.setStatus(404);
        Medicine medicine = new Medicine();
        medicine.setMedDescrip(medDescrip);
        medicine.setMedName(medName);
        if(medicineService.addMedicine(medicine)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/structure/medicine/{medId}")
    ResponseMsg updateMedicine(@PathVariable("medId") int medId, @RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Medicine medicine = medicineService.getMedicine(medId);
        if(medicine==null) return msg;
        medicine.updateMedicine(params);
        if(medicineService.changeMedicine(medicine)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/structure/medicine")
    ResponseMsg deleteMedicine(@RequestParam("medId") int medId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!medicineService.checkId(medId)) return msg;
        if(medicineService.deleteMedicine(medId)>0) msg.setStatus(200);
        return msg;
    }
}
