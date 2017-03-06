package com.example.ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/23/2017.
 */

public class Course implements Serializable {

    public static final int COURSE_TYPE_GENERAL = 0;
    public static final int COURSE_TYPE_SPECIALIZATION = 1;
    public static final int COURSE_TYPE_CHOICE = 2;
    public static final int COURSE_TYPE_MINOR = 3;
    public static final int COURSE_TYPE_INTERNSHIP = 4;

    private int id;
    private String code;
    private String name;
    private int ec;
    private double grade;
    private int period;
    private int year;

    private int courseType;

    public Course() {}
    public Course(String code, String name, int grade, int period, int ec, int year, int courseType) {
        this.code = code.toUpperCase();
        this.name = name;
        this.ec = ec;
        this.grade = grade;
        this.period = period;
        this.year = year;
        this.courseType = courseType;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getEc() {
        return ec;
    }
    public void setEc(int ec) {
        this.ec = ec;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getPeriod() {
        return period;
    }
    public void setPeriod(int period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getCourseType() { return courseType; }
    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }
}

