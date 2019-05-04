package dao;


import java.util.List;

import org.hibernate.SessionFactory;

import entity.Code;

public interface CodeDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persist(Code entity);
    
    public void update(Code entity);
    
    public Code findById(Integer id);
    
    public List<Code> findByType(String type);
    
    public List<Code> findAll();
     
}
