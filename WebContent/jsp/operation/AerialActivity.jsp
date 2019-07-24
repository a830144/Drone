<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
var aerialActivity_obj = {		
		
		addOrUpdate : function (action){
			  $("#aerialActivityForm_activity").find("#projectId").prop("disabled", false);
			  $("#aerialActivityForm_activity").find("#name").prop("disabled", false);
			  $("#aerialActivityForm_activity").find("#aerialPlanId").prop("disabled",false);
			  $("#aerialActivityForm_activity").find("#usage").prop("disabled",false);							
			  			  
			  var jsonObject = {};
			  jsonObject.projectId =$("#aerialActivityForm_activity").find("#projectId").val();
			  jsonObject.name = $("#aerialActivityForm_activity").find("#name").val();
			  jsonObject.aerialPlanId = $("#aerialActivityForm_activity").find("#aerialPlanId").val();
			  jsonObject.usage = $("#aerialActivityForm_activity").find("#usage").val();
			  jsonObject.aerialPlanStartDate = $("#aerialActivityForm_activity").find("#aerialPlanStartDate").val();
			  jsonObject.aerialPlanEndDate = $("#aerialActivityForm_activity").find("#aerialPlanEndDate").val();
			  jsonObject.aerialActivityStartDate = $("#aerialActivityForm_activity").find("#aerialActivityStartDate").val();
			  jsonObject.aerialActivityEndDate = $("#aerialActivityForm_activity").find("#aerialActivityEndDate").val();
			  
			  var arr = new Array();
			  jsonObject.equipmentPersonArray = arr;
			  var table = $('#equipmentPersonList_activity_sub').DataTable();
			  table.rows().eq(0).each( function ( index ) {
			    	var row = table.row( index );
			    	var cell = table.cell( index ,0);
					var equipmentId = cell.data();
					cell = table.cell( index ,6);
					var personId_1 = cell.data();
					cell = table.cell( index ,7);
					var personId_2 = cell.data();
					jsonObject.equipmentPersonArray.push({
						"equipmentId":equipmentId,
						"personId_1":personId_1,
						"personId_2":personId_2
					});
			  } );
			  myJson = JSON.stringify(jsonObject);
			  console.log(myJson);
			  $.ajax({
				 type : "POST", 
				 url : action==='update'?"/Drone/operation/UpdateAerialActivityProcess":"/Drone/operation/AddAerialActivityProcess",
				 data : {
						data : myJson
				 },
				 success : function() {
					alert(action==='update'?'修改航拍活動紀錄成功':'新增航拍活動紀錄成功');
				 }
			  })
		},
		
		initState : function (id){
			store_obj.projectId = id;
		},
		removeState : function (){
			store_obj.projectId = '';
		},
		
		initReactComponent : function (action){	
			var container;
			container = document.getElementById('aerialActivityDialog_'+action);
			aerialActivity_obj.aerialActivityDialog = ReactDOM.render(React.createElement(aerialActivityDialog, {domId:action}), container);
			action_obj.aerialActivityList_select_Action_subscribe(aerialActivity_obj.aerialActivityDialog);
			action_obj.aerialActivityList_deselect_Action_subscribe(aerialActivity_obj.aerialActivityDialog);
			
			container = document.getElementById('aerialActivityList_'+action);
			aerialActivity_obj.aerialActivityList = ReactDOM.render(React.createElement(aerialActivityList, {domId:action}), container);
			
			container = document.getElementById('aerialActivityTabs_'+action);
			aerialActivity_obj.tabs = ReactDOM.render(React.createElement(aerialActivityTabs, {domId:action}), container);				
						
			container = document.getElementById('aerialActivityEPList_'+action);
			aerialActivity_obj.aerialActivityEPList = ReactDOM.render(React.createElement(aerialActivityEPList, {domId:action}), container);
			action_obj.aerialActivitySteps_clickFinish_Action_subscribe(aerialActivity_obj.aerialActivityEPList);
			action_obj.aerialActivityList_select_Action_subscribe(aerialActivity_obj.aerialActivityEPList);
			action_obj.aerialActivityList_deselect_Action_subscribe(aerialActivity_obj.aerialActivityEPList);
			
			container = document.getElementById('aerialActivityForm_'+action);
			aerialActivity_obj.aerialActivityForm = ReactDOM.render(React.createElement(aerialActivityForm, {domId:action,projectId:store_obj.projectId}), container); 
			action_obj.aerialActivityList_select_Action_subscribe(aerialActivity_obj.aerialActivityForm);
			action_obj.aerialActivityList_deselect_Action_subscribe(aerialActivity_obj.aerialActivityForm);
			
			container = document.getElementById('aerialActivitySteps_'+action);
			aerialActivity_obj.steps = ReactDOM.render(React.createElement(aerialActivitySteps, {domId:action}), container);
			action_obj.aerialActivityEPList_add_Action_subscribe(aerialActivity_obj.steps);			
			
			container = document.getElementById('firstStep_'+action);
			aerialActivity_obj.firstStep = ReactDOM.render(React.createElement(firstStep, {domId:action}), container);
			action_obj.aerialActivityForm_planIdChange_Action_subscribe(aerialActivity_obj.firstStep);
			
			container = document.getElementById('secondStep_'+action);
			aerialActivity_obj.secondStep = ReactDOM.render(React.createElement(secondStep, {domId:action}), container);
			action_obj.equipmentList_select_Action_subscribe(aerialActivity_obj.secondStep);
			action_obj.equipmentList_deselect_Action_subscribe(aerialActivity_obj.secondStep);
			
			container = document.getElementById('thirdStep_'+action);
			aerialActivity_obj.thirdStep = ReactDOM.render(React.createElement(thirdStep, {domId:action}), container);
			action_obj.equipmentList_select_Action_subscribe(aerialActivity_obj.thirdStep);
			action_obj.equipmentList_deselect_Action_subscribe(aerialActivity_obj.thirdStep);
			action_obj.personList_select_Action_subscribe(aerialActivity_obj.thirdStep);
			action_obj.personList_deselect_Action_subscribe(aerialActivity_obj.thirdStep);
			//debugger;			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('firstStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('secondStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('thirdStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivitySteps_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivityEPList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivityForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivityTabs_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivityList_'+action);
			ReactDOM.unmountComponentAtNode(container);			
			container = document.getElementById('aerialActivityDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			action_obj.aerialActivityList_select_Action_handler = [];
			action_obj.aerialActivityList_deselect_Action_handler = [];
			action_obj.aerialActivitySteps_clickFinish_Action_handler = [];
			action_obj.aerialActivityEPList_add_Action_handler = [];
			action_obj.aerialActivityForm_planIdChange_Action_handler = [];
			action_obj.equipmentList_select_Action_handler = [];
			action_obj.equipmentList_deselect_Action_handler = [];
			action_obj.personList_select_Action_handler = [];
			action_obj.personList_deselect_Action_handler = [];

		},		
}

function aerialActivity(id){	
	aerialActivity_obj.initState(id);
	aerialActivity_obj.initReactComponent('aerialActivity');		
}
</script>
<div id="aerialActivityDialog_aerialActivity" title="檢視航拍活動資料" style="display: none;">
</div>
<script src="/Drone/js/operation/aerialActivity.main.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialActivity.form.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialActivity.steps.js" charset="utf-8"></script>