package stateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class EquipmentPersistHandlerConfig {
	@Autowired
	private StateMachine<String, String> stateMachine;

	@Autowired
	private EquipmentPersistStateChangeListener equipmentPersistStateChangeListener;

	@Bean
	public PersistStateMachineHandler persistStateMachineHandler() {
		PersistStateMachineHandler handler = new PersistStateMachineHandler(stateMachine);
		handler.addPersistStateChangeListener(equipmentPersistStateChangeListener);
		return handler;
	}
}
