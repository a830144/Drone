<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var view_obj = {		
		initState : function (id,domId){
			store_obj.equipmentId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.equipmentId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('equipmentDialog_'+action);
			view_obj.equipmentDialog = ReactDOM.render(React.createElement(equipmentDialog, {domId:action}), container);			
			container = document.getElementById('tabs_'+action);
			view_obj.tabs = ReactDOM.render(React.createElement(tabs, {domId:action}), container);							
			
			container = document.getElementById('stateButtons_equipment');
			view_obj.stateButtons_equipment = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"equipment",domIdCN:"設備"}), container);
			action_obj.equipmentForm_load_Action_subscribe(view_obj.stateButtons_equipment);
			
			container = document.getElementById('equipmentForm_'+action);
			view_obj.equipmentForm = ReactDOM.render(React.createElement(equipmentForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.checkButton_click_Action_subscribe(view_obj.equipmentForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.equipmentForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.equipmentForm);
						
			container = document.getElementById('modifyList_'+action);
			view_obj.modifyList = ReactDOM.render(React.createElement(modifyList, {domId:action}), container);
			
			container = document.getElementById('stateButtons_modify');
			view_obj.stateButtons_modify = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"modify",domIdCN:"改裝紀錄"}), container);
			action_obj.modifyList_select_Action_subscribe(view_obj.stateButtons_modify);
			action_obj.modifyList_deselect_Action_subscribe(view_obj.stateButtons_modify);
			
			container = document.getElementById('modifyForm_'+action);
			view_obj.modifyForm = ReactDOM.render(React.createElement(modifyForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.modifyList_select_Action_subscribe(view_obj.modifyForm);
			action_obj.modifyList_deselect_Action_subscribe(view_obj.modifyForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.modifyForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.modifyForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.modifyForm);
			
			container = document.getElementById('maintainList_'+action);
			view_obj.maintainList = ReactDOM.render(React.createElement(maintainList, {domId:action}), container);
						
			container = document.getElementById('stateButtons_maintain');
			view_obj.stateButtons_maintain = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"maintain",domIdCN:"保養紀錄"}), container);
			action_obj.maintainList_select_Action_subscribe(view_obj.stateButtons_maintain);
			action_obj.maintainList_deselect_Action_subscribe(view_obj.stateButtons_maintain);
			
			container = document.getElementById('maintainForm_'+action);
			view_obj.maintainForm = ReactDOM.render(React.createElement(maintainForm, {domId:action,extraClass :"ui-state-disabled"}), container); 
			action_obj.maintainList_select_Action_subscribe(view_obj.maintainForm);
			action_obj.maintainList_deselect_Action_subscribe(view_obj.maintainForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.maintainForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.maintainForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.maintainForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('maintainForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_maintain');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('maintainList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_modify');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('equipmentForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_equipment');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('tabs_'+action);
			ReactDOM.unmountComponentAtNode(container);				
			container = document.getElementById('equipmentDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			action_obj.equipmentForm_load_Action_handler = [];
			action_obj.maintainList_select_Action_handler = [];
			action_obj.maintainList_deselect_Action_handler = [];
			action_obj.modifyList_select_Action_handler = [];
			action_obj.modifyList_deselect_Action_handler = [];
			action_obj.checkButton_click_Action_handler = [];
			action_obj.approveButton_click_Action_handler = [];
			action_obj.rejectButton_click_Action_handler = [];

		},		
};

function viewEquipment(id){	
	view_obj.initState(id,'view');
	view_obj.initReactComponent('view');	
}
</script>
<div id="equipmentDialog_view" title="檢視設備" style="display: none;">
</div>
<script src="/Drone/js/equipment/viewEquipment.js" charset="utf-8"></script>
<script src="/Drone/js/equipment/maintain.js" charset="utf-8"></script>
<script src="/Drone/js/equipment/modify.js" charset="utf-8"></script>
<script src="/Drone/js/common/stateButtons.js" charset="utf-8"></script>