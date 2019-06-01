package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Events;
import entity.Participations;

public interface EventDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Events entity);
	
	public void persistParticipation(Participations entity);
    
    public void update(Events entity);
     
    public Events findById(Integer id);
    
    public Events findByName(String name);
    
    public List<Events> findByNameWildcard(String name);
     
    public void delete(Events entity);
     
    public List<Events> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
}
