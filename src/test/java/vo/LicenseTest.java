package vo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import entity.Licenses;
import entity.PersonsLicenses;
import junit.framework.TestCase;

public class LicenseTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLicenseInPerson(){
		LicenseInPerson vo = new LicenseInPerson();
		Licenses en1 = new Licenses();
		en1.setSubType("1");
		en1.setType("2");
		PersonsLicenses en2 = new PersonsLicenses();
		en2.setGotDate(new Date());
		
		try {
			BeanUtils.copyProperties(vo, en1);
			BeanUtils.copyProperties(vo, en2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(vo);
	}

}
