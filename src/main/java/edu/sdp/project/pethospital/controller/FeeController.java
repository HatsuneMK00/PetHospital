package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.FeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class FeeController {
    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @ResponseBody
    @GetMapping("/admin/structure/fee")

    ResponseMsg fetchAllFees() {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Fee> result = feeService.getAllFees();
        if (result != null)
            msg.setStatus(200);
        msg.getResponseMap().put("result", result);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/structure/fee/{feeId}")
    @RequestMapping(value = { "/admin/structure/fee/{feeId}" }, method = RequestMethod.PUT)

    ResponseMsg fetchFee(@PathVariable("feeId") int feeId) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Fee fee = feeService.getFee(feeId);
        if (fee != null)
            msg.setStatus(200);
        msg.getResponseMap().put("result", fee);
        return msg;
    }

    @ResponseBody
    @PutMapping("/admin/structure/fee")
    ResponseMsg addFee(@RequestParam("feeName") String feeName, @RequestParam("feePrice") Double feePrice,
            @RequestParam("feeDescrip") String feeDescrip) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Fee fee = new Fee();
        fee.setFeeName(feeName);
        fee.setFeePrice(feePrice);
        fee.setFeeDescrip(feeDescrip);
        int result = feeService.addFee(fee);
        if (result > 0) {
            msg.setStatus(200);
            msg.getResponseMap().put("result", result);
        }
        return msg;
    }

    @ResponseBody
    @PostMapping("/admin/structure/fee/{feeId}")
    ResponseMsg updateFee(@PathVariable("feeId") int feeId, @RequestBody Map param) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Fee fee = feeService.getFee(feeId);
        if (fee == null)
            return msg;
        fee.updateFee(param);
        if (feeService.updateFee(fee) > 0)
            msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @DeleteMapping("/admin/structure/fee")
    ResponseMsg deleteFee(@RequestParam("feeId") int feeId) {
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if (!feeService.checkId(feeId))
            return msg;
        if (feeService.deleteFee(feeId) > 0)
            msg.setStatus(200);
        return msg;
    }

}
