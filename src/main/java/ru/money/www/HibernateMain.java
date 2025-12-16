package ru.money.www;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.money.www.entity.*;
import ru.money.www.service.CourseService;
import ru.money.www.service.GroupService;
import ru.money.www.service.ProfileService;
import ru.money.www.service.StudentService;

import java.util.List;


public class HibernateMain {


    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.money.www");
        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        CourseService courseService = context.getBean(CourseService.class);

      /*  Student student = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);

        student = studentService.save(student);
        studentService.save(student2);

        Profile profile1= new Profile("Bio description", LocalDateTime.now(), student);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        profileService.saveProfile(profile1);
        session.getTransaction().commit();
        student = session.find(Student.class, 1L);
        session.beginTransaction();
        profileService.deleteProfile(student.getProfile().getId());
        studentService.deleteStudent(student.getId());
        session.getTransaction().commit();
        session.close();*/


        // Group group1 = groupService.saveGroup("1", 2024L);
        //Group group2 = groupService.saveGroup("2", 2025L);
        //Group group3 = groupService.saveGroup("3", 2025L);


       /* Student student1 = new Student("Vasya", 22, group1);
        Student student2 = new Student("Pasha", 20, group1);

        studentService.save(student1);
        studentService.save(student2);*/

       /* try(Session session = sessionFactory.openSession()) {
            group1 = session.find(Group.class, 1L);

        }*/


        // Проблема N+1
        //List<Group> groupList = groupService.findAll();
        //List<Student> studentList = groupList.get(0).getStudentList();
        //int k=0;


        Course course1 = new Course("math-1", "math");
        Course course2 = new Course("math-2", "math");
        Course course3 = new Course("math-3", "math");


        /*courseService.saveCourse(course1);
        courseService.saveCourse(course2);
        courseService.saveCourse(course3);*/


        courseService.enrollStudentToCourseSql(2L, 2L);
        courseService.enrollStudentToCourseSql(3L, 2L);

        Student student = studentService.getById(2L);
        System.out.println(student);


        Person person = new Person();
        person.setGender(Gender.MALE);
        List<String> phones = List.of("928345", "89234567896");
        Address address = new Address();
        address.setCity("Saratov");
        address.setStreet("Moskovskaya");
        Address address2 = new Address();
        address2.setCity("Saratov");
        address2.setStreet("Piterskaya");
        person.setAddresses(List.of(address, address2));
        person.setPhones(phones);
        Address address3 = new Address();
        address3.setCity("Saratov1");
        address3.setStreet("Moskovskaya2");
        person.setAddress(address3);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(person);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        person = session.find(Person.class, person.getId());
        session.remove(person);
        session.getTransaction().commit();
        session.close();


    }
}
