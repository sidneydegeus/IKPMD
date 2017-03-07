package ikpmd.dao.impl;

import ikpmd.dao.DAO;
import ikpmd.dao.interf.StudentDAO;
import ikpmd.entity.StudentEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Sidney on 1/30/2017.
 */
@Component
@Repository
@Transactional
public class StudentDAOImpl extends DAO implements StudentDAO {
    @Override
    public StudentEntity getByStudentID(StudentEntity studentEntity) {
        StudentEntity entity = manager.find(StudentEntity.class, studentEntity.getStudentNr());
 /*       manager.flush();
        manager.clear();*/
        return entity;
    }

    @Override
    public void insert(StudentEntity studentEntity) {
        performInsert(studentEntity);
    }

    @Override
    public void delete(StudentEntity studentEntity) {
        performDelete(studentEntity);
    }
}
