package ru.money.www;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.money.www.entity.*;

import java.awt.print.Paper;

@Configuration
public class HibernateConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addPackage("ru.money.www")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Person.class)
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5448/hibernate")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.username", "hib")
                .setProperty("hibernate.connection.password", "hib")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration.buildSessionFactory();

    }
}
