package ikpmd.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Sidney on 1/19/2017.
 */
@Embeddable
public class CourseEntityPrimaryKey implements Serializable {
    @Column(name="code")
    private String code;

    @Column(name="year")
    private int year;

    public CourseEntityPrimaryKey() {}
    public CourseEntityPrimaryKey(String code, int year) {
        this.code = code;
        this.year = year;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
