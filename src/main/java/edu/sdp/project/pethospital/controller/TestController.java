package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.service.OptionUserService;
import edu.sdp.project.pethospital.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class TestController {
    private final TestService testService;
    private final OptionUserService optionUserService;

    public TestController(TestService testService, OptionUserService optionUserService) {
        this.testService = testService;
        this.optionUserService = optionUserService;
    }

    @ResponseBody
    @GetMapping("/admin/test/exams")
    ResponseMsg fetchAllTests(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Test> result = testService.getAllTests();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/exams/user/{userId}")
    ResponseMsg fetchUserTests(@PathVariable("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Test> result = testService.getAllTestsByUser(userId);
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/exams/option/{testOptionId}")
    ResponseMsg fetchOptionTests(@PathVariable("testOptionId") int testOptionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Test> result = testService.getAllTestsByOptionId(testOptionId);
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/exams/{testId}")
    ResponseMsg fetchTest(@PathVariable("testId") int testId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Test test= testService.getTestById(testId);
        if(test!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",test);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/exams/{testOptionId}")
    ResponseMsg updateTest(@PathVariable("testOptionId") int testOptionId,@RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Test test = testService.getTestById(testOptionId);
        if(test==null) return msg;
        test.updateTest(params);
        if(testService.changeTest(test)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/test/exams")
    ResponseMsg deleteTest(@RequestParam("testId") int testId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!testService.checkId(testId)) return msg;
        if(testService.deleteTestById(testId)>0) msg.setStatus(200);
        return msg;
    }

    /**
     * test的新增应该只有在用户开始一场考试时才生成，因此不属于管理员的功能
     * 调用该接口会在test表中插入一条新记录，并返回一套试卷（一个Question列表）和新插入的testId
     * 分别通过result和testId拿取
     * 此接口会进行一些条件判断
     * 返回404表示该用户无权限参加考试
     * 返回400表示该用户已经参加过该考试
     * 返回500表示该用户可以参加该考试但是目前事件已经超过或者还未到考试时间
     */
    @ResponseBody
    @PutMapping("/exam/{testOptionId}/user/{userId}")
    ResponseMsg startTest(@PathVariable("testOptionId") int testOptionId,@PathVariable("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!optionUserService.checkId(testOptionId,userId)) return msg;
        msg.setStatus(400);
        if(testService.checkTest(userId,testOptionId)) return msg;
        msg.setStatus(500);
        if(!testService.checkDate(testOptionId)) return msg;
        List<Question> result = testService.getNewTest(userId,testOptionId);
        int testId = testService.addTest(userId,testOptionId);
        if(result!=null&&result.size()>0&&testId>0) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        msg.getResponseMap().put("testId",testId);
        return msg;
    }
}
