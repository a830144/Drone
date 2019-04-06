package service.impl;

import java.util.List;

import entity.Persons;
import service.PersonService;

public abstract class PersonServiceDecorator implements PersonService{

	public PersonServiceDecorator(PersonService personService) {
		super();
		this.personService = personService;
	}

	private PersonService personService;

	@Override
	public void persist(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePerson(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Persons entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persons> queryPersons(String ename) {		

		return personService.queryPersons(ename);
	}

}
