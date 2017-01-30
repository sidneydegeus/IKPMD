package ikpmd.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Sidney on 1/29/2017.
 */
public class GradeCompoundKey implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn (name = "student_nr", referencedColumnName = "student_nr")
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudentEntity studentEntity;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn (name = "course_code", referencedColumnName = "code")
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CourseEntity courseEntity;

    public GradeCompoundKey() {}
    public GradeCompoundKey(String studentNr, String courseCode) {
        studentEntity = new StudentEntity();
        studentEntity.setStudentNr(studentNr);
        courseEntity = new CourseEntity();
        courseEntity.setCode(courseCode);
    }


    public StudentEntity getStudentEntity() {
        return studentEntity;
    }
    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }
    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }
}
