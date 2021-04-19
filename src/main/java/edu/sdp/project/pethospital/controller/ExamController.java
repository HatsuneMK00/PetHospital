package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Exam;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.mapper.ExamMapper;
import edu.sdp.project.pethospital.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @ResponseBody
    @GetMapping("/admin/structure/examination")
    ResponseMsg fetchAllExam(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Exam> result = examService.getAllExams();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/structure/examination/{examId}")
    ResponseMsg fetchExam(@PathVariable("examId") int examId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Exam exam = examService.getExam(examId);
        if(exam!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",exam);
        return msg;
    }

    /**
     * 通过result拿取examId
     */
    @ResponseBody
    @PutMapping("/admin/structure/examination")
    ResponseMsg addExam(@RequestParam("examName") String examName,@RequestParam("examDescrip") String examDescrip){
        ResponseMsg msg =new ResponseMsg();
        msg.setStatus(404);
        Exam exam = new Exam();
        exam.setExamDescrip(examDescrip);
        exam.setExamName(examName);
        int result = examService.addExam(exam);
        if(result>0) {
            msg.setStatus(200);
            msg.getResponseMap().put("result",result);
        }
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/structure/examination/{examId}")
    ResponseMsg updateExam(@PathVariable("examId") int examId, @RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Exam exam = examService.getExam(examId);
        if(exam==null) return msg;
        exam.updateExam(params);
        if(examService.changeExam(exam)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/structure/examination")
    ResponseMsg deleteExam(@RequestParam("examId") int examId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!examService.checkId(examId)) return msg;
        if(examService.deleteExam(examId)>0) msg.setStatus(200);
        return msg;
    }

}
