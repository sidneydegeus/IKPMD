package ikpmd.model;

/**
 * Created by Sidney on 1/29/2017.
 */
public class StudentModel {

    private String studentNr;
    private String password;

    public StudentModel() {}
    public StudentModel(String studentNr, String password) {
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
}
