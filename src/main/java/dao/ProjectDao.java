package dao;


import java.util.List;

import entity.Projects;

public interface ProjectDao{

	public void persist(Projects entity);
    
    public void update(Projects entity);
     
    public Projects findById(Integer id);
    
    public List<Projects> findByName(String name);
     
    public void delete(Projects entity);
     
    public List<Projects> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
}
