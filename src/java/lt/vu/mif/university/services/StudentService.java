/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university.services;

import javax.ejb.Stateless;
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
@Stateless
public class StudentService {
    
    @PersistenceContext(type= PersistenceContextType.TRANSACTION ,synchronization=SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    public void createStudent(Student student) {
        em.persist(student);
    }
    
    public Student selectStudent(String reg){
        try{
            return (Student)em.createNamedQuery("Student.findByRegistrationNo").setParameter("registrationNo", reg).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
