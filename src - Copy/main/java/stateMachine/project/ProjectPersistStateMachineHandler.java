package stateMachine.project;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class ProjectPersistStateMachineHandler extends PersistStateMachineHandler{
	public ProjectPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}
}
