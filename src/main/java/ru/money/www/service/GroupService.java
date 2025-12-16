package ru.money.www.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.money.www.entity.Group;

import java.util.List;

@Service
public class GroupService {

    private final SessionFactory sessionFactory;
    private final TransactionHelperService transactionHelperService;

    public GroupService(SessionFactory sessionFactory, TransactionHelperService transactionHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionHelperService = transactionHelperService;
    }


    public Group saveGroup(String number, Long graduationYear) {
        return transactionHelperService.executeInTransaction(session -> {
            var group = new Group(number, graduationYear);
            session.persist(group);
            return group;
        });
    }


    public List<Group> findAll() {
        try (var session = sessionFactory.openSession()) {

            List<Group> groups = session.createQuery("select g from Group g left join fetch g.studentList s " +
                    "left join fetch s.profile", Group.class).list();
            return groups;
        }

    }


}


