package edu.sdp.project.pethospital.service;

import edu.sdp.project.pethospital.entity.Exam;
import edu.sdp.project.pethospital.mapper.ExamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExamService {
    private final ExamMapper examMapper;

    public ExamService(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }
    public List<Exam> getAllExams(){
        return examMapper.selectAllUser();
    }
    public Exam getExam(int examId){
        return examMapper.selectById(examId);
    }
    public int addExam(Exam exam){
        Exam exist = examMapper.selectByName(exam.getExamName());
        if(exist!=null) return 0;
        return examMapper.insert(exam);
    }
    public int changeExam(Exam exam){
        return examMapper.updateByModel(exam);
    }
    public int deleteExam(int examId){
        return examMapper.deleteById(examId);
    }
    public boolean checkId(int examId){
        Exam exam = examMapper.selectById(examId);
        return exam!=null;
    }
}
