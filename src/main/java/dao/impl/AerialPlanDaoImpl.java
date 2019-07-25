package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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

	/**
	 * Here we need to adjust and rethink about the logic of
	 * mission,aerialactivity,equipment-->persons own the license to drive this kind of equipment
	 */
	@Override
	public List<Object[]> findMission(Integer aerialPlanId) {
		//TODO 
		String sql = 
				"SELECT rm.Equipment_ID,rm.Person_ID,GROUP_CONCAT(mi.person_id)"
				+ " FROM drone.real_missions rm ,drone.aerial_activities ac ,drone.aerial_plans ap, drone.missions mi"
				+ " WHERE rm.Aerial_Activity_ID = ac.Aerial_Activity_ID"
				+ " AND ac.Aerial_Plan_ID = ap.Aerial_Plan_ID"
				+ " AND  mi.Aerial_Plan_ID = ap.Aerial_Plan_ID"
				+ " AND mi.equipment_ID = rm.Equipment_ID"
				+ " AND rm.Aerial_Activity_ID=:aerialActivityId"
				+ " group by rm.equipment_id,rm.person_id";
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("aerialActivityId", aerialPlanId);
		List<Object[]> results = query.list();
		return results;
	}

}
