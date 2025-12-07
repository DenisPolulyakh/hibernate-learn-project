package ru.money.www.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.money.www.entity.Student;
import java.util.*;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Student save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public void deleteStudent(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.find(Student.class, id);
        session.remove(student);
        session.getTransaction().commit();
        session.close();
    }

    public Student getById(Long id) {
        Session session = sessionFactory.openSession();
        Student student = session.find(Student.class, id);
        session.close();
        return student;
    }

    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        List<Student> studentList = session.createQuery("select s from Student s", Student.class).list();
        session.close();
        return studentList;
    }

    public Student updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        student = session.merge(student);
        session.getTransaction().commit();
        session.close();
        return student;

    }
}
