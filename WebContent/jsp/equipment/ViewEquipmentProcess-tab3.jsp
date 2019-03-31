<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="service.EquipmentService"%>
<%@ page import=" com.google.gson.JsonArray"%>


<%
	String id = request.getParameter("id");
	ServletContext context = request.getSession().getServletContext();  
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
	EquipmentService equipmentService = (EquipmentService)ctx.getBean("equipmentService");
	JsonArray jsonArray = equipmentService.queryModificationsById(new Integer(id));
	response.setCharacterEncoding("UTF-8");
	
	String jsonString =jsonArray.toString();
	System.out.println("tab-3::" + jsonString);

	response.getWriter().write(jsonString);
%>