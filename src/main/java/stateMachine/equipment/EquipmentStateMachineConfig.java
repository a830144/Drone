package stateMachine.equipment;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "equipmentmachine")
public class EquipmentStateMachineConfig extends StateMachineConfig{

}
