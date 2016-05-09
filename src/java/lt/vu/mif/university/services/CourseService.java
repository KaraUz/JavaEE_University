/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import lt.vu.mif.university.entities.Course;

/**
 *
 * @author Karolis
 */
@Stateless
public class CourseService {
        
    @PersistenceContext(type= PersistenceContextType.TRANSACTION ,synchronization=SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    public void createCourse(Course course) {
        em.persist(this);
    }
}
