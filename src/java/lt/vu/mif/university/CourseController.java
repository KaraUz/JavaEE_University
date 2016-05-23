/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.university;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import lt.vu.mif.university.entities.Course;
import lt.vu.mif.university.entities.Student;
import lt.vu.mif.university.services.CourseService;
import lt.vu.mif.university.services.StudentService;

/**
 *
 * @author Karolis
 */
@Named
@ConversationScoped
@Stateful
public class CourseController {

    String INDEX = "/index?faces-redirect=true";
    String REGISTER_STUDENT = "/conversation/registerStudent";
    String CONFIRM = "/conversation/confirmCourseStudent";
    String RESULT = "/conversation/courseList?faces-redirect=true";

    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    private Conversation conversation;

    @Inject
    private StudentService studentService;

    @Inject
    private CourseService courseService;

    private Course course;
    private Student student;

    @PostConstruct
    public void init() {
        course = new Course();
        student = new Student();
    }

    public String createCourse() {
        if (!conversation.isTransient()) {
            conversation.end();
            return INDEX;
        }

        conversation.begin();
        Course tmpCourse = courseService.selectCourse(course.getName());
        if(tmpCourse == null){
            course = courseService.createCourse(course);
        }else{
            course = tmpCourse;
        }
            
        return REGISTER_STUDENT;
    }

    public String createStudent() {
        Student tmpStudent = studentService.selectStudent(student.getRegistrationNo());
        if(tmpStudent == null){
            student = studentService.createStudent(student);
        }else{
            student=tmpStudent;
        }
        return CONFIRM;
    }

    public String confirm() {
        try {
            em.joinTransaction();
            course.getStudentList().add(student);
            student.getCourseList().add(course);
            em.flush(); // try to catch DB problems
            conversation.end();
            return RESULT;
        } catch (OptimisticLockException ole) {
            // Kažkas kitas buvo greitesnis...
            System.out.println("OptimistiLocking problem: " + ole.getMessage());
            return null;
        }
    }

    public String cancel() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return INDEX;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
