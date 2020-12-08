package stateMachine.aerialplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.AerialPlanDao;
import entity.AerialPlans;
import stateMachine.States;
import util.EntityConstants;

@Component
public class AerialplanPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private AerialPlanDao aerialPlanDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			AerialPlans entity = message.getHeaders().get(EntityConstants.entityHeader, AerialPlans.class);
			entity.getAerialPlanFlow().setState(States.valueOf(state.getId()));
			aerialPlanDao.persist(entity);
		}
	}
}