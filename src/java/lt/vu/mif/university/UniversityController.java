/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.vu.mif.university.entities.Student;
import lt.vu.mif.university.entities.University;
import lt.vu.mif.university.services.StudentService;
import lt.vu.mif.university.services.UniversityService;

/**
 *
 * @author Karolis
 */
@Named
@RequestScoped
@Stateful 
public class UniversityController {
    @PersistenceContext
    private EntityManager em;
    @Inject 
    private UniversityService universityService;
    @Inject
    private StudentService studentService;
    
    private String universityTitle;
    private List<Student> Students = new ArrayList<>();

    @PostConstruct
    public void init() {
        Students.add(new Student());
        Students.add(new Student());
        Students.add(new Student());
    }
    
    public String prepareUniversity() {
        if(universityTitle == null || universityTitle.isEmpty()) return "";
        
        em.isOpen(); // Initialize entity cache
        University uni = universityService.selectUniversity(universityTitle);
        if(uni == null) universityService.createUniversity(universityTitle);// em gets propagated
        for(Student stud:Students){
            
            if(stud.getRegistrationNo()==null || stud.getRegistrationNo().isEmpty() || studentService.selectStudent(stud.getRegistrationNo()) != null)continue;
            System.out.println("regno:"+stud.getRegistrationNo());
            stud.setUniversityId(uni);
            studentService.createStudent(stud);
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("universityTitle", universityTitle);
        return "/result";
    }
    
    public String getUniversityTitle() {
        return universityTitle;
    }

    public void setUniversityTitle(String universityTitle) {
        this.universityTitle = universityTitle;
    }

    public List<Student> getStudents() {
        return Students;
    }

    public void setStudents(List<Student> Students) {
        this.Students = Students;
    }
}
