package service;
import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import static org.mockito.Mockito.*;
import dao.impl.EquipmentDaoImpl;
import dao.impl.MaintenanceDaoImpl;
import dao.impl.ModificationDaoImpl;
import entity.Equipments;
import junit.framework.TestCase;
import service.impl.EquipmentServiceImpl;
import vo.MaintainEquipment;
import vo.ModifyEquipment;

public class EquipmentServiceTest extends TestCase {
	EquipmentService equipmentService;
	protected void setUp() throws Exception {
		equipmentService = new EquipmentServiceImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPersist(){
		equipmentService = new EquipmentServiceImpl();
		Equipments entity = new  Equipments();
		short s = 10;
		//entity.setAntiWind(s);
		entity.setConstructionType("A");
		Gson gson = new Gson();
		String jsonString = gson.toJson(entity);
		equipmentService.persist(jsonString);;
	}
	

	
	public void testUpdate(){
		equipmentService = new EquipmentServiceImpl();
		String jsonString = equipmentService.queryEquipmentById(new Integer(34));
		Gson gson = new Gson();
		Equipments e = gson.fromJson(jsonString, Equipments.class);
		
		e.setProductName("modify plane");
		
		
		jsonString = gson.toJson(e);
		equipmentService.updateEquipment(jsonString);
	}

	
	public void testMaintain(){
		EquipmentDaoImpl equipmentDao = mock(EquipmentDaoImpl.class);
		MaintenanceDaoImpl maintenanceDao = mock(MaintenanceDaoImpl.class);
		ModificationDaoImpl modificationDao = mock(ModificationDaoImpl.class);
		EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();

MaintainEquipment vo = new MaintainEquipment();
		
		vo.setEquipmentId(42);
		
		vo.setMaintenanceDate(new Date());
		vo.setMaintenancePerson("æ–½å�ˆå¿ƒ");
		
		vo.setAirframe("R");
		vo.setAirframe_comment("very big problem");
		vo.setBattery("R");
		vo.setBattery_comment("very big problem");
		vo.setController("R");
		vo.setController_comment("very big problem");
		
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();				
		String jsonString = gson.toJson(vo);
		equipmentService.maintainEquipment(jsonString);
		verify(equipmentDao,times(1)).findById(42);
		/*ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		EquipmentService equipmentService =(EquipmentService)  ctx.getBean("equipmentService");
		MaintainEquipment vo = new MaintainEquipment();
		
		vo.setEquipmentId(42);
		
		vo.setMaintenanceDate(new Date());
		vo.setMaintenancePerson("æ–½å�ˆå¿ƒ");
		
		vo.setAirframe("R");
		vo.setAirframe_comment("very big problem");
		vo.setBattery("R");
		vo.setBattery_comment("very big problem");
		vo.setController("R");
		vo.setController_comment("very big problem");
		
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();				
		String jsonString = gson.toJson(vo);
		equipmentService.maintainEquipment(jsonString);*/
	}
	
	public void testModify(){
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		EquipmentService equipmentService =(EquipmentService)  ctx.getBean("equipmentService");
		//equipmentService = new EquipmentServiceImpl();
		ModifyEquipment vo = new ModifyEquipment();
		vo.setEquipmentId(42);
		
		vo.setModificationDate(new Date());
		vo.setModificationPerson("æ–½å�ˆå¿ƒ");
		
		vo.setAirframe("M");
		vo.setAirframe_comment("SMALL problem");
		vo.setBattery("M");
		vo.setBattery_comment("SMALL problem");
		vo.setController("M");
		vo.setController_comment("SMALL problem");
		vo.setPayload("M");
		vo.setPayload_comment("SMALL problem");
		vo.setPropulsion("M");
		vo.setOthers("M");
		vo.setOthers_comment("SMALL problem");
		
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();		
		String jsonString = gson.toJson(vo);
		equipmentService.modifyEquipment(jsonString);
	}
	
	
	public void testFindAll(){
		//equipmentService = new EquipmentServiceImpl();
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "E:\\02_CASE\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		
		Object myClassInstance = ctx.getBean("concreteEquipmentServiceDecorator");
		Object obj=AopProxyUtils.getSingletonTarget(myClassInstance);
		//ConcreteEquipmentServiceDecorator concreteEquipmentServiceDecorator=(ConcreteEquipmentServiceDecorator)obj;
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
	
	
	public void testFindById(){
		equipmentService = new EquipmentServiceImpl();
		String jsonString = equipmentService.queryEquipmentById(new Integer(36));
	
		System.out.println(jsonString);
	}
	
	public void testQueryMaintenancesById(){
		equipmentService = new EquipmentServiceImpl();
		JsonArray jsonArr = equipmentService.queryMaintenancesById(new Integer(34));
		System.out.println(jsonArr.size());
		Iterator iter = jsonArr.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}

	}
	
	public void testQueryEquipmentsByAerialPlanId(){
		ApplicationContext ctx 
	      = new FileSystemXmlApplicationContext(new String[] {
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
	        "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
	        },true);
		EquipmentService equipmentService =(EquipmentService)  ctx.getBean("equipmentService");
		equipmentService = new EquipmentServiceImpl();
		List list = equipmentService.queryEquipmentsByAerialPlanId(new Integer(1));
		

	}
	
	
}
