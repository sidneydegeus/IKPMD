package ikpmd.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 1/19/2017.
 */
@Entity(name="CourseEntity")
@Table(name="course")
public class CourseEntity implements EntityObject {

    @Id
    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="ec")
    private int ec;

    @Column(name="type")
    private int type;

    @OneToMany(mappedBy = "gradeCompoundKey.courseEntity", cascade={CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<GradeEntity> gradeEntityList = new ArrayList<>();

    public CourseEntity() {}
    public CourseEntity(String code, String name, int ec, int type) {
        this.code = code;
        this.name = name;
        this.ec = ec;
        this.type = type;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getEc() {
        return ec;
    }
    public void setEc(int ec) {
        this.ec = ec;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public List<GradeEntity> getGradeEntityList() {
        return gradeEntityList;
    }
    public void setGradeEntityList(List<GradeEntity> gradeEntityList) {
        this.gradeEntityList = gradeEntityList;
    }
    public void addGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntityList.add(gradeEntity);
        gradeEntity.getGradeCompoundKey().setCourseEntity(this);
    }
    public void removeGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntityList.remove(gradeEntity);
    }
}
