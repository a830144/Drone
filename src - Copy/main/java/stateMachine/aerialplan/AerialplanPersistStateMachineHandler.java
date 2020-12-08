package stateMachine.aerialplan;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class AerialplanPersistStateMachineHandler extends PersistStateMachineHandler{
	public AerialplanPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}
}
