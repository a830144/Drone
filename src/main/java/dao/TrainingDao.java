package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Trainings;

public interface TrainingDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Trainings entity);
    
    public void update(Trainings entity);
     
    public Trainings findById(Integer id);
    
    public Trainings findByName(String name);
    
    public List<Trainings> findByNameWildcard(String name);
     
    public void delete(Trainings entity);
     
    public List<Trainings> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
}
