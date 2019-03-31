<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.PersonService"%>
<%
String jsonString=request.getParameter("data");	
ServletContext context = request.getSession().getServletContext();  
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
PersonService personService = (PersonService)ctx.getBean("personService");
personService.licenseInPerson(jsonString);

%>