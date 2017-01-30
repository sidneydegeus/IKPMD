package ikpmd.dao.interf;

import ikpmd.entity.CourseEntity;
import ikpmd.entity.GradeEntity;

import java.util.ArrayList;

/**
 * Created by Sidney on 1/29/2017.
 */
public interface CourseDAO {
    public ArrayList<CourseEntity> getAll();
    public void insert(CourseEntity courseEntity);
    public void update(CourseEntity courseEntity);
    public void delete(CourseEntity courseEntity);
}
