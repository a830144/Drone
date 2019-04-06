<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#menu" ).menu();
  } );
  </script>
  <style>
  .ui-menu { width: 150px; }
  </style>
<style>
body {
    background-image: url('../images/menu.jpg');
    background-attachment: fixed;
    background-repeat: no-repeat;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>menu</title>
</head>
<body>
<ul id="menu">
  <li><a href="./role.jsp" target=_content><li><i class="fa fa-file-text-o" aria-hidden="true"></i> 角色權限管理</li></a></li>
  <li><a href="./account.jsp" target=_content><li><i class="fa fa-file-text-o" aria-hidden="true"></i> 帳號角色管理</li></a></li>
  <li><a href="./person/Person.jsp" target=_content><li><i class="fa fa-file-text-o" aria-hidden="true"></i> 人員管理</li></a></li>
  <li><a href="./equipment/Equipment.jsp" target=_content><li><i class="fa fa-file-text-o" aria-hidden="true"></i> 設備管理</li></a></li>
  <li><a href="./operation/Operation.jsp" target=_content><li><i class="fa fa-file-text-o" aria-hidden="true"></i> 作業管理</li></a></li>
  
</ul>

</body>
</html>