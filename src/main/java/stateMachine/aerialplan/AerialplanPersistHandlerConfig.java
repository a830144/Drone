package stateMachine.aerialplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class AerialplanPersistHandlerConfig {
	@Autowired
	@Qualifier("aerialplanmachine")
	private StateMachine<String, String> aerialplanmachine;

	@Autowired
	private AerialplanPersistStateChangeListener aerialplanPersistStateChangeListener;

	@Bean(name = "aerialplanPersistStateMachineHandler")
	public AerialplanPersistStateMachineHandler persistStateMachineHandler() {
		AerialplanPersistStateMachineHandler aerialplanPersistStateMachineHandler = new AerialplanPersistStateMachineHandler(aerialplanmachine);
		aerialplanPersistStateMachineHandler.addPersistStateChangeListener(aerialplanPersistStateChangeListener);
		return aerialplanPersistStateMachineHandler;
	}
}
