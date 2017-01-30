package ikpmd.controller;

import ikpmd.dao.interf.StudentDAO;
import ikpmd.entity.StudentEntity;
import ikpmd.model.StudentModel;
import ikpmd.utility.BeanHandler;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Sidney on 1/29/2017.
 */
@RestController
public class StudentController {

    @RequestMapping(
            value = "add/student/",
            method = RequestMethod.POST
    )
    public String addStudent(@RequestParam("studentNr") String studentNr, @RequestParam("password") String password) {
        StudentEntity studentEntity = new StudentEntity(studentNr, password);
        StudentDAO studentDAO = BeanHandler.getInstance().createStudentDAO();
        if (studentDAO.getByStudentID(studentEntity) == null) {
            studentDAO.insert(studentEntity);
        }
        return "success";
    }

/*    @RequestMapping(
            value = "add/student/",
            method = RequestMethod.POST
    )
    @ResponseBody
    public String addStudent(@RequestBody StudentModel studentModel) {
        System.out.println(studentModel);
*//*        StudentEntity studentEntity = new StudentEntity(studentNr, password);
        StudentDAO studentDAO = BeanHandler.getInstance().createStudentDAO();
        if (studentDAO.getByStudentID(studentEntity) == null) {
            studentDAO.insert(studentEntity);
        }*//*
        return "success";
    }*/

/*    public void addStudent(@PathVariable String studentId) {
        System.out.println(studentId);
    }*/
}