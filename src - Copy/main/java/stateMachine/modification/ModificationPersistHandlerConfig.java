package stateMachine.modification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class ModificationPersistHandlerConfig {
	@Autowired
	@Qualifier("modificationmachine")
	private StateMachine<String, String> modificationmachine;

	@Autowired
	private ModificationPersistStateChangeListener modificationPersistStateChangeListener;

	@Bean(name = "modificationPersistStateMachineHandler")
	public ModificationPersistStateMachineHandler persistStateMachineHandler() {
		ModificationPersistStateMachineHandler handler = new ModificationPersistStateMachineHandler(modificationmachine);
		handler.addPersistStateChangeListener(modificationPersistStateChangeListener);
		return handler;
	}
}
