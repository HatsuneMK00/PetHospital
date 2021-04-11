package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.exception.FileException;
import edu.sdp.project.pethospital.service.ImageService;
import edu.sdp.project.pethospital.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class QuestionController {
    private final QuestionService questionService;
    private final ImageService imageService;

    public QuestionController(QuestionService questionService, ImageService imageService) {
        this.questionService = questionService;
        this.imageService = imageService;
    }

    @ResponseBody
    @GetMapping("/admin/test/question")
    ResponseMsg fetchAllQuestions(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Question> result = questionService.getAllQues();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/question/{quesId}")
    ResponseMsg fetchQuestion(@PathVariable("quesId") int quesId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Question question = questionService.getQuestionById(quesId);
        if(question!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",question);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/test/question/tag/{tag}")
    ResponseMsg fetchQuestionByTag(@PathVariable("tag") String tag){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Question> result = questionService.getAllQuesByTag(tag);
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    /**
     * 本接口支持通过id搜索，格式为：qid:{id1},{id2},...
     * 如果不按以上格式则按照关键词处理，处理方式为返回描述中有匹配关键字的问题
     */
    @ResponseBody
    @GetMapping("/admin/test/question/search")
    ResponseMsg fetchQuestionBySearch(@RequestParam("searchParam") String searchParam){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Question> result = questionService.getQuesBySearch(searchParam);
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    /**
     * 新增问题时不用给出id，id自增管理
     * 但必须给出描述（题面），理论上不同问题的描述不应该相同，所以如果插入的问题描述已经存在，则无法插入
     * 其他属性(除image外，image应该在新增问题之后通过上传添加)都是可选项。
     * 但是建议在新建问题时便给出除image外的所有属性
     */
    @ResponseBody
    @PutMapping("/admin/test/question")
    ResponseMsg addQuestion(@RequestBody Map param){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(400);
        if(!(param.containsKey("descrip")&&param.containsKey("type"))) return msg;
        msg.setStatus(500);
        Question question = new Question();
        question.updateQuestion(param);
        int result = questionService.addQuestion(question);
        if(result>0) {
            msg.setStatus(200);
            msg.getResponseMap().put("result",result);
        }
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/question/{quesId}/image")
    ResponseMsg uploadImage(@PathVariable("quesId") int quesId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!questionService.checkId(quesId)) return msg;
        msg.setStatus(500);
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e){
            log.error(e.getMessage());
        }
        assert storeFile != null;
        String url = questionService.setImageUrl(quesId,storeFile);
        if(url==null) return msg;
        msg.setStatus(200);
        msg.getResponseMap().put("result",url);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/question/{quesId}")
    ResponseMsg changeQuestion(@PathVariable("quesId") Integer quesId,@RequestBody Map param){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Question question = questionService.getQuestionById(quesId);
        if(question==null) return msg;
        question.updateQuestion(param);
        if(questionService.updateQuestion(question)>0) msg.setStatus(200);
        return msg;
    }

    /**
     * 此方法不建议调用，因为仅删除了数据库中的记录，并没有删除对应的图片
     * 会导致服务器上出现冗余文件
     * TODO: 增加自动删除相关图片的功能
     */
    @ResponseBody
    @DeleteMapping("/admin/test/question")
    ResponseMsg deleteQuestion(@RequestParam("quesId") int quesId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!questionService.checkId(quesId)) return msg;
        if(questionService.deleteQuestionById(quesId)>0) msg.setStatus(200);
        return msg;
    }
}
