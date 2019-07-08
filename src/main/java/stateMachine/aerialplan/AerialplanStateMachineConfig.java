package stateMachine.aerialplan;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "aerialplanmachine")
public class AerialplanStateMachineConfig extends StateMachineConfig{

}
