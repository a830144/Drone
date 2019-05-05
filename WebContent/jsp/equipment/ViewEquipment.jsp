<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function prepareViewDomAction(){
		$("#dialog-view-form").dialog({
			autoOpen : false,
			height : 700,
			width : 1350,
			modal : true,
			buttons : {
				"關閉" : function() {
					$(this).dialog("close");					
				}
			},
			close : function() {
				$("#dialog-view-form-1").find("#checkEquipment").button().unbind("click");
				$("#dialog-view-form-1").find("#approveEquipment").button().unbind("click");
				$("#dialog-view-form-1").find("#rejectEquipment").button().unbind("click");
				$("#dialog-view-form-2").find("#checkMaintenance").button().unbind("click");
				$("#dialog-view-form-2").find("#approveMaintenance").button().unbind("click");
				$("#dialog-view-form-2").find("#rejectMaintenance").button().unbind("click");
				$("#dialog-view-form-2").find("#checkModification").button().unbind("click");
				$("#dialog-view-form-2").find("#approveModification").button().unbind("click");
				$("#dialog-view-form-2").find("#rejectModification").button().unbind("click");
				$( "#single-view-table-2-1").unbind( "select" );
				var table2 = $('#single-view-table-2-1').DataTable();
				table2.destroy();
				$( "#single-view-table-3-1").unbind( "select" );
				var table3 = $('#single-view-table-3-1').DataTable();
				table3.destroy();
				reloadQueryEquipmentAction();
			}
		});
		$("#dialog-view-form").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});
		$("#dialog-view-form-1").find("#checkEquipment").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-1").find("#approveEquipment").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-1").find("#rejectEquipment").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#dialog-view-form-2").find("#checkMaintenance").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-2").find("#approveMaintenance").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-2").find("#rejectMaintenance").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#dialog-view-form-2").find("#checkModification").button({
			icons: { primary: "ui-icon-play" }
		});
		$("#dialog-view-form-2").find("#approveModification").button({
			icons: { primary: "ui-icon-check" }
		});
		$("#dialog-view-form-2").find("#rejectModification").button({
			icons: { primary: "ui-icon-eject" }
		});
		$("#single-view-table-2-2").find("input[type='radio']").checkboxradio();
		$("#single-view-table-3-2").find("input[type='radio']").checkboxradio();
	}
	function viewDomFinishState(id){
		$("#view-form-1").find("select").val("");
		$("#view-form-1").find("input").val("");
		$("#view-form-1").find("textarea").val("");
		$("#view-form-2-2").find("select").val("");
		$("#view-form-2-2").find("input").val("");
		$("#view-form-3-2").find("select").val("");
		$("#view-form-3-2").find("input").val("");
		window.viewId = id;
	}
	function transferEquipmentMainFormViewAction(){
		$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
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
				});				
				id = $("#view-form-1").find("#equipmentId").val();				
				stateMachine();	
				registerStateMachineEvent();
			}
		})
		
	}
	function transferMaintainListViewAction(){		
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
			    "url": "/Drone/equipment/ViewMaintenance",  
			    "data": {  
			        "id": window.viewId  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(10);
			    	}
			    	for (i=0; i <json.length; i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("maintainState")?obj.maintainState:'';			        		
		        		myarray[i][1]=obj.hasOwnProperty("maintenanceId")?obj.maintenanceId:'';
		        		myarray[i][2]=obj.hasOwnProperty("maintenanceType")?obj.maintenanceType:'';
		        		myarray[i][3]=obj.hasOwnProperty("maintenanceDate")?obj.maintenanceDate:'';
		        		myarray[i][4]=obj.hasOwnProperty("airframe")?obj.airframe:'';
		        		myarray[i][5]=obj.hasOwnProperty("propulsion")?obj.propulsion:'';
		        		myarray[i][6]=obj.hasOwnProperty("battery")?obj.battery:'';
		        		myarray[i][7]=obj.hasOwnProperty("controller")?obj.controller:'';
		        		myarray[i][8]=obj.hasOwnProperty("payload")?obj.payload:'';
		        		myarray[i][9]=obj.hasOwnProperty("others")?obj.others:'';
		        	}
			    	registerMaintainListViewEvent();
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
		
	}
	function registerMaintainListViewEvent(){
		var singleviewtable21 = $('#single-view-table-2-1').DataTable();
		singleviewtable21.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singleviewtable21.cell( indexes ,1);
			var maintenanceId = cell.data();
			alert('您選取了保養編號 :'+ maintenanceId);
			window.maintenanceId = maintenanceId;
			$.ajax({
			  url:"/Drone/equipment/ViewMaintenanceByMaintenanceId",
			  type:"POST",
			  data:{"id" : maintenanceId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-2-2");
				$("#view-form-2-2").find("#maintenanceIdTD").empty().append("<input type='text' name='maintenanceId' id='maintenanceId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#view-form-2-2").find("#maintainStateTD").empty().append("<input type='text' name='maintainState' id='maintainState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-2-2").find("#" + key).val(value);	
					$("#view-form-2-2").find("input[name='" + key +"']").val(value);
				});
				stateMachineMaintenance();
				reverseRadioValue("#view-form-2-2","ma-view");
			}})
		});
		singleviewtable21.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeMaintainMainFormViewState();
		    }
		});
		
	}
	function transferModifyListViewAction(){
		jQuery('#single-view-table-3-1').wrap('<div class="dataTables_scroll" />');
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
				"url" : "/Drone/equipment/ViewModification",
				"data" : {
					"id" : window.viewId
				},
				"dataSrc" : function(json) {
					var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(10);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("modifyState")?obj.modifyState:'';	;		        		
		        		myarray[i][1]=obj.hasOwnProperty("modificationId")?obj.modificationId:'';
		        		myarray[i][2]=obj.hasOwnProperty("modificationDate")?obj.modificationDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("modificationPerson")?obj.modificationPerson:'';
		        		myarray[i][4]=obj.hasOwnProperty("airframe")?obj.airframe:'';
		        		myarray[i][5]=obj.hasOwnProperty("propulsion")?obj.propulsion:'';
		        		myarray[i][6]=obj.hasOwnProperty("battery")?obj.battery:'';
		        		myarray[i][7]=obj.hasOwnProperty("controller")?obj.controller:'';
		        		myarray[i][8]=obj.hasOwnProperty("payload")?obj.payload:'';
		        		myarray[i][9]=obj.hasOwnProperty("others")?obj.others:'';
		        	}
			    	registerModifyListViewEvent();
			        return myarray;
				},
				dataType : 'json'
			}
		});
		
	}
	function registerModifyListViewEvent(){
		var singleviewtable31 = $('#single-view-table-3-1').DataTable();
		singleviewtable31.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singleviewtable31.cell( indexes ,1);
			var modificationId = cell.data();
			alert('您選取了改裝編號 :'+ modificationId);
			window.modificationId = modificationId;
			$.ajax({
			  url:"/Drone/equipment/ViewModificationByModificationId",
			  type:"POST",
			  data:{"id" : modificationId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-3-2");
				$("#view-form-3-2").find("#modificationIdTD").empty().append("<input type='text' name='modificationId' id='modificationId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#view-form-3-2").find("#modifyStateTD").empty().append("<input type='text' name='modifyState' id='modifyState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-3-2").find("#" + key).val(value);	
					$("#view-form-3-2").find("input[name='" + key +"']").val(value);
				});
				stateMachineModification();
				reverseRadioValue("#view-form-3-2","mo-view");
			}})
		});
		singleviewtable31.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeModifyMainFormViewState();
		    }
		});
	}
	function registerStateMachineEvent(){
		//equipment
		$("#dialog-view-form-1").find("#checkEquipment").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/CheckEquipmentProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('檢核成功');
						$("#view-form-1").find("#state").val("CHECKED");
						stateMachine();
				  }
			})
		});

		$("#dialog-view-form-1").find("#approveEquipment").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/ApproveEquipmentProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('核可成功');
						$("#view-form-1").find("#state").val("APPROVED");
						stateMachine();
				  }
			})
		});
		
		$("#dialog-view-form-1").find("#rejectEquipment").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/RejectEquipmentProcess",
				  type:"POST",
				  data:{"id" : window.viewId},
				  success : function() {
						alert('退回成功');
						$("#view-form-1").find("#state").val("REJECTED");
						stateMachine();
				  }
			})
		});
		//maintenance
		$("#dialog-view-form-2").find("#checkMaintenance").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/CheckMaintenanceProcess",
				  type:"POST",
				  data:{"id" : window.maintenanceId},
				  success : function() {
						alert('檢核保養資料成功');
						$("#view-form-2-2").find("#maintainState").val("CHECKED");
						stateMachineMaintenance();
				  }
			})
		});

		$("#dialog-view-form-2").find("#approveMaintenance").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/ApproveMaintenanceProcess",
				  type:"POST",
				  data:{"id" : window.maintenanceId},
				  success : function() {
						alert('核可保養資料成功');
						$("#view-form-2-2").find("#maintainState").val("APPROVED");
						stateMachineMaintenance();
				  }
			})
		});
		
		$("#dialog-view-form-2").find("#rejectMaintenance").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/RejectMaintenanceProcess",
				  type:"POST",
				  data:{"id" : window.maintenanceId},
				  success : function() {
						alert('退回保養資料成功');
						$("#view-form-2-2").find("#maintainState").val("REJECTED");
						stateMachineMaintenance();
				  }
			})
		});
		//modification
		$("#dialog-view-form-3").find("#checkModification").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/CheckModificationProcess",
				  type:"POST",
				  data:{"id" : window.modificationId},
				  success : function() {
						alert('檢核改裝資料成功');
						$("#view-form-3-2").find("#modifyState").val("CHECKED");
						stateMachineModification();
				  }
			})
		});

		$("#dialog-view-form-3").find("#approveModification").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/ApproveModificationProcess",
				  type:"POST",
				  data:{"id" : window.modificationId},
				  success : function() {
						alert('核可改裝資料成功');
						$("#view-form-3-2").find("#modifyState").val("APPROVED");
						stateMachineModification();
				  }
			})
		});
		
		$("#dialog-view-form-3").find("#rejectModification").button().click(function() {
			$.ajax({
				  url:"/Drone/equipment/RejectModificationProcess",
				  type:"POST",
				  data:{"id" : window.modificationId},
				  success : function() {
						alert('退回改裝資料成功');
						$("#view-form-3-2").find("#modifyState").val("REJECTED");
						stateMachineModification();
				  }
			})
		});
	}
	function initializeEquipmentMainFormViewState(){		
		$("#view-form-1").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-1").find("input").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-1").find("textarea").prop("disabled", true).addClass("ui-state-disabled");		
	}
	function initializeMaintainListViewState(){

	}
	function initializeMaintainMainFormViewState(){
		$("#view-form-2-2").find("#maintainStateTD").empty();
		$("#view-form-2-2").find("#maintenanceIdTD").empty().removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("#maintenanceDate").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("#maintenancePerson").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("#maintenanceType").val("W").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("input[id*='clean']").prop("checked", true ).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("input[name*='radio']").button("enable").button("refresh").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("input[name*='comment']").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-2-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
	}
	function initializeModifyListViewState(){}
	function initializeModifyMainFormViewState(){
		$("#view-form-2-2").find("#modifyStateTD").empty();
		$("#view-form-3-2").find("#modificationIdTD").empty().removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("#modificationDate").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("#modificationPerson").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("#modificationType").val("W").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("input[id*='clean']").prop("checked", true ).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("input[name*='radio']").button("enable").button("refresh").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("input[name*='comment']").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
		$("#view-form-3-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
	}
	
	function stateMachine(){
		if($("#view-form-1").find("#state").val()==="PROCESSING"){
			$("#dialog-view-form-1").find("#checkEquipment").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#rejectEquipment").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#approveEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-1").find("#state").val()==="CHECKED"){
			$("#dialog-view-form-1").find("#checkEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectEquipment").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-1").find("#approveEquipment").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-1").find("#state").val()==="APPROVED"){
			$("#dialog-view-form-1").find("#checkEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#approveEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-1").find("#state").val()==="REJECTED"){
			$("#dialog-view-form-1").find("#checkEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#rejectEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-1").find("#approveEquipment").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	function stateMachineMaintenance(){
		if($("#view-form-2-2").find("#maintainState").val()==="PROCESSING"){
			$("#dialog-view-form-2").find("#checkMaintenance").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#rejectMaintenance").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#approveMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-2-2").find("#maintainState").val()==="CHECKED"){
			$("#dialog-view-form-2").find("#checkMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectMaintenance").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-2").find("#approveMaintenance").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-2-2").find("#maintainState").val()==="APPROVED"){
			$("#dialog-view-form-2").find("#checkMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#approveMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-2-2").find("#maintainState").val()==="REJECTED"){
			$("#dialog-view-form-2").find("#checkMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#rejectMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-2").find("#approveMaintenance").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}
	
	function stateMachineModification(){
		if($("#view-form-3-2").find("#modifyState").val()==="PROCESSING"){
			$("#dialog-view-form-3").find("#checkModification").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#rejectModification").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#approveModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-3-2").find("#modifyState").val()==="CHECKED"){
			$("#dialog-view-form-3").find("#checkModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectModification").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#dialog-view-form-3").find("#approveModification").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else if($("#view-form-3-2").find("#modifyState").val()==="APPROVED"){
			$("#dialog-view-form-3").find("#checkModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#approveModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}else if($("#view-form-3-2").find("#modifyState").val()==="REJECTED"){
			$("#dialog-view-form-3").find("#checkModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#rejectModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#dialog-view-form-3").find("#approveModification").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
	}

	$(function() {
		prepareViewDomAction();
		
	});

	function viewEquipment(id) {
		viewDomFinishState(id);	
		transferEquipmentMainFormViewAction();
		transferMaintainListViewAction();
		transferModifyListViewAction();
		initializeEquipmentMainFormViewState();		
		initializeMaintainListViewState();
		initializeMaintainMainFormViewState();				
		initializeModifyListViewState();		
		initializeModifyMainFormViewState();		
		$("#dialog-view-form").dialog("open");

	}
</script>

<div id="dialog-view-form" title="檢視設備資料">
	<ul>
		<li><a href="#dialog-view-form-1">主要資訊</a></li>
		<li><a href="#dialog-view-form-2">保養資訊</a></li>
		<li><a href="#dialog-view-form-3">改裝資訊</a></li>
	</ul>
	<div id="dialog-view-form-1">
		<button id="checkEquipment" name="checkEquipment" class="ui-button ui-corner-all ui-widget">檢核設備</button>
		<button id="approveEquipment" name="approveEquipment" class="ui-button ui-corner-all ui-widget">核可設備</button>
		<button id="rejectEquipment" name="rejectEquipment" class="ui-button ui-corner-all ui-widget">退回設備</button>
		<form id="view-form-1">
			<table id="single-view-table-1">
				<%@ include file="./pages/Equipment_main_form.jsp" %>			
			</table>
		</form>
	</div>
	<div id="dialog-view-form-2">
		<table id="single-view-table-2-1" class="display" style="width: 100%">	
			<%@ include file="./pages/MaintainEquipment_list.jsp" %>		
		</table>
		<button id="checkMaintenance" name="checkMaintenance" class="ui-button ui-corner-all ui-widget">檢核保養紀錄</button>
		<button id="approveMaintenance" name="approveMaintenance" class="ui-button ui-corner-all ui-widget">核可保養紀錄</button>
		<button id="rejectMaintenance" name="rejectMaintenance" class="ui-button ui-corner-all ui-widget">退回保養紀錄</button>
		<form id="view-form-2-2">
			<table id="single-view-table-2-2">	
				<%@ include file="./pages/MaintainEquipment_main_form_for_view.jsp" %>			
			</table>
		</form>
	</div>
	<div id="dialog-view-form-3">
		<table id="single-view-table-3-1" class="display" style="width: 100%">	
			<%@ include file="./pages/ModifyEquipment_list.jsp" %>		
		</table>
		<button id="checkModification" name="checkModification" class="ui-button ui-corner-all ui-widget">檢核改裝紀錄</button>
		<button id="approveModification" name="approveModification" class="ui-button ui-corner-all ui-widget">核可改裝紀錄</button>
		<button id="rejectModification" name="rejectModification" class="ui-button ui-corner-all ui-widget">退回改裝紀錄</button>
		<form id="view-form-3-2">
			<table id="single-view-table-3-2">		
				<%@ include file="./pages/ModifyEquipment_main_form.jsp" %>	
			</table>
		</form>
	</div>

</div>