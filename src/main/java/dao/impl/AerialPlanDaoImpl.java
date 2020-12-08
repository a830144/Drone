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
	public AerialPlans findById(Integer id,boolean detach) {
		Session session = this.sessionFactory.getCurrentSession();
		AerialPlans aerialPlans = session.get(AerialPlans.class, id);
		if(detach)session.detach(aerialPlans);
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
				"SELECT t1.equipment_id,t1.person_id, GROUP_CONCAT(t2.Person_ID)"
				+ " FROM"
				+ " (SELECT eq.equipment_id,eq.construction_type,mi.person_id"
				+ " 	FROM missions mi"
				+ " 	INNER JOIN aerial_plans ap  ON ap.Aerial_Plan_ID = mi.Aerial_Plan_ID"
				+ " 	INNER JOIN equipments eq ON mi.equipment_id = eq.equipment_id"
				+ " 	WHERE ap.Aerial_PLAN_ID =:aerialPlanId) t1,"
				+ " (SELECT DISTINCT eq.Construction_Type,p.Person_ID"				
				+ " 	FROM persons p"
				+ "		INNER JOIN persons_licenses pl ON p.person_id = pl.person_id"
				+ " 	INNER JOIN equipments eq ON pl.construction_type = eq.Construction_Type) t2"
				+ " WHERE t1.construction_type = t2.construction_type"
				+ " GROUP BY t1.equipment_id,t1.person_id";
		System.out.println("sql::"+sql);
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("aerialPlanId", aerialPlanId);
		List<Object[]> results = query.list();
		return results;
	}

}
