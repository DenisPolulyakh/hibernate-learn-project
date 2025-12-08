package ru.money.www;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.money.www.entity.Profile;
import ru.money.www.entity.Student;
import ru.money.www.service.StudentService;

import java.time.LocalDateTime;


public class HibernateMain {


    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.money.www");
        StudentService studentService = context.getBean(StudentService.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Student student = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);

        student = studentService.save(student);
        studentService.save(student2);

        Profile profile1= new Profile("Bio description", LocalDateTime.now(), student);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(profile1);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();

       // profile1 = session.find(Profile.class, 1L);

        student = session.find(Student.class, 1L);
        //log.info(profile1.toString());
        session.beginTransaction();
        session.remove(student);
        session.getTransaction().commit();
        session.close();

    }
}
