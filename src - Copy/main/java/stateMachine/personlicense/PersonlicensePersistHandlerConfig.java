package stateMachine.personlicense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class PersonlicensePersistHandlerConfig {
	@Autowired
	@Qualifier("personlicensemachine")
	private StateMachine<String, String> personlicensemachine;

	@Autowired
	private PersonlicensePersistStateChangeListener personlicensePersistStateChangeListener;

	@Bean(name = "personlicensePersistStateMachineHandler")
	public PersonlicensePersistStateMachineHandler persistStateMachineHandler() {
		PersonlicensePersistStateMachineHandler personlicensePersistStateMachineHandler = new PersonlicensePersistStateMachineHandler(personlicensemachine);
		personlicensePersistStateMachineHandler.addPersistStateChangeListener(personlicensePersistStateChangeListener);
		return personlicensePersistStateMachineHandler;
	}
}
