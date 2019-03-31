<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.EquipmentService"%>
<%
		String id=request.getParameter("id");	

		ServletContext context = request.getSession().getServletContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
		EquipmentService equipmentService = (EquipmentService)ctx.getBean("equipmentService");
		String jsonString = equipmentService.queryEquipmentById(new Integer(Integer.parseInt(id)));
		response.setCharacterEncoding("UTF-8");
		System.out.println("jsonString::"+jsonString);
		response.getWriter().write(jsonString);	
%>