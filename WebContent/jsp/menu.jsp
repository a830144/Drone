<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.ui-menu {
	width: 150px;
}
</style>
<style>
body {
	background-image: url('../images/menu.jpg');
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>menu</title>
</head>
<body>
	<ul id="menu">
		<%				
				Connection conn = null;
				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Cannot find the driver in the classpath!", e);
					}
					
					String url = "jdbc:mysql://localhost:3306/drone1";
					conn = DriverManager.getConnection(url, "root", "4688");					

					String sql = "SELECT re.resource_Name, re.Entry_Point,re.type,GROUP_CONCAT(a.action_name) as operations"+
								 " FROM users u"+
								 " inner join users_roles ur on u.user_id = ur.user_id"+
								 " inner join roles r on ur.role_id = r.role_id"+
								 " inner join roles_permissions rp on r.role_id = rp.role_id"+
								 " inner join permissions p on rp.permission_id = p.permission_id"+
								 " inner join actions a on p.action_id = a.action_id"+
								 " inner join resources re on p.resource_id = re.resource_id"+				
							     " WHERE "+
							     " u.user_id="+session.getAttribute("id")+
							     " GROUP BY re.resource_name" +
							     " ORDER BY re.resource_id";
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()){
						if("Tree".equals(rs.getString("type"))){
							String resourceName = rs.getString("resource_Name");
		                	String entryPoint = rs.getString("Entry_Point");
		                	String function_display = "<li><a href='"+entryPoint+"' target=_content><li><i class='fa fa-file-text-o' aria-hidden='true'></i>"+ resourceName+"</li></a></li>";
		                	out.println(function_display);
		                	System.out.println("function_display!"+function_display);
						}
						String resourceName = rs.getString("resource_Name");
						String operations = rs.getString("operations");						
						session.setAttribute(resourceName, operations);
					}
					
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

	%>

	</ul>
	<script>
  $( function() {
    $( "#menu" ).menu();
  } );
  </script>
</body>
</html>