<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var view_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('personDialog_'+action);
			view_obj.personDialog = ReactDOM.render(React.createElement(personDialog, {domId:action}), container);			
			container = document.getElementById('tabs_'+action);
			view_obj.tabs = ReactDOM.render(React.createElement(tabs, {domId:action}), container);							
			
			container = document.getElementById('stateButtons_person');
			view_obj.stateButtons_person = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"person",domIdCN:"設備"}), container);
			action_obj.personForm_load_Action_subscribe(view_obj.stateButtons_person);
			
			container = document.getElementById('personForm_'+action);
			view_obj.personForm = ReactDOM.render(React.createElement(personForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.checkButton_click_Action_subscribe(view_obj.personForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.personForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.personForm);
						
			container = document.getElementById('licenseList_'+action);
			view_obj.licenseList = ReactDOM.render(React.createElement(licenseList, {domId:action}), container);
			
			container = document.getElementById('stateButtons_license');
			view_obj.stateButtons_license = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"license",domIdCN:"操作證紀錄"}), container);
			action_obj.licenseList_select_Action_subscribe(view_obj.stateButtons_license);
			action_obj.licenseList_deselect_Action_subscribe(view_obj.stateButtons_license);
			
			container = document.getElementById('licenseForm_'+action);
			view_obj.licenseForm = ReactDOM.render(React.createElement(licenseForm, {domId:action,extraClass :"ui-state-disabled"}), container);
			action_obj.licenseList_select_Action_subscribe(view_obj.licenseForm);
			action_obj.licenseList_deselect_Action_subscribe(view_obj.licenseForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.licenseForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.licenseForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.licenseForm);
			
			container = document.getElementById('trainingList_'+action);
			view_obj.trainingList = ReactDOM.render(React.createElement(trainingList, {domId:action}), container);
						
			container = document.getElementById('stateButtons_training');
			view_obj.stateButtons_training = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"training",domIdCN:"訓練紀錄"}), container);
			action_obj.trainingList_select_Action_subscribe(view_obj.stateButtons_training);
			action_obj.trainingList_deselect_Action_subscribe(view_obj.stateButtons_training);
			
			container = document.getElementById('trainingForm_'+action);
			view_obj.trainingForm = ReactDOM.render(React.createElement(trainingForm, {domId:action,extraClass :"ui-state-disabled"}), container); 
			action_obj.trainingList_select_Action_subscribe(view_obj.trainingForm);
			action_obj.trainingList_deselect_Action_subscribe(view_obj.trainingForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.trainingForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.trainingForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.trainingForm);
			
			container = document.getElementById('eventList_'+action);
			view_obj.eventList = ReactDOM.render(React.createElement(eventList, {domId:action}), container);
						
			container = document.getElementById('stateButtons_event');
			view_obj.stateButtons_event = ReactDOM.render(React.createElement(stateButtons, {domId:action,typeId:"event",domIdCN:"特殊事蹟紀錄"}), container);
			action_obj.eventList_select_Action_subscribe(view_obj.stateButtons_event);
			action_obj.eventList_deselect_Action_subscribe(view_obj.stateButtons_event);
			
			container = document.getElementById('eventForm_'+action);
			view_obj.eventForm = ReactDOM.render(React.createElement(eventForm, {domId:action,extraClass :"ui-state-disabled"}), container); 
			action_obj.eventList_select_Action_subscribe(view_obj.eventForm);
			action_obj.eventList_deselect_Action_subscribe(view_obj.eventForm);
			action_obj.checkButton_click_Action_subscribe(view_obj.eventForm);
			action_obj.approveButton_click_Action_subscribe(view_obj.eventForm);
			action_obj.rejectButton_click_Action_subscribe(view_obj.eventForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('eventForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_event');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('eventList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('trainingForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_training');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('trainingList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('licenseForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_license');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('licenseList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('personForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('stateButtons_person');
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('tabs_'+action);
			ReactDOM.unmountComponentAtNode(container);				
			container = document.getElementById('personDialog_'+action);
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

function viewPerson(id){	
	view_obj.initState(id,'view');
	view_obj.initReactComponent('view');	
}
</script>
<div id="personDialog_view" title="檢視人員" style="display: none;">
</div>
<script src="/Drone/js/person/viewPerson.js" charset="utf-8"></script>
<script src="/Drone/js/person/license.js" charset="utf-8"></script>
<script src="/Drone/js/person/training.js" charset="utf-8"></script>
<script src="/Drone/js/person/event.js" charset="utf-8"></script>
<script src="/Drone/js/common/stateButtons.js" charset="utf-8"></script>