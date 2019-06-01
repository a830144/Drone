package stateMachine.person;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class PersonPersistStateMachineHandler extends PersistStateMachineHandler{

	public PersonPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}

}
