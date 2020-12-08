package stateMachine.certificate;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

import stateMachine.StateMachineConfig;

@Configuration
@EnableStateMachine(name = "certificatemachine")
public class CertificateStateMachineConfig extends StateMachineConfig{

}
