/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.vu.mif.university.entities.Course;
import lt.vu.mif.university.entities.Student;
import lt.vu.mif.university.entities.University;
import lt.vu.mif.university.services.CourseService;
import lt.vu.mif.university.services.UniversityService;

/**
 *
 * @author Karolis
 */
@Named
@RequestScoped
@Stateful 
public class EnrollController {
    @PersistenceContext
    private EntityManager em;
    @Inject 
    private UniversityService universityService;
    @Inject
    private CourseService courseService;
    
    public void prepareUniversity() {
        em.isOpen(); // Initialize entity cache
        universityService.createUniversity("aasdas"); // em gets propagated
        courseService.createCourse(new Course()); // em gets propagated
    }
}
