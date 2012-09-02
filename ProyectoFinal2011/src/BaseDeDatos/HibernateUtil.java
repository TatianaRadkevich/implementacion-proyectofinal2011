/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Rodrigo
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session session;
    private static String user, pass, url;

    static {
        try {
            // Create the SessionFactory from standard
            sessionFactory = addClases(new AnnotationConfiguration()).configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.COMMIT);


            ////////////////////////// obtener propiedades del SessionFactory por reflexion
            Field f = SessionFactoryImpl.class.getDeclaredField("properties");
            f.setAccessible(true);
            Properties p = (Properties) f.get(sessionFactory);
            user = p.getProperty(org.hibernate.cfg.Environment.USER);
            pass = p.getProperty(org.hibernate.cfg.Environment.PASS);
            url = p.getProperty(org.hibernate.cfg.Environment.URL);
            ////////////////////////////

        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static AnnotationConfiguration addClases(AnnotationConfiguration ac) throws Exception {
        //metodo super groso mapea automaticamente todas las clases del proyecto con @entity
        // the following will detect all classes that are annotated as @Entity
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        // only register classes within "com.fooPackage" package
        for (BeanDefinition bd : scanner.findCandidateComponents("")) {
            String name = bd.getBeanClassName();
            // register detected classes with AnnotationSessionFactoryBean
            ac.addAnnotatedClass(Class.forName(name));
        } // for

        return ac;
    }

    public static String getPass() {
        return pass;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static Session getSession() {
        return session;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getNewSession() {
        return sessionFactory.openSession();
    }

    public static void guardarObjeto(Object o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static void guardarObjeto(Session ss, Object o) {
        ss.beginTransaction();
        ss.save(o);
        ss.getTransaction().commit();
        ss.flush();
    }

    public static void guardarModificarObjeto(Object o) {
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static void guardarModificarObjeto(Session ss, Object o) {
        ss.beginTransaction();
        ss.saveOrUpdate(o);
        ss.getTransaction().commit();
        ss.flush();
    }

    public static Object getObjeto(Class type, Serializable srlzbl) {
        Object salida;
        session.beginTransaction();
        salida = session.get(type, srlzbl);
        session.getTransaction().commit();
        return salida;
    }

    public static Object getObjeto(Session ss, Class type, Serializable srlzbl) {
        Object salida;
        ss.beginTransaction();
        salida = ss.get(type, srlzbl);
        ss.getTransaction().commit();
        return salida;
    }

    public static void modificarObjeto(Object o) {

        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static void modificarObjeto(Session ss, Object o) {

        ss.beginTransaction();
        ss.update(o);
        ss.getTransaction().commit();
        ss.flush();
    }

    public static void EliminarObjeto(Object o) {
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static void EliminarObjeto(Session ss, Object o) {
        ss.beginTransaction();
        ss.delete(o);
        ss.getTransaction().commit();
        ss.flush();
    }

    public static List ejecutarConsulta(String HQL) {
        Query q = session.createQuery(HQL);
        return q.list();
    }

    public static List ejecutarConsulta(Session ss, String HQL) {
        Query q = ss.createQuery(HQL);
        return q.list();
    }
}
