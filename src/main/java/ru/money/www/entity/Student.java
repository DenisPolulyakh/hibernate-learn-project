package ru.money.www.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "students_name", unique = true, nullable = false)
    private String name;

    private Integer age;

    @OneToOne(mappedBy = "student")
    private Profile profile;


    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id")
    )
    private List<Course> courses = new ArrayList<>();


    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }



    public Student(String name, Integer age, Group group) {
        this.name = name;
        this.age = age;
        this.group = group;

    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
