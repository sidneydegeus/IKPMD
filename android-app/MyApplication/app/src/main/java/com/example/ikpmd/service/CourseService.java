package com.example.ikpmd.service;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.ikpmd.model.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Sidney on 1/29/2017.
 */

public class CourseService extends AbstractService {



    public ArrayList<Course> getAllByStudent(String studentNr) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = host + "get/course/" + studentNr + "/all";
        ArrayList<Course> list = new ArrayList<>();
        try{
            JSONArray jsonArray = getJSONObjectFromURL(url);
            for (int i = 0; i < jsonArray.length(); i++) {
                Course course = new Course();
                JSONObject row = jsonArray.getJSONObject(i);
                course.setCode(row.getString("code"));
                course.setName(row.getString("name"));
                course.setEc(Integer.parseInt(row.getString("ec")));
                course.setGrade(Double.parseDouble(row.getString("grade")));
                course.setPeriod(Integer.parseInt(row.getString("period")));
                course.setYear(Integer.parseInt(row.getString("year")));
                list.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


}
