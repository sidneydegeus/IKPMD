package com.example.ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/29/2017.
 */

public class Student implements Serializable {

    private String studentNr;
    private String password;

    public Student() {}
    public Student(String studentNr, String password) {
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
