package stateMachine.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class PersonPersistHandlerConfig {
	@Autowired
	@Qualifier("personmachine")
	private StateMachine<String, String> personmachine;

	@Autowired
	private PersonPersistStateChangeListener personPersistStateChangeListener;

	@Bean(name = "personPersistStateMachineHandler")
	public PersonPersistStateMachineHandler persistStateMachineHandler() {
		PersonPersistStateMachineHandler personPersistStateMachineHandler = new PersonPersistStateMachineHandler(personmachine);
		personPersistStateMachineHandler.addPersistStateChangeListener(personPersistStateChangeListener);
		return personPersistStateMachineHandler;
	}
}
