package stateMachine.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class ParticipationPersistHandlerConfig {
	@Autowired
	@Qualifier("participationmachine")
	private StateMachine<String, String> participationmachine;

	@Autowired
	private ParticipationPersistStateChangeListener participationPersistStateChangeListener;

	@Bean(name = "participationPersistStateMachineHandler")
	public ParticipationPersistStateMachineHandler persistStateMachineHandler() {
		ParticipationPersistStateMachineHandler participationPersistStateMachineHandler = new ParticipationPersistStateMachineHandler(participationmachine);
		participationPersistStateMachineHandler.addPersistStateChangeListener(participationPersistStateChangeListener);
		return participationPersistStateMachineHandler;
	}
}
