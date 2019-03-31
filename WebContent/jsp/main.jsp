<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main page</title>
</head>

	<%
		String login = (String) session.getAttribute("login");

		//redirect user to login page if not logged in
		if (login == null) {
			response.sendRedirect("/Drone/index.jsp");
		}
	%>



<frameset rows="10%,84%,6%">
<frame src="/Drone/jsp/header.jsp" name="header" id="header">
<frameset cols="14%,86%">
<frame src="/Drone/jsp/menu.jsp" name="menu" id="menu">
<frame src="/Drone/jsp/content.jsp" name="_content" id="_content">
</frameset>
<frame src="/Drone/jsp/footer.jsp" name="footer" id="footer">
</frameset> 

</html>