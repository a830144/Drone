package stateMachine.participation;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "participationmachine")
public class ParticipationStateMachineConfig extends StateMachineConfig{

}
