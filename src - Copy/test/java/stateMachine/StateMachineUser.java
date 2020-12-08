package stateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.statemachine.StateMachine;

import service.EquipmentService;

public class StateMachineUser {
	
	
	public static void main(String args[]){
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		EquipmentService equipmentService =(EquipmentService)  ctx.getBean("equipmentService");
		//equipmentService..run(new Integer(42));
	}
}
