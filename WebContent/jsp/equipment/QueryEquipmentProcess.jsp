<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@ page import="service.impl.ConcreteEquipmentServiceDecorator"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="org.springframework.aop.framework.AopProxyUtils"%>

<%
		String ename=request.getParameter("name");	
		ServletContext context = request.getSession().getServletContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		
		Object myClassInstance = ctx.getBean("concreteEquipmentServiceDecorator");
		Object obj=AopProxyUtils.getSingletonTarget(myClassInstance);
		ConcreteEquipmentServiceDecorator concreteEquipmentServiceDecorator=(ConcreteEquipmentServiceDecorator)obj;
		
		JsonArray jsonArray = concreteEquipmentServiceDecorator.queryEquipmentsJsonSring(ename);
		response.setCharacterEncoding("UTF-8");
		
		String jsonString =jsonArray.toString();		
		response.getWriter().write(jsonString);
%>