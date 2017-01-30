package ikpmd.dao.interf;

import ikpmd.entity.GradeEntity;
import ikpmd.entity.StudentEntity;
import ikpmd.model.StudentModel;

import java.util.ArrayList;

/**
 * Created by Sidney on 1/29/2017.
 */
public interface GradeDAO {
    public ArrayList<GradeEntity> getAllByStudent(StudentModel studentModel);
    public GradeEntity getByCompoundKey(GradeEntity gradeEntity);
    public void insert(GradeEntity gradeEntity);
    public void update(GradeEntity gradeEntity);
    public void delete(GradeEntity gradeEntity);
    public void deleteAll();
}
