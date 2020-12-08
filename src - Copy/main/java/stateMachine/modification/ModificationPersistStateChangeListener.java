package stateMachine.modification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.ModificationDao;
import entity.Modifications;
import stateMachine.States;
import util.EntityConstants;

@Component
public class ModificationPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private ModificationDao modificationDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Modifications entity = message.getHeaders().get(EntityConstants.entityHeader, Modifications.class);
			entity.getModificationFlow().setModifyState(States.valueOf(state.getId()));
			modificationDao.persist(entity);
		}
	}
}