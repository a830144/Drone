<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="service.PersonService"%>
<%@ page import=" com.google.gson.JsonArray"%>


<%
	String id = request.getParameter("id");
	ServletContext context = request.getSession().getServletContext();  
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
	PersonService personService = (PersonService)ctx.getBean("personService");
	JsonArray jsonArray = personService.queryEventsById(new Integer(id));
	response.setCharacterEncoding("UTF-8");
	
	String jsonString =jsonArray.toString();	
	response.getWriter().write(jsonString);
%>