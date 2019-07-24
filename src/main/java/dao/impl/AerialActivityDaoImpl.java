package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.AerialActivityDao;
import entity.AerialActivities;
import entity.RealMissions;

@Repository
public class AerialActivityDaoImpl implements AerialActivityDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persist(AerialActivities entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}
	
	@Override
	public void persistRealMission(RealMissions entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);		
	}

	@Override
	public void update(AerialActivities entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.update(entity);	
	}

	@Override
	public AerialActivities findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		AerialActivities aerialPlans = session.get(AerialActivities.class, id);
		session.detach(aerialPlans);
        return aerialPlans; 
	}



	@Override
	public List<AerialActivities> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AerialActivities> findByProjectId(Integer projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM AerialActivities as E where E.aerialPlans.projects.projectId = :searchField";
		Query<AerialActivities> query = session.createQuery(hql); 
		query.setParameter("searchField",projectId);
		
		List<AerialActivities> results = query.list();
        for(int i=0;i<results.size();i++){
        	AerialActivities entity = results.get(i);
        	session.detach(entity);
        }
        return results;
	}

	@Override
	public List<Object[]> findRealMission(Integer aerialActivityId) {
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
		query.setParameter("aerialActivityId", aerialActivityId);
		List<Object[]> results = query.list();
		return results;
	}

}
