package dao;


import java.util.List;

import entity.AerialPlans;
import entity.Missions;

public interface AerialPlanDao{

	public void persist(AerialPlans entity);
    
    public void update(AerialPlans entity);
     
    public AerialPlans findById(Integer id,boolean detach);
    
    public List<AerialPlans> findByName(String name);
     
    public void delete(AerialPlans entity);
     
    public List<AerialPlans> findAll();
     
    public void deleteAll();
    
    public void deleteById(Integer id);
    
    public void persistMission(Missions entity);
    
    public List<AerialPlans> findByProjectId(Integer projectId);
    
    public List<Object[]> findMission(Integer aerialPlanId);
}
