package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Licenses;

public interface LicenseDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Licenses entity);
    
    public void update(Licenses entity);
     
    public Licenses findById(Integer id);
    
    public Licenses findByType(String type);
     
    public void delete(Licenses entity);
     
    public List<Licenses> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
}
