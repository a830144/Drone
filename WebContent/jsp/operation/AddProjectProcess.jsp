
<%@ page import="service.OperationService"%>
<%@ page import="service.impl.OperationServiceImpl"%>
<%
		String jsonString=request.getParameter("data");		
		OperationService operationService = new OperationServiceImpl();
		operationService.persistProject(jsonString);		

%>