package ikpmd.controller;

import ikpmd.dao.interf.CourseDAO;
import ikpmd.dao.interf.GradeDAO;
import ikpmd.entity.CourseEntity;
import ikpmd.entity.GradeCompoundKey;
import ikpmd.entity.GradeEntity;
import ikpmd.entity.StudentEntity;
import ikpmd.model.CourseModel;
import ikpmd.model.StudentModel;
import ikpmd.utility.BeanHandler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 1/19/2017.
 */
@RestController
public class CourseController {

    @RequestMapping(
            value = "get/course/{studentNr}/all",
            method= RequestMethod.GET
    )
    public List<CourseModel> getStudentCourseAll(@PathVariable String studentNr) {
        StudentModel studentModel = new StudentModel();
        studentModel.setStudentNr(studentNr);
        ArrayList<GradeEntity> gradeEntityList = BeanHandler.getInstance().createGradeDAO().getAllByStudent(studentModel);
        List<CourseModel> modelList = new ArrayList<>();
        for (GradeEntity gradeEntity : gradeEntityList) {
            CourseModel courseModel = new CourseModel();

            courseModel.setCode( gradeEntity.getGradeCompoundKey().getCourseEntity().getCode());
            courseModel.setName(gradeEntity.getGradeCompoundKey().getCourseEntity().getName());
            courseModel.setEc(gradeEntity.getGradeCompoundKey().getCourseEntity().getEc());
            courseModel.setGrade(gradeEntity.getGrade());
            courseModel.setPeriod(gradeEntity.getPeriod());
            courseModel.setYear(gradeEntity.getYear());

            modelList.add(courseModel);
        }
        return modelList;
    }

    @RequestMapping(
            value = "add/courses/{studentNr}",
            method= RequestMethod.POST
    )
    @ResponseBody
    public String addCoursesByStudent(@RequestBody List<CourseModel> courseModelList, @PathVariable String studentNr) {
        System.out.println(courseModelList.size());
        System.out.println(courseModelList.get(0).getCode());
        CourseDAO courseDAO = BeanHandler.getInstance().createCourseDAO();
        GradeDAO gradeDAO = BeanHandler.getInstance().createGradeDAO();
        gradeDAO.deleteAll();
        for (CourseModel courseModel : courseModelList) {
            CourseEntity courseEntity = new CourseEntity(courseModel.getCode(), courseModel.getName(), courseModel.getEc());
            // first check if they exist already, else insert
            if (courseDAO.getByCode(courseEntity) == null) {
                courseDAO.insert(courseEntity);
            } else {
                //courseDAO.update(courseEntity);
            }

            GradeEntity gradeEntity = new GradeEntity();
            gradeEntity.setGradeCompoundKey(new GradeCompoundKey(studentNr, courseModel.getCode()));
            gradeEntity.setGrade(courseModel.getGrade());
            gradeEntity.setPeriod(courseModel.getPeriod());
            gradeEntity.setYear(courseModel.getYear());

            gradeDAO.insert(gradeEntity);
        }
        return "success";
    }
}
