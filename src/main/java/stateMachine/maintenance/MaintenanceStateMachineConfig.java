package stateMachine.maintenance;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "maintenancemachine")
public class MaintenanceStateMachineConfig extends StateMachineConfig{

}
