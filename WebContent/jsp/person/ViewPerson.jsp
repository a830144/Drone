<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function prepareViewDomAction(){
		$("#dialog-view-form").dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : {
				"關閉" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				$("#dialog-view-form-1").find("#checkPerson").button().unbind("click");
				$("#dialog-view-form-1").find("#approvePerson").button().unbind("click");
				$("#dialog-view-form-1").find("#rejectPerson").button().unbind("click");
				$("#dialog-view-form-2").find("#checkLicense").button().unbind("click");
				$("#dialog-view-form-2").find("#approveLicense").button().unbind("click");
				$("#dialog-view-form-2").find("#rejectLicense").button().unbind("click");
				$("#dialog-view-form-3").find("#checkTraining").button().unbind("click");
				$("#dialog-view-form-3").find("#approveTraining").button().unbind("click");
				$("#dialog-view-form-3").find("#rejectTraining").button().unbind("click");
				$("#dialog-view-form-4").find("#checkEvent").button().unbind("click");
				$("#dialog-view-form-4").find("#approveEvent").button().unbind("click");
				$("#dialog-view-form-4").find("#rejectEvent").button().unbind("click");
				$( "#single-view-table-2-1").unbind( "select" );
				var table2 = $('#single-view-table-2-1').DataTable();
				table2.destroy();
				$( "#single-view-table-3-1").unbind( "select" );
				var table3 = $('#single-view-table-3-1').DataTable();
				table3.destroy();
				$( "#single-view-table-4-1").unbind( "select" );
				var table4 = $('#single-view-table-4-1').DataTable();
				table4.destroy();
				reloadQueryPersonAction();
			}
		});
		$.ajax({
			url : "/Drone/other/QueryCodes",
			type : "POST",
			data : {
				type : 'License_type',
				code : ''
			},
			success : function(tag) {  
				$("#view-form-2-2").find("#type").append(tag);
            }
		});
		$("#dialog-view-form").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});
		$("#dialog-view-form-1").find("#checkPerson").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-1").find("#approvePerson").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-1").find("#rejectPerson").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#dialog-view-form-2").find("#checkLicense").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-2").find("#approveLicense").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-2").find("#rejectLicense").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#dialog-view-form-3").find("#checkTraining").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-3").find("#approveTraining").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-3").find("#rejectTraining").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#dialog-view-form-4").find("#checkEvent").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-4").find("#approveEvent").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-4").find("#rejectEvent").button({
			icons: { primary: "ui-icon-eject" }
		});
	}
	function viewDomFinishState(id){
		$("#view-form-1").find("select").val("");
		$("#view-form-1").find("input").val("");
		$("#view-form-2-2").find("select").val("");
		$("#view-form-2-2").find("input").val("");
		$("#view-form-3-2").find("select").val("");
		$("#view-form-3-2").find("input").val("");
		$("#view-form-4-2").find("select").val("");
		$("#view-form-4-2").find("input").val("");
		window.viewId = id;
		
	}
	function transferPersonMainFormViewAction(){
		$.ajax({
			  url:"/Drone/person/ViewPersonProcess",
			  type:"POST",
			  data:{"id" : window.viewId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-1");
				$.each(obj, function(key, value) {
					$("#view-form-1").find("#" + key).val(value);
					$("#view-form-2-2").find("#" + key).val(value);
					$("#view-form-3-2").find("#" + key).val(value);
					$("#view-form-4-2").find("#" + key).val(value);
				});				
				id = $("#view-form-1").find("#personId").val();				
				stateMachine();	
				registerStateMachineEvent();
			}
		})
	}
	function transferLicenseListViewAction(){
		var singleviewtable21 = $('#single-view-table-2-1').DataTable(  {
			columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
			"select":true,
	        "order": [[ 1, 'asc' ]],
			"scrollY": "100px",
	        "scrollCollapse": true,		
			"ajax": {
				"type": "POST",
			    "url": "/Drone/person/QueryLicenses",  
			    "data": {  
			        "id": window.viewId  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("licenseId")?obj.licenseId:'';
		        		myarray[i][2]=obj.hasOwnProperty("gotDate")?obj.gotDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("type")?obj.type:'';
		        	}
			    	registerLicenseListViewEvent();
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
		
	}
	function registerLicenseListViewEvent(){
		var singleviewtable21 = $('#single-view-table-2-1').DataTable();
		singleviewtable21.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singleviewtable21.cell( indexes ,1);
			window.licenseId = cell.data();
			alert('您選取了證照編號 :'+ window.licenseId);
			$.ajax({
			  url:"/Drone/person/ViewLicenseInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "licenseId" : window.licenseId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-2-2");
				$("#view-form-2-2").find("#licenseIdTD").empty().append("<input type='text' name='licenseId' id='licenseId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#view-form-2-2").find("#licenseStateTD").empty().append("<input type='text' name='licenseState' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-2-2").find("#" + key).val(value);	
					$("#view-form-2-2").find("input[name='" + key +"']").val(value);
				});
				stateMachineLicense();
			}})
		});
		singleviewtable21.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeLicenseMainFormViewState();
		    }
		});
	}
	function transferTrainingListViewAction(){		
		var singleviewtable31 = $('#single-view-table-3-1').DataTable({
			columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],			
			"select":true,
			"order": [[ 1, 'asc' ]],
			"scrollY": "100px",
			"scrollCollapse": true,	
			"ajax" : {
				"type": "POST",
				"url" : "/Drone/person/QueryTrainings",
				"data" : {
					"id" : window.viewId
				},
				"dataSrc" : function(json) {
					var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(6);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("trainingId")?obj.trainingId:'';
		        		myarray[i][2]=obj.hasOwnProperty("trainingName")?obj.trainingName:'';
		        		myarray[i][3]=obj.hasOwnProperty("unit")?obj.unit:'';
		        		myarray[i][4]=obj.startDate+'~'+obj.endDate;
		        		myarray[i][5]=obj.hasOwnProperty("hours")?obj.hours:'';
		        		myarray[i][6]=obj.hasOwnProperty("trainingType")?obj.trainingType:'';		   
		        	}
			    	registerTrainingListViewEvent();
			        return myarray;
				},
				dataType : 'json'
			}
		});
	}
	function registerTrainingListViewEvent(){
		var singleviewtable31 = $('#single-view-table-3-1').DataTable();
		singleviewtable31.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singleviewtable31.cell( indexes ,1);
			window.trainingId = cell.data();
			alert('您選取了訓練紀錄編號 :'+ window.trainingId);
			$.ajax({
			  url:"/Drone/person/ViewTrainingInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "trainingId" : window.trainingId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-3-2");
				$("#view-form-3-2").find("#trainingIdTD").empty().append("<input type='text' name='trainingId' id='trainingId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#view-form-3-2").find("#trainingStateTD").empty().append("<input type='text' name='trainingState' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-3-2").find("#" + key).val(value);	
					$("#view-form-3-2").find("input[name='" + key +"']").val(value);
				});
				stateMachineTraining();
			}})
		});
		singleviewtable31.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeTrainingMainFormViewState();
		    }
		});
	}
	function transferEventListViewAction(){
		var singleviewtable41 = $('#single-view-table-4-1').DataTable({
			columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],		
			"select":true,
			"order": [[ 1, 'asc' ]],
			"scrollY": "100px",
			"scrollCollapse": true,	
			"ajax" : {
				"type": "POST",
				"url" : "/Drone/person/QueryEvents",
				"data" : {
					"id" : window.viewId
				},
				"dataSrc" : function(json) {
					var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(5);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("eventId")?obj.eventId:'';
		        		myarray[i][2]=obj.hasOwnProperty("eventName")?obj.eventName:'';
		        		myarray[i][3]=obj.hasOwnProperty("eventName")?obj.eventName:'';
		        		myarray[i][4]=obj.startDate+'~'+obj.endDate;
		        		myarray[i][5]=obj.hasOwnProperty("description")?obj.description:'';
		        	}		    	
			    	registerEventListViewEvent();
			        return myarray;
				},
				dataType : 'json'
			}
		});
	}
	function registerEventListViewEvent(){
		var singleviewtable41 = $('#single-view-table-4-1').DataTable();
		singleviewtable41.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singleviewtable41.cell( indexes ,1);
			window.eventId = cell.data();
			alert('您選取了事件編號 :'+ window.eventId);
			$.ajax({
			  url:"/Drone/person/ViewEventInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "eventId" : window.eventId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-4-2");
				$("#view-form-4-2").find("#eventIdIdTD").empty().append("<input type='text' name='eventId' id='eventId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#view-form-4-2").find("#eventStateTD").empty().append("<input type='text' name='eventState' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-4-2").find("#" + key).val(value);	
					$("#view-form-4-2").find("input[name='" + key +"']").val(value);
				});
				stateMachineEvent();
			}})
		});
		singleviewtable41.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeEventMainFormViewState();
		    }
		});
	}
	function registerStateMachineEvent(){
		//person
		$("#dialog-view-form-1").find("#checkPerson").button().click(function() {
			$.ajax({
				  url:"/Drone/person/CheckPersonProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('檢核成功');
						$("#view-form-1").find("#state").val("CHECKED");
						stateMachine();
				  }
			})
		});

		$("#dialog-view-form-1").find("#approvePerson").button().click(function() {
			$.ajax({
				  url:"/Drone/person/ApprovePersonProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('核可成功');
						$("#view-form-1").find("#state").val("APPROVED");
						stateMachine();
				  }
			})
		});
		
		$("#dialog-view-form-1").find("#rejectPerson").button().click(function() {
			$.ajax({
				  url:"/Drone/person/RejectPersonProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('退回成功');
						$("#view-form-1").find("#state").val("REJECTED");
						stateMachine();
				  }
			})
		});
		//license
		$("#dialog-view-form-2").find("#checkLicense").button().click(function() {
			$.ajax({
				  url:"/Drone/person/CheckPersonLicenseProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "licenseId" : window.licenseId
				  },
				  
				  success : function() {
						alert('檢核成功');
						$("#view-form-2-2").find("#state").val("CHECKED");
						stateMachineLicense();
				  }
			})
		});

		$("#dialog-view-form-2").find("#approveLicense").button().click(function() {
			$.ajax({
				  url:"/Drone/person/ApprovePersonLicenseProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "licenseId" : window.licenseId
				  },
				  success : function() {
						alert('核可成功');
						$("#view-form-2-2").find("#state").val("APPROVED");
						stateMachineLicense();
				  }
			})
		});
		
		$("#dialog-view-form-2").find("#rejectLicense").button().click(function() {
			$.ajax({
				  url:"/Drone/person/RejectPersonLicenseProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "licenseId" : window.licenseId
				  },
				  success : function() {
						alert('退回成功');
						$("#view-form-2-2").find("#state").val("REJECTED");
						stateMachineLicense();
				  }
			})
		});
		
		//training
		$("#dialog-view-form-3").find("#checkTraining").button().click(function() {
			$.ajax({
				  url:"/Drone/person/CheckCertificateProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "trainingId" : window.trainingId
				  },
				  
				  success : function() {
						alert('檢核成功');
						$("#view-form-3-2").find("#state").val("CHECKED");
						stateMachineTraining();
				  }
			})
		});

		$("#dialog-view-form-3").find("#approveTraining").button().click(function() {
			$.ajax({
				  url:"/Drone/person/ApproveCertificateProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "trainingId" : window.trainingId
				  },
				  success : function() {
						alert('核可成功');
						$("#view-form-3-2").find("#state").val("APPROVED");
						stateMachineTraining();
				  }
			})
		});
		
		$("#dialog-view-form-3").find("#rejectTraining").button().click(function() {
			$.ajax({
				  url:"/Drone/person/RejectCertificateProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "trainingId" : window.trainingId
				  },
				  success : function() {
						alert('退回成功');
						$("#view-form-3-2").find("#state").val("REJECTED");
						stateMachineTraining();
				  }
			})
		});
		
		//event
		$("#dialog-view-form-4").find("#checkEvent").button().click(function() {
			$.ajax({
				  url:"/Drone/person/CheckParticipationProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "eventId" : window.eventId
				  },
				  
				  success : function() {
						alert('檢核成功');
						$("#view-form-4-2").find("#state").val("CHECKED");
						stateMachineEvent();
				  }
			})
		});

		$("#dialog-view-form-4").find("#approveEvent").button().click(function() {
			$.ajax({
				  url:"/Drone/person/ApproveParticipationProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "eventId" : window.eventId
				  },
				  success : function() {
						alert('核可成功');
						$("#view-form-4-2").find("#state").val("APPROVED");
						stateMachineEvent();
				  }
			})
		});
		
		$("#dialog-view-form-4").find("#rejectEvent").button().click(function() {
			$.ajax({
				  url:"/Drone/person/RejectParticipationProcess",
				  type:"POST",
				  data:{
					  "personId" : window.viewId,
					  "eventId" : window.eventId
				  },
				  success : function() {
						alert('退回成功');
						$("#view-form-4-2").find("#state").val("REJECTED");
						stateMachineEvent();
				  }
			})
		});
		
	}
	function initializePersonMainFormViewState(){
		$("#view-form-1").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-1").find("input").prop("disabled", true).addClass("ui-state-disabled");
	}
	function initializeLicenseListViewState(){
		
	}
	function initializeLicenseMainFormViewState(){
		$("#view-form-2-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-2-2").find("input").prop("disabled", true).addClass("ui-state-disabled");
	}
	function initializeTrainingListViewState(){
		
	}
	function initializeTrainingMainFormViewState(){
		$("#view-form-3-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-3-2").find("input").prop("disabled", true).addClass("ui-state-disabled");
	}
	function initializeEventListViewState(){
		
	}
	function initializeEventMainFormViewState(){
		$("#view-form-4-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-4-2").find("input").prop("disabled", true).addClass("ui-state-disabled");
	}
	
	function stateMachine(){
		if($("#view-form-1").find("#state").val()==="PROCESSING"){
			$("#dialog-view-form-1").find("#checkPerson").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#rejectPerson").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#approvePerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-1").find("#state").val()==="CHECKED"){
			$("#dialog-view-form-1").find("#checkPerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectPerson").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#approvePerson").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-1").find("#state").val()==="APPROVED"){
			$("#dialog-view-form-1").find("#checkPerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectPerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#approvePerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-1").find("#state").val()==="REJECTED"){
			$("#dialog-view-form-1").find("#checkPerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectPerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#approvePerson").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	function stateMachineLicense(){
		if($("#view-form-2-2").find("#state").val()==="PROCESSING"){
			$("#dialog-view-form-2").find("#checkLicense").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#rejectLicense").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#approveLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-2-2").find("#state").val()==="CHECKED"){
			$("#dialog-view-form-2").find("#checkLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectLicense").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#approveLicense").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-2-2").find("#state").val()==="APPROVED"){
			$("#dialog-view-form-2").find("#checkLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#approveLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-2-2").find("#state").val()==="REJECTED"){
			$("#dialog-view-form-2").find("#checkLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#approveLicense").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	function stateMachineTraining(){
		if($("#view-form-3-2").find("#state").val()==="PROCESSING"){
			$("#dialog-view-form-3").find("#checkTraining").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#rejectTraining").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#approveTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-3-2").find("#state").val()==="CHECKED"){
			$("#dialog-view-form-3").find("#checkTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectTraining").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#approveTraining").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-3-2").find("#state").val()==="APPROVED"){
			$("#dialog-view-form-3").find("#checkTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#approveTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-3-2").find("#state").val()==="REJECTED"){
			$("#dialog-view-form-3").find("#checkTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#approveTraining").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	
	function stateMachineEvent(){
		if($("#view-form-4-2").find("#state").val()==="PROCESSING"){
			$("#dialog-view-form-4").find("#checkEvent").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-4").find("#rejectEvent").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-4").find("#approveEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-4-2").find("#state").val()==="CHECKED"){
			$("#dialog-view-form-4").find("#checkEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-4").find("#rejectEvent").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-4").find("#approveEvent").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-4-2").find("#state").val()==="APPROVED"){
			$("#dialog-view-form-4").find("#checkEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-4").find("#rejectEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-4").find("#approveEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-4-2").find("#state").val()==="REJECTED"){
			$("#dialog-view-form-4").find("#checkEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-4").find("#rejectEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-4").find("#approveEvent").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	$(function() {
		prepareViewDomAction();
	});

	function viewPerson(id) {
		viewDomFinishState(id);	
		transferPersonMainFormViewAction();
		transferLicenseListViewAction();
		transferTrainingListViewAction();
		transferEventListViewAction();
		initializePersonMainFormViewState();		
		initializeLicenseListViewState();
		initializeLicenseMainFormViewState();				
		initializeTrainingListViewState();		
		initializeTrainingMainFormViewState();	
		initializeEventListViewState();		
		initializeEventMainFormViewState();
		$("#dialog-view-form").dialog("open");
	}

</script>
<div id="dialog-view-form" title="檢視設備資料">
	<ul>
		<li><a href="#dialog-view-form-1">主要資訊</a></li>
		<li><a href="#dialog-view-form-2">操作證資訊</a></li>
		<li><a href="#dialog-view-form-3">訓練紀錄資訊</a></li>
		<li><a href="#dialog-view-form-4">特殊事蹟資訊</a></li>
	</ul>
	<div id="dialog-view-form-1">
		<button id="checkPerson" name="checkPerson" class="ui-button ui-corner-all ui-widget">檢核人員</button>
		<button id="approvePerson" name="approvePerson" class="ui-button ui-corner-all ui-widget">核可人員</button>
		<button id="rejectPerson" name="rejectPerson" class="ui-button ui-corner-all ui-widget">退回人員</button>
		<form id="view-form-1">
			<table id="single-view-table-1">
				<%@ include file="./pages/Person_main_form.jsp" %>
		</table>
		</form>
	</div>
	<div id="dialog-view-form-2">
		<table id="single-view-table-2-1" class="display" style="width: 100%">	
			<%@ include file="./pages/LicenseInPerson_list.jsp" %>		
		</table>
		<button id="checkLicense" name="checkLicense" class="ui-button ui-corner-all ui-widget">檢核操作證紀錄</button>
		<button id="approveLicense" name="approveLicense" class="ui-button ui-corner-all ui-widget">核可操作證紀錄</button>
		<button id="rejectLicense" name="rejectLicense" class="ui-button ui-corner-all ui-widget">退回操作證紀錄</button>
		<form id="view-form-2-2">
			<table id="single-view-table-2-2">	
				<%@ include file="./pages/LicenseInPerson_main_form.jsp" %>			
			</table>
		</form>
	</div>
	<div id="dialog-view-form-3">
		<table id="single-view-table-3-1" class="display" style="width: 100%">	
			<%@ include file="./pages/TrainingInPerson_list.jsp" %>		
		</table>
		<button id="checkTraining" name="checkTraining" class="ui-button ui-corner-all ui-widget">檢核訓練紀錄</button>
		<button id="approveTraining" name="approveTraining" class="ui-button ui-corner-all ui-widget">核可訓練紀錄</button>
		<button id="rejectTraining" name="rejectTraining" class="ui-button ui-corner-all ui-widget">退回訓練紀錄</button>
		<form id="view-form-3-2">
			<table id="single-view-table-3-2">		
				<%@ include file="./pages/TrainingInPerson_main_form.jsp" %>	
			</table>
		</form>
	</div>
	<div id="dialog-view-form-4">
		<table id="single-view-table-4-1" class="display" style="width: 100%">	
			<%@ include file="./pages/EventInPerson_list.jsp" %>		
		</table>
		<button id="checkEvent" name="checkEvent" class="ui-button ui-corner-all ui-widget">檢核特殊事蹟紀錄</button>
		<button id="approveEvent" name="approveEvent" class="ui-button ui-corner-all ui-widget">核可特殊事蹟紀錄</button>
		<button id="rejectEvent" name="rejectEvent" class="ui-button ui-corner-all ui-widget">退回特殊事蹟紀錄</button>
		<form id="view-form-4-2">
			<table id="single-view-table-4-2">		
				<%@ include file="./pages/EventInPerson_main_form.jsp" %>	
			</table>
		</form>
	</div>
</div>