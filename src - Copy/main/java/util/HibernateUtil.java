package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

	private static final SessionFactory sessionFactory;
	private static final ThreadLocal threadSession = new ThreadLocal();
	private static final ThreadLocal threadTransaction = new ThreadLocal();
	static {
		try {
			Configuration cfg = new Configuration();
			sessionFactory = cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() {
		Session s = (Session) threadSession.get();
		try {
			if (s == null) {
				s = sessionFactory.openSession();
				threadSession.set(s);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return s;
	}

	public static void closeSession() {
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen())
				s.close();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	public static void beginTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	public static void commitTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx != null)
				tx.commit();
			threadTransaction.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			throw new InfrastructureException(ex);
		}
	}

	public static void rollbackTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null ) {
				tx.rollback();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
	}
	
	public static void shutdown() {
        // Close caches and connection pools
		sessionFactory.close();
    }
}
