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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
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
				$("#queryTrainingDiv").load("./QueryTraining.jsp");
				$("#queryEventDiv").load("./QueryEvent.jsp");
				$("#queryCodeDiv").load("./QueryCode.jsp");
				$("#addTrainingDiv").load("./AddTraining.jsp");
				$("#addEventDiv").load("./AddEvent.jsp");
				$("#addCodeDiv").load("./AddCode.jsp");
				
				$("#queryTrainingDiv").hide();
				$("#queryEventDiv").hide();
				$("#queryCodeDiv").hide();
				$("#addTrainingDiv").hide();
				$("#addEventDiv").hide();
				$("#addCodeDiv").hide();
				
			});
</script>
</head>
<body>
	<button id="add-training" name="add-training" class="ui-button ui-corner-all ui-widget">新增訓練項目</button>
	<button id="add-event" name="add-event" class="ui-button ui-corner-all ui-widget">新增特殊事蹟項目</button>
	<button id="add-code" name="add-code" class="ui-button ui-corner-all ui-widget">新增代碼項目</button>
	<button id="query-training" name="query-training" class="ui-button ui-corner-all ui-widget">查詢訓練項目</button>
	<button id="query-event" name="query-event" class="ui-button ui-corner-all ui-widget">查詢特殊事蹟項目</button>
	<button id="query-code" name="query-code" class="ui-button ui-corner-all ui-widget">查詢代碼項目</button>
	<div id="queryTrainingDiv"></div>
	<div id="queryEventDiv"></div>
	<div id="queryCodeDiv"></div>
	<div id="addTrainingDiv"></div>
	<div id="addEventDiv"></div>
	<div id="addCodeDiv"></div>

</body>
</html>