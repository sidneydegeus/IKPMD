package ikpmd.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

/**
 * Created by Sidney on 1/19/2017.
 */
public class CourseEntity implements EntityObject {

    @EmbeddedId
    CourseEntityPrimaryKey courseEntityPrimaryKey;

    @Column(name="name")
    private String name;

    public CourseEntity() {}
    public CourseEntity(String code, int year, String name) {
        this.courseEntityPrimaryKey = new CourseEntityPrimaryKey(code, year);
        this.name = name;
    }

    public CourseEntityPrimaryKey getCourseEntityPrimaryKey() {
        return courseEntityPrimaryKey;
    }
    public void setCourseEntityPrimaryKey(CourseEntityPrimaryKey courseEntityPrimaryKey) {
        this.courseEntityPrimaryKey = courseEntityPrimaryKey;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
