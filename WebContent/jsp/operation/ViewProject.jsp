<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var view_obj = {		
		initState : function (id,domId){
			store_obj.projectId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.projectId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('projectDialog_'+action);
			view_obj.projectDialog = ReactDOM.render(React.createElement(projectDialog, {domId:action}), container);			
			
			container = document.getElementById('projectTabs_'+action);
			view_obj.tabs = ReactDOM.render(React.createElement(projectTabs, {domId:action}), container);							
			
			container = document.getElementById('stateButtons_project');
			view_obj.stateButtons_project = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"project",domIdCN:"專案"}), container);
			action_obj.projectForm_load_Action_subscribe(view_obj.stateButtons_project);
			
			container = document.getElementById('projectForm_'+action);
			view_obj.projectForm = ReactDOM.render(React.createElement(projectForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.checkButton_click_Action_subscribe(view_obj.projectForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.projectForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.projectForm); 
						
			container = document.getElementById('aerialPlanList_'+action);
			view_obj.aerialPlanList = ReactDOM.render(React.createElement(aerialPlanList, {domId:action}), container);
			
			container = document.getElementById('stateButtons_aerialPlan');
			view_obj.stateButtons_aerialPlan = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"aerialPlan",domIdCN:"航拍計畫紀錄"}), container);
			action_obj.aerialPlanList_select_Action_subscribe(view_obj.stateButtons_aerialPlan);
			action_obj.aerialPlanList_deselect_Action_subscribe(view_obj.stateButtons_aerialPlan); 
			
			container = document.getElementById('aerialPlanForm_'+action);
			view_obj.aerialPlanForm = ReactDOM.render(React.createElement(aerialPlanForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.aerialPlanList_select_Action_subscribe(view_obj.aerialPlanForm);
			action_obj.aerialPlanList_deselect_Action_subscribe(view_obj.aerialPlanForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.aerialPlanForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.aerialPlanForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.aerialPlanForm); 
			
			container = document.getElementById('aerialActivityList_'+action);
			view_obj.aerailActivityList = ReactDOM.render(React.createElement(aerialActivityList, {domId:action}), container);
						
			container = document.getElementById('stateButtons_aerialActivity');
			view_obj.stateButtons_aerialActivity = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"aerialActivity",domIdCN:"航拍活動紀錄"}), container);
			action_obj.aerialActivityList_select_Action_subscribe(view_obj.stateButtons_aerialActivity);
			action_obj.aerialActivityList_deselect_Action_subscribe(view_obj.stateButtons_aerialActivity); 
			
			container = document.getElementById('aerialActivityForm_'+action);
			view_obj.aerialActivityForm = ReactDOM.render(React.createElement(aerialActivityForm, {domId:action,extraClass :"ui-state-disabled"}), container); 
			action_obj.aerialActivityList_select_Action_subscribe(view_obj.aerialActivityForm);
			action_obj.aerialActivityList_deselect_Action_subscribe(view_obj.aerialActivityForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.aerialActivityForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.aerialActivityForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.aerialActivityForm); 
		},
		removeReactComponent : function (action){
			var container = document.getElementById('aerialActivityForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_aerialActivity');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialActivityList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_aerialPlan');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('aerialPlanList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('projectForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_project');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('projectTabs_'+action);
			ReactDOM.unmountComponentAtNode(container);				
			container = document.getElementById('projectDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			action_obj.projectForm_load_Action_handler = [];
			action_obj.aerialPlanList_select_Action_handler = [];
			action_obj.aerialPlanList_deselect_Action_handler = [];
			action_obj.aerialPlanSteps_clickFinish_Action_handler = [];
			action_obj.aerialPlanEPList_add_Action_handler = [];
			action_obj.equipmentAPList_select_Action_handler = [];
			action_obj.equipmentAPList_deselect_Action_handler = [];
			action_obj.personAPList_select_Action_handler = [];
			action_obj.personAPList_deselect_Action_handler = [];
			action_obj.equipmentForm_load_Action_handler = [];
			action_obj.aerialActivityList_select_Action_handler = [];
			action_obj.aerialActivityList_deselect_Action_handler = [];
			action_obj.aerialActivitySteps_clickFinish_Action_handler = [];
			action_obj.aerialActivityEPList_add_Action_handler = [];
			action_obj.aerialActivityForm_planIdChange_Action_handler = [];
			action_obj.equipmentList_select_Action_handler = [];
			action_obj.equipmentList_deselect_Action_handler = [];
			action_obj.personList_select_Action_handler = [];
			action_obj.personList_deselect_Action_handler = [];
			action_obj.checkButton_click_Action_handler = [];
			action_obj.approveButton_click_Action_handler = [];
			action_obj.rejectButton_click_Action_handler = [];
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });

		},		
};

function viewProject(id){	
	view_obj.initState(id,'view');
	view_obj.initReactComponent('view');	
}
</script>
<div id="projectDialog_view" title="檢視專案" style="display: none;">
</div>
<script src="/Drone/js/operation/viewProject.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialPlan.main.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialPlan.form.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialPlan.steps.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialActivity.main.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialActivity.form.js" charset="utf-8"></script>
<script src="/Drone/js/operation/aerialActivity.steps.js" charset="utf-8"></script>
<script src="/Drone/js/common/stateButtons.js" charset="utf-8"></script>