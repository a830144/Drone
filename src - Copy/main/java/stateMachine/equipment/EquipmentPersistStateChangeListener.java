package stateMachine.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.EquipmentDao;
import entity.Equipments;
import stateMachine.States;
import util.EntityConstants;

@Component
public class EquipmentPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private EquipmentDao equipmentDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Equipments entity = message.getHeaders().get(EntityConstants.entityHeader, Equipments.class);
			entity.getEquipmentFlow().setState(States.valueOf(state.getId()));
			equipmentDao.persist(entity);
		}
	}
}