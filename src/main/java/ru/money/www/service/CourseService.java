package ru.money.www.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.money.www.entity.Course;
import ru.money.www.entity.Student;

@Service
public class CourseService {

    private final SessionFactory sessionFactory;
    private final TransactionHelperService transactionHelperService;

    public CourseService(SessionFactory sessionFactory, TransactionHelperService transactionHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionHelperService = transactionHelperService;
    }

    public Course saveCourse(Course course) {
        return transactionHelperService.executeInTransaction(session -> {
            session.persist(course);
            return course;
        });
    }


    public void enrollStudentToCourse(Long courseId, Long studentId) {
        transactionHelperService.executeInTransaction(session -> {
            var student = session.find(Student.class, studentId);
            var course = session.find(Course.class, courseId);
            student.getCourses().add(course);
        });
    }


    public void enrollStudentToCourseSql(Long courseId, Long studentId) {
        transactionHelperService.executeInTransaction(session -> {
            String sql = "insert into student_courses(student_id, course_id) values(:studentId, :courseId);";
            session.createNativeQuery(sql, Void.class).setParameter("studentId", studentId).setParameter("courseId", courseId).executeUpdate();
        });
    }

}
