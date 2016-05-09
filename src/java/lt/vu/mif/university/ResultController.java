/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.vu.mif.university.entities.Student;
import lt.vu.mif.university.entities.University;
import lt.vu.mif.university.services.CourseService;
import lt.vu.mif.university.services.UniversityService;

/**
 *
 * @author Karolis
 */
@Named
@Stateless
public class ResultController {
    @PersistenceContext
    private EntityManager em;
    @Inject 
    private UniversityService universityService;
    
    public String retrieveUniversity() {
        String universityTitle =  (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("universityTitle");
        University uni = universityService.selectUniversity(universityTitle);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("id = %d, title = %s", uni.getId(),uni.getTitle()));
        for(Student stud:uni.getStudentList()){
            builder.append(String.format("Vardas: %s, Pavardė: %s, reg: %s", stud.getFirstName(),stud.getLastName(),stud.getRegistrationNo()));
        }
        return builder.toString();
    }

}
