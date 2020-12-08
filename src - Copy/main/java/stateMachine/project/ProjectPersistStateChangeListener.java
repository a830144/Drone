package stateMachine.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.ProjectDao;
import entity.Projects;
import stateMachine.States;
import util.EntityConstants;

@Component
public class ProjectPersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private ProjectDao projectDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Projects entity = message.getHeaders().get(EntityConstants.entityHeader, Projects.class);
			entity.getProjectFlow().setState(States.valueOf(state.getId()));
			projectDao.persist(entity);
		}
	}
}