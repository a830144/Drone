package dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.PersonDao;
import entity.Certificates;
import entity.Participations;
import entity.Persons;
import entity.PersonsLicenses;

@Repository
public class PersonDaoImpl implements PersonDao{
   
	@Override
	public PersonsLicenses findLicenseInfo(Integer targetId) {
		Session session = this.sessionFactory.getCurrentSession();
		PersonsLicenses personsLicenses = session.get(PersonsLicenses.class, targetId);
        return personsLicenses; 
	}
	@Override
	public Certificates findTrainingInfo(Integer targetId) {
		Session session = this.sessionFactory.getCurrentSession();
		Certificates certificates = session.get(Certificates.class, targetId);
        return certificates;
	}
	@Override
	public Participations findEventInfo(Integer targetId) {
		Session session = this.sessionFactory.getCurrentSession();
		Participations participations = session.get(Participations.class, targetId);
        return participations;
	}
	@Autowired
	private SessionFactory sessionFactory;
	
    @Override
    public void persist(Persons entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Persons entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Persons findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Persons Persons = session.get(Persons.class, id);
        return Persons; 
    }
    @Override
    public void delete(Persons entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Persons> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Persons> persons = (List<Persons>) session.createQuery("from Persons").list();
        for(int i=0;i<persons.size();i++){
        	Persons entity = persons.get(i);
        	session.detach(entity);
        }
        return persons;
    }
    @Override
    public void deleteAll() {
        List<Persons> entityList = findAll();
        for (Persons entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public List<Persons> findByName(String name) {
		String hql = "FROM Persons as E where E.name like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Persons> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+name+"%");
		
		List<Persons> results = query.list();
		return results;
	}
	@Override
	public void deleteById(Integer id) {
		Persons Persons = findById(id);
		delete(Persons);
	}
	
	@Override
	public List<Object[]> findLicenseDetail(Integer personId) {
		// TODO
		String sql = "SELECT pl.State,l.License_ID,pl.got_Date,c.Code_Content,pl.Persons_Licenses_ID,pl.Construction_Type,"
				+ " (SELECT code_content FROM drone.code where code.code = pl.Construction_Type"
                + "  AND code.Code_Type = 'Construction_type') as Construction_Type_name"
				+ " FROM drone.persons p,drone.persons_licenses pl, drone.licenses l, drone.code c"
				+ " WHERE p.person_id = pl.Person_ID"
				+ " and pl.License_ID = l.License_ID" 
				+ " and l.type = c.code"
				+ " and c.Code_Type = 'License_type'" 
				+ " and p.person_id =:personId"  ;
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("personId", personId);
		List<Object[]> results = query.list();
		return results;
		
		
		
	}

	@Override
	public PersonsLicenses findLicenseInfo(Integer personId, Integer licenseId) {
		String hql = "FROM PersonsLicenses as E where E.persons.personId = :searchField1 and E.licenses.licenseId = :searchField2 ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<PersonsLicenses> query = session.createQuery(hql); 
		query.setParameter("searchField1",personId);
		query.setParameter("searchField2",licenseId);
		
		List<PersonsLicenses> results = query.list();
		return results.get(0);
		
	}
	@Override
	public Certificates findTrainingInfo(Integer personId, Integer trainingId) {
		String hql = "FROM Certificates as E where E.persons.personId = :searchField1 and E.trainings.trainingId = :searchField2 ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Certificates> query = session.createQuery(hql); 
		query.setParameter("searchField1",personId);
		query.setParameter("searchField2",trainingId);
		
		List<Certificates> results = query.list();
		return results.get(0);
	}
	@Override
	public Participations findEventInfo(Integer personId, Integer eventId) {
		String hql = "FROM Participations as E where E.persons.personId = :searchField1 and E.events.eventId = :searchField2 ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Participations> query = session.createQuery(hql); 
		query.setParameter("searchField1",personId);
		query.setParameter("searchField2",eventId);
		
		List<Participations> results = query.list();
		return results.get(0);
	}
	@Override
	public List<Persons> findPersonWithLicense(String constructionType,Set<String> type) {
		List<Persons> results;
		if (type == null) {
			String hql = "SELECT distinct P FROM entity.Persons P join P.personsLicenseses PL "
					+ "WHERE PL.constructionType = :constructionType " ;
			Session session = this.sessionFactory.getCurrentSession();
			Query<Persons> query = session.createQuery(hql);
			query.setParameter("constructionType", constructionType);
			results = query.list();
		} else {
			String hql = "SELECT distinct P FROM entity.Persons P join P.personsLicenseses PL "
					+ "WHERE  PL.licenses.type in (:searchField1) " 
					+ "AND PL.constructionType = :constructionType";
			Session session = this.sessionFactory.getCurrentSession();
			Query<Persons> query = session.createQuery(hql);
			query.setParameterList("searchField1", type);
			query.setParameter("constructionType", constructionType);
			results = query.list();
		}
		return results;
	}
	@Override
	public void updatePersonsLicenses(PersonsLicenses entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
		
	}
	@Override
	public void updateParticipation(Participations entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
		
	}
	@Override
	public void updateCertificate(Certificates entity) {
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);		
	}
	@Override
	public List<Persons> findByMission(Integer aerialPlanId, Integer equipmentId) {
		String hql = "select distinct M.persons FROM Missions M where M.aerialPlans.aerialPlanId="+aerialPlanId+" and M.equipments.equipmentId="+equipmentId;
		Session session = this.sessionFactory.getCurrentSession();
		Query<Persons> query = session.createQuery(hql);
		List<Persons> results = query.list();
		
		return results;
	}
	
}
