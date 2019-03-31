<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人員管理</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>

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
				$("#addDiv").load("./AddPerson.jsp");
				$("#queryDiv").load("./QueryPerson.jsp");
				$("#viewDiv").load("./ViewPerson.jsp");
				$("#updateDiv").load("./UpdatePerson.jsp");
				$("#licenseDiv").load("./LicenseInPerson.jsp");
				$("#trainingDiv").load("./TrainingInPerson.jsp");
				$("#eventDiv").load("./EventInPerson.jsp");
				$(document).on('click', '.view', function() {
					viewPerson(this.id);
				});
				$(document).on('click', '.update', function() {
					updatePerson(this.id);
				});
				$(document).on('click', '.license', function() {
					licenseInPerson(this.id);
				});
				$(document).on('click', '.training', function() {
					trainingInPerson(this.id);
				});
				$(document).on('click', '.event', function() {
					eventInPerson(this.id);
				});
				$(document).on('click', '.delete', function() {
					alert("delete :" + this.id);
				});
				

			});
</script>
</head>
<body>
	<button id="add" name="add">新增人員</button>
	<button id="query" name="query">查詢人員</button>
	<form id="query_form">
		<input type="text" name="query_name" id="query_name"
			class="text ui-widget-content ui-corner-all">
	</form>
	<div id="addDiv"></div>
	<div id="queryDiv"></div>
	<div id="viewDiv"></div>
	<div id="updateDiv"></div>
	<div id="licenseDiv"></div>
	<div id="trainingDiv"></div>
	<div id="eventDiv"></div>

</body>
</html>