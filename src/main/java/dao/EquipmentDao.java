package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Equipments;

public interface EquipmentDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Equipments entity);
    
    public void update(Equipments entity);
     
    public void delete(Equipments entity);
    
    public void deleteById(Integer id);
    
    public void deleteAll();
    
    public Equipments findById(Integer id);
     
    public List<Equipments> findAll();
    
    public List<Equipments> findByName(String name);
     
}
