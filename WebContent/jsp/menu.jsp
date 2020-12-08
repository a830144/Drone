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

					String sql = "SELECT distinct f.Function_Name, f.Entry_Point,f.type,GROUP_CONCAT(op.type) as operations"+
								 " FROM users u"+
								 " inner join users_roles ur on u.user_id = ur.user_id"+
								 " inner join roles r on ur.role_id = r.role_id"+
								 " inner join roles_functions rf on r.role_id = rf.role_id"+
								 " inner join functions f on rf.function_id = f.function_id"+
								 " inner join (Select rf2.Roles_Functions_ID,o.type"+
								 " 			   FROM roles_functions rf2 "+
							     "			   inner join operations o on rf2.Roles_Functions_ID = o.Roles_Functions_ID) op on rf.Roles_Functions_ID = op.Roles_Functions_ID"+								 
							     " WHERE "+
							     " u.user_id="+session.getAttribute("id")+
							     " GROUP BY f.function_name"+
							     " ORDER BY f.function_id";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()){
						if("Tree".equals(rs.getString("type"))){
							String functionName = rs.getString("Function_Name");
		                	String entryPoint = rs.getString("Entry_Point");
		                	String function_display = "<li><a href='"+entryPoint+"' target=_content><li><i class='fa fa-file-text-o' aria-hidden='true'></i>"+ functionName+"</li></a></li>";
		                	out.println(function_display);
		                	System.out.println("function_display!"+function_display);
						}
						String functionName = rs.getString("Function_Name");
						String operations = rs.getString("operations");						
						session.setAttribute(functionName, operations);
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