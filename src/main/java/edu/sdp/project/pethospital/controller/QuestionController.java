package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.exception.FileException;
import edu.sdp.project.pethospital.service.ImageService;
import edu.sdp.project.pethospital.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
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
        if(!param.containsKey("descrip")) return msg;
        msg.setStatus(404);
        Question question = new Question();
        question.updateQuestion(param);
        if(questionService.addQuestion(question)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/test/question/{quesId}/image")
    ResponseMsg uploadImage(@PathVariable("quesId") int quesId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!questionService.checkId(quesId)) return msg;
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e){
            log.error(e.getMessage());
        }
        assert storeFile != null;
        int result = questionService.setImageUrl(quesId,storeFile);
        msg.setStatus(result);
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
    @Delete("/admin/test/question")
    ResponseMsg deleteQuestion(@RequestParam("quesId") int quesId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!questionService.checkId(quesId)) return msg;
        if(questionService.deleteQuestionById(quesId)>0) msg.setStatus(200);
        return msg;
    }
}
