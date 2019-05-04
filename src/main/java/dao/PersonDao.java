package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Certificates;
import entity.Participations;
import entity.Persons;
import entity.PersonsLicenses;

public interface PersonDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Persons entity);
    
    public void update(Persons entity);
     
    public Persons findById(Integer id);
    
    public List<Persons> findByName(String name);
     
    public void delete(Persons entity);
     
    public List<Persons> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
    
    public PersonsLicenses findLicenseInfo(Integer personId, Integer licenseId);
    
    public Certificates findTrainingInfo(Integer personId,Integer trainingId);
    
    public Participations findEventInfo(Integer personId,Integer eventId);
}
