package stateMachine.personlicense;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class PersonlicensePersistStateMachineHandler extends PersistStateMachineHandler{

	public PersonlicensePersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}

}
