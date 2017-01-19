package ikpmd.controller;

import ikpmd.entity.CourseEntity;
import ikpmd.greeting.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.util.ArrayList;

/**
 * Created by Sidney on 1/19/2017.
 */
@RestController
public class CourseController {

    @RequestMapping("/course/all")
    public ArrayList<CourseEntity> get() {
        ArrayList<CourseEntity> list = new ArrayList<>();
        list.add(new CourseEntity("abc", 2016, "aa bee cee"));
        return list;
    }
}
