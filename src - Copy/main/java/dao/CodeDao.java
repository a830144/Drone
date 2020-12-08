package dao;

import java.util.List;

import entity.Code;

public interface CodeDao{

	public void persist(Code entity);
    
    public void update(Code entity);
    
    public Code findById(Integer id);
    
    public List<Code> findByType(String type);
    
    public List<Code> findAll();
     
}
