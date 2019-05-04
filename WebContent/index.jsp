<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.SQLException"%>
<title>Login Page</title>
<script>
	function checkValue() {
		var id = document.getElementById("txtUserName").value;
		var password = document.getElementById("txtPassword").value;
		var errMsg = "";
		if (isEmpty(id) || isBlank(id) || id === undefined) {
			errMsg = errMsg + 'account input error \n';
		}
		if (isEmpty(password) || isBlank(password) || password === undefined) {
			errMsg = errMsg + 'password input error';
		}
		if (0 != errMsg) {
			window.alert(errMsg);
		} else {
			document.getElementById("myForm").submit();
		}

	}

	function isEmpty(str) {
		return (!str || 0 === str.length);
	}

	function isBlank(str) {
		return (!str || /^\s*$/.test(str));
	}
</script>
<style>
/* Basics */
html, body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
	font-family: "Helvetica Neue", Helvetica, sans-serif;
	background: #FFFFFF;
}

.logincontent {
	position: fixed;
	width: 350px;
	height: 300px;
	top: 50%;
	left: 50%;
	margin-top: -150px;
	margin-left: -175px;
	background: #07A8C3;
	padding-top: 10px;
}

.loginheading {
	border-bottom: solid 1px #ECF2F5;
	padding-left: 18px;
	padding-bottom: 10px;
	color: #ffffff;
	font-size: 20px;
	font-weight: bold;
	font-family: sans-serif;
}

label {
	color: #ffffff;
	display: inline-block;
	margin-left: 18px;
	padding-top: 10px;
	font-size: 15px;
}

input[type=text], input[type=password] {
	font-size: 14px;
	padding-left: 10px;
	margin: 10px;
	margin-top: 12px;
	margin-left: 18px;
	width: 300px;
	height: 35px;
	border: 1px solid #ccc;
	border-radius: 2px;
	box-shadow: inset 0 1.5px 3px rgba(190, 190, 190, .4), 0 0 0 5px #f5f5f5;
	font-size: 14px;
}

input[type=checkbox] {
	margin-left: 18px;
	margin-top: 30px;
}

.loginremember {
	background: #ECF2F5;
	height: 70px;
	margin-top: 20px;
}

.check {
	font-family: sans-serif;
	position: relative;
	top: -2px;
	margin-left: 2px;
	padding: 0px;
	font-size: 12px;
	color: #321;
}

.loginbtn {
	float: right;
	margin-right: 20px;
	margin-top: 20px;
	padding: 6px 20px;
	font-size: 14px;
	font-weight: bold;
	color: #fff;
	background-color: #07A8C3;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#07A8C3),
		to(#6EE4E8));
	background-image: -moz-linear-gradient(top left 90deg, #07A8C3 0%, #6EE4E8 100%);
	background-image: linear-gradient(top left 90deg, #07A8C3 0%, #6EE4E8 100%);
	border-radius: 30px;
	border: 1px solid #07A8C3;
	cursor: pointer;
}

.loginbtn:hover {
	background-image: -webkit-gradient(linear, left top, left bottom, from(#b6e2ff),
		to(#6ec2e8));
	background-image: -moz-linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
	background-image: linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
}
</style>
</head>
<body>
	<%
		String lonin = (String) session.getAttribute("login");

		//redirect user to home page if already logged in
		if (lonin != null) {
			String nextJSP = "/jsp/main.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}

	%>

	<form id="myForm" method="get" action="" autocomplete="off">
		<div class="logincontent">
			<div class="loginheading">Login</div>
			<label for="txtUserName"> User name:</label> <input type="text"
				id="txtUserName" name="txtUserName" value="" /> <label
				for="txtPassword"> Password:</label> <input type="password"
				id="txtPassword" name="txtPassword" value="" />
			<div class="loginremember">
				<input type="checkbox" id="chbRemember" name="chbRemember" /> <label
					class="check" for="chbRemember">Remember me next time</label> <input
					type="button" class="loginbtn" value="Login" id="btnSubmit"
					onclick="checkValue()" />
			</div>
			<div class="loginremember" id="display" name="display"
				style="display: none;">
				<input type="text" id="display_info" name="display_info"
					value="authenticate " />
			</div>
		</div>
	</form>
	<%
		String id = request.getParameter("txtUserName");
			String password = request.getParameter("txtPassword");

			if (id != null && password != null) {
		Connection conn = null;

		String js_display_info_successful = "<script>document.getElementById('display_info').value='authentication successful';</script>";
		String js_display_info_fail = "<script>document.getElementById('display_info').value='authentication fail';</script>";
		String js_display = "<script>document.getElementById('display').style.display='block';</script>";
		String js_display_id = "<script>document.getElementById('txtUserName').value='" + id + "';</script>";
		String js_display_password = "<script>document.getElementById('txtPassword').value='" + password
				+ "';</script>";
		if ("admin".equals(id) && "admin".equals(password)) {
			out.println(js_display);
			out.println(js_display_id);
			out.println(js_display_password);
			
			// New location to be redirected
			String nextJSP = "/jsp/main.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} else {
			try {
				// db parameters
				// create a connection to the database
				//String url = "jdbc:mysql://localhost:3306/autoplane";					
				//conn = DriverManager.getConnection(url,"root","4688");
				try {
	   					System.out.println("Loading driver...");
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Driver loaded!");
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Cannot find the driver in the classpath!", e);
					}
					String url = "jdbc:mysql://localhost:3306/drone";
					conn = DriverManager.getConnection(url, "root", "4688");

					String sql = "SELECT user_ID, password FROM users Where username='" + id + "' and password='"
							+ password + "'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					// get through the result set
					if (rs.next()) {
						out.println(js_display_info_successful);
						session.setAttribute("login", "true");
						String nextJSP = "/home";
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
						dispatcher.forward(request, response);
					} else {
						out.println(js_display_info_fail);
					}
					out.println(js_display);
					out.println(js_display_id);
					out.println(js_display_password);

				} catch (SQLException e) {
					out.println(e.getMessage());
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex) {
						out.println(ex.getMessage());
					}
				}
			}
		}
	%>
</body>
</html>