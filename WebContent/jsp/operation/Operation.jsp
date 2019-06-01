<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>設備管理</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css"/>
<script type="text/javascript"  src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedheader/3.1.4/css/fixedHeader.dataTables.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/fixedheader/3.1.4/js/dataTables.fixedHeader.js"></script>
<script src="/Drone/js/jquery.steps.min.js"></script>
<script src="/Drone/js/jquery.steps.js"></script>
<link rel="stylesheet" href="/Drone/css/normalize.css">
<link rel="stylesheet" href="/Drone/css/main.css">
<link rel="stylesheet" href="/Drone/css/jquery.steps.css">
<script src="/Drone/js/modernizr-2.6.2.min.js"></script>
<script src="/Drone/js/jquery.cookie-1.3.1.js"></script>
<script src="/Drone/js/react.js"></script>
<script src="/Drone/js/react-dom.js"></script>


<style type="text/css">
.dataTables_scroll
{
    overflow:auto;
}
</style>

<script>
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	


	$(document).ready(
			function() {
				$("#addDiv").load("./AddProject.jsp");
				$("#queryDiv").load("./QueryProject.jsp");
				$("#viewDiv").load("./ViewProject.jsp");
				$("#updateDiv").load("./UpdateProject.jsp");
				$("#aerialPlanDiv").load("./AerialPlan.jsp");
				$(document).on('click', '.view', function() {
					viewProject(this.id);
				});
				$(document).on('click', '.update', function() {
					updateProject(this.id);
				});
				$(document).on('click', '.delete', function() {
					alert("delete :" + this.id);
				});
				$(document).on('click', '.aerialPlan', function() {
					aerialPlan(this.id);
				});
				

			});
</script>
</head>
<body>
	<button id="add" name="add">新增專案</button>
	<button id="query" name="query" class="ui-button ui-corner-all ui-widget">查詢專案</button>
	<form id="query_form">
		<input type="text" name="query_name" id="query_name"
			class="text ui-widget-content ui-corner-all">
	</form>
	<div id="addDiv"></div>
	<div id="queryDiv"></div>
	<div id="viewDiv"></div>
	<div id="updateDiv"></div>
	<div id="aerialPlanDiv"></div>
	

</body>
</html>