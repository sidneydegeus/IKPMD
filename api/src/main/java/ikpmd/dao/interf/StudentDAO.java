package ikpmd.dao.interf;

import ikpmd.entity.StudentEntity;

/**
 * Created by Sidney on 1/30/2017.
 */
public interface StudentDAO {
    public StudentEntity getByStudentID(StudentEntity studentEntity);
    public void insert(StudentEntity studentEntity);
    public void delete(StudentEntity studentEntity);
}
