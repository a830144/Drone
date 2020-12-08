const system_name	= 'Drone';
var store_obj = {
		
	
	crud : '',
	equipmentId : '',
	maintenanceId : '',
	modificationId : '',
	personId : '',
	licenseId : '',
	trainingId : '',
	eventId : '',
	state : {},
	targetId : {},

	stepHide : 'true',
	stepIn : 'initial',
	projectId : '',
	aerialPlanId : '',
	aerialActivityId : '',
	equipmentId : '',
	personId_1 : '',
	personId_2 : '',
	
	license : {
		constructionType :'',
		type :'',
		typeContent :'',
		gotDate : ''
	},
	
	training : {
		trainingId:'',
		unit:'',
		trainingName:'',
		trainingDate:'',
		hours:'',
		trainingType:''
	},
	
	event : {
		eventId:'',
		unit:'',
		eventName:'',
		eventDate:'',
		description:''
	},
	
	maintain : {
		maintenanceType:'',
		maintenanceDate:'',
		airframe:'',
		propulsion:'',
		battery:'',
		controller:'',
		payload:'',
		other:''
	},
	
	modify : {
		modificationDate:'',
		modificationPerson:'',
		airframe:'',
		propulsion:'',
		battery:'',
		controller:'',
		payload:'',
		other:''
	},

	aerialActivityEPList : {
		equipmentId : '',	
		manufactoryName : '',
		constructionType : '',
		productName : '',
		airTime : '',
		personId_1 : '',
		personId_2 : ''
	},
	
	aerialPlanEPList : {
		equipmentId : '',	
		manufactoryName : '',
		constructionType : '',
		productName : '',
		airTime : '',
		personId_1 : '',
		personId_2 : ''
	}
	
	
};
