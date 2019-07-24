<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
var aerialPlan_obj = {		
		
		addOrUpdate : function (action){
			  $("#aerialPlan-form").find("#projectId").prop("disabled", false);
			  $("#aerialPlan-form").find("#name").prop("disabled", false);
			  $("#aerialPlan-form").find("#aerialPlanId").prop("disabled",false);
			  $("#aerialPlan-form").find("#usage").prop("disabled",false);							
			  			  
			  var jsonObject = {};
			  jsonObject.projectId =$("#aerialPlan-form").find("#projectId").val();
			  jsonObject.name = $("#aerialPlan-form").find("#name").val();
			  jsonObject.aerialPlanId = $("#aerialPlan-form").find("#aerialPlanId").val();
			  jsonObject.usage = $("#aerialPlan-form").find("#usage").val();
			  jsonObject.aerialPlanStartDate = $("#aerialPlan-form").find("#aerialPlanStartDate").val();
			  jsonObject.aerialPlanEndDate = $("#aerialPlan-form").find("#aerialPlanEndDate").val();
			  jsonObject.aerialPlanStartDate = $("#aerialPlan-form").find("#aerialPlanStartDate").val();
			  jsonObject.aerialPlanEndDate = $("#aerialPlan-form").find("#aerialPlanEndDate").val();
			  
			  var arr = new Array();
			  jsonObject.equipmentPersonArray = arr;
			  var table = $('#aerialPlan-form').DataTable();
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
				 url : action==='update'?"/Drone/operation/UpdateAerialPlanProcess":"/Drone/operation/AddAerialPlanProcess",
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
			var container = document.getElementById('aerialPlanDialog_'+action);
			aerialPlan_obj.aerialPlanDialog = ReactDOM.render(React.createElement(aerialPlanDialog, {domId:action}), container);
			action_obj.aerialPlanList_select_Action_subscribe(aerialPlan_obj.aerialPlanDialog);
			action_obj.aerialPlanList_deselect_Action_subscribe(aerialPlan_obj.aerialPlanDialog);
			
			container = document.getElementById('aerialPlanList_'+action);
			aerialPlan_obj.aerialPlanList = ReactDOM.render(React.createElement(aerialPlanList, {domId:action}), container);
			
			container = document.getElementById('aerialPlanTabs_'+action);
			aerialPlan_obj.tabs = ReactDOM.render(React.createElement(aerialPlanTabs, {domId:action}), container);
			
			container = document.getElementById('aerialPlanEPList_'+action);
			aerialPlan_obj.aerialPlanEPList = ReactDOM.render(React.createElement(aerialPlanEPList, {domId:action}), container);
			action_obj.aerialPlanSteps_clickFinish_Action_subscribe(aerialPlan_obj.aerialPlanEPList);
			action_obj.aerialPlanList_select_Action_subscribe(aerialPlan_obj.aerialPlanEPList);
			action_obj.aerialPlanList_deselect_Action_subscribe(aerialPlan_obj.aerialPlanEPList); 
			
			container = document.getElementById('aerialPlanForm_'+action);
			aerialPlan_obj.aerialPlanForm = ReactDOM.render(React.createElement(aerialPlanForm, {domId:action,projectId:store_obj.projectId}), container); 
			action_obj.aerialPlanList_select_Action_subscribe(aerialPlan_obj.aerialPlanForm);
			action_obj.aerialPlanList_deselect_Action_subscribe(aerialPlan_obj.aerialPlanForm); 
			
			container = document.getElementById('otherForm_'+action);
			aerialPlan_obj.otherForm = ReactDOM.render(React.createElement(otherForm, {domId:action,projectId:store_obj.projectId}), container);
			action_obj.aerialPlanList_select_Action_subscribe(aerialPlan_obj.otherForm);
			action_obj.aerialPlanList_deselect_Action_subscribe(aerialPlan_obj.otherForm);
			
			container = document.getElementById('aerialPlanSteps_'+action);
			aerialPlan_obj.steps = ReactDOM.render(React.createElement(aerialPlanSteps, {domId:action}), container);
			action_obj.aerialPlanEPList_add_Action_subscribe(aerialPlan_obj.steps);
			
			container = document.getElementById('p_firstStep_'+action);
			aerialPlan_obj.firstStep = ReactDOM.render(React.createElement(p_firstStep, {domId:action}), container);
			
			container = document.getElementById('p_secondStep_'+action);
			aerialPlan_obj.secondStep = ReactDOM.render(React.createElement(p_secondStep, {domId:action}), container);
			
			container = document.getElementById('p_thirdStep_'+action);
			aerialPlan_obj.thirdStep = ReactDOM.render(React.createElement(p_thirdStep, {domId:action}), container);
			
			container = document.getElementById('p_fourthStep_'+action);
			aerialPlan_obj.fourthStep = ReactDOM.render(React.createElement(p_fourthStep, {domId:action}), container);
			action_obj.equipmentAPList_select_Action_subscribe(aerialPlan_obj.fourthStep);
			action_obj.equipmentAPList_deselect_Action_subscribe(aerialPlan_obj.fourthStep);
			action_obj.personAPList_select_Action_subscribe(aerialPlan_obj.fourthStep);
			action_obj.personAPList_deselect_Action_subscribe(aerialPlan_obj.fourthStep);
			
			//debugger;			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('p_firstStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('p_secondStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('p_thirdStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('p_fourthStep_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanSteps_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanEPList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanTabs_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanList_'+action);
			ReactDOM.unmountComponentAtNode(container);			
			container = document.getElementById('aerialPlanDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			action_obj.aerialPlanList_select_Action_handler = [];
			action_obj.aerialPlanList_deselect_Action_handler = [];
			action_obj.aerialPlanSteps_clickFinish_Action_handler = [];
			action_obj.aerialPlanEPList_add_Action_handler = [];
			action_obj.equipmentAPList_select_Action_handler = [];
			action_obj.equipmentAPList_deselect_Action_handler = [];
			action_obj.personAPList_select_Action_handler = [];
			action_obj.personAPList_deselect_Action_handler = [];

		},		
}

function aerialPlan(id){	
	aerialPlan_obj.initState(id);
	aerialPlan_obj.initReactComponent("aerialPlan");		
}

</script>
<div id="aerialPlanDialog_aerialPlan" title="檢視航拍計畫資料" style="display: none;">
</div>
<script src="/Drone/js/operation/aerialPlan.main.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialPlan.form.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialPlan.steps.js" charset="utf-8"></script>