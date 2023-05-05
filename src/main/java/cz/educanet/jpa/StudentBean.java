package cz.educanet.jpa;

import cz.educanet.jpa.entities.StudentEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class StudentBean {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentsTest");
    public void createStudent(String name, String birthday, int avgMark){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setBirthday(birthday);
        student.setAvgMark(avgMark);
        em.persist(student);

        et.commit();
        em.close();


    }
}
