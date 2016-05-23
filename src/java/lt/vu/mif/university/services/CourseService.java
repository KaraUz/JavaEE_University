/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import lt.vu.mif.university.entities.Course;

/**
 *
 * @author Karolis
 */
@Named
@Stateless
public class CourseService {
        
    @PersistenceContext(type= PersistenceContextType.TRANSACTION ,synchronization=SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    public Course createCourse(Course course) {
        course.setStudentList(new ArrayList<>());
        em.persist(course);
        return course;
    }
    public Course selectCourse(String name){
        try{
            Course course = (Course)em.createNamedQuery("Course.findByName").setParameter("name", name).getSingleResult();
            if(course.getStudentList()== null){
                course.setStudentList(new ArrayList<>());
            }          
            return course;
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<Course> selectAllCourses(){
        try{
            return (List<Course>)em.createNamedQuery("Course.findAll").getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
}
