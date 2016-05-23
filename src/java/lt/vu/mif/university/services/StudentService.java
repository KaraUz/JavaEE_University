/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university.services;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import lt.vu.mif.university.entities.Student;
import lt.vu.mif.university.entities.University;

/**
 *
 * @author Karolis
 */
@Named
@Stateless
public class StudentService {
    
    @PersistenceContext(type= PersistenceContextType.TRANSACTION ,synchronization=SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    public Student createStudent(Student student) {
        student.setCourseList(new ArrayList<>());
        em.persist(student);
        return student;
    }
    
    public Student selectStudent(String reg){
        try{
            Student stud = (Student)em.createNamedQuery("Student.findByRegistrationNo").setParameter("registrationNo", reg).getSingleResult();
            if(stud.getCourseList() == null){
                stud.setCourseList(new ArrayList<>());
            }          
            return stud;
        }catch(NoResultException e){
            return null;
        }
    }
}
