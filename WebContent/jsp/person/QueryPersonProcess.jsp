<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.impl.ConcretePersonServiceDecorator"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="org.springframework.aop.framework.AopProxyUtils"%>

<%
		String ename=request.getParameter("name");	
		ServletContext context = request.getSession().getServletContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		
		Object myClassInstance = ctx.getBean("concretePersonServiceDecorator");
		Object obj=AopProxyUtils.getSingletonTarget(myClassInstance);
		ConcretePersonServiceDecorator concretePersonServiceDecorator=(ConcretePersonServiceDecorator)obj;
		
		JsonArray jsonArray = concretePersonServiceDecorator.queryPersonsJsonSring(ename);
		response.setCharacterEncoding("UTF-8");
		
		String jsonString =jsonArray.toString();		
		response.getWriter().write(jsonString);
%>