package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.AerialPlanDao;
import entity.AerialPlans;
import entity.Missions;

@Repository
public class AerialPlanDaoImpl implements AerialPlanDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persist(AerialPlans entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}

	@Override
	public void update(AerialPlans entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.update(entity);	
	}

	@Override
	public AerialPlans findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		AerialPlans aerialPlans = session.get(AerialPlans.class, id);
		session.detach(aerialPlans);
        return aerialPlans; 
	}



	@Override
	public List<AerialPlans> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AerialPlans> findByProjectId(Integer projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM AerialPlans as E where E.projects.projectId = :searchField";
		Query<AerialPlans> query = session.createQuery(hql); 
		query.setParameter("searchField",projectId);		
		List<AerialPlans> results = query.list();
        for(int i=0;i<results.size();i++){
        	AerialPlans entity = results.get(i);
        	session.detach(entity);
        }
        return results;
	}



	@Override
	public void delete(AerialPlans entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<AerialPlans> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persistMission(Missions entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);		
	}

}
