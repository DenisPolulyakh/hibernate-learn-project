package ru.money.www.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class TransactionHelperService {

    Logger log = LoggerFactory.getLogger(TransactionHelperService.class);
    private final SessionFactory sessionFactory;

    public TransactionHelperService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public <T> T executeInTransaction(Function<Session, T> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            var t = action.apply(session);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
