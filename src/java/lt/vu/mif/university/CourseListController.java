/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import lt.vu.mif.university.entities.Course;

/**
 *
 * @author Karolis
 */
@Named
public class CourseListController {
    List<Course> courses;
    
    @PostConstruct
    public void init(){
        
    }
}
