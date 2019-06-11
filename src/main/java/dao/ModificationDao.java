package dao;


import java.util.List;

import entity.Modifications;

public interface ModificationDao{

	public void persist(Modifications entity);
    
    public void update(Modifications entity);
     
    public void delete(Modifications entity);
    
    public void deleteById(Integer id);
    
    public int deleteAllDetailsByMaintenanceId(Integer id);
    
    public Modifications findById(Integer id);
     
    public List<Modifications> findAll();
    
    public List<Modifications> findByName(String name);
     
}
