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
    background-repeat: no-repeat;
}
</style>
<title>The include Directive Example</title>
</head>

<body>
	<center>
		<h2>無人機管理系統雛型畫面<a href="javascript://" onclick="self.parent.location='./logout.jsp'">Logout</a></h2>
		
	</center>

</body>
</html>