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
			response.sendRedirect("./index.jsp");
		}
	%>



<frameset rows="18%,82%">
<frame src="./jsp/header.jsp" name="header" id="header">
<frameset cols="10%,90%">
<frame src="./jsp/menu.jsp" name="menu" id="menu">
<frame src="./jsp/content.jsp" name="_content" id="_content">
</frameset>

</frameset> 

</html>