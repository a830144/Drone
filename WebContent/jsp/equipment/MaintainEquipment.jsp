<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
	function prepareMaintainDomAction(){
		$("#dialog-maintain-form").dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改保養資料",
				          icon: "ui-icon-pencil",
				          id: "updateMaintenanceBtn",
				          click: function() {
				    	      $("#maintain-form-2").find("#equipmentId").prop("disabled", false);
							  $("#maintain-form-2").find("#manufactoryName").prop("disabled", false);
							  $("#maintain-form-2").find("#constructionType").prop("disabled",false);
							  radioValue('#maintain-form-2');							
							  var myJson = JSON.stringify($("#maintain-form-2").serializeObject());
							  $.ajax({
								 url : "/Drone/equipment/UpdateMaintainEquipmentProcess",
								 type : "POST",
								 data : {
									"data" : myJson
								 },
								 success : function() {
									alert('修改保養紀錄成功');
								  }
							   })
							   //$(this).dialog("close");
				           }			 
			 		    },
			    		{
			      		  text: "新增保養資料",
			              icon: "ui-icon-plus",
			              id: "addMaintenanceBtn",
			              click: function() {
			    	         $("#maintain-form-2").find("#equipmentId").prop("disabled", false);
						     $("#maintain-form-2").find("#manufactoryName").prop("disabled", false);
						     $("#maintain-form-2").find("#constructionType").prop("disabled",false);
						     radioValue('#maintain-form-2');							
						     var myJson = JSON.stringify($("#maintain-form-2").serializeObject());
						     $.ajax({
							    url : "/Drone/equipment/MaintainEquipmentProcess",
							    type : "POST",
							    data : {
								  "data" : myJson
							    },
							    success : function() {
								  alert('新增保養紀錄成功');
							    }
						    })
						    //$(this).dialog("close");
			              }
			            },
					    {
				          text: "關閉",
				          icon: "ui-icon-closethick",
				          click: function() {
				             $( this ).dialog( "close" );
				          }
				 
				 		}],
			close : function() {
						$( "#single-maintain-table-1").unbind( "select" );
						var table1 = $('#single-maintain-table-1').DataTable();
						table1.destroy();
			}		
		});
		$("#maintain-form-2").find("#maintenanceDate").datepicker();
		$("#maintain-form-2").find("input[name*='radio']").checkboxradio();	
	}
	function maintainDomFinishState(id){
		$("#maintain-form-2").find("select").val("");
		window.equipmentId = id;
	}
	function transferMaintainListAction(){
		var singlemaintaintable1 = $('#single-maintain-table-1').DataTable( {
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
			        "id": window.equipmentId 
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
			    	registerMaintainListEvent();
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
		
	}
	function registerMaintainListEvent(){
		var singlemaintaintable1 = $('#single-maintain-table-1').DataTable();
		singlemaintaintable1.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singlemaintaintable1.cell( indexes ,1);
			var maintenanceId = cell.data();
			alert('您選取了保養編號 :'+ maintenanceId);
			$.ajax({
			  url:"/Drone/equipment/ViewMaintenanceByMaintenanceId",
			  type:"POST",
			  data:{"id" : maintenanceId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#maintain-form-2");
				$("#maintain-form-2").find("#maintenanceIdTD").empty().append("<input type='text' name='maintenanceId' id='maintenanceId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#maintain-form-2").find("#maintainStateTD").empty().append("<input type='text' name='maintainState' id='maintainState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#maintain-form-2").find("#" + key).val(value);	
					$("#maintain-form-2").find("input[name='" + key +"']").val(value);
				});
				reverseRadioValue("#maintain-form-2","ma");
				$("#addMaintenanceBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#updateMaintenanceBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			}})
		});
		singlemaintaintable1.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeMaintainMainFormState();
		    }
		});
	}
	function transferMaintainMainFormAction(){
		$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
			  type:"POST",
			  data:{"id" : window.equipmentId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#maintain-form-2");
				$.each(obj, function(key, value) {
					$("#maintain-form-2").find("#" + key).val(value);
				});
			}
		})
	}
	function initializeMaintainListState(){
		
	}
	function initializeMaintainMainFormState(){
		$("#maintain-form-2").find("#maintenanceIdTD").empty();
		$("#maintain-form-2").find("#maintainStateTD").empty();
		$("#maintain-form-2").find("#maintenanceDate").val("");
		$("#maintain-form-2").find("#maintenancePerson").val("");
		$("#maintain-form-2").find("#maintenanceType").val("W");
		$("#maintain-form-2").find("input[id*='clean']").prop("checked", true );
		$("#maintain-form-2").find("input[name*='radio']").button("enable").button("refresh");
		$("#maintain-form-2").find("input[name*='comment']").val("");
		$("#addMaintenanceBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateMaintenanceBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
	}
	
	$(function() {		
		prepareMaintainDomAction();
	});

	function maintainEquipment(id) {
		maintainDomFinishState(id);	
		transferMaintainListAction();
		transferMaintainMainFormAction();
		initializeMaintainListState();
		initializeMaintainMainFormState();		
		$("#dialog-maintain-form").dialog("open");
	}	
	
</script>


<div id="dialog-maintain-form" title="保養維護" >
	<div id = "dialog-maintain-form-1">
		<table id="single-maintain-table-1" class="display" style="width: 100%">
			<%@ include file="./pages/MaintainEquipment_list.jsp" %>
		</table>
	</div>
	<div id = "dialog-maintain-form-2">
		<form id="maintain-form-2">
			<table id="single-maintain-table-2">
				<%@ include file="./pages/MaintainEquipment_main_form.jsp" %>				
			</table>
		</form>
	</div>
</div>

