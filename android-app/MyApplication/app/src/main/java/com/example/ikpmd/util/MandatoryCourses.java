package com.example.ikpmd.util;

import android.content.Context;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

/**
 * Created by Sidney on 3/6/2017.
 */

public class MandatoryCourses {

    public MandatoryCourses() {}

    public ArrayList<Course> createGeneralCourses() {
        ArrayList<Course> list = new ArrayList<>();
        // irdmbs
        Course irdbms = new Course();
        irdbms.setCode("IRDBMS");
        irdbms.setName("Relationele Databasemanagementsystemen");
        irdbms.setPeriod(1);
        irdbms.setEc(3);
        irdbms.setYear(2);
        irdbms.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(irdbms);

        // icommh
        Course icommh = new Course();
        icommh.setCode("ICOMMH");
        icommh.setName("Communicatie hoofdfase");
        icommh.setPeriod(3);
        icommh.setEc(3);
        icommh.setYear(2);
        icommh.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(icommh);

        // iscp
        Course iscp = new Course();
        iscp.setCode("ISCP");
        iscp.setName("Scripting niet forensisch");
        iscp.setPeriod(2);
        iscp.setEc(3);
        iscp.setYear(2);
        iscp.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(iscp);

        // iqua
        Course iqua = new Course();
        iqua.setCode("IQUA");
        iqua.setName("Kwaliteit in de IT");
        iqua.setPeriod(3);
        iqua.setEc(3);
        iqua.setYear(2);
        iqua.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(iqua);

        // iitorg
        Course iitorg = new Course();
        iitorg.setCode("IITORG");
        iitorg.setName("Kwaliteit in de IT");
        iitorg.setEc(3);
        iitorg.setYear(3);
        iitorg.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(iitorg);

        // isec
        Course isec = new Course();
        isec.setCode("ISEC");
        isec.setName("");
        isec.setEc(3);
        isec.setYear(3);
        isec.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(isec);

        // islh
        Course islh = new Course();
        islh.setCode("ISLH");
        islh.setName("Studieloopbaanbegeleiding hoofdfase");
        islh.setEc(3);
        islh.setYear(4);
        islh.setCourseType(Course.COURSE_TYPE_GENERAL);
        list.add(islh);

        return list;
    }

    public ArrayList<Course> createSpecializationCourses() {
        ArrayList<Course> list = new ArrayList<>();
        // ipsen2
        Course ipsen2 = new Course();
        ipsen2.setCode("IPSEN2");
        ipsen2.setName("Project Software Engineering 2");
        ipsen2.setPeriod(1);
        ipsen2.setEc(6);
        ipsen2.setYear(2);
        ipsen2.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ipsen2);

        // ipsen3
        Course ipsen3 = new Course();
        ipsen3.setCode("IPSEN3");
        ipsen3.setName("Project Software Engineering 3");
        ipsen3.setPeriod(2);
        ipsen3.setEc(6);
        ipsen3.setYear(2);
        ipsen3.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ipsen3);

        // ipsen4
        Course ipsen4 = new Course();
        ipsen4.setCode("IPSEN4");
        ipsen4.setName("Project Software Engineering 4");
        ipsen4.setPeriod(3);
        ipsen4.setEc(6);
        ipsen4.setYear(2);
        ipsen4.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ipsen4);

        // ipsen5
        Course ipsen5 = new Course();
        ipsen5.setCode("IPSEN5");
        ipsen5.setName("Project Software Engineering 5");
        ipsen5.setPeriod(4);
        ipsen5.setEc(6);
        ipsen5.setYear(2);
        ipsen5.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ipsen5);

        // ipsenh
        Course ipsenh = new Course();
        ipsenh.setCode("IPSENH");
        ipsenh.setName("Hoofdfase-Project Software Engineering");
        ipsenh.setEc(9);
        ipsenh.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ipsenh);

        // iopr3
        Course iopr3 = new Course();
        iopr3.setCode("IOPR3");
        iopr3.setName("Objectgeoriënteerd programmeren 3");
        iopr3.setPeriod(1);
        iopr3.setEc(3);
        iopr3.setYear(2);
        iopr3.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(iopr3);

        // irdm
        Course irdm = new Course();
        irdm.setCode("IRDM");
        irdm.setName("Relationele Databases modelleren");
        irdm.setPeriod(2);
        irdm.setEc(3);
        irdm.setYear(2);
        irdm.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(irdm);

        // ilg1
        Course ilg1 = new Course();
        ilg1.setCode("ILG1");
        ilg1.setName("Logica");
        ilg1.setPeriod(3);
        ilg1.setEc(3);
        ilg1.setYear(2);
        ilg1.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(ilg1);

        // iiad
        Course iiad = new Course();
        iiad.setCode("IIAD");
        iiad.setName("Inleiding algoritmen en datastructuren");
        iiad.setPeriod(4);
        iiad.setEc(3);
        iiad.setYear(2);
        iiad.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(iiad);

        // iad1
        Course iad1 = new Course();
        iad1.setCode("IAD1");
        iad1.setName("Algoritmen en datastructuren 1");
        iad1.setEc(3);
        iad1.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(iad1);

        // icp
        Course icp = new Course();
        icp.setCode("ICP");
        icp.setName("Concepten in programmeertalen");
        icp.setEc(3);
        icp.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(icp);

        // itest
        Course itest = new Course();
        itest.setCode("ITEST");
        itest.setName("Testen van software");
        itest.setEc(3);
        itest.setCourseType(Course.COURSE_TYPE_SPECIALIZATION);
        list.add(itest);

        return list;
    }

    private void createMinorCourses() {
        Course minor = new Course();
        minor.setCode("IKM30");
        minor.setName("Externe Minor");
        minor.setEc(30);
        minor.setCourseType(Course.COURSE_TYPE_MINOR);

    }
}
