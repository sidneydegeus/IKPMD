package ikpmd.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 1/29/2017.
 */
@Entity(name="StudentEntity")
@Table(name="student")
public class StudentEntity implements EntityObject {

    @Id
    @Column(name="student_nr")
    private String studentNr;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "gradeCompoundKey.studentEntity", cascade={CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<GradeEntity> gradeEntityList = new ArrayList<>();

    public StudentEntity() {}
    public StudentEntity(String studentNr, String password) {
        this.studentNr = studentNr;
        this.password = password;
    }

    public String getStudentNr() {
        return studentNr;
    }
    public void setStudentNr(String studentNr) {
        this.studentNr = studentNr;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<GradeEntity> getGradeEntityList() {
        return gradeEntityList;
    }
    public void setGradeEntityList(List<GradeEntity> gradeEntityList) {
        this.gradeEntityList = gradeEntityList;
    }
    public void addGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntityList.add(gradeEntity);
        gradeEntity.getGradeCompoundKey().setStudentEntity(this);
    }
    public void removeGradeEntity(GradeEntity gradeEntityList) {
        this.gradeEntityList.remove(gradeEntityList);
    }
}
