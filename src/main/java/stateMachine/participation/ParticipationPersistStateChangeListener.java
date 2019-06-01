package stateMachine.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.EventDao;
import entity.Participations;
import stateMachine.States;
import util.EntityConstants;

@Component
public class ParticipationPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private EventDao eventDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Participations entity = message.getHeaders().get(EntityConstants.entityHeader, Participations.class);
			entity.getParticipationFlow().setState(States.valueOf(state.getId()));
			eventDao.persistParticipation(entity);
		}
	}
}