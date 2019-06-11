package dao;


import java.util.List;

import entity.Licenses;
import entity.PersonsLicenses;

public interface LicenseDao{

	public void persist(Licenses entity);
	
	public void persistPersonLicense(PersonsLicenses entity);
    
    public void update(Licenses entity);
     
    public Licenses findById(Integer id);
    
    public Licenses findByType(String type);
     
    public void delete(Licenses entity);
     
    public List<Licenses> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
}
