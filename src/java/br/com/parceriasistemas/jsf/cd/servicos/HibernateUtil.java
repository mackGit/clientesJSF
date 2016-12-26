/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.servicos;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Mack
 */
/*
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
*/

public class HibernateUtil {
   private static final SessionFactory sessionFactory;
   // O bloco static e executado somente ao criar a primeira instancia
   static {
      try {
         // Cria uma SessionFactory usando o hibernate.cfg.xml
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
          System.err.println("Erro ao criar a SessionFactory A." + ex);
          throw new ExceptionInInitializerError(ex);
      }
   }
   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }
   public static Session getSession () {
      return sessionFactory.openSession(); 
   }
}

