package stateMachine.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class ProjectPersistHandlerConfig {
	@Autowired
	@Qualifier("projectmachine")
	private StateMachine<String, String> projectmachine;

	@Autowired
	private ProjectPersistStateChangeListener projectPersistStateChangeListener;

	@Bean(name = "projectPersistStateMachineHandler")
	public ProjectPersistStateMachineHandler persistStateMachineHandler() {
		ProjectPersistStateMachineHandler projectPersistStateMachineHandler = new ProjectPersistStateMachineHandler(projectmachine);
		projectPersistStateMachineHandler.addPersistStateChangeListener(projectPersistStateChangeListener);
		return projectPersistStateMachineHandler;
	}
}
