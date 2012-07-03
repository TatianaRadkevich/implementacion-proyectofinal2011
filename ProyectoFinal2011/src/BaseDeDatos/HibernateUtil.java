/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
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
    private static Session sessionQuery;

    static {
        try {
            // Create the SessionFactory from standard
            sessionFactory = addClases(new AnnotationConfiguration()).configure().buildSessionFactory();
            session = sessionFactory.openSession();
            sessionQuery = sessionFactory.openSession();
            session.setFlushMode(FlushMode.COMMIT);
            sessionQuery.setFlushMode(FlushMode.MANUAL);            

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

    public static void guardarModificarObjeto(Object o) {
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static Object getObjeto(Class type, Serializable srlzbl) {
        Object salida;
        session.beginTransaction();
        salida = session.get(type, srlzbl);
        session.getTransaction().commit();
        return salida;
    }

    public static void modificarObjeto(Object o) {

        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static void EliminarObjeto(Object o) {
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.flush();
    }

    public static List ejecutarConsulta(String HQL) {
          Query q=sessionQuery.createQuery(HQL);         
          return q.list();
    }
}
