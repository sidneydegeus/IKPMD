package com.example.ikpmd.service;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.ikpmd.model.Course;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Sidney on 1/29/2017.
 */

public class CourseService extends AbstractService {


    public void addCoursesByStudent(List<Course> courseList, String studentNr) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < courseList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("code", courseList.get(i).getCode());
                jsonObject.put("name", courseList.get(i).getName());
                jsonObject.put("grade", courseList.get(i).getGrade());
                jsonObject.put("ec", courseList.get(i).getEc());
                jsonObject.put("period", courseList.get(i).getPeriod());
                jsonObject.put("year", courseList.get(i).getYear());
                jsonObject.put("type", courseList.get(i).getCourseType());
                jsonArray.put(i, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(jsonArray.toString());
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(host + "add/courses/" + studentNr);

        try {

            StringEntity se = new StringEntity(jsonArray.toString());

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");


            HttpResponse response = httpClient.execute(httpPost, httpContext); //execute your request and parse response
            HttpEntity entity = response.getEntity();

            String jsonString = EntityUtils.toString(entity); //if response in JSON format

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    public void addCourseByStudent(Course course, String studentNr) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", course.getCode());
            jsonObject.put("name", course.getName());
            jsonObject.put("grade", course.getGrade());
            jsonObject.put("ec", course.getEc());
            jsonObject.put("period", course.getPeriod());
            jsonObject.put("year", course.getYear());
            jsonObject.put("type", course.getCourseType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(host + "add/course/" + studentNr);

        try {

            StringEntity se = new StringEntity(jsonObject.toString());

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");


            HttpResponse response = httpClient.execute(httpPost, httpContext); //execute your request and parse response
            HttpEntity entity = response.getEntity();

            String jsonString = EntityUtils.toString(entity); //if response in JSON format

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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
                course.setCourseType(Integer.parseInt(row.getString("type")));
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
