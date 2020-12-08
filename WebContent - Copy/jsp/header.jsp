<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! 
   int pageCount = 0;
   void addCount() {
      pageCount++;
   }
%>

<% addCount(); %>

<html>
<head>
<style>
body {
    background-image: url('../images/header.png');
    background-attachment: fixed;
    background-position: center;
   	background-size: cover;
    background-repeat: no-repeat;

}
.relative {
  position: absolute;
  left: 1700px;
}

</style>
<title>The include Directive Example</title>
</head>

<body>
	<div id ="gnav">
	<div id = "user" class="relative">
		<h2 style="color:white"><%=session.getAttribute("username") %></h2>		
	</div>
	<br>
	<div id = "logout" class="relative">
		<h2 style="color:white"><a href="javascript://" onclick="self.parent.location='./logout.jsp'" style="color:white">登出</a></h2>		
	</div>
	
	</div>
</body>
</html>