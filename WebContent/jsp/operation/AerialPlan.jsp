<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
var aerialPlan_obj = {		
		
		addOrUpdate : function (action){
			  var form = $("#aerialPlanForm_aerialPlan_sub");
			  var form_other = $("#otherForm_aerialPlan_sub");
			  var validator =form.validate({
      	  			rules: {
      	  				aerialPlanStartDate: {
      	  				 	required: true,
      	  				 	maxlength: 10
      	  				},
      	  				aerialPlanEndDate: {
      	  				 	required: true,
      	  				 	maxlength: 10
      	  				},      	  			      	  			
      	  			}
      	      });
			  var validator_other =form_other.validate({
    	  			rules: {
    	  				takeOffLocationName: {
    	  				 	required: true,
    	  				 	maxlength: 25
    	  				}      	  			      	  			
    	  			}
    	      });	
			  if (form.valid() && form_other.valid()) {
			  
			  	var jsonObject = {};
			  	jsonObject.projectId =$(form).find("#projectId").val();
			  	jsonObject.name = $(form).find("#name").val();
			  	jsonObject.aerialPlanId = $(form).find("#aerialPlanId").val();
			  	jsonObject.usage = $(form).find("#usage").val();
			  	jsonObject.aerialPlanStartDate = $(form).find("#aerialPlanStartDate").val();
			  	jsonObject.aerialPlanEndDate = $(form).find("#aerialPlanEndDate").val();
			  	jsonObject.photo = $(form).find("#photo").val();
			  	jsonObject.state = $(form).find("#state").val();
			
			  
			 	var tableName = "#aerialPlanEPList_aerialPlan_sub";
			  	var arr = new Array();
			  	jsonObject.equipmentsArray = arr;
			  	var table = $(tableName).DataTable();			  
			  	table.rows().eq(0).each( function ( index ) {
			    	var row = table.row( index );
			    	var cell = table.cell( index ,3);
					var equipmentId = cell.data();									
					var personId_1 = $("#EProw"+index+"col"+6).val();					
					var personId_2 = $("#EProw"+index+"col"+7).val(); 
					
					jsonObject.equipmentsArray.push({
						"equipmentId":equipmentId,
						"personId_1":personId_1,
						"personId_2":personId_2
					});
			  	} );
			  
			  										  			  
			  	jsonObject.amslFrom =$(form_other).find("#amslFrom").val();
			  	jsonObject.amslTo = $(form_other).find("#amslTo").val();
			  	jsonObject.agl = $(form_other).find("#agl").val();
			  	jsonObject.takeOffNDegree = $(form_other).find("#takeOffNDegree").val();
			  	jsonObject.takeOffNMinute = $(form_other).find("#takeOffNMinute").val();
			  	jsonObject.takeOffNSecond = $(form_other).find("#takeOffNSecond").val();
			  	jsonObject.takeOffEDegree = $(form_other).find("#takeOffEDegree").val();
			  	jsonObject.takeOffEMinute = $(form_other).find("#takeOffEMinute").val();
			  	jsonObject.takeOffESecond = $(form_other).find("#takeOffESecond").val();			  
			  	jsonObject.areaCenterNDegree = $(form_other).find("#areaCenterNDegree").val();
			  	jsonObject.areaCenterNMinute = $(form_other).find("#areaCenterNMinute").val();
			  	jsonObject.areaCenterNSecond = $(form_other).find("#areaCenterNSecond").val();
			  	jsonObject.areaCenterEDegree = $(form_other).find("#areaCenterEDegree").val();
			  	jsonObject.areaCenterEMinute = $(form_other).find("#areaCenterEMinute").val();
			  	jsonObject.areaCenterESecond = $(form_other).find("#areaCenterESecond").val();
			  	jsonObject.operationDiameter = $(form_other).find("#operationDiameter").val();
			  
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
			  	});
			  	
	
				var fileData = new FormData(document.getElementById("aerialPlanForm_aerialPlan_sub"));				 
			    fileData.append("action","aerialPlan");
					 
			    $.ajax({
					   type: "POST",
					   enctype: 'multipart/form-data',
					   url: "/Drone/other/uploadMultipleFile",
					   data: fileData,
					   processData: false,
					   contentType: false,
					   cache: false,
					   timeout: 600000,
					   success: function (data) {
					         console.log("SUCCESS : ", data);
					   },
					   error: function (e) {
					         console.log("ERROR : ", e);
					   }
			    });		
			  }
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
			action_obj.aerialPlanList_select_Action_subscribe(aerialPlan_obj.aerialPlanEPList);
			action_obj.aerialPlanList_deselect_Action_subscribe(aerialPlan_obj.aerialPlanEPList); 
			action_obj.aerialPlanEPList_add_Action_subscribe(aerialPlan_obj.aerialPlanEPList);
			action_obj.aerialPlanSteps_clickFinish_Action_subscribe(aerialPlan_obj.aerialPlanEPList);
			
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
			action_obj.aerialPlanSteps_clickFinish_Action_subscribe(aerialPlan_obj.steps);
			
			container = document.getElementById('p_firstStep_'+action);
			aerialPlan_obj.firstStep = ReactDOM.render(React.createElement(p_firstStep, {domId:action}), container);
			
			container = document.getElementById('p_secondStep_'+action);
			aerialPlan_obj.secondStep = ReactDOM.render(React.createElement(p_secondStep, {domId:action}), container);
			
			container = document.getElementById('p_thirdStep_'+action);
			aerialPlan_obj.thirdStep = ReactDOM.render(React.createElement(p_thirdStep, {domId:action}), container);
			action_obj.equipmentAPList_select_Action_subscribe(aerialPlan_obj.thirdStep);
			action_obj.equipmentAPList_deselect_Action_subscribe(aerialPlan_obj.thirdStep);
			action_obj.operationLimit_check_Action_subscribe(aerialPlan_obj.thirdStep);
			
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
			action_obj.operationLimit_check_Action_handler = [];
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });

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