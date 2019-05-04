package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Maintenances;

public interface MaintenanceDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Maintenances entity);
    
    public void update(Maintenances entity);
     
    public void delete(Maintenances entity);
    
    public void deleteById(Integer id);
    
    public int deleteAllDetailsByMaintenanceId(Integer id);
    
    public Maintenances findById(Integer id);
     
    public List<Maintenances> findAll();
    
    public List<Maintenances> findByName(String name);
     
}
