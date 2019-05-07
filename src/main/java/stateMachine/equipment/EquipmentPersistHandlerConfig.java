package stateMachine.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class EquipmentPersistHandlerConfig {
	@Autowired
	@Qualifier("equipmentmachine")
	private StateMachine<String, String> equipmentmachine;

	@Autowired
	private EquipmentPersistStateChangeListener equipmentPersistStateChangeListener;

	@Bean(name = "equipmentPersistStateMachineHandler")
	public EquipmentPersistStateMachineHandler persistStateMachineHandler() {
		EquipmentPersistStateMachineHandler equipmentPersistStateMachineHandler = new EquipmentPersistStateMachineHandler(equipmentmachine);
		equipmentPersistStateMachineHandler.addPersistStateChangeListener(equipmentPersistStateChangeListener);
		return equipmentPersistStateMachineHandler;
	}
}
