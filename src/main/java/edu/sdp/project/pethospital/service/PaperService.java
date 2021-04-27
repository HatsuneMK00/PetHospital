package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Paper;
import edu.sdp.project.pethospital.mapper.PaperMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaperService {
    private final PaperMapper paperMapper;

    public PaperService(PaperMapper paperMapper) {
        this.paperMapper = paperMapper;
    }

    public List<Paper> getAllPapers(){
        return paperMapper.selectAllPaper();
    }
    public Paper getPaper(int paperId){
        return paperMapper.selectById(paperId);
    }
    public int addPaper(String paperName){
        Paper paper = new Paper();
        Paper exist = paperMapper.selectByName(paperName);
        if(exist!=null) return -1;
        paper.setPaperName(paperName);
        if(paperMapper.insert(paper)>0) return paper.getPaperId();
        return 0;
    }
    public int setPaperName(int paperId,String paperName){
        return paperMapper.updatePaperName(paperId,paperName);
    }
    public int deletePaper(int paperId){
        return paperMapper.deleteById(paperId);
    }
    public boolean checkId(int paperId){
        return paperMapper.selectById(paperId)!=null;
    }
}
