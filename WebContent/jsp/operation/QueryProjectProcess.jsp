
<%@ page import="service.OperationService"%>
<%@ page import="service.impl.OperationServiceImpl"%>

<%
		String ename=request.getParameter("name");	
		OperationService operationService = new OperationServiceImpl();
		String jsonString = operationService.queryProjects(ename);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);	
%>