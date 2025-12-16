package ru.money.www.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.money.www.entity.Profile;

import java.util.List;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;
    private final TransactionHelperService transactionHelperService;

    public ProfileService(SessionFactory sessionFactory, TransactionHelperService transactionHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionHelperService = transactionHelperService;
    }

    public Profile saveProfile(Profile profile) {
        return transactionHelperService.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }

    public void deleteProfile(Long id) {
        transactionHelperService.executeInTransaction(session -> {
                    Profile profile = session.find(Profile.class, id);
                    session.remove(profile);
                }
        );
    }

    public Profile getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Profile.class, id);
        }
    }

    public List<Profile> getAll() {
        Session session = sessionFactory.openSession();
        List<Profile> profileList = session.createQuery("select s from Profile s", Profile.class).list();
        session.close();
        return profileList;
    }

    public Profile updateProfile(Profile profile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        profile = session.merge(profile);
        session.getTransaction().commit();
        session.close();
        return profile;

    }


}
