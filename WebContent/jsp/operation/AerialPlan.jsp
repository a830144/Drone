<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
function prepareAerialPlanDomAction(){
	$("#dialog-aerialPlan-form").dialog({
		autoOpen : false,
		height : 700,
		width : 1350,
		modal : true,
		buttons : [
			{
			  text: "修改航拍計畫資料",
	          icon: "ui-icon-pencil",
	          id: "updateAerialPlanBtn",
	          click: function() {
	        	     $("#aerialPlan-form").find("#projectId").prop("disabled", false);
				     $("#aerialPlan-form").find("#name").prop("disabled", false);						
				     var myJson = JSON.stringify($("#aerialPlan-form").serializeObject());
				     var jsonObject = JSON.parse(myJson);
				     var arr = new Array();
				     jsonObject.equipmentsArray = arr;
				     var table = $('#single-aerialPlan-table-3-1').DataTable();
				     table.rows().eq(0).each( function ( index ) {
				    	    var row = table.row( index );
				    	    var cell = table.cell( index ,0);
							var equipmentId = cell.data();
							cell = table.cell( index ,6);
							var personId_1 = cell.data();
							cell = table.cell( index ,7);
							var personId_2 = cell.data();
							jsonObject['equipmentsArray'].push({"equipmentId":equipmentId,"personId_1":personId_1,"personId_2":personId_2});
				     } );
				     myJson = JSON.stringify(jsonObject);
				     $.ajax({
						 url : "/Drone/operation/UpdateAerialPlanProcess",
						 type : "POST",
						 data : {
							data : myJson
						 },
						 success : function() {
							alert('修改航拍計畫紀錄成功');
						 }
					 })
	          }			 
 		    },
    		{
      		  text: "新增航拍計畫資料",
              icon: "ui-icon-plus",
              id: "addAerialPlanBtn",
              click: function() {
    	         $("#aerialPlan-form").find("#projectId").prop("disabled", false);
			     $("#aerialPlan-form").find("#name").prop("disabled", false);						
			     var myJson = JSON.stringify($("#aerialPlan-form").serializeObject());
			     var jsonObject = JSON.parse(myJson);
			     var arr = new Array();
			     jsonObject.equipmentsArray = arr;
			     var table = $('#single-aerialPlan-table-3-1').DataTable();
			     table.rows().eq(0).each( function ( index ) {
			    	    var row = table.row( index );
			    	    var cell = table.cell( index ,0);
						var equipmentId = cell.data();
						cell = table.cell( index ,6);
						var personId_1 = cell.data();
						cell = table.cell( index ,7);
						var personId_2 = cell.data();
						jsonObject['equipmentsArray'].push({"equipmentId":equipmentId,"personId_1":personId_1,"personId_2":personId_2});
			     } );
			     myJson = JSON.stringify(jsonObject);
			     $.ajax({
					 url : "/Drone/operation/AddAerialPlanProcess",
					 type : "POST",
					 data : {
						"data" : myJson
					 },
					 success : function() {
						alert('新增航拍計畫紀錄成功');
					  }
				 })
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
			$( "#single-aerialPlan-table-1").unbind( "select" );
			var table1 = $('#single-aerialPlan-table-1').DataTable();
			table1.destroy();

			var table3 = $('#single-aerialPlan-table-3-1').DataTable();
			$( "#single-aerialPlan-table-3-1").unbind( "select" );
			table3.destroy();
		}
	});	
	$("#dialog-aerialPlan-tabs").tabs({
		  activate: function( event, ui ) {
			  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
		  }
	});
	
	$("#aerialPlan-form").find("#startDate").datepicker();
	$("#aerialPlan-form").find("#endDate").datepicker();
}

function viewDomFinishState(id){
	window.projectId = id;
}

function transferAerialPlanListAction(){
	var singleaerialPlantable1 = $('#single-aerialPlan-table-1').DataTable( {
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
		    "url": "/Drone/operation/QueryAerailPlansProcess",  
		    "data": {  
		        "projectId": window.projectId 
		    }, 
		    "dataSrc": function ( json ) {
		    	var myarray=new Array(json.length);
		    	for (i=0; i <json.length; i++){
		    	    myarray[i]=new Array(4);
		    	}
		    	for (i=0; i <json.length; i++){
	        		var obj = $.parseJSON(json[i]);
	        		myarray[i][0]='';		        		
	        		myarray[i][1]=obj.hasOwnProperty("aerialPlanId")?obj.aerialPlanId:'';
	        		myarray[i][2]=obj.hasOwnProperty("usage")?obj.usage:'';
	        		myarray[i][3]=obj.aerialPlanStartDate+'~'+obj.aerialPlanEndDate;	        		
	        	}
		    	registerAerialPlanListEvent();
		        return myarray;
		      }
		    ,
	        dataType: 'json'
		}
	});
	$(' .dataTables_scrollBody').height(100);
}

function registerAerialPlanListEvent(){
	var singleaerialplantable1 = $('#single-aerialPlan-table-1').DataTable();
	singleaerialplantable1.on( 'select', function ( e, dt, type, indexes ) {
		var cell = singleaerialplantable1.cell( indexes ,1);
		var aerialPlanId = cell.data();
		alert('您選取了航拍計畫編號 :'+ aerialPlanId);
		$.ajax({
		  url:"/Drone/operation/ViewAerialPlanByAerialPlanId",
		  type:"POST",
		  data:{"id" : aerialPlanId},
		  dataType: "json",
		  success: function(data){				  
			var obj = data;
			var form = $("#aerialPlan-form");
			form.find("#aerialPlanIdTD").empty().append("<input type='text' name='aerialPlanId' id='aerialPlanId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
			form.find("#aerialPlanStateTD").empty().append("<input type='text' name='aerialPlanState' id='aerialPlanState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
			$.each(obj, function(key, value) {
				form.find("#" + key).val(value);	
				form.find("input[name='" + key +"']").val(value);
			});
			$("#addAerialPlanBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateAerialPlanBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}})
	});
	singleaerialplantable1.on( 'deselect', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {				
			initializeAerialPlanMainFormState();
	    }
	});
}


function transferAerialPlanMainFormAction(){
	 $.post("/Drone/operation/ViewProjectProcess", {
		id : window.projectId
	}, function(data, status) {
		if (status == 'success') {
			var obj = data;
			var form = $("#dialog-aerialPlan-form-2");
			$.each(obj, function(key, value) {
				form.find("#" + key).val(value);
			});
			form.find("#projectId").prop("disabled", true);
			form.find("#name").prop("disabled", true);
		}

	}); 
	
}

function transferEquipmentInPlanListAction(){
	var singleaerialPlantable31 = $('#single-aerialPlan-table-3-1').DataTable( {
		"select":true,
        "order": [[ 1, 'asc' ]],
		"scrollY": "100px",
        "scrollCollapse": true

	});
	
}

function initializeAerialPlanListState(){
	
}

function initializeAerialPlanMainFormState(){
	
}

$(function() {
	prepareAerialPlanDomAction();
});


function aerialPlan(id){
	viewDomFinishState(id);	
	transferAerialPlanListAction();
	transferAerialPlanMainFormAction();
	transferEquipmentInPlanListAction();
	initializeAerialPlanListState();	
	$("#dialog-aerialPlan-form").dialog("open");

}
</script>
<div id="dialog-aerialPlan-form" title="檢視航拍計畫資料">
	<div id="dialog-aerialPlan-form-1">
		<table id="single-aerialPlan-table-1">
			<%@ include file="./pages/AerialPlan_list.jsp"%>
		</table>
	</div>


	<div id="dialog-aerialPlan-tabs">
		<ul>
			<li><a href="#dialog-aerialPlan-form-2">主要資訊</a></li>
			<li><a href="#dialog-aerialPlan-form-3">使用設備資訊</a></li>
			<li><a href="#dialog-aerialPlan-form-4">其他資訊</a></li>
		</ul>
		<form id="aerialPlan-form">
			<div id="dialog-aerialPlan-form-2">
				<table id="single-aerialPlan-table-2-1" class="display"
					style="width: 100%">
					<%@ include file="./pages/AerialPlan_main_form.jsp"%>
				</table>
			</div>
			<div id="dialog-aerialPlan-form-3">
				<table id="single-aerialPlan-table-3-1" class="display"
					style="width: 100%">
					<%@ include file="./pages/EquipmentInPlan_list.jsp"%>
				</table>
				<div id="root"></div>
			</div>
			<div id="dialog-aerialPlan-form-4">
				<table id="single-aerialPlan-table-4-1" class="display"
					style="width: 100%">
					<%@ include file="./pages/AerialPlan_other_form.jsp"%>
				</table>
			</div>
		</form>
	</div>
</div>
<script src="/Drone/js/aerialPlan.steps.js" charset="utf-8"></script>