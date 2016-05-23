/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university.services;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import lt.vu.mif.university.entities.University;

/**
 *
 * @author Karolis
 */
@Stateless
public class UniversityService {
            
    @PersistenceContext
    private EntityManager em;
    
    public University createUniversity(String universityTitle) {
        University uni = new University();
        uni.setTitle(universityTitle);
        uni.setStudentList(new ArrayList<>());
        em.persist(uni);
        return uni;
    }
    
    public University selectUniversity(String title){
        System.out.println("title:"+title);
        try{
            return (University)em.createNamedQuery("University.findByTitle").setParameter("title", title).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
