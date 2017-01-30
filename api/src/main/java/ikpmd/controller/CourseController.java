package ikpmd.controller;

import ikpmd.entity.GradeEntity;
import ikpmd.entity.StudentEntity;
import ikpmd.model.CourseModel;
import ikpmd.model.StudentModel;
import ikpmd.utility.BeanHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
