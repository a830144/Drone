package stateMachine.aerialactivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class AerialactivityPersistHandlerConfig {
	@Autowired
	@Qualifier("aerialactivitymachine")
	private StateMachine<String, String> aerialactivitymachine;

	@Autowired
	private AerialactivityPersistStateChangeListener aerialactivityPersistStateChangeListener;

	@Bean(name = "aerialplanPersistStateMachineHandler")
	public AerialactivityPersistStateMachineHandler persistStateMachineHandler() {
		AerialactivityPersistStateMachineHandler aerialactivityPersistStateMachineHandler = new AerialactivityPersistStateMachineHandler(aerialactivitymachine);
		aerialactivityPersistStateMachineHandler.addPersistStateChangeListener(aerialactivityPersistStateChangeListener);
		return aerialactivityPersistStateMachineHandler;
	}
}
