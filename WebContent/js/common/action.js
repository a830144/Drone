var action_obj = {
	equipmentForm_load_Action_handler : [],
	equipmentForm_load_Action_subscribe : function(fn) {
		action_obj.equipmentForm_load_Action_handler.push(fn);
    },		
	equipmentForm_load_Action : function(state,equipmentId){
		store_obj.state["equipment"] = state;
		store_obj.typeId = "equipment";
		store_obj.targetId["equipment"] = equipmentId;
		action_obj.equipmentForm_load_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)

	},
	
	personForm_load_Action_handler : [],
	personForm_load_Action_subscribe : function(fn) {
		action_obj.personForm_load_Action_handler.push(fn);
    },		
    personForm_load_Action : function(state,personId){
		store_obj.state["person"] = state;
		store_obj.typeId = "person";
		store_obj.targetId["person"] = personId;
		action_obj.personForm_load_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)

	},
	
	projectForm_load_Action_handler : [],
	projectForm_load_Action_subscribe : function(fn) {
		action_obj.projectForm_load_Action_handler.push(fn);
    },		
    projectForm_load_Action : function(state,projectId){
		store_obj.state["project"] = state;
		store_obj.typeId = "project";
		store_obj.targetId["project"] = projectId;
		action_obj.projectForm_load_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)

	},
	
	maintainList_select_Action_handler : [],
	maintainList_select_Action_subscribe : function(fn) {
		action_obj.maintainList_select_Action_handler.push(fn);
    },    
	maintainList_select_Action : function(maintenanceId,state) {
		store_obj.crud = 'U';
		store_obj.maintenanceId = maintenanceId;
		store_obj.typeId = "maintain";
		store_obj.targetId["maintain"] = maintenanceId;
		store_obj.state["maintain"] = state;
		//notify
		action_obj.maintainList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	maintainList_deselect_Action_handler : [],
	maintainList_deselect_Action_subscribe : function(fn) {
		action_obj.maintainList_deselect_Action_handler.push(fn);
    },
	maintainList_deselect_Action : function(maintenanceId) {
		store_obj.crud = 'C';
		store_obj.maintenanceId = '-';
		store_obj.typeId = '-';
		store_obj.targetId["maintain"] = '-';
		store_obj.state["maintain"] = '';
		//notify
		action_obj.maintainList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},

	modifyList_select_Action_handler : [],
	modifyList_select_Action_subscribe : function(fn) {
		action_obj.modifyList_select_Action_handler.push(fn);
    },
	modifyList_select_Action : function(modificationId,state) {
		store_obj.crud = 'U';
		store_obj.modificationId = modificationId;
		store_obj.typeId = "modify";
		store_obj.targetId["modify"] = modificationId;
		store_obj.state["modify"] = state;
		//notify
		action_obj.modifyList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	modifyList_deselect_Action_handler : [],
	modifyList_deselect_Action_subscribe : function(fn) {
		action_obj.modifyList_deselect_Action_handler.push(fn);
    },
	modifyList_deselect_Action : function(modificationId) {
		store_obj.crud = 'C';
		store_obj.modificationId = '-';
		store_obj.typeId = "-";
		store_obj.targetId["modify"] = '-';
		store_obj.state["modify"] = '';
		//notify
		action_obj.modifyList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	checkButton_click_Action_handler : [],
	checkButton_click_Action_subscribe : function(fn) {
		action_obj.checkButton_click_Action_handler.push(fn);
    },
	checkButton_click_Action : function(typeId) {
		store_obj.state[typeId] = "CHECKED";
		//notify
		action_obj.checkButton_click_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},

	approveButton_click_Action_handler : [],
	approveButton_click_Action_subscribe : function(fn) {
		action_obj.approveButton_click_Action_handler.push(fn);
    },
	approveButton_click_Action : function(typeId) {
		store_obj.state[typeId] = "APPROVED";
		//notify				
		action_obj.approveButton_click_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},

	rejectButton_click_Action_handler : [],
	rejectButton_click_Action_subscribe : function(fn) {
		action_obj.rejectButton_click_Action_handler.push(fn);
    },
	rejectButton_click_Action : function(typeId) {
		store_obj.state[typeId] = "REJECTED";
		//notify
		action_obj.rejectButton_click_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	addButton_click_Action_handler : [],
	addButton_click_Action_subscribe : function(fn) {
		action_obj.addButton_click_Action_handler.push(fn);
    },
    addButton_click_Action : function(typeId) {
		store_obj.state[typeId] = "ADD";
		//notify
		action_obj.addButton_click_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},
	
	updateButton_click_Action_handler : [],
	updateButton_click_Action_subscribe : function(fn) {
		action_obj.updateButton_click_Action_handler.push(fn);
    },
    updateButton_click_Action : function(typeId) {
		store_obj.state[typeId] = "PROCESSING";
		//notify
		action_obj.updateButton_click_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},
	
	licenseList_select_Action_handler : [],
	licenseList_select_Action_subscribe : function(fn) {
		action_obj.licenseList_select_Action_handler.push(fn);
    },    
    licenseList_select_Action : function(licenseId,mappingId,state) {
		store_obj.crud = 'U';
		store_obj.licenseId = licenseId;
		store_obj.typeId = "license";
		store_obj.targetId["license"] = mappingId;
		store_obj.state["license"] = state;
		//notify
		action_obj.licenseList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	licenseList_deselect_Action_handler : [],
	licenseList_deselect_Action_subscribe : function(fn) {
		action_obj.licenseList_deselect_Action_handler.push(fn);
    },
    licenseList_deselect_Action : function(licenseId) {
		store_obj.crud = 'C';
		store_obj.licenseId = '-';
		store_obj.typeId = '-';
		store_obj.targetId["license"] = '-';
		store_obj.state["license"] = '';
		//notify
		action_obj.licenseList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},
	
	trainingList_select_Action_handler : [],
	trainingList_select_Action_subscribe : function(fn) {
		action_obj.trainingList_select_Action_handler.push(fn);
    },    
    trainingList_select_Action : function(trainingId,mappingId,state) {
		store_obj.crud = 'U';
		store_obj.trainingId = trainingId;
		store_obj.typeId = "training";
		store_obj.targetId["training"] = mappingId;
		store_obj.state["training"] = state;
		//notify
		action_obj.trainingList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	trainingList_deselect_Action_handler : [],
	trainingList_deselect_Action_subscribe : function(fn) {
		action_obj.trainingList_deselect_Action_handler.push(fn);
    },
    trainingList_deselect_Action : function(trainingId) {
		store_obj.crud = 'C';
		store_obj.trainingId = '-';
		store_obj.typeId = '-';
		store_obj.targetId["training"] = '-';
		store_obj.state["training"] = '';
		//notify
		action_obj.trainingList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},
	
	eventList_select_Action_handler : [],
	eventList_select_Action_subscribe : function(fn) {
		action_obj.eventList_select_Action_handler.push(fn);
    },    
    eventList_select_Action : function(eventId,mappingId,state) {
		store_obj.crud = 'U';
		store_obj.eventId = eventId;
		store_obj.typeId = "event";
		store_obj.targetId["event"] = mappingId;
		store_obj.state["event"] = state;
		//notify
		action_obj.eventList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	eventList_deselect_Action_handler : [],
	eventList_deselect_Action_subscribe : function(fn) {
		action_obj.eventList_deselect_Action_handler.push(fn);
    },
    eventList_deselect_Action : function(eventId) {
		store_obj.crud = 'C';
		store_obj.eventId = '-';
		store_obj.typeId = '-';
		store_obj.targetId["event"] = '-';
		store_obj.state["event"] = '';
		//notify
		action_obj.eventList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
		
	},
	
	aerialActivityList_select_Action_handler : [],
	aerialActivityList_select_Action_subscribe : function(fn) {
		action_obj.aerialActivityList_select_Action_handler.push(fn);
    }, 
	aerialActivityList_select_Action : function(aerialPlanId,aerialActivityId,state){
		store_obj.crud = 'U';		
		store_obj.aerialPlanId = aerialPlanId;
		store_obj.aerialActivityId = aerialActivityId;
		store_obj.typeId = "aerialActivity";
		store_obj.targetId["aerialActivity"] = aerialActivityId;
		store_obj.state["aerialActivity"] = state;
		//notify
		action_obj.aerialActivityList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialActivityList_deselect_Action_handler : [],
	aerialActivityList_deselect_Action_subscribe : function(fn) {
		action_obj.aerialActivityList_deselect_Action_handler.push(fn);
    },
	aerialActivityList_deselect_Action : function(){
		store_obj.crud = 'C';
		store_obj.planId = '-';
		store_obj.aerialActivityId = '-';
		store_obj.typeId = "-";
		store_obj.targetId["aerialActivity"] = '-';
		store_obj.state["aerialActivity"] = '';
		//notify
		action_obj.aerialActivityList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialActivityEPList_add_Action_handler : [],
	aerialActivityEPList_add_Action_subscribe : function(fn) {
		action_obj.aerialActivityEPList_add_Action_handler.push(fn);
    },
    aerialActivityEPList_add_Action : function(){
		store_obj.stepIn = 'initial';
		store_obj.stepHide = 'false';
		//notify
		action_obj.aerialActivityEPList_add_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialActivityForm_planIdChange_Action_handler : [],
	aerialActivityForm_planIdChange_Action_subscribe : function(fn) {
		action_obj.aerialActivityForm_planIdChange_Action_handler.push(fn);
    },
	aerialActivityForm_planIdChange_Action : function(aerialPlanId){
		store_obj.aerialPlanId = aerialPlanId;
		//notify
		action_obj.aerialActivityForm_planIdChange_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	equipmentList_select_Action_handler : [],
	equipmentList_select_Action_subscribe : function(fn) {
		action_obj.equipmentList_select_Action_handler.push(fn);
    },
	equipmentList_select_Action : function(equipmentId){
		store_obj.equipmentId = equipmentId;		
		//notify
		action_obj.equipmentList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	equipmentList_deselect_Action_handler : [],
	equipmentList_deselect_Action_subscribe : function(fn) {
		action_obj.equipmentList_deselect_Action_handler.push(fn);
    },
	equipmentList_deselect_Action : function(equipmentId){
		store_obj.equipmentId = '-';
		//notify
		action_obj.equipmentList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	personList_select_Action_handler : [],
	personList_select_Action_subscribe : function(fn) {
		action_obj.personList_select_Action_handler.push(fn);
    },
	personList_select_Action : function(personId){
		if(store_obj.personId_1==''||store_obj.personId_1=='-'||store_obj.personId_1==null){
            store_obj.personId_1=personId;
        }else{
        	store_obj.personId_2=personId;
        }
		//notify
		action_obj.personList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	personList_deselect_Action_handler : [],
	personList_deselect_Action_subscribe : function(fn) {
		action_obj.personList_deselect_Action_handler.push(fn);
    },
	personList_deselect_Action : function(personId){
		if(store_obj.personId_1==personId){
			store_obj.personId_1='-';
        }else if(store_obj.personId_2==personId){
        	store_obj.personId_2='-';
        }
		//notify
		action_obj.personList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialActivitySteps_clickFinish_Action_handler : [],
	aerialActivitySteps_clickFinish_Action_subscribe : function(fn) {
		action_obj.aerialActivitySteps_clickFinish_Action_handler.push(fn);
    },
	aerialActivitySteps_clickFinish_Action : function(){
		store_obj.stepIn = 'finish';
		store_obj.stepHide = 'true';
		//notify
		action_obj.aerialActivitySteps_clickFinish_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialPlanList_select_Action_handler : [],
	aerialPlanList_select_Action_subscribe : function(fn) {
		action_obj.aerialPlanList_select_Action_handler.push(fn);
    },
	aerialPlanList_select_Action : function(aerialPlanId,state){
		store_obj.crud = 'U';
		store_obj.aerialPlanId = aerialPlanId;
		store_obj.typeId = "aerialPlan";
		store_obj.targetId["aerialPlan"] = aerialPlanId;
		store_obj.state["aerialPlan"] = state;
		//notify
		action_obj.aerialPlanList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialPlanList_deselect_Action_handler : [],
	aerialPlanList_deselect_Action_subscribe : function(fn) {
		action_obj.aerialPlanList_deselect_Action_handler.push(fn);
    },
	aerialPlanList_deselect_Action : function(aerialPlanId){
		store_obj.crud = 'C';
		store_obj.aerialPlanId = '-';
		store_obj.typeId = "-";
		store_obj.targetId["aerialPlan"] = '-';
		store_obj.state["aerialPlan"] = '';
		//notify
		action_obj.aerialPlanList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialPlanEPList_add_Action_handler : [],
	aerialPlanEPList_add_Action_subscribe : function(fn) {
		action_obj.aerialPlanEPList_add_Action_handler.push(fn);
    },
	aerialPlanEPList_add_Action : function(){
		store_obj.stepIn = 'initial';
		store_obj.stepHide = 'false';
		//notify
		action_obj.aerialPlanEPList_add_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	
	selectConstruction_change_Action_handler : [],
	selectConstruction_change_Action_subscribe : function(fn) {
		action_obj.selectConstruction_change_Action_handler.push(fn);
    },
    selectConstruction_change_Action : function(constructionType){
		store_obj.constructionType = constructionType;
		//notify			
		action_obj.selectConstruction_change_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	equipmentAPList_select_Action_handler : [],
	equipmentAPList_select_Action_subscribe : function(fn) {
		action_obj.equipmentAPList_select_Action_handler.push(fn);
    },
	equipmentAPList_select_Action : function(equipmentId,constructionType){
		store_obj.equipmentId = equipmentId;
		store_obj.constructionType = constructionType;
		//notify			
		action_obj.equipmentAPList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	equipmentAPList_deselect_Action_handler : [],
	equipmentAPList_deselect_Action_subscribe : function(fn) {
		action_obj.equipmentAPList_deselect_Action_handler.push(fn);
    },
	equipmentAPList_deselect_Action : function(equipmentId){
		store_obj.equipmentId = '';
		//notify
		action_obj.equipmentAPList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	
	operationLimit_check_Action_handler : [],
	operationLimit_check_Action_subscribe : function(fn) {
		action_obj.operationLimit_check_Action_handler.push(fn);
    },
    operationLimit_check_Action : function(operationLimit){
		store_obj.operationLimit = operationLimit;
		//notify
		action_obj.operationLimit_check_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	personAPList_select_Action_handler : [],
	personAPList_select_Action_subscribe : function(fn) {
		action_obj.personAPList_select_Action_handler.push(fn);
    },
	personAPList_select_Action : function(personIds){
		store_obj.personId_1=personIds[0];
		store_obj.personId_2=personIds[1];
		//notify
		action_obj.personAPList_select_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	personAPList_deselect_Action_handler : [],
	personAPList_deselect_Action_subscribe : function(fn) {
		action_obj.personAPList_deselect_Action_handler.push(fn);
    },
	personAPList_deselect_Action : function(personIds){
		store_obj.personId_1=personIds[0];
		store_obj.personId_2=personIds[1];
		//notify
		action_obj.personAPList_deselect_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
	
	aerialPlanSteps_clickFinish_Action_handler : [],
	aerialPlanSteps_clickFinish_Action_subscribe : function(fn) {
		action_obj.aerialPlanSteps_clickFinish_Action_handler.push(fn);
    },
	aerialPlanSteps_clickFinish_Action : function(){
		store_obj.stepIn = 'finish';
		store_obj.stepHide = 'true';
		//notify
		action_obj.aerialPlanSteps_clickFinish_Action_handler.forEach(
				function(item){
					item.notify(store_obj);
				}
		)
	},
};
