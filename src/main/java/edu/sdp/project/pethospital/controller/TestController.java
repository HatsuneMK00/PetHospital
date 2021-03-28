package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
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
     * 此接口应该属于用户功能部分，暂时留空
     */
    @ResponseBody
    @PutMapping("/exam")
    ResponseMsg startTest(){
        return null;
    }
}
