package dbService;

import dbService.dao.UsersDAO;
import dbService.datasets.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by turge on 007 07.12.16.
 */
public class DBService {
    // отображение SQL-запросов
    public static final String hibernate_show_sql = "true";
    // режим работы со схемами базы данных
    public static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    // конфигурация Hibernate
    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hiberante.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    // чтение пользователя по имени
    public UserDataSet getUser(String name) {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        UserDataSet user = dao.getByName(name);
        session.close();
        return user;
    }

    // добавление пользователя в базу
    public void addUser(String name, String password) {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        dao.insertUser(name, password);
        session.close();
    }

    public static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
