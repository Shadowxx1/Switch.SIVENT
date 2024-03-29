

package com.jswitch.base.modelo;

import com.jswitch.auditoria.modelo.AuditLogInterceptor;
import com.jswitch.base.controlador.logger.LoggerUtil;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionFactoryImpl;

/**
 * Hibernate Utility class with a convenient method 
 * to get Session Factory object.
 * @author Nelson Moncada
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final SessionFactory sessionFactorySinIntercertor;
    private static final AnnotationConfiguration annotationConfiguration;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            annotationConfiguration = new AnnotationConfiguration()
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.password", Clave.leer().getPassDB())
                    .setProperty("hibernate.connection.username", Clave.leer().getUsuarioDB());
            sessionFactory = new AnnotationConfiguration()
                    .setInterceptor(AuditLogInterceptor.INSTANCE2)
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.password", Clave.leer().getPassDB())
                    .setProperty("hibernate.connection.username", Clave.leer().getUsuarioDB())
                    .buildSessionFactory();
            sessionFactorySinIntercertor = new AnnotationConfiguration()
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.password", Clave.leer().getPassDB())
                    .setProperty("hibernate.connection.username", Clave.leer().getUsuarioDB())
                    .buildSessionFactory();
        } catch (Exception ex) {
            LoggerUtil.error(HibernateUtil.class, "static", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static AnnotationConfiguration getAnnotationConfiguration() {
        return annotationConfiguration;
    }

    public static SessionFactoryImpl getSessionFactoryImpl() {
        return (SessionFactoryImpl) sessionFactory;
    }

    public static SessionFactory getSessionFactorySinIntercertor() {
        return sessionFactorySinIntercertor;
    }
}
