package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Vaccine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.VaccineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @ResponseBody
    @GetMapping("/admin/structure/vaccine")
    ResponseMsg fetchAllVaccine() {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Vaccine> result = vaccineService.getAllVaccines();
        if (result != null)
            msg.setStatus(200);
        msg.getResponseMap().put("result", result);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = { "/admin/structure/vaccine/{vacId}" }, method = RequestMethod.PUT)
    @GetMapping("/admin/structure/vaccine/{vacId}")
    ResponseMsg fetchVaccine(@PathVariable("vacId") int vacId) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Vaccine vaccine = vaccineService.getVaccine(vacId);
        if (vaccine != null)
            msg.setStatus(200);
        msg.getResponseMap().put("result", vaccine);
        return msg;
    }

    @ResponseBody
    @PutMapping("/admin/structure/vaccine")
    ResponseMsg addVaccine(@RequestParam("vacName") String vacName, @RequestParam("vacDescrip") String vacDescrip) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Vaccine vaccine = new Vaccine();
        vaccine.setVacDescrip(vacDescrip);
        vaccine.setVacName(vacName);
        int result = vaccineService.addVaccine(vaccine);
        if (result > 0) {
            msg.setStatus(200);
            msg.getResponseMap().put("result", result);
        }
        return msg;
    }

    @ResponseBody
    @PostMapping("/admin/structure/vaccine/{vacId}")
    ResponseMsg updateVaccine(@PathVariable("vacId") int vacId, @RequestBody Map params) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Vaccine vaccine = vaccineService.getVaccine(vacId);
        if (vaccine == null)
            return msg;
        vaccine.updateVaccine(params);
        if (vaccineService.changeVaccine(vaccine) > 0)
            msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @DeleteMapping("/admin/structure/vaccine")
    ResponseMsg deleteVaccine(@RequestParam("vacId") int vacId) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if (!vaccineService.checkId(vacId))
            return msg;
        if (vaccineService.deleteVaccine(vacId) > 0)
            msg.setStatus(200);
        return msg; 
    }
}
