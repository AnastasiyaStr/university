package DAO;

import entity.Department;
import entity.Lector;
import entity.University;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * class to initialize and return session factory
 */
class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(University.class)
                    .addAnnotatedClass(Lector.class)
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}