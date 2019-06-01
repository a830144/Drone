<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
function prepareTrainingDomAction(){
	$("#dialog-training-form").dialog({
		autoOpen : false,
		height : 600,
		width : 1250,
		modal : true,
		buttons : [
			{
				  text: "修改人員訓練資料",
		          icon: "ui-icon-pencil",
		          id: "updateTrainingBtn",
		          click: function() {
		    	      $("#training-form-2").find("#personId").prop("disabled", false);
					  $("#training-form-2").find("#name").prop("disabled", false);						
					  var myJson = JSON.stringify($("#training-form-2").serializeObject());
					  $.ajax({
						 url : "/Drone/equipment/UpdateTrainingInPersonProcess",
						 type : "POST",
						 data : {
							"data" : myJson
						 },
						 success : function() {
							alert('修改人員訓練資料成功');
						  }
					   })
		           }			 
	 		    },
	    		{
	      		  text: "新增人員訓練資料",
	              icon: "ui-icon-plus",
	              id: "addTrainingBtn",
	              click: function() {
	            	 $("#training-form-2").find("#personId").prop("disabled", false);
					 $("#training-form-2").find("#name").prop("disabled", false);						
				     var myJson = JSON.stringify($("#training-form-2").serializeObject());
				     $.ajax({
					    url : "/Drone/person/TrainingInPersonProcess",
					    type : "POST",
					    data : {
						  "data" : myJson
					    },
					    success : function() {
						  alert('新增人員訓練紀錄成功');
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
			$( "#single-training-table-1").unbind( "select" );
			var table1 = $('#single-training-table-1').DataTable();
			table1.destroy();
		}
	});
	
	$.ajax({
		url : "/Drone/other/QueryTrainingIDs",
		type : "POST",
		success : function(tag) {  
			$("#training-form-2").find("#trainingId").append(tag);
			registerTrainingIdEvent();
        }
	});	
	
	$("#training-form-2").find("#startDate").datepicker();
	$("#training-form-2").find("#endDate").datepicker();
}
function registerTrainingIdEvent(){
	$("#training-form-2").find("#trainingId").change(function() {
		
		var id = $("#training-form-2").find('#trainingId').val();
		$.ajax({
			url : "/Drone/other/ViewTrainingProcess",
			type : "POST",	
			data : {
				"id" : id
			},
			success : function(json) {   
				//alert(json.trainingName);
				$("#training-form-2").find("#trainingName").val(json.trainingName);
				$("#training-form-2").find("#unit").val(json.unit);
				$("#training-form-2").find("#trainingType").val(json.trainingType);
            }
		});
	});

}

function trainingDomFinishState(id){
	$("#training-form-2").find("input").val("");
	window.personId = id;
}
function transferTrainingListAction(){
	var singletrainingtable1 = $('#single-training-table-1').DataTable( {
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
		    "url": "/Drone/person/QueryTrainings",  
		    "data": {  
		        "id": window.personId  
		    }, 
		    "dataSrc": function ( json ) {
		    	var myarray=new Array(json.length);
		    	for (i=0; i <json.length; i++){
		    	    myarray[i]=new Array(7);
		    	}
		    	for(i=0;i<json.length;i++){
	        		var obj = $.parseJSON(json[i]);
	        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
	        		myarray[i][1]=obj.hasOwnProperty("trainingId")?obj.trainingId:'';
	        		myarray[i][2]=obj.hasOwnProperty("trainingName")?obj.trainingName:'';
	        		myarray[i][3]=obj.hasOwnProperty("unit")?obj.unit:'';
	        		myarray[i][4]=obj.startDate + "~" + obj.endDate;
	        		myarray[i][5]=obj.hasOwnProperty("hours")?obj.hours:'';
	        		myarray[i][6]=obj.hasOwnProperty("trainingType")?obj.trainingType:'';
	        	}
		    	registerTrainingListEvent();
		        return myarray;
		      }
		    ,
	        dataType: 'json'
		}

	});
}
function registerTrainingListEvent(){
	var singletrainingtable1 = $('#single-training-table-1').DataTable();
	singletrainingtable1.on( 'select', function ( e, dt, type, indexes ) {
		var cell = singletrainingtable1.cell( indexes ,1);
		var trainingId = cell.data();
		alert('您選取了證照編號 :'+ trainingId);
		$.ajax({
		  url:"/Drone/person/ViewTrainingInfo",
		  type:"POST",
		  data:{
			  "personId" : window.personId,
			  "trainingId" : trainingId
		  },
		  dataType: "json",
		  success: function(data){				  
			var obj = data;
			var form = $("#training-form-2");
			$.each(obj, function(key, value) {
				$("#training-form-2").find("#" + key).val(value);	
				$("#training-form-2").find("input[name='" + key +"']").val(value);
			});
			$("#addTrainingBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateTrainingBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}})
	});
	singletrainingtable1.on( 'deselect', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {				
			initializeTrainingMainFormState();
	    }
	});
}
function transferTrainingMainFormAction(){
	$.post("/Drone/person/ViewPersonProcess", {
		id : window.personId
	}, function(data, status) {
		if (status == 'success') {
			var obj = data;
			var form = $("#training-form");
			$.each(obj, function(key, value) {
				$("#training-form-2").find("#" + key).val(value);
			});

			$("#training-form-2").find("#personId").prop("disabled", true);
			$("#training-form-2").find("#name").prop("disabled", true);
		}

	});
}
function initializeTrainingListState(){
	
}
function initializeTrainingMainFormState(){
	$("#training-form-2").find("#trainingId").val("1");
	$("#training-form-2").find("#trainingType").val("1");
	$("#training-form-2").find("#unit").val("");
	$("#training-form-2").find("#trainingName").val("");
	$("#training-form-2").find("#startDate").val("");
	$("#training-form-2").find("#endDate").val("");
	$("#training-form-2").find("#hours").val("");
	$("#addTrainingBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
	$("#updateTrainingBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
}

$(function() {		
	prepareTrainingDomAction();
});

function trainingInPerson(id) {
	trainingDomFinishState(id);	
	transferTrainingListAction();
	transferTrainingMainFormAction();
	initializeTrainingListState();
	initializeTrainingMainFormState();		
	$("#dialog-training-form").dialog("open");
}
</script>
<div id="dialog-training-form" title="新增訓練" >
	<div id = "dialog-training-form-1">
		<table id="single-training-table-1" class="display" style="width: 100%">
			<%@ include file="./pages/TrainingInPerson_list.jsp" %>
		</table>
	</div>
	<div id = "dialog-training-form-2">
		<form id="training-form-2">
			<table id="single-training-table-2">
				<%@ include file="./pages/TrainingInPerson_main_form.jsp" %>	
			</table>
		</form>
	</div>
</div>