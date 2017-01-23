package com.example.ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/23/2017.
 */

public class Course implements Serializable {

    private String code;
    private String name;
    private int grade;
    private int period;

    public Course() {}
    public Course(String code, String name, int grade, int period) {
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.period = period;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPeriod() {
        return period;
    }
    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return name;
    }
}
