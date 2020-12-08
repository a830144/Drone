package stateMachine.maintenance;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

public class MaintenancePersistStateMachineHandler extends PersistStateMachineHandler{

	public MaintenancePersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
		// TODO Auto-generated constructor stub
	}

}
