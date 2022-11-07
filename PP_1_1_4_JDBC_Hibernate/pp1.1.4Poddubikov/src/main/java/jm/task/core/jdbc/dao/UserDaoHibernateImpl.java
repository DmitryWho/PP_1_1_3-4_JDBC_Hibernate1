package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserDaoHibernateImpl implements UserDao {
    private static final String createTable = "CREATE TABLE IF NOT EXISTS Users" +
            "(Id INTEGER NOT NULL AUTO_INCREMENT, Name VARCHAR(65) NOT NULL, LastName VARCHAR(65) NOT NULL, Age INT NOT NULL, PRIMARY KEY (ID))";
    private static final String dropTable = "DROP TABLE IF EXISTS Users";
    private static final String deleteUserById ="DELETE FROM Users WHERE ID = ?";
    private static final String cleanTable ="TRUNCATE TABLE Users";
    private static final SessionFactory SESSIONFACTORY = Util.getSessionFactory();
    Transaction transaction = null;



    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(deleteUserById).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List users = new ArrayList<>();
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction= session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(cleanTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}