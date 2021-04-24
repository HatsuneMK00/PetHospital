package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Paper;
import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.PaperQuesService;
import edu.sdp.project.pethospital.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class PaperController {
    private final PaperService paperService;
    private final PaperQuesService paperQuesService;

    public PaperController(PaperService paperService, PaperQuesService paperQuesService) {
        this.paperService = paperService;
        this.paperQuesService = paperQuesService;
    }
    @ResponseBody
    @GetMapping("/admin/test/problemSet")
    ResponseMsg fetchAllProblemSet(){
        ResponseMsg msg =new ResponseMsg();
        msg.setStatus(404);
        List<Paper> result = paperService.getAllPapers();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/problemSet/{paperId}")
    ResponseMsg fetchPaper(@PathVariable("paperId") Integer paperId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Paper paper = paperService.getPaper(paperId);
        if(paper==null) return msg;
        msg.setStatus(200);
        msg.getResponseMap().put("result",paper);
        List<Question> ques = paperQuesService.getQuesByPaperId(paperId);
        msg.getResponseMap().put("questions",ques);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/test/problemSet")
    ResponseMsg addProbSet(@RequestParam("paperName") String paperName){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(400);
        if(paperName==null||paperName.equals("")) return msg;
        int result = paperService.addPaper(paperName);
        if(result>0) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/problemSet/{paperId}")
    ResponseMsg changePaperName(@PathVariable("paperId") Integer paperId,@RequestParam("paperName") String paperName){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!paperService.checkId(paperId)) return msg;
        if(paperService.setPaperName(paperId,paperName)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/test/problemSet")
    ResponseMsg deletePaper(@RequestParam("paperId") Integer paperId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!paperService.checkId(paperId)) return msg;
        if(paperQuesService.deleteQuesByPaperId(paperId)>=0&&paperService.deletePaper(paperId)>=0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/test/problemSet/{paperId}/addQues")
    ResponseMsg addQuesToPaper(@PathVariable("paperId") int paperId,@RequestBody List<Integer> quesIds){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        int result1 = paperQuesService.deleteQuesByPaperId(paperId);
        int result2 = paperQuesService.addQuesToPaper(paperId,quesIds);
        log.info(result1+" result is "+result2);
        if(quesIds.size()==0||(result1>=0&&result2>0)) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @DeleteMapping("/admin/test/problemSet/{paperId}/deleteQues")
    ResponseMsg deleteQuesFromPaper(@PathVariable("paperId") int paperId,@RequestBody List<Integer> quesIds ){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(500);
        if(paperQuesService.deleteQuesFromPaper(paperId,quesIds)>0||quesIds.size()==0) msg.setStatus(200);
        return msg;
    }
}
