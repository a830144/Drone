<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var training_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('trainingDialog_'+action);
			training_obj.trainingDialog = ReactDOM.render(React.createElement(trainingDialog, {domId:action}), container);
			action_obj.trainingList_select_Action_subscribe(training_obj.trainingDialog);
			action_obj.trainingList_deselect_Action_subscribe(training_obj.trainingDialog);
			
			container = document.getElementById('trainingList_'+action);
			training_obj.trainingList = ReactDOM.render(React.createElement(trainingList, {domId:action}), container);
			action_obj.trainingList_select_Action_subscribe(training_obj.trainingList);
			action_obj.trainingList_deselect_Action_subscribe(training_obj.trainingList);
			action_obj.updateButton_click_Action_subscribe(training_obj.trainingList);
			
			container = document.getElementById('trainingForm_'+action);
			training_obj.trainingForm = ReactDOM.render(React.createElement(trainingForm, {domId:action}), container);
			action_obj.trainingList_select_Action_subscribe(training_obj.trainingForm);
			action_obj.trainingList_deselect_Action_subscribe(training_obj.trainingForm);
			action_obj.updateButton_click_Action_subscribe(training_obj.trainingForm);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('trainingForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('trainingList_'+action);
			ReactDOM.unmountComponentAtNode(container);
			container = document.getElementById('trainingDialog_'+action);
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

function trainingInPerson(id){	
	training_obj.initState(id,'training');
	training_obj.initReactComponent('training');		
}
</script>
<div id="trainingDialog_training" title="人員訓練維護" style="display: none;">
</div>
<script src="../../js/person/training.js" charset="utf-8"></script>


