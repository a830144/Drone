package stateMachine.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.MaintenanceDao;
import entity.Maintenances;
import stateMachine.States;
import util.EntityConstants;

@Component
public class MaintenancePersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private MaintenanceDao maintenanceDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Maintenances entity = message.getHeaders().get(EntityConstants.entityHeader, Maintenances.class);
			entity.getMaintenanceFlow().setMaintainState(States.valueOf(state.getId()));
			maintenanceDao.persist(entity);
		}
	}
}