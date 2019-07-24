<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var event_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('eventDialog_'+action);
			event_obj.eventDialog = ReactDOM.render(React.createElement(eventDialog, {domId:action}), container);
			action_obj.eventList_select_Action_subscribe(event_obj.eventDialog);
			action_obj.eventList_deselect_Action_subscribe(event_obj.eventDialog);
			
			container = document.getElementById('eventList_'+action);
			event_obj.eventList = ReactDOM.render(React.createElement(eventList, {domId:action}), container);
			
			container = document.getElementById('eventForm_'+action);
			event_obj.eventForm = ReactDOM.render(React.createElement(eventForm, {domId:action}), container);
			action_obj.eventList_select_Action_subscribe(event_obj.eventForm);
			action_obj.eventList_deselect_Action_subscribe(event_obj.eventForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('eventForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('eventList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('eventDialog_'+action);
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

function eventInPerson(id){	
	event_obj.initState(id,'event');
	event_obj.initReactComponent('event');		
}
</script>
<div id="eventDialog_event" title="人員特殊事蹟維護" style="display: none;">
</div>
<script src="/Drone/js/person/event.js" charset="utf-8"></script>


