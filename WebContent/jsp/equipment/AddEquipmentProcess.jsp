<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.EquipmentService"%>
<%
String jsonString=request.getParameter("data");	
ServletContext context = request.getSession().getServletContext();  
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
EquipmentService equipmentService = (EquipmentService)ctx.getBean("equipmentService");
equipmentService.persist(jsonString);

%>