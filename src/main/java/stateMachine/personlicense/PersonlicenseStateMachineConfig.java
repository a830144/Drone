package stateMachine.personlicense;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "personlicensemachine")
public class PersonlicenseStateMachineConfig extends StateMachineConfig{

}
