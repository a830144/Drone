package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
 
public class HibernateSessionFactoryListener implements ServletContextListener {
     
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("\n\tInside contextInitialized()\n");
        HibernateUtil.beginTransaction();
        Session session = HibernateUtil.getSession();
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("\n\tInside contextDestroyed()\n");
        HibernateUtil.shutdown();       
    }
}