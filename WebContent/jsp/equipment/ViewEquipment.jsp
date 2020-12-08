<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
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
			var iamList = "<%=(String)session.getAttribute("設備管理")%>".split(",");
			var iamObj = checkIAM(iamList);	
			view_obj.stateButtons_equipment = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"equipment",domIdCN:"設備",canCheck:iamObj.canCheck,canApprove:iamObj.canApprove,canReject:iamObj.canReject}), container);
			action_obj.equipmentForm_load_Action_subscribe(view_obj.stateButtons_equipment);
			
			container = document.getElementById('equipmentForm_'+action);
			view_obj.equipmentForm = ReactDOM.render(React.createElement(equipmentForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.checkButton_click_Action_subscribe(view_obj.equipmentForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.equipmentForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.equipmentForm);
			
			container = document.getElementById('maintainList_'+action);
			view_obj.maintainList = ReactDOM.render(React.createElement(maintainList, {domId:action}), container);
			action_obj.maintainList_select_Action_subscribe(view_obj.maintainList);
			action_obj.maintainList_deselect_Action_subscribe(view_obj.maintainList);
			action_obj.checkButton_click_Action_subscribe(view_obj.maintainList);
			action_obj.approveButton_click_Action_subscribe(view_obj.maintainList);
			action_obj.rejectButton_click_Action_subscribe(view_obj.maintainList);
						
			container = document.getElementById('stateButtons_maintain');
			var iamList = "<%=(String)session.getAttribute("保養")%>".split(",");
			var iamObj = checkIAM(iamList);
			view_obj.stateButtons_maintain = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"maintain",domIdCN:"保養紀錄",canCheck:iamObj.canCheck,canApprove:iamObj.canApprove,canReject:iamObj.canReject}), container);
			action_obj.maintainList_select_Action_subscribe(view_obj.stateButtons_maintain);
			action_obj.maintainList_deselect_Action_subscribe(view_obj.stateButtons_maintain);
			
			container = document.getElementById('maintainForm_'+action);
			view_obj.maintainForm = ReactDOM.render(React.createElement(maintainForm, {domId:action,extraClass :"ui-state-disabled"}), container); 
			action_obj.maintainList_select_Action_subscribe(view_obj.maintainForm);
			action_obj.maintainList_deselect_Action_subscribe(view_obj.maintainForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.maintainForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.maintainForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.maintainForm);
						
			container = document.getElementById('modifyList_'+action);
			view_obj.modifyList = ReactDOM.render(React.createElement(modifyList, {domId:action}), container);
			action_obj.modifyList_select_Action_subscribe(view_obj.modifyList);
			action_obj.modifyList_deselect_Action_subscribe(view_obj.modifyList);
			action_obj.checkButton_click_Action_subscribe(view_obj.modifyList);
			action_obj.approveButton_click_Action_subscribe(view_obj.modifyList);
			action_obj.rejectButton_click_Action_subscribe(view_obj.modifyList);
			
			container = document.getElementById('stateButtons_modify');
			var iamList = "<%=(String)session.getAttribute("改裝")%>".split(",");
			var iamObj = checkIAM(iamList);
			view_obj.stateButtons_modify = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"modify",domIdCN:"改裝紀錄",canCheck:iamObj.canCheck,canApprove:iamObj.canApprove,canReject:iamObj.canReject}), container);
			action_obj.modifyList_select_Action_subscribe(view_obj.stateButtons_modify);
			action_obj.modifyList_deselect_Action_subscribe(view_obj.stateButtons_modify);
			
			container = document.getElementById('modifyForm_'+action);
			view_obj.modifyForm = ReactDOM.render(React.createElement(modifyForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.modifyList_select_Action_subscribe(view_obj.modifyForm);
			action_obj.modifyList_deselect_Action_subscribe(view_obj.modifyForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.modifyForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.modifyForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.modifyForm);
			
			
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
			action_obj.updateButton_click_Action_handler = [];

			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = '';
				}else{
					store_obj[index] = {};
				}
		    });
		},		
};

function viewEquipment(id){	
	view_obj.initState(id,'view');
	view_obj.initReactComponent('view');	
}
</script>
<div id="equipmentDialog_view" title="檢視設備" style="display: none;">
</div>
<script src="../../js/equipment/viewEquipment.js" charset="utf-8"></script>
<script src="../../js/equipment/maintain.js" charset="utf-8"></script>
<script src="../../js/equipment/modify.js" charset="utf-8"></script>
<script src="../../js/common/stateButtons.js" charset="utf-8"></script>