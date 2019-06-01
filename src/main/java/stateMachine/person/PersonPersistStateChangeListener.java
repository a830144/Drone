package stateMachine.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.PersonDao;
import entity.Persons;
import stateMachine.States;
import util.EntityConstants;

@Component
public class PersonPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private PersonDao personDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Persons entity = message.getHeaders().get(EntityConstants.entityHeader, Persons.class);
			entity.getPersonFlow().setState(States.valueOf(state.getId()));
			personDao.persist(entity);
		}
	}
}