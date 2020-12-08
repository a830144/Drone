<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>設備管理</title>
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
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 5%;
}
</style>
<script>
	$(function () {
		$("#help").tooltip({
       		content: '<img src="../../images/state.jpg" />'
   		});
	});
	window.alert = function(message){
		$(document.createElement('div'))
		.attr({title: 'Alert', 'class': 'alert'})
		.html(message)
		.dialog({
			buttons: {OK: function(){$(this).dialog('close');}},
			close: function(){$(this).remove();},
			draggable: true,
			modal: true,
			resizable: false,
			width: 200
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
	
	function fileValue(form){
		var details = ['airframe', 'propulsion','battery','controller','payload'];
		details.forEach(function(item, index, array) {
			$(form).find("#mo-"+item+"_photo").val($(form).find("#"+item+"_photo").val());					  
		});	
	}
	function radioValue(form){
		$(form).find("input[name='airframe']").val($(form).find("input:radio[name='airframe_radio']:checked").val());
		$(form).find("input[name='propulsion']").val($(form).find("input:radio[name='propulsion_radio']:checked").val());
		$(form).find("input[name='battery']").val($(form).find("input:radio[name='battery_radio']:checked").val());
		$(form).find("input[name='controller']").val($(form).find("input:radio[name='controller_radio']:checked").val());
		$(form).find("input[name='payload']").val($(form).find("input:radio[name='payload_radio']:checked").val());		
	}
	function reverseRadioValue(form,filter){
		var details = ['airframe', 'propulsion','battery','controller','payload'];
		details.forEach(function(item, index, array) {
			if(filter==="ma"){
				$(form).find("input[name='"+item+"']").val()==="C" ? $(form).find("#"+item+"_c").prop('checked', true):$(form).find("#"+item+"_r").prop('checked', true);
			}else if(filter==="mo"){
				$(form).find("input[name='"+item+"']").val()==="N" ? $(form).find("#"+item+"_n").prop('checked', true):$(form).find("#"+item+"_m").prop('checked', true);
			}			  
		});	

		$($(form).find("input[name*='radio']")).button("enable").button("refresh");		
	}
	
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
	
	$(document).ready(
			function() {
				$("#addDiv").load("./AddEquipment.jsp");
				$("#queryDiv").load("./QueryEquipment.jsp");
				$("#viewDiv").load("./ViewEquipment.jsp");
				$("#updateDiv").load("./UpdateEquipment.jsp");
				$("#maintainDiv").load("./MaintainEquipment.jsp");
				$("#modifyDiv").load("./ModifyEquipment.jsp");
				$("#deleteDiv").load("./DeleteEquipment.jsp");
				$(document).on('click', '#add', function() {
					addEquipment();	
				});
				$(document).on('click', '.view', function() {
					viewEquipment(this.id);
				});
				$(document).on('click', '.update', function() {
					updateEquipment(this.id);
				});
				$(document).on('click', '.maintain', function() {
					maintainEquipment(this.id);
				});
				$(document).on('click', '.modify', function() {
					modifyEquipment(this.id);
				});
				$(document).on('click', '.delete', function() {
					deleteEquipment(this.id);
				});	
			});
</script>
</head>
<body>
	<button id="add" name="add" class="ui-button ui-corner-all ui-widget">新增設備</button>	
	<button id="query" name="query" class="ui-button ui-corner-all ui-widget">查詢設備</button>
	<a id="help" href="#" title=''>線上說明</a>
	<form id="query_form">		
		<input type="text" name="query_name" id="query_name" class="text ui-widget-content ui-corner-all">
	</form>
	<div id="addDiv"></div>
	<div id="queryDiv"></div>
	<div id="viewDiv"></div>
	<div id="updateDiv"></div>
	<div id="maintainDiv"></div>
	<div id="modifyDiv"></div>
	<div id="deleteDiv"></div>

</body>
</html>