package stateMachine.certificate;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;


public class CertificatePersistStateMachineHandler extends PersistStateMachineHandler{

	public CertificatePersistStateMachineHandler(StateMachine<String, String> stateMachine) {
		super(stateMachine);
	}

}
