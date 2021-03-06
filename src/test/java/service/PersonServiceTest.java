package service;
import java.util.Iterator;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonArray;

import junit.framework.TestCase;

public class PersonServiceTest extends TestCase {
	PersonService personService;
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testQueryPersonsByLicenseType(){
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		PersonService personService = (PersonService)ctx.getBean("personService");
		JsonArray jsonArr = personService.queryPersonsByLicenseType("true");
		System.out.println(jsonArr.size());
		Iterator iter = jsonArr.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}
	}
	
	public void testGetLicensesById(){
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		PersonService personService = (PersonService)ctx.getBean("personService");
		JsonArray jsonArr = personService.queryLicensesById(3);
		System.out.println(jsonArr.size());
		Iterator iter = jsonArr.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}
	}
	
	public void testGetTrainingsById(){
		@SuppressWarnings("resource")
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		PersonService personService = (PersonService)ctx.getBean("personService");
		JsonArray jsonArr = personService.queryTrainingsById(3);
		System.out.println(jsonArr.size());
		Iterator iter = jsonArr.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}
	}
	
	public void testGetEventsById(){
		@SuppressWarnings("resource")
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		PersonService personService = (PersonService)ctx.getBean("personService");
		JsonArray jsonArr = personService.queryEventsById(3);
		System.out.println(jsonArr.size());
		Iterator iter = jsonArr.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}
	}
	
	
	public void testFindAll(){
		//equipmentService = new EquipmentServiceImpl();
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		
		Object myClassInstance = ctx.getBean("concretePersonServiceDecorator");
		Object obj=AopProxyUtils.getSingletonTarget(myClassInstance);
		//ConcretePersonServiceDecorator concretePersonServiceDecorator=(ConcretePersonServiceDecorator)obj;
		/*ConcreteEquipmentServiceDecorator concreteEquipmentServiceDecorator;
		try {
			concreteEquipmentServiceDecorator = (ConcreteEquipmentServiceDecorator)clazz.newInstance();
			JsonArray jsonArray = concreteEquipmentServiceDecorator.queryEquipmentsJsonSring("");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//EquipmentServiceImpl equipmentService = (EquipmentServiceImpl)ctx.getBean("equipmentService");
				
	}
	
	
	
}
