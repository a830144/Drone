package stateMachine.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import dao.TrainingDao;
import entity.Certificates;
import stateMachine.States;
import util.EntityConstants;

@Component
public class CertificatePersistStateChangeListener implements PersistStateChangeListener {

	@Autowired
  	private TrainingDao trainingDao;

	@Override
	public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
			StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(EntityConstants.entityHeader)) {
			Certificates entity = message.getHeaders().get(EntityConstants.entityHeader, Certificates.class);
			entity.getCertificateFlow().setState(States.valueOf(state.getId()));
			trainingDao.persistCertificate(entity);
		}
	}
}