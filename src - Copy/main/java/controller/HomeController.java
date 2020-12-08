package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home" })
	public String showHomePage(Map<String, String> model) {
		model.put("welcome","hi,welcome to Drone" );
		System.out.println("main");
		return "main";
	}
}
