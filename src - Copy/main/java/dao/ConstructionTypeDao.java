package dao;


import org.hibernate.SessionFactory;

import entity.Aircrafts;
import entity.Helicopters;
import entity.MultiRotors;

public interface ConstructionTypeDao{
	public void setSessionFactory(SessionFactory sessionFactory);

	public void persistAircrafts(Aircrafts entity);
	public void persistHelicopters(Helicopters entity);
	public void persistMultiRotors(MultiRotors entity);
	public Aircrafts findAircraftById(Integer id);
	public Helicopters findHelicoptersById(Integer id);
	public MultiRotors findMultiRotorsById(Integer id);
     
}
