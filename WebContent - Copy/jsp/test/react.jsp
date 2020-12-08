<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css"/>
<script type="text/javascript"  src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
<script src="/Drone/js/jquery.steps.min.js"></script>
<script src="/Drone/js/jquery.steps.js"></script>
<link rel="stylesheet" href="/Drone/css/normalize.css">
<link rel="stylesheet" href="/Drone/css/main.css">
<link rel="stylesheet" href="/Drone/css/jquery.steps.css">
<script src="/Drone/js/modernizr-2.6.2.min.js"></script>
<script src="/Drone/js/jquery.cookie-1.3.1.js"></script>
	<script
		src="/Drone/js/react.js"></script>
	<script
		src="/Drone/js/react-dom.js"></script>
		
	
</head>

<script>
var modify_obj = {		
		modifyList_select_Action : function(modificationId){
			modify_obj.store.crud = 'U';
			modify_obj.store.modificationId = modificationId;
			//notify
			modify_obj.modifyDialog.notify(modify_obj.store);
			modify_obj.modifyForm.notify(modify_obj.store);
		},
		modifyList_deselect_Action : function(modificationId){
			modify_obj.store.crud = 'C';
			modify_obj.store.modificationId = '-';
			//notify
			modify_obj.modifyDialog.notify(modify_obj.store);
			modify_obj.modifyForm.notify(modify_obj.store);
		},
		store : {
			crud : '',
			equipmentId : '',
			modificationId : ''			
		},
		initState : function (id){
			modify_obj.equipmentId = id;
		},
		removeState : function (){
			modify_obj.equipmentId = '';
		},
		
		initReactComponent : function (){	
			var container = document.getElementById('modifyDialog_modify');
			modify_obj.modifyDialog = ReactDOM.render(React.createElement(modifyDialog_modify, {}), container);
			container = document.getElementById('modifyList_modify');
			modify_obj.modifyList = ReactDOM.render(React.createElement(modifyList_modify, {}), container);
			container = document.getElementById('modifyForm_modify');
			modify_obj.modifyForm = ReactDOM.render(React.createElement(modifyForm_modify, {}), container);
			
		},
		removeReactComponent : function (){
			var container = document.getElementById('modifyForm_modify');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyList_modify');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyDialog_modify');
			ReactDOM.unmountComponentAtNode(container);

		},		
}
function modifyEquipment(id){	
	modify_obj.initState(id);
	modify_obj.initReactComponent(id);		
}

</script>
<div id="modifyDialog_modify" title="設備維護" style="display: none;">
</div>
<script src="/Drone/js/equipment/modify.js" charset="utf-8"></script>