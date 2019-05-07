package stateMachine.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class MaintenancePersistHandlerConfig {
	@Autowired
	@Qualifier("maintenancemachine")
	private StateMachine<String, String> maintenancemachine;

	@Autowired
	private MaintenancePersistStateChangeListener maintenancePersistStateChangeListener;

	@Bean(name = "maintenancePersistStateMachineHandler")
	public MaintenancePersistStateMachineHandler persistStateMachineHandler() {
		MaintenancePersistStateMachineHandler handler = new MaintenancePersistStateMachineHandler(maintenancemachine);
		handler.addPersistStateChangeListener(maintenancePersistStateChangeListener);
		return handler;
	}
}
