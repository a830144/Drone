package dao;


import java.util.List;
import java.util.Set;

import entity.Certificates;
import entity.Participations;
import entity.PersonsLicenses;
import entity.Persons;

public interface PersonDao{

	public void persist(Persons entity);
    
    public void update(Persons entity);
    
    public void updatePersonsLicenses(PersonsLicenses entity);
    
    public void updateParticipation(Participations entity);
    
    public void updateCertificate(Certificates entity);
     
    public Persons findById(Integer id);
     
    public void delete(Persons entity);
          
    public void deleteAll();
    
    public void deleteById(Integer id);
    
    public PersonsLicenses findLicenseInfo(Integer personId, Integer licenseId);
    
    public PersonsLicenses findLicenseInfo(Integer targetId);
    
    public Certificates findTrainingInfo(Integer personId,Integer trainingId);
    
    public Certificates findTrainingInfo(Integer targetId);
    
    public Participations findEventInfo(Integer personId,Integer eventId);
    
    public Participations findEventInfo(Integer targetId);
    
    public List<Persons> findPersonWithLicense(Set<String> type);
    
    public List<Persons> findAll();
    
    public List<Persons> findByName(String name);
    
    public List<Persons> findByMission(Integer aerialPlanId,Integer equipmentId);
}
