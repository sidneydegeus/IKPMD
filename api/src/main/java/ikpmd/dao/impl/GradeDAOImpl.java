package ikpmd.dao.impl;

import ikpmd.dao.DAO;
import ikpmd.dao.interf.GradeDAO;
import ikpmd.entity.GradeEntity;
import ikpmd.entity.StudentEntity;
import ikpmd.model.StudentModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by Sidney on 1/29/2017.
 */
@Component
@Repository
@Transactional
public class GradeDAOImpl extends DAO implements GradeDAO {

    @Override
    public ArrayList<GradeEntity> getAllByStudent(StudentModel studentModel) {
        ArrayList<GradeEntity> gradeList = new ArrayList<>();
        String query = "SELECT grade FROM GradeEntity grade WHERE grade.gradeCompoundKey.studentEntity.studentNr = :studentNr";
        try {
            gradeList = (ArrayList<GradeEntity>) manager.createQuery(query, GradeEntity.class)
                    .setParameter("studentNr", studentModel.getStudentNr())
                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gradeList;
    }

    @Override
    public GradeEntity getByCompoundKey(GradeEntity gradeEntity) {
        return manager.find(GradeEntity.class, gradeEntity.getGradeCompoundKey());
    }


    @Override
    public void insert(GradeEntity gradeEntity) {
        performInsert(gradeEntity);
    }

    @Override
    public void update(GradeEntity gradeEntity) {
        performUpdate(gradeEntity);
    }

    @Override
    public void delete(GradeEntity gradeEntity) {
        performDelete(gradeEntity);
    }

    @Override
    public void deleteAll() {
        String hql = String.format("delete from GradeEntity");
        Query query = manager.createQuery(hql);
        query.executeUpdate();
    }
}
