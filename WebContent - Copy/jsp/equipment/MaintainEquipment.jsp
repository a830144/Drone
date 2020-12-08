<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var maintain_obj = {		
		initState : function (id,domId){
			store_obj.equipmentId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.equipmentId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('maintainDialog_'+action);
			maintain_obj.maintainDialog = ReactDOM.render(React.createElement(maintainDialog, {domId:action}), container);
			action_obj.maintainList_select_Action_subscribe(maintain_obj.maintainDialog);
			action_obj.maintainList_deselect_Action_subscribe(maintain_obj.maintainDialog);
			
			container = document.getElementById('maintainList_'+action);
			maintain_obj.maintainList = ReactDOM.render(React.createElement(maintainList, {domId:action}), container);
			action_obj.maintainList_select_Action_subscribe(maintain_obj.maintainList);
			action_obj.maintainList_deselect_Action_subscribe(maintain_obj.maintainList);
			action_obj.updateButton_click_Action_subscribe(maintain_obj.maintainList);
			
			container = document.getElementById('maintainForm_'+action);
			maintain_obj.maintainForm = ReactDOM.render(React.createElement(maintainForm, {domId:action}), container);
			action_obj.maintainList_select_Action_subscribe(maintain_obj.maintainForm);
			action_obj.maintainList_deselect_Action_subscribe(maintain_obj.maintainForm);
			action_obj.updateButton_click_Action_subscribe(maintain_obj.maintainForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('maintainForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('maintainList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('maintainDialog_'+action);
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

function maintainEquipment(id){	
	maintain_obj.initState(id);
	maintain_obj.initReactComponent('maintain');
}
</script>
<div id="maintainDialog_maintain" title="設備維護" style="display: none;">
</div>
<script src="../../js/equipment/maintain.js" charset="utf-8"></script>


