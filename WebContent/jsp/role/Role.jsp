<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedheader/3.1.4/css/fixedHeader.dataTables.css"/>
<link rel="stylesheet" href="../../css/normalize.css">
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="../../css/jquery.steps.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"  src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
<script type="text/javascript"  src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/fixedheader/3.1.4/js/dataTables.fixedHeader.js"></script>
<script src="../../js/jquery.steps.min.js"></script>
<script src="../../js/jquery.steps.js"></script>
<script src="../../js/modernizr-2.6.2.min.js"></script>
<script src="../../js/jquery.cookie-1.3.1.js"></script>
<script src="../../js/react.js"></script>
<script src="../../js/react-dom.js"></script>
<script src="../../js/common/action.js" charset="utf-8"></script>
<script src="../../js/common/store.js" charset="utf-8"></script>
<script src="../../js/jquery.validate.min.js"></script>
<script src="../../js/additional-methods.min.js"></script>
<style type="text/css">
table.dataTable thead th {
	  white-space: nowrap
}

.hide-true  {
   display: none;
}
.hide-false  {
   display: initial;
}
.dataTables_scroll
{
    overflow:auto;
}

pre{
	display:block;
	font:100% "Courier New", Courier, monospace;
	padding:10px;
	border:1px solid #bae2f0;
	background:#e3f4f9;	
	margin:.5em 0;
	overflow:auto;
	width:800px;
}

img{border:none;}
ul,li{
	margin:0;
	padding:0;
}
li{
	list-style:none;
	float:left;
	display:inline;
	margin-right:10px;
}



/*  */

#screenshot{
	position:absolute;
	border:1px solid #ccc;
	background:#333;
	padding:5px;
	display:none;
	color:#fff;
	}

/*  */


</style>
<script>
	$(function () {
		$("#help").tooltip({
	       content: '<img src="../../images/state.jpg" />'
	   	});
	});
	window.alert = function(message) {
		$(document.createElement('div')).attr({
			title : 'Alert',
			'class' : 'alert'
		}).html(message).dialog({
			buttons : {
				OK : function() {
					$(this).dialog('close');
				}
			},
			close : function() {
				$(this).remove();
			},
			draggable : true,
			modal : true,
			resizable : false,
			width : 200
		});
	};
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
	
	function checkIAM(iamList){				
		var obj = {
			canCheck : false,
			canApprove : false,
			canReject : false,	
		};
		for(var i =0;i<iamList.length;i++){
			if(iamList[i]==="CHECK"){
				obj.canCheck = true;
			};
			if(iamList[i]==="APPROVE"){
				obj.canApprove = true;
			};
			if(iamList[i]==="REJECT"){
				obj.canReject = true;
			};
		}
		return obj;
	};
	
	

	$(document).ready(function() {
		$("#addDiv").load("./AddRole.jsp");
		$("#queryDiv").load("./QueryRole.jsp");
		$("#viewDiv").load("./ViewRole.jsp");
		$("#updateDiv").load("./UpdateRole.jsp");
		$("#deleteDiv").load("./DeleteRole.jsp");
		$(document).on('click', '#add', function() {
			addRole();
		});
		$(document).on('click', '.view', function() {
			viewRole(this.id);
		});
		$(document).on('click', '.update', function() {
			updateRole(this.id);
		});
		$(document).on('click', '.delete', function() {
			deleteRole(this.id);
		});

	});
</script>
</head>
<body>
	
	<button id="add" name="add" class="ui-button ui-corner-all ui-widget">新增角色</button>
	<a id="help" href="#" title=''>線上說明</a>
	<div id="addDiv"></div>
	<div id="queryDiv"></div>
	<div id="viewDiv"></div>
	<div id="updateDiv"></div>
	<div id="deleteDiv"></div>

</body>
</html>