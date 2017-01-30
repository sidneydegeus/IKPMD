package ikpmd.dao.impl;

import ikpmd.dao.DAO;
import ikpmd.dao.interf.CourseDAO;
import ikpmd.dao.interf.GradeDAO;
import ikpmd.entity.CourseEntity;
import ikpmd.entity.GradeEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by Sidney on 1/29/2017.
 */
@Component
@Repository
@Transactional
public class CourseDAOImpl extends DAO implements CourseDAO {

    @Override
    public ArrayList<CourseEntity> getAll() {
        ArrayList<CourseEntity> gradeList = new ArrayList<>();
        String query = "SELECT course FROM CourseEntity course";
        try {
            gradeList = (ArrayList<CourseEntity>) manager.createQuery(query, CourseEntity.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gradeList;
    }

    @Override
    public void insert(CourseEntity courseEntity) {
        performInsert(courseEntity);
    }

    @Override
    public void update(CourseEntity courseEntity) {
        performUpdate(courseEntity);
    }

    @Override
    public void delete(CourseEntity courseEntity) {
        performDelete(courseEntity);
    }
}
