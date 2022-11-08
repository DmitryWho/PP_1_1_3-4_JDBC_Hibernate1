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
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Users" +
            "(Id INTEGER NOT NULL AUTO_INCREMENT, Name VARCHAR(65) NOT NULL, LastName VARCHAR(65) NOT NULL, Age INT NOT NULL, PRIMARY KEY (ID))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Users";
    private static final String DELETE_USER_BY_ID ="DELETE FROM Users WHERE ID = ?";
    private static final String CLEAN_TABLE ="TRUNCATE TABLE Users";
    private static final SessionFactory SESSION_FACTORY = Util.getSessionFactory();
    Transaction transaction = null;



    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DELETE_USER_BY_ID).executeUpdate();
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
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction= session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}