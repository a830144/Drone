package stateMachine.participation;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class ParticipationPersistStateMachineHandler extends PersistStateMachineHandler{

	public ParticipationPersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}

}
