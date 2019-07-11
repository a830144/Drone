package dao;


import java.util.List;

import entity.AerialActivities;
import entity.RealMissions;

public interface AerialActivityDao{

	public void persist(AerialActivities entity);
    
    public void update(AerialActivities entity);
     
    public AerialActivities findById(Integer id);
    
    public List<AerialActivities> findByName(String name);
    
    public List<AerialActivities> findByProjectId(Integer projectId);
    
    public void persistRealMission(RealMissions entity);
}
