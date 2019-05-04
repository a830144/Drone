package stateMachine;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration 
@ComponentScan
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {
	
	
	@Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
		/**
		 * ADD-->PROCESSING
		 */
		Set<String> stringStates = new HashSet<>();
	    EnumSet.allOf(States.class).forEach(entity -> stringStates.add(entity.name()));
        states.withStates().initial(States.PROCESSING.name()).states(stringStates);;
    }
	
	@Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
		/**
		 * MAIN state transition trigger by event
		 */
        transitions
        	.withExternal()
        		.source(States.PROCESSING.name()).target(States.PROCESSING.name()).event(Events.UPDATE.name())
        		.and()
            .withExternal()
                .source(States.PROCESSING.name()).target(States.CHECKED.name()).event(Events.CHECK.name())
                .and()
            .withExternal()
                .source(States.CHECKED.name()).target(States.PROCESSING.name()).event(Events.UPDATE.name())
                .and()
            .withExternal()
                .source(States.CHECKED.name()).target(States.APPROVED.name()).event(Events.APPROVE.name())
                .and()
            .withExternal()
                .source(States.CHECKED.name()).target(States.REJECTED.name()).event(Events.REJECT.name())
                .and()
            .withExternal()
                .source(States.CHECKED.name()).target(States.DELETED.name()).event(Events.DELETE.name())
                .and()
            .withExternal()
                .source(States.PROCESSING.name()).target(States.REJECTED.name()).event(Events.REJECT.name())
                .and()
            .withExternal()
                .source(States.REJECTED.name()).target(States.PROCESSING.name()).event(Events.UPDATE.name())
                .and()
            .withExternal()
                .source(States.PROCESSING.name()).target(States.DELETED.name()).event(Events.DELETE.name())
                .and()
            .withExternal()
                .source(States.REJECTED.name()).target(States.DELETED.name()).event(Events.DELETE.name())
                .and()
            .withExternal()
                .source(States.APPROVED.name()).target(States.DELETED.name()).event(Events.DELETE.name())
                .and()
        	.withExternal()
        		.source(States.APPROVED.name()).target(States.PROCESSING.name()).event(Events.UPDATE.name())
        		.and();
    }
	
	private static final class StateMachineListener extends StateMachineListenerAdapter<States, Events> {
		@Override
		public void stateChanged(State<States, Events> from, State<States, Events> to) {
			System.out.println("Use state changed to " + to.getId());
		}
	}
}
