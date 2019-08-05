<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
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
    background-image: url('../images/header.jpg');
    background-attachment: fixed;
    background-position: center;
   	background-size: cover;
    background-repeat: no-repeat;
}

</style>
<title>The include Directive Example</title>
</head>

<body>
	<div id ="gnav">
	<center>
		<h2>無人機管理系統	&nbsp;	&nbsp;	&nbsp;<a href="javascript://" onclick="self.parent.location='./logout.jsp'">登出</a></h2>
		
	</center>
	</div>
</body>
</html>