package stateMachine.aerialactivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.AerialActivityDao;
import entity.AerialActivities;
import stateMachine.States;
import util.EntityConstants;

@Component
public class AerialactivityPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private AerialActivityDao aerialActivityDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			AerialActivities entity = message.getHeaders().get(EntityConstants.entityHeader, AerialActivities.class);
			entity.getAerialActivityFlow().setState(States.valueOf(state.getId()));
			aerialActivityDao.persist(entity);
		}
	}
}