package stateMachine.person;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "personmachine")
public class PersonStateMachineConfig extends StateMachineConfig{

}
