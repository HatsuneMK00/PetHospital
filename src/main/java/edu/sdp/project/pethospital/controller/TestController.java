package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.Test;
import edu.sdp.project.pethospital.entity.TestOption;
import edu.sdp.project.pethospital.service.OptionQuesService;
import edu.sdp.project.pethospital.service.OptionUserService;
import edu.sdp.project.pethospital.service.TestOptionService;
import edu.sdp.project.pethospital.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class TestController {
    private final TestService testService;
    private final OptionUserService optionUserService;
    private final TestOptionService testOptionService;
    private final OptionQuesService optionQuesService;

    public TestController(TestService testService, OptionUserService optionUserService, TestOptionService testOptionService, OptionQuesService optionQuesService) {
        this.testService = testService;
        this.optionUserService = optionUserService;
        this.testOptionService = testOptionService;
        this.optionQuesService = optionQuesService;
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
    @GetMapping(value = {"/admin/test/exams/user/{userId}","/exam/user/{userId}"})
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
    @GetMapping(value = {"/admin/test/exams/{testId}","/exam/{testId}"})
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
     * test????????????????????????????????????????????????????????????????????????????????????????????????
     * ?????????????????????test????????????????????????????????????????????????????????????Question????????????????????????testId
     * ????????????result???testId??????
     * ????????????????????????????????????
     * ??????404??????????????????????????????????????????????????????
     * ??????400???????????????????????????????????????
     * ??????500?????????????????????????????????????????????????????????????????????????????????????????????
     * ??????501?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ??????502??????test????????????????????????????????????
     */
    @ResponseBody
    @PutMapping("/exam/{testOptionId}/user/{userId}")
    ResponseMsg startTest(@PathVariable("testOptionId") int testOptionId,@PathVariable("userId") int userId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!optionUserService.checkId(testOptionId,userId)) return msg;
        TestOption testOption = testOptionService.getOptionById(testOptionId);
        if(testOption==null) return msg;
        msg.setStatus(400);
        if(testService.checkTest(userId,testOptionId)) return msg;
        msg.setStatus(500);
        if(!testService.checkDate(testOptionId)) return msg;
        if(testOption.getSelectNum()==0&&testOption.getJudgeNum()==0&&testOption.getQaNum()==0){
            List<Question> result = optionQuesService.getQuesByOptionId(testOptionId);
            int testId=testService.addTest(userId,testOptionId);
            if(result!=null&&testId>0) msg.setStatus(200);
            msg.getResponseMap().put("result",result);
            msg.getResponseMap().put("testId",testId);
            return msg;
        }
        msg.setStatus(501);
        List<Question> result = testService.getNewTest(userId,testOptionId);
        if(result==null||result.size()==0) return msg;
        msg.setStatus(502);
        int testId = testService.addTest(userId,testOptionId);
        if(testId<=0) return msg;
        msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        msg.getResponseMap().put("testId",testId);
        return msg;
    }
    @ResponseBody
    @PostMapping("/exam/{testId}/submit")
    ResponseMsg endTest(@PathVariable("testId") int testId,@RequestBody List<Object> params){
        Test test = testService.getTestById(testId);
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(test==null) return msg;
        List<String> answers = new ArrayList<>();
        List<Integer> quesIds = new ArrayList<>();
        for (int i = 0; i < params.size(); i+=2) {
            quesIds.add(Integer.valueOf(params.get(i).toString()));
            answers.add(params.get(i+1).toString());
        }
        int score = testService.getScore(quesIds,answers,testId);
        msg.setStatus(500);
        log.info(score+" ");
        if(score<0) return msg;
        test.setScore(score);
        test.setEndDate(new Date());
        int result = testService.changeTest(test);
        if(result<=0) return msg;
        msg.setStatus(200);
        return msg;
    }

}
