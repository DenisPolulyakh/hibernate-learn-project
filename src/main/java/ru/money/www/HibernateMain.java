package ru.money.www;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.money.www.entity.Student;
import ru.money.www.service.StudentService;


public class HibernateMain {



    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.money.www");
        StudentService studentService = context.getBean(StudentService.class);

        Student student = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);

        studentService.save(student);
        studentService.save(student2);

        studentService.deleteStudent(1L);



    }
}
