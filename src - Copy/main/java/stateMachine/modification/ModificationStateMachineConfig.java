package stateMachine.modification;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "modificationmachine")
public class ModificationStateMachineConfig extends StateMachineConfig{

}
