<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#projects {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#projects td, #projects th {
	border: 1px solid black;
	padding: 8px;
}

#projects tr:nth-child(even) {
	background-color: #f2f2f2;
}

#projects tr:hover {
	background-color: #ddd;
}

#projects th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
	border: 1px solid black;
	white-space: nowrap;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>作業管理</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>

<style>
body {
	font-size: 62.5%;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
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
	function makeTable(container, data, notEmpty) {
		$('#projects').remove();
		var table = $("<table/>").attr('id', 'projects');
		var row = $("<tr/>");
		row.append($("<th/>").text("專案編號"));
		row.append($("<th/>").text("專案名稱"));
		row.append($("<th/>").text("委託公司"));
		row.append($("<th/>").text("專案結案日期"));
		row.append($("<th/>").text("專案經理"));
		row.append($("<th/>").text("狀態"));
		row.append($("<th/>").text("系統功能"));
		table.append(row);

		if (notEmpty) {
			$.each(data, function(rowIndex, r) {
				var row = $("<tr/>");
				row.append($("<td/>").text(data[rowIndex].projectId));
				row.append($("<td/>").text(data[rowIndex].name));
				row.append($("<td/>").text(data[rowIndex].company));
				row.append($("<td/>").text(data[rowIndex].endDate));
				row.append($("<td/>").text(data[rowIndex].projectManager));
				row.append($("<td/>").text(data[rowIndex].status));
				var actionColumn = $("<td/>");
				var view = $('<button/>', {
					'name' : 'view' + data[rowIndex].projectId,
					'id' : data[rowIndex].projectId,
					'class' : 'view',
					'value' : 'view'
				}).text("檢視").appendTo(actionColumn);
				var view = $('<button/>', {
					'name' : 'update' + data[rowIndex].projectId,
					'id' : data[rowIndex].projectId,
					'class' : 'update',
					'value' : 'update'
				}).text("修改").appendTo(actionColumn);
				var maintain = $('<button/>', {
					'name' : 'aerialPlan' + data[rowIndex].projectId,
					'id' : data[rowIndex].projectId,
					'class' : 'aerialPlan',
					'value' : 'aerialPlan'
				}).text("新增/修改航拍計畫").appendTo(actionColumn);
				var modify = $('<button/>', {
					'name' : 'aerialActivity' + data[rowIndex].projectId,
					'id' : data[rowIndex].projectId,
					'class' : 'aerialActivity',
					'value' : 'aerialActivity'
				}).text("新增/修改航拍活動").appendTo(actionColumn);
				var deleteit = $('<button/>', {
					'name' : 'delete' + data[rowIndex].projectId,
					'id' : data[rowIndex].projectId,
					'class' : 'delete',
					'value' : 'delete'
				}).text("刪除").appendTo(actionColumn);

				row.append(actionColumn);
				table.append(row);
			});
		}
		return container.append(table);
	}


	$(document).ready(
			function() {
				var data = $.post("./QueryProjectProcess.jsp", {}, function(
						data, status) {
					var obj = $.parseJSON(data);
					var cityTable = makeTable($(document.body), obj, true);
				});
				$("#addDiv").load("./AddProject.jsp");
				$("#queryDiv").load("./QueryProject.jsp");
				$("#viewDiv").load("./ViewProject.jsp");
				$("#updateDiv").load("./UpdateProject.jsp");
				//$("#aerialPlanDiv").load("./AerialPlan.jsp");
				//$("#aerialActivityDiv").load("./AerialActivity.jsp");
				$(document).on('click', '.view', function() {
					viewProject(this.id);
				});
				$(document).on('click', '.update', function() {
					updateProject(this.id);
				});
				$(document).on('click', '.aerialPlan', function() {
					aerialPlan(this.id);
				});
				$(document).on('click', '.aerialActivity', function() {
					aerialActivity(this.id);
				});
				$(document).on('click', '.delete', function() {
					alert("delete :" + this.id);
				});

			});
	function aerialPlan(id) {
		parent.content.location = './AerialPlan.jsp';

	}
	
</script>
</head>
<body>
	<button id="add" name="add">新增專案</button>
	<button id="query" name="query">查詢專案</button>
	<form id="query_form">
		<input type="text" name="query_name" id="query_name"
			class="text ui-widget-content ui-corner-all">
	</form>
	<div id="addDiv"></div>
	<div id="queryDiv"></div>
	<div id="viewDiv"></div>
	<div id="updateDiv"></div>
	

</body>
</html>