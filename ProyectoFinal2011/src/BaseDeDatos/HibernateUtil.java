/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.util.List;
import javax.persistence.Entity;
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

    static {
        try {
            // Create the SessionFactory from standard
            sessionFactory = addClases(new AnnotationConfiguration()).configure().buildSessionFactory();
            session = sessionFactory.openSession();

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
    }

    public static List ejecutarConsulta(String HQL) {
        List salida;
        session.beginTransaction();
        salida = session.createQuery(HQL).list();
        session.getTransaction().commit();

        return salida;
    }
}
