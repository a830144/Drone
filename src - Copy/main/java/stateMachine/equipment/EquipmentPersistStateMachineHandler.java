package stateMachine.equipment;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class EquipmentPersistStateMachineHandler extends PersistStateMachineHandler{

	public EquipmentPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
		// TODO Auto-generated constructor stub
	}

}
