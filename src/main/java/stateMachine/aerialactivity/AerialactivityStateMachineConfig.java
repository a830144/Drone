package stateMachine.aerialactivity;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "aerialactivitymachine")
public class AerialactivityStateMachineConfig extends StateMachineConfig{

}
