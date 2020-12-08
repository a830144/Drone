<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var modify_obj = {		
		initState : function (id,domId){
			store_obj.equipmentId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.equipmentId = '';
			store_obj.domId = '';
		},			
		initReactComponent : function (action){	
			var container = document.getElementById('modifyDialog_'+action);
			modify_obj.modifyDialog = ReactDOM.render(React.createElement(modifyDialog, {domId:action}), container);
			action_obj.modifyList_select_Action_subscribe(modify_obj.modifyDialog);
			action_obj.modifyList_deselect_Action_subscribe(modify_obj.modifyDialog);
			
			container = document.getElementById('modifyList_'+action);
			modify_obj.modifyList = ReactDOM.render(React.createElement(modifyList, {domId:action}), container);
			action_obj.modifyList_select_Action_subscribe(modify_obj.modifyList);
			action_obj.modifyList_deselect_Action_subscribe(modify_obj.modifyList);
			action_obj.updateButton_click_Action_subscribe(modify_obj.modifyList);
			
			container = document.getElementById('modifyForm_'+action);
			modify_obj.modifyForm = ReactDOM.render(React.createElement(modifyForm, {domId:action}), container);
			action_obj.modifyList_select_Action_subscribe(modify_obj.modifyForm);
			action_obj.modifyList_deselect_Action_subscribe(modify_obj.modifyForm);
			action_obj.updateButton_click_Action_subscribe(modify_obj.modifyForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('modifyForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('modifyDialog_'+action);
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

function modifyEquipment(id){	
	modify_obj.initState(id,'modify');
	modify_obj.initReactComponent('modify');		
}
</script>
<div id="modifyDialog_modify" title="設備維護" style="display: none;">
</div>
<script src="../../js/equipment/modify.js" charset="utf-8"></script>


