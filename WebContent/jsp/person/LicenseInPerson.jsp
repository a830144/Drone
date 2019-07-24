<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var license_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('licenseDialog_'+action);
			license_obj.licenseDialog = ReactDOM.render(React.createElement(licenseDialog, {domId:action}), container);
			action_obj.licenseList_select_Action_subscribe(license_obj.licenseDialog);
			action_obj.licenseList_deselect_Action_subscribe(license_obj.licenseDialog);
			
			container = document.getElementById('licenseList_'+action);
			license_obj.licenseList = ReactDOM.render(React.createElement(licenseList, {domId:action}), container);
			
			container = document.getElementById('licenseForm_'+action);
			license_obj.licenseForm = ReactDOM.render(React.createElement(licenseForm, {domId:action}), container);
			action_obj.licenseList_select_Action_subscribe(license_obj.licenseForm);
			action_obj.licenseList_deselect_Action_subscribe(license_obj.licenseForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('licenseForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('licenseList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('licenseDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			action_obj.personForm_load_Action_handler = [];
			action_obj.licenseList_select_Action_handler = [];
			action_obj.licenseList_deselect_Action_handler = [];
			action_obj.trainingList_select_Action_handler = [];
			action_obj.trainingList_deselect_Action_handler = [];
			action_obj.eventList_select_Action_handler = [];
			action_obj.eventList_deselect_Action_handler = [];
			action_obj.checkButton_click_Action_handler = [];
			action_obj.approveButton_click_Action_handler = [];
			action_obj.rejectButton_click_Action_handler = [];

		},		
};

function licenseInPerson(id){	
	license_obj.initState(id,'license');
	license_obj.initReactComponent('license');		
}
</script>
<div id="licenseDialog_license" title="人員操作證維護" style="display: none;">
</div>
<script src="/Drone/js/person/license.js" charset="utf-8"></script>


