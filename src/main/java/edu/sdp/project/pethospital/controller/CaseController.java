package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.*;
import edu.sdp.project.pethospital.exception.FileException;
import edu.sdp.project.pethospital.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class CaseController {
    private final CaseService caseService;
    private final CaseConsultService caseConsultService;
    private final CaseDiagService caseDiagService;
    private final CaseTherapyService caseTherapyService;
    private final ImageService imageService;
    private final VideoService videoService;

    public CaseController(CaseService caseService, CaseConsultService caseConsultService, CaseDiagService caseDiagService, CaseTherapyService caseTherapyService, ImageService imageService, VideoService videoService) {
        this.caseService = caseService;
        this.caseConsultService = caseConsultService;
        this.caseDiagService = caseDiagService;
        this.caseTherapyService = caseTherapyService;
        this.imageService = imageService;
        this.videoService = videoService;
    }

    /**
     * @return 返回3个对象，一个包含所有case的列表,case总数统计,以及相应case的所有文字描述（一个嵌套列表，分别是consult描述，diag描述和therapy描述）
     * 分别通过result,count和descrip拿取
     */
    @ResponseBody
    @GetMapping("/admin/case")
    ResponseMsg fetchAllCases(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Cas> result = caseService.traverseCases();
        if(result==null) return msg;
        msg.setStatus(200);
        List<List<String>> descrips = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Cas cas = result.get(i);
            String consultDescrip = caseConsultService.getConsultDescrip(cas.getCaseConsultId());
            String diagDescrip = caseDiagService.getDiagDescrip(cas.getCaseDiagId());
            String therapyDecrip = caseTherapyService.getTherapyDescrip(cas.getCaseTherapyId());
            List<String> tem = new ArrayList<>();
            tem.add(consultDescrip);
            tem.add(diagDescrip);
            tem.add(therapyDecrip);
            descrips.add(tem);
        }
        msg.getResponseMap().put("result",result);
        msg.getResponseMap().put("count",result.size());
        msg.getResponseMap().put("descrip",descrips);
        return msg;
    }

    /**
     * @return 返回四个对象，case，caseConsult，caseDiag和caseTherapy，
     * 通过对应的名称从返回体的map中拿去
     */
    @ResponseBody
    @GetMapping("/admin/case/{caseId}")
    ResponseMsg fetchCaseDetail(@PathVariable("caseId") Integer caseId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Cas record = caseService.getCaseById(caseId);
        if(record==null) return msg;
        CaseConsult caseConsult = caseConsultService.getConsultById(caseService.getCaseConsultId(caseId));
        CaseDiag caseDiag = caseDiagService.getCaseDiagById(caseService.getCaseDiagId(caseId));
        CaseTherapy caseTherapy = caseTherapyService.getCaseTherapyById(caseService.getCaseTherapyId(caseId));
        msg.setStatus(200);
        msg.getResponseMap().put("case",record);
        msg.getResponseMap().put("caseConsult",caseConsult);
        msg.getResponseMap().put("caseDiag",caseDiag);
        msg.getResponseMap().put("caseTherapy",caseTherapy);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/case/{caseId}/consult")
    ResponseMsg fetchCaseConsult(@PathVariable("caseId") Integer caseId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseConsultId = caseService.getCaseConsultId(caseId);
        if(!caseConsultService.checkId(caseConsultId)) return msg;
        CaseConsult caseConsult = caseConsultService.getConsultById(caseConsultId);
        if(caseConsult==null) return msg;
        msg.getResponseMap().put("result",caseConsult);
        msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/case/{caseId}/diag")
    ResponseMsg fetchCaseDiag(@PathVariable("caseId") Integer caseId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseDiagId = caseService.getCaseDiagId(caseId);
        if(!caseDiagService.checkId(caseDiagId)) return msg;
        CaseDiag caseDiag= caseDiagService.getCaseDiagById(caseDiagId);
        if(caseDiag==null) return msg;
        msg.getResponseMap().put("result",caseDiag);
        msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @GetMapping("/admin/case/{caseId}/therapy")
    ResponseMsg fetchCaseTherapy(@PathVariable("caseId") Integer caseId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseTherapyId=caseService.getCaseTherapyId(caseId);
        if(!caseTherapyService.checkId(caseTherapyId)) return msg;
        CaseTherapy caseTherapy = caseTherapyService.getCaseTherapyById(caseTherapyId);
        if(caseTherapy==null) return msg;
        msg.getResponseMap().put("result",caseTherapy);
        msg.setStatus(200);
        return msg;
    }

    /**
     * 实际上case能被修改的应该只有caseName,因此不提供case的整体更新接口
     * 如果要修改case相关的内容请调用相关的update函数
     * 请不要尝试改变case的consult，diag，therapy的id
     */
    @ResponseBody
    @PostMapping("/admin/case/{caseId}")
    ResponseMsg updateCaseName(@PathVariable("caseId") Integer caseId,@RequestParam("caseName") String caseName){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(caseService.checkId(caseId)) return msg;
        if(caseService.setCaseName(caseId,caseName)<=0) return msg;
        msg.setStatus(200);
        return msg;
    }
//    @ResponseBody
//    @PostMapping("/admin/case/{caseId}/consult")
//    ResponseMsg updateCaseConsultDescrip(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip){
//        ResponseMsg msg = new ResponseMsg();
//        msg.setStatus(404);
//        if (!caseService.checkId(caseId)) return msg;
//        int caseConsultId = caseService.getCaseConsultId(caseId);
//        if(!caseConsultService.checkId(caseConsultId)) return msg;
//        if(caseConsultService.setDescrip(caseConsultId,descrip)<=0) return msg;
//        msg.setStatus(200);
//        return msg;
//    }
//    @ResponseBody
//    @PostMapping("/admin/case/{caseId}/diag")
//    ResponseMsg updateCaseDiagDescrip(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip ){
//        ResponseMsg msg = new ResponseMsg();
//        msg.setStatus(404);
//        if(!caseService.checkId(caseId)) return msg;
//        int caseDiagId=caseService.getCaseDiagId(caseId);
//        if(!caseDiagService.checkId(caseDiagId)) return msg;
//        if(caseDiagService.setDescrip(caseDiagId,descrip)<=0) return msg;
//        msg.setStatus(200);
//        return msg;
//    }
//    @ResponseBody
//    @PostMapping("/admin/case/{caseId}/therapy")
//    ResponseMsg updateCaseTherapyDescrip(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip){
//        ResponseMsg msg = new ResponseMsg();
//        msg.setStatus(404);
//        if(!caseService.checkId(caseId)) return msg;
//        int caseTherapyId=caseService.getCaseTherapyId(caseId);
//        if(!caseTherapyService.checkId(caseTherapyId)) return msg;
//        if(caseTherapyService.setDescrip(caseTherapyId,descrip)<=0) return msg;
//        msg.setStatus(200);
//        return msg;
//    }
    /**
     * 如果要修改相应部分，请分别给出consultDescrip，diagDescrip和therapyDescrip
     */
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/descrip")
    ResponseMsg updateCaseDescrips(@PathVariable("caseId") int caseId,@RequestBody Map param){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int consultId = caseService.getCaseConsultId(caseId);
        int diagId = caseService.getCaseDiagId(caseId);
        int therapyId = caseService.getCaseTherapyId(caseId);
        int result1=0;
        int result2=0;
        int result3=0;
        if(param.containsKey("consultDescrip")){
            if(!caseConsultService.checkId(consultId)) return msg;
            result1 = caseConsultService.setDescrip(consultId,param.get("consultDescrip").toString());
        }
        if(param.containsKey("diagDescrip")){
            if(!caseDiagService.checkId(diagId)) return msg;
            result2=caseDiagService.setDescrip(diagId, param.get("diagDescrip").toString());
        }
        if(param.containsKey("therapyDescrip")){
            if(!caseTherapyService.checkId(therapyId)) return msg;
            result3=caseTherapyService.setDescrip(therapyId,param.get("therapyDescrip").toString());
        }
        if(result1>0&&result2>0&&result3>0) msg.setStatus(200);
        return msg;
    }

    /**
     * 新建case时只需要提供一个name就好
     * 添加图片和视频的操作应该在创建好case之后的页面进行
     */
    @ResponseBody
    @PutMapping("/admin/case")
    ResponseMsg addCase(@RequestParam("caseName") String caseName){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(caseService.addCase(caseName)>0) msg.setStatus(200);
        return msg;
    }

    /**
     * 新建consult，diag，therapy也同样遵循此原则，在新建时需要先提供一个descrip.
     * 添加图片和视频的操作应该在进入相应case的相应界面（比如consult界面）之后再调用上传图片的接口
     */
    @ResponseBody
    @PutMapping("/admin/case/{caseId}/consult")
    ResponseMsg addCaseConsult(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int result = caseConsultService.addCaseConsult(descrip);
        if(result>0) {
            msg.setStatus(200);
            caseService.changeCaseConsultId(caseId,result);
        }
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/case/{caseId}/diag")
    ResponseMsg addCaseDiag(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int result = caseDiagService.addCaseDiag(descrip);
        if(result>0){
            msg.setStatus(200);
            caseService.changeCaseDiagId(caseId,result);
        }
        return msg;
    }
    @ResponseBody
    @PutMapping("/admin/case/{caseId}/therapy")
    ResponseMsg addCaseTherapy(@PathVariable("caseId") Integer caseId,@RequestParam("descrip") String descrip){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int result = caseTherapyService.addCaseTherapy(descrip);
        if(result>0){
            msg.setStatus(200);
            caseService.changeCaseTherapyId(caseId,result);
        }
        return msg;
    }

    /**
     * 上传的图片和视频前端应该以一种和consult一对一的原则进行重命名后传给后端
     * 不然更新图片会导致服务器上出现冗余的图片和视频
     * 对于diag，therapy等所有需要上传图片和视频的类都应如此
     */
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/consult/image")
    ResponseMsg uploadConsultImage(@PathVariable("caseId") Integer caseId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseConsultId = caseService.getCaseConsultId(caseId);
        if(!caseConsultService.checkId(caseConsultId)) return msg;
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseConsultService.setImageUrl(caseConsultId,storeFile);
        msg.setStatus(result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/consult/video")
    ResponseMsg uploadConsultVideo(@PathVariable("caseId") Integer caseId,@RequestParam("video") MultipartFile video){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseConsultId = caseService.getCaseConsultId(caseId);
        if(!caseConsultService.checkId(caseConsultId)) return msg;
        String storeFile = null;
        try {
            storeFile = videoService.storeFile(video);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseConsultService.setVideoUrl(caseConsultId,storeFile);
        msg.setStatus(result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/diag/image")
    ResponseMsg uploadDiagImage(@PathVariable("caseId") Integer caseId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseDiagId = caseService.getCaseDiagId(caseId);
        if(!caseDiagService.checkId(caseDiagId)) return msg;
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseDiagService.setImageUrl(caseDiagId,storeFile);
        msg.setStatus(result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/diag/video")
    ResponseMsg uploadDiagVideo(@PathVariable("caseId") Integer caseId,@RequestParam("video") MultipartFile video){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseDiagId = caseService.getCaseDiagId(caseId);
        if(!caseDiagService.checkId(caseDiagId)) return msg;
        String storeFile = null;
        try {
            storeFile = videoService.storeFile(video);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseDiagService.setVideoUrl(caseDiagId,storeFile);
        msg.setStatus(result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/therapy/image")
    ResponseMsg uploadTherapyImage(@PathVariable("caseId") Integer caseId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseTherapyId = caseService.getCaseTherapyId(caseId);
        if(!caseTherapyService.checkId(caseTherapyId)) return msg;
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseTherapyService.setImageUrl(caseTherapyId,storeFile);
        msg.setStatus(result);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/case/{caseId}/therapy/video")
    ResponseMsg uploadTherapyVideo(@PathVariable("caseId") Integer caseId,@RequestParam("video") MultipartFile video){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        int caseTherapyId = caseService.getCaseTherapyId(caseId);
        if(!caseTherapyService.checkId(caseTherapyId)) return msg;
        String storeFile = null;
        try {
            storeFile = videoService.storeFile(video);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = caseTherapyService.setVideoUrl(caseTherapyId,storeFile);
        msg.setStatus(result);
        return msg;
    }

    /**
     * 此接口并不建议使用，因为仅删除了数据库中的记录而没有删除相关图片，视频
     * TODO: 增加自动删除视频和图片的功能
     */
    @ResponseBody
    @DeleteMapping("/admin/case")
    ResponseMsg deleteCase(@RequestParam("caseId") Integer caseId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!caseService.checkId(caseId)) return msg;
        caseConsultService.deleteCaseConsult(caseService.getCaseConsultId(caseId));
        caseDiagService.deleteCaseDiag(caseService.getCaseDiagId(caseId));
        caseTherapyService.deleteCaseTherapy(caseService.getCaseTherapyId(caseId));
        int result = caseService.deleteCaseById(caseId);
        if(result>0) msg.setStatus(200);
        return msg;
    }

}
