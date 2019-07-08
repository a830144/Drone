package stateMachine.aerialactivity;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class AerialactivityPersistStateMachineHandler extends PersistStateMachineHandler{
	public AerialactivityPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}
}
