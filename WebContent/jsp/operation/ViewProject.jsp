<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	var view_project_obj = {
			projectId : '',
			aerialPlanId : '',
			equipmentId : '',
			
			prepareViewDomAction : function(){
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
						$("#dialog-view-form-1").find("#checkProject").button().unbind("click");
						$("#dialog-view-form-1").find("#approveProject").button().unbind("click");
						$("#dialog-view-form-1").find("#rejectProject").button().unbind("click");
						$("#dialog-view-form-2").find("#checkAerialPlan").button().unbind("click");
						$("#dialog-view-form-2").find("#approveAerialPlan").button().unbind("click");
						$("#dialog-view-form-2").find("#rejectAerialPlan").button().unbind("click");
						$("#dialog-view-form-3").find("#checkAerialActivity").button().unbind("click");
						$("#dialog-view-form-3").find("#approveAerialActivity").button().unbind("click");
						$("#dialog-view-form-3").find("#rejectAerialActivity").button().unbind("click");
						$( "#single-view-table-2-1").unbind( "select" );
						var table2 = $('#single-view-table-2-1').DataTable();
						table2.destroy();
						$( "#single-view-table-3-1").unbind( "select" );
						var table3 = $('#single-view-table-3-1').DataTable();
						table3.destroy();
						//reloadQueryEquipmentAction();
					}
				});
				$("#dialog-view-form").tabs({
					  activate: function( event, ui ) {
						  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
					  }
				});
				$("#dialog-view-form-1").find("#checkProject").button({
					icons: { primary: "ui-icon-play" }
				});
				$("#dialog-view-form-1").find("#approveProject").button({
					icons: { primary: "ui-icon-check" }
				});
				$("#dialog-view-form-1").find("#rejectProject").button({
					icons: { primary: "ui-icon-eject" }
				});
				$("#dialog-view-form-2").find("#checkAerialPlan").button({
					icons: { primary: "ui-icon-play" }
				});
				$("#dialog-view-form-2").find("#approveAerialPlan").button({
					icons: { primary: "ui-icon-check" }
				});
				$("#dialog-view-form-2").find("#rejectAerialPlan").button({
					icons: { primary: "ui-icon-eject" }
				});
				$("#dialog-view-form-2").find("#checkAerialActivity").button({
					icons: { primary: "ui-icon-play" }
				});
				$("#dialog-view-form-2").find("#approveAerialActivity").button({
					icons: { primary: "ui-icon-check" }
				});
				$("#dialog-view-form-2").find("#rejectAerialActivity").button({
					icons: { primary: "ui-icon-eject" }
				});
			},
			viewDomFinishState : function (id){
				$("#view-form-1").find("select").val("");
				$("#view-form-1").find("input").val("");
				$("#view-form-1").find("textarea").val("");
				$("#view-form-2-2").find("select").val("");
				$("#view-form-2-2").find("input").val("");
				$("#view-form-3-2").find("select").val("");
				$("#view-form-3-2").find("input").val("");
				view_project_obj.projectId = id;
			},
			transferProjectMainFormViewAction : function (){
				$.ajax({
					  url: "/Drone/operation/ViewProjectProcess",
					  type: "POST",
					  data:{
						  id : view_project_obj.projectId
					  },
					  dataType: "json",
					  success: function(data){				  
						var obj = data;
						var form = $("#view-form-1");
						$.each(obj, function(key, value) {
							form.find("#" + key).val(value);
							$("#view-form-2-2").find("#" + key).val(value);
							$("#view-form-3-2").find("#" + key).val(value);
						});								
						//stateMachine();	
						//registerStateMachineEvent();
					}
				})
				
			},
			transferAerialPlanListViewAction : function (){		
				var singleviewtable21 = $('#single-view-table-2-1').DataTable(  {
					columnDefs: [
						{	
						    className: 'dt-center',
						    targets: '_all'
						}],
					select :true,
			        order : [[ 1, 'asc' ]],
					scrollY : "100px",
			        scrollCollapse : true,		
					ajax : {
						type : "POST",
					    url : "/Drone/operation/QueryAerailPlansProcess",  
					    data : {  
					    	projectId : view_project_obj.projectId  
					    }, 
					    dataSrc : function ( json ) {
					    	var myarray=new Array(json.length);
					    	for (i=0; i <json.length; i++){
					    	    myarray[i]=new Array(10);
					    	}
					    	for (i=0; i <json.length; i++){
				        		var obj = $.parseJSON(json[i]);
				        		myarray[i][0]='';		        		
				        		myarray[i][1]=obj.hasOwnProperty("aerialPlanId")?obj.aerialPlanId:'';
				        		myarray[i][2]=obj.hasOwnProperty("usage")?obj.usage:'';
				        		myarray[i][3]=obj.aerialPlanStartDate+'~'+obj.aerialPlanEndDate;
				        	}
					    	view_project_obj.registerAerialPlanListViewEvent();
					        return myarray;
					      }
					    ,
				        dataType: 'json'
					}

				});
				
			},
			registerAerialPlanListViewEvent : function (){
				var singleviewtable21 = $('#single-view-table-2-1').DataTable();
				singleviewtable21.on( 'select', function ( e, dt, type, indexes ) {
					var cell = singleviewtable21.cell( indexes ,1);
					var aerialPlanId = cell.data();
					alert('您選取了航拍計畫編號 :'+ aerialPlanId);
					view_project_obj.aerialPlanId = aerialPlanId;
					$.ajax({
					  url:"/Drone/operation/ViewAerialPlanByAerialPlanId",
					  type:"POST",
					  data:{
						  id : view_project_obj.aerialPlanId
					  },
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
				
			},
			transferAerialActivityListViewAction : function (){
				jQuery('#single-view-table-3-1').wrap('<div class="dataTables_scroll" />');
				var singleviewtable31 = $('#single-view-table-3-1').DataTable({
					columnDefs: [
						{	
						    className: 'dt-center',
						    targets: '_all'
						}],
					select :true,
					order : [[ 1, 'asc' ]],
					scrollY : "100px",
					scrollCollapse : true,	
					ajax : {
						type: "POST",
						url : "/Drone/operation/QueryAerailActivitiesProcess",
						data : {
							projectId : view_project_obj.projectId
						},
						dataSrc : function(json) {
							var myarray=new Array(json.length);
							for (i=0; i <json.length; i++){
					    	    myarray[i]=new Array(4);
					    	}
					    	for (i=0; i <json.length; i++){
				        		var obj = $.parseJSON(json[i]);
				        		myarray[i][0]='';		        		
				        		myarray[i][1]=obj.hasOwnProperty("aerialPlanId")?obj.aerialPlanId:'';
				        		myarray[i][2]=obj.hasOwnProperty("aerialActivityId")?obj.aerialActivityId:'';
				        		myarray[i][3]=obj.aerialActivityStartDate+'~'+obj.aerialActivityEndDate;
				        	}
					    	view_project_obj.registerAerialActivityListViewEvent();
					        return myarray;
						},
						dataType : 'json'
					}
				});
				
			},
			registerAerialActivityListViewEvent : function (){
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
			},
			registerStateMachineEvent : function (){
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
			},
			initializeProjectMainFormViewState : function (){		
				$("#view-form-1").find("select").prop("disabled", true).addClass("ui-state-disabled");
				$("#view-form-1").find("input").prop("disabled", true).addClass("ui-state-disabled");
				$("#view-form-1").find("textarea").prop("disabled", true).addClass("ui-state-disabled");		
			},
			initializeAerialPlanListViewState : function (){

			},
			initializeAerialPlanMainFormViewState : function (){
				$("#view-form-2-2").find("#maintainStateTD").empty();
				$("#view-form-2-2").find("#maintenanceIdTD").empty().removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("#maintenanceDate").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("#maintenancePerson").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("#maintenanceType").val("W").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("input[id*='clean']").prop("checked", true ).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("input[name*='radio']").button("enable").button("refresh").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("input[name*='comment']").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-2-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
			},
			initializeAerialActivityListViewState : function (){},
			initializeAerialActivityMainFormViewState : function (){
				$("#view-form-2-2").find("#modifyStateTD").empty();
				$("#view-form-3-2").find("#modificationIdTD").empty().removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("#modificationDate").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("#modificationPerson").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("#modificationType").val("W").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("input[id*='clean']").prop("checked", true ).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("input[name*='radio']").button("enable").button("refresh").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("input[name*='comment']").val("").removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#view-form-3-2").find("select").prop("disabled", true).addClass("ui-state-disabled");
			},
			
			stateMachine : function(){
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
			},
			
			stateMachineAerialPlan : function (){
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
			},
			
			stateMachineAerialActivity : function (){
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
	};
	

	$(function() {
		view_project_obj.prepareViewDomAction();
		
	});

	function viewProject(id) {
		view_project_obj.viewDomFinishState(id);	
		view_project_obj.transferProjectMainFormViewAction();
		view_project_obj.transferAerialPlanListViewAction();
		view_project_obj.transferAerialActivityListViewAction();
		view_project_obj.initializeProjectMainFormViewState();		
		view_project_obj.initializeAerialPlanListViewState();
		view_project_obj.initializeAerialPlanMainFormViewState();				
		view_project_obj.initializeAerialActivityListViewState();		
		view_project_obj.initializeAerialActivityMainFormViewState();		
		$("#dialog-view-form").dialog("open");

	}
</script>

<div id="dialog-view-form" title="檢視專案資料">
	<ul>
		<li><a href="#dialog-view-form-1">主要資訊</a></li>
		<li><a href="#dialog-view-form-2">航拍計畫資訊</a></li>
		<li><a href="#dialog-view-form-3">航拍活動資訊</a></li>
	</ul>
	<div id="dialog-view-form-1">
		<button id="checkProject" name="checkProject" class="ui-button ui-corner-all ui-widget">檢核專案</button>
		<button id="approveProject" name="approveProject" class="ui-button ui-corner-all ui-widget">核可專案</button>
		<button id="rejectProject" name="rejectProject" class="ui-button ui-corner-all ui-widget">退回專案</button>
		<form id="view-form-1">
			<table id="single-view-table-1">
				<%@ include file="./pages/Project_main_form.jsp" %>			
			</table>
		</form>
	</div>
	<div id="dialog-view-form-2">
		<table id="single-view-table-2-1" class="display" style="width: 100%">	
			<%@ include file="./pages/AerialPlan_list.jsp" %>		
		</table>
		<button id="checkAerialPlan" name="checkAerialPlan" class="ui-button ui-corner-all ui-widget">檢核航拍活動</button>
		<button id="approveAerialPlan" name="approveAerialPlan" class="ui-button ui-corner-all ui-widget">核可航拍活動</button>
		<button id="rejectAerialPlan" name="rejectAerialPlan" class="ui-button ui-corner-all ui-widget">退回航拍活動</button>
		<form id="view-form-2-2">
			<table id="single-view-table-2-2">		
				<%@ include file="./pages/AerialPlan_main_form.jsp" %>	
			</table>
		</form>
	</div>
	<div id="dialog-view-form-3">
		<table id="single-view-table-3-1" class="display" style="width: 100%">	
			<%@ include file="./pages/AerialActivity_list.jsp" %>		
		</table>
		<button id="checkAerialActivity" name="checkAerialActivity" class="ui-button ui-corner-all ui-widget">檢核航拍計畫</button>
		<button id="approveAerialActivity" name="approveAerialActivity" class="ui-button ui-corner-all ui-widget">核可航拍計畫</button>
		<button id="rejectAerialActivity" name="rejectAerialActivity" class="ui-button ui-corner-all ui-widget">退回航拍計畫</button>
		<form id="view-form-3-2">
			<table id="single-view-table-3-2">	
				<%@ include file="./pages/AerialActivity_main_form.jsp" %>			
			</table>
		</form>
	</div>
	

</div>