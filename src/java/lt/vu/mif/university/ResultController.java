/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.vu.mif.university.entities.University;
import lt.vu.mif.university.services.UniversityService;

/**
 *
 * @author Karolis
 */
@Named
@Stateful
@RequestScoped
public class ResultController {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private UniversityService universityService;

    private University uni;

    @PostConstruct
    public void init() {
        String universityTitle = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("universityTitle");
        uni = universityService.selectUniversity(universityTitle);
    }

    public University getUni() {
        return uni;
    }
}
