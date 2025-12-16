package ru.money.www.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.money.www.entity.Student;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionHelperService transactionHelperService;

    public StudentService(SessionFactory sessionFactory, TransactionHelperService transactionHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionHelperService = transactionHelperService;
    }


    public Student save(Student student) {
        return transactionHelperService.executeInTransaction(session -> {
            session.persist(student);
            return student;
        });

    }

    public void deleteStudent(Long id) {
        transactionHelperService.executeInTransaction(session -> {
            var s = session.find(Student.class, id);
            session.remove(s);
        });


    }

    public Student getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Student.class, id);
        }
    }

    public List<Student> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select s from Student s", Student.class).list();
        }
    }

    public Student updateStudent(Student student) {
        return transactionHelperService.executeInTransaction(session -> {
            session.merge(student);
            return student;
        });
    }
}
