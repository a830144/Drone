package stateMachine.personlicense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.LicenseDao;
import entity.PersonsLicenses;
import stateMachine.States;
import util.EntityConstants;

@Component
public class PersonlicensePersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private LicenseDao licenseDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			PersonsLicenses entity = message.getHeaders().get(EntityConstants.entityHeader, PersonsLicenses.class);
			entity.getPersonsLicensesFlow().setState(States.valueOf(state.getId()));
			licenseDao.persistPersonLicense(entity);
		}
	}
}