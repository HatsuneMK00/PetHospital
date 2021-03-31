package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Section;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.exception.FileException;
import edu.sdp.project.pethospital.service.ImageService;
import edu.sdp.project.pethospital.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class SectionController {
    private final SectionService sectionService;
    private final ImageService imageService;

    public SectionController(SectionService sectionService, ImageService imageService) {
        this.sectionService = sectionService;
        this.imageService = imageService;
    }

    @ResponseBody
    @GetMapping("/admin/structure/section")
    ResponseMsg fetchAllSection(){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        List<Section> result = sectionService.getAllSections();
        if(result!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",result);
        return msg;
    }

    @ResponseBody
    @GetMapping("/admin/structure/section/{sectionId}")
    ResponseMsg fetchSection(@PathVariable("sectionId") int sectionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Section section = sectionService.getSection(sectionId);
        if(section!=null) msg.setStatus(200);
        msg.getResponseMap().put("result",section);
        return msg;
    }

    /**
     * 新增新的section的时候只需给出sectionName
     * 不同角色的Descrip和image都应该在创建之后的页面通过调用update进行
     */
    @ResponseBody
    @PutMapping("/admin/structure/section")
    ResponseMsg addSection(@RequestParam("sectionName") String sectionName){
        ResponseMsg msg =new ResponseMsg();
        msg.setStatus(404);
        Section section = new Section();
        section.setSectionName(sectionName);
        if(sectionService.addSection(section)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/structure/section/{sectionId}")
    ResponseMsg updateSection(@PathVariable("sectionId") int sectionId, @RequestBody Map params){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        Section section = sectionService.getSection(sectionId);
        if(section==null) return msg;
        section.updateSection(params);
        if(sectionService.changeSection(section)>0) msg.setStatus(200);
        return msg;
    }
    @ResponseBody
    @PostMapping("/admin/structure/section/{sectionId}/image")
    ResponseMsg uploadSectionImage(@PathVariable("sectionId") Integer sectionId, @RequestParam("image") MultipartFile image){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!sectionService.checkId(sectionId)) return msg;
        String storeFile = null;
        try {
            storeFile = imageService.storeFile(image);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
        }
        assert storeFile != null;
        int result = sectionService.setImageUrl(sectionId,storeFile);
        msg.setStatus(result);
        return msg;
    }

    /**
     * 不建议使用
     * 仅删除了数据库中的数据，会导致图片冗余
     * TODO: 增加自动删除对应图片的功能
     */
    @ResponseBody
    @DeleteMapping("/admin/structure/section")
    ResponseMsg deleteSection(@RequestParam("sectionId") int sectionId){
        ResponseMsg msg = new ResponseMsg();
        msg.setStatus(404);
        if(!sectionService.checkId(sectionId)) return msg;
        if(sectionService.deleteSection(sectionId)>0) msg.setStatus(200);
        return msg;
    }
}
