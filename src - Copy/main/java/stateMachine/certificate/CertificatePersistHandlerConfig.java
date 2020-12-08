package stateMachine.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class CertificatePersistHandlerConfig {
	@Autowired
	@Qualifier("certificatemachine")
	private StateMachine<String, String> certificatemachine;

	@Autowired
	private CertificatePersistStateChangeListener certificatePersistStateChangeListener;

	@Bean(name = "certificatePersistStateMachineHandler")
	public CertificatePersistStateMachineHandler persistStateMachineHandler() {
		CertificatePersistStateMachineHandler certificatePersistStateMachineHandler = new CertificatePersistStateMachineHandler(certificatemachine);
		certificatePersistStateMachineHandler.addPersistStateChangeListener(certificatePersistStateChangeListener);
		return certificatePersistStateMachineHandler;
	}
}
