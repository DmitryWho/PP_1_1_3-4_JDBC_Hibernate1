package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.hibernate.cfg.Configuration;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/?user=root";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "85502424Ald";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        Properties property = new Properties();
        property.setProperty("hibernate.connection.url", HOST);
        property.setProperty("hibernate.connection.username", LOGIN);
        property.setProperty("hibernate.connection.password", PASSWORD);
        property.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        property.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactory = new Configuration()
                .addProperties(property)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}
