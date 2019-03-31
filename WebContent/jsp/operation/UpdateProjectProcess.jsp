
<%@ page import="service.OperationService"%>
<%@ page import="service.impl.OperationServiceImpl"%>
<%
	String jsonString = request.getParameter("data");
	System.out.println(jsonString);
	OperationService operationService = new OperationServiceImpl();
	operationService.modifyProject(jsonString);
%>