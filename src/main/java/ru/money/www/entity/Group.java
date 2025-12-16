package ru.money.www.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "number")
    private String number;


    @Column(name = "graduation_year")
    private Long graduationYear;


    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> studentList;

    public Group() {
    }

    public Group(String number, Long graduationYear) {
        this.number = number;
        this.graduationYear = graduationYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Long graduationYear) {
        this.graduationYear = graduationYear;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
