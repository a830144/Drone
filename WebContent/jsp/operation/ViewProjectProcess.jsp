
<%@ page import="service.OperationService"%>
<%@ page import="service.impl.OperationServiceImpl"%>
<%
		String id=request.getParameter("id");			
		OperationService operationService = new OperationServiceImpl();
		String jsonString = operationService.queryProjectById(new Integer(Integer.parseInt(id)));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);	
%>