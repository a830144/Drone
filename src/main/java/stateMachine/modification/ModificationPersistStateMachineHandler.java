package stateMachine.modification;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

public class ModificationPersistStateMachineHandler extends PersistStateMachineHandler{

	public ModificationPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
		// TODO Auto-generated constructor stub
	}

}
