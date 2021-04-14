package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.service.OptionQuesService;
import edu.sdp.project.pethospital.service.OptionUserService;
import edu.sdp.project.pethospital.service.TestOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@CrossOrigin(origins="*")
public class TestOptionController {
    private final TestOptionService testOptionService;
    private final OptionUserService optionUserService;
    private final OptionQuesService optionQuesService;

    public TestOptionController(TestOptionService testOptionService, OptionUserService optionUserService, OptionQuesService optionQuesService) {
        this.testOptionService = testOptionService;
        this.optionUserService = optionUserService;
        this.optionQuesService = optionQuesService;
    }

    @ResponseBody
    @GetMapping("/admin/test/paper")
    ResponseMsg fetchAllOptions(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<TestOption> result = testOptionService.getAllOptions();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/paper/{testOptionId}")
    ResponseMsg fetchTestOption(@PathVariable("testOptionId") int testOptionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        TestOption testOption = testOptionService.getOptionById(testOptionId);
        if(testOption!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",testOption);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/paper/{testOptionId}/parUsers")
    ResponseMsg fetchParticipationUsers(@PathVariable("testOptionId") int testOptionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if (!testOptionService.checkId(testOptionId)) return msg;
        List<User> result = optionUserService.getUsersByOptionId(testOptionId);
        if(result==null) return msg;
        msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/exam/paper/user/{userId}")
    ResponseMsg fetchUserExams(@PathVariable("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<TestOption> result = optionUserService.getOptionsByUserId(userId);
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/test/paper/{testOptionId}/addUser")
    ResponseMsg addUserToOption(@PathVariable("testOptionId") int testOptionId,@RequestBody List<Integer> userIds){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        if(optionUserService.addUserOptions(testOptionId,userIds)>0||userIds.size()==0) msg.setStatus(200);
        return msg;
    }

    @ResponseBody
    @DeleteMapping("/admin/test/paper/{testOptionId}/deleteUser")
    ResponseMsg deleteUserFromOption(@PathVariable("testOptionId") int testOptionId,@RequestBody List<Integer> userIds){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        if(optionUserService.deleteUserOptions(testOptionId,userIds)>0||userIds.size()==0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/test/paper/{testOptionId}/addQues")
    ResponseMsg addQuesToOption(@PathVariable("testOptionId") int testOptionId,@RequestBody List<Integer> quesIds){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        if(optionQuesService.addQuesToOption(testOptionId,quesIds)>0||quesIds.size()==0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/test/paper/{testOptionId}/deleteQues")
    ResponseMsg deleteQuesFromOption(@PathVariable("testOptionId") int testOptionId,@RequestBody List<Integer> quesIds ){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        if(optionQuesService.deleteQuesFromOption(testOptionId,quesIds)>0||quesIds.size()==0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/test/paper")
    ResponseMsg addTestOption(@RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(400);
        log.info(params.get("testOptionName").toString());
        if(!params.containsKey("testOptionName")) return null;
        msg.setStatus(404);
        log.info(params.get("testOptionName").toString());
        TestOption testOption = new TestOption();
        testOption.updateTestOption(params);
        int result = testOptionService.addOption(testOption);
        if(result>0) {
            msg.setStatus(200);
            log.info(params.get("testOptionName").toString());
            msg.getResponseMap().put("result",result);
        }
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/paper/{testOptionId}")
    ResponseMsg changeTestOption(@PathVariable("testOptionId") int testOptionId,@RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        TestOption testOption = testOptionService.getOptionById(testOptionId);
        if(testOption==null) return msg;
        testOption.updateTestOption(params);
        if(testOptionService.updateTestOption(testOption)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/test/paper")
    ResponseMsg deleteOption(@RequestParam("testOptionId") int testOptionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!testOptionService.checkId(testOptionId)) return msg;
        if(testOptionService.deleteOptionById(testOptionId)>0) msg.setStatus(200);
        return msg;
    }
}
