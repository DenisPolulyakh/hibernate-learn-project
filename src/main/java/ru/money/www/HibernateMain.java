package ru.money.www;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.money.www.entity.Student;



public class HibernateMain {

    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.money.www");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Student student = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);
        session.beginTransaction();
        session.persist(student);
        session.persist(student2);
        session.getTransaction().commit();

        Student studentById1 = session.find(Student.class,1L);
        log.info("Студент : {}", studentById1);
        Student studentById2 = session.createQuery("select s from Student s where s.id = :id", Student.class).setParameter("id", 2).getSingleResult();
        log.info("Студент : {}", studentById2.toString());
        session.close();
    }
}
