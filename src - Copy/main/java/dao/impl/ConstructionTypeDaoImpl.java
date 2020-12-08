package dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.ConstructionTypeDao;
import entity.Aircrafts;
import entity.Helicopters;
import entity.MultiRotors;

public class ConstructionTypeDaoImpl implements ConstructionTypeDao {
	
	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void persistAircrafts(Aircrafts entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}

	@Override
	public void persistHelicopters(Helicopters entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}

	@Override
	public void persistMultiRotors(MultiRotors entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}

	

	@Override
	public Aircrafts findAircraftById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Aircrafts aircrafts = session.get(Aircrafts.class, id);
        return aircrafts;
	}

	@Override
	public Helicopters findHelicoptersById(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Helicopters helicopters = session.get(Helicopters.class, id);
        return helicopters;
	}

	@Override
	public MultiRotors findMultiRotorsById(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		MultiRotors multiRotors = session.get(MultiRotors.class, id);
        return multiRotors;
	}

}
