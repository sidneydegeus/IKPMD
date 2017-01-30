package com.example.ikpmd.model;

import java.io.Serializable;

/**
 * Created by Sidney on 1/23/2017.
 */

public class Course implements Serializable {

    private int id;
    private String code;
    private String name;
    private int ec;
    private double grade;
    private int period;
    private int year;

    public Course() {}
    public Course(String code, String name, int grade, int period, int ec, int year) {
        this.code = code.toUpperCase();
        this.name = name;
        this.ec = ec;
        this.grade = grade;
        this.period = period;
        this.year = year;
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
}
