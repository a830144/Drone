<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.PersonService"%>
<%
		String id=request.getParameter("id");	
System.out.println("id::"+id);
		ServletContext context = request.getSession().getServletContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
		PersonService personService = (PersonService)ctx.getBean("personService");
		String jsonString = personService.queryPersonById(new Integer(Integer.parseInt(id)));
		response.setCharacterEncoding("UTF-8");
		System.out.println("jsonString::"+jsonString);
		response.getWriter().write(jsonString);	
%>