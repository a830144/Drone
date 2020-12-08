package dao;


import java.util.List;

import entity.Maintenances;

public interface MaintenanceDao{

	public void persist(Maintenances entity);
    
    public void update(Maintenances entity);
     
    public void delete(Maintenances entity);
    
    public void deleteById(Integer id);
    
    public int deleteAllDetailsByMaintenanceId(Integer id);
    
    public Maintenances findById(Integer id);
     
    public List<Maintenances> findAll();
    
    public List<Maintenances> findByName(String name);
     
}
