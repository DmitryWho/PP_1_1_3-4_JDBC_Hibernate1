package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "Name1", (byte) 20);
        userDao.saveUser("Name2", "Name2", (byte) 25);
        userDao.saveUser("Name3", "Name3", (byte) 31);
        userDao.saveUser("Name4", "Name4", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}