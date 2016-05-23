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
    private Student student;

    @PostConstruct
    public void init() {
        student = new Student();
    }

    public String prepareUniversity() {
        if (universityTitle == null || universityTitle.isEmpty()) {
            return "";
        }

        em.isOpen(); // Initialize entity cache
        University uni = universityService.selectUniversity(universityTitle);

        if (uni == null) {
            uni = universityService.createUniversity(universityTitle);// em gets propagated
        }
        student.setUniversityId(uni);
        student = studentService.createStudent(student);
        uni.getStudentList().add(student);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("universityTitle", universityTitle);
        return "/result?faces-redirect=true";
    }

    public String getUniversityTitle() {
        return universityTitle;
    }

    public void setUniversityTitle(String universityTitle) {
        this.universityTitle = universityTitle;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
