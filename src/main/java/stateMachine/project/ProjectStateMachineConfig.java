package stateMachine.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "projectmachine")
public class ProjectStateMachineConfig extends StateMachineConfig{

}
