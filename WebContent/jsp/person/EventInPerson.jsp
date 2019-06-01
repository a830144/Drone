<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

function prepareEventDomAction(){
	$("#dialog-event-form").dialog({
		autoOpen : false,
		height : 600,
		width : 1250,
		modal : true,
		buttons : [
			{
				  text: "修改人員事件資料",
		          icon: "ui-icon-pencil",
		          id: "updateEventBtn",
		          click: function() {
		    	      $("#event-form-2").find("#personId").prop("disabled", false);
					  $("#event-form-2").find("#name").prop("disabled", false);						
					  var myJson = JSON.stringify($("#event-form-2").serializeObject());
					  $.ajax({
						 url : "/Drone/equipment/UpdateEventInPersonProcess",
						 type : "POST",
						 data : {
							"data" : myJson
						 },
						 success : function() {
							alert('修改人員事件資料成功');
						  }
					   })
		           }			 
	 		    },
	    		{
	      		  text: "新增人員事件資料",
	              icon: "ui-icon-plus",
	              id: "addEventBtn",
	              click: function() {
	            	 $("#event-form-2").find("#personId").prop("disabled", false);
					 $("#event-form-2").find("#name").prop("disabled", false);						
				     var myJson = JSON.stringify($("#event-form-2").serializeObject());
				     $.ajax({
					    url : "/Drone/person/EventInPersonProcess",
					    type : "POST",
					    data : {
						  "data" : myJson
					    },
					    success : function() {
						  alert('新增人員事件紀錄成功');
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
			$( "#single-event-table-1").unbind( "select" );
			var table1 = $('#single-event-table-1').DataTable();
			table1.destroy();
		}
	});
	
	$.ajax({
		url : "/Drone/other/QueryEventIDs",
		type : "POST",
		success : function(tag) {  
			$("#event-form-2").find("#eventId").append(tag);
			registerEventIdEvent();
        }
	});	
	
	$("#event-form-2").find("#startDate").datepicker();
	$("#event-form-2").find("#endDate").datepicker();
}
function registerEventIdEvent(){
	$("#event-form-2").find("#eventId").change(function() {
		
		var id = $("#event-form-2").find('#eventId').val();
		$.ajax({
			url : "/Drone/other/ViewEventProcess",
			type : "POST",	
			data : {
				"id" : id
			},
			success : function(json) {   
				$("#event-form-2").find("#startDate").val(json.startDate);
				$("#event-form-2").find("#endDate").val(json.endDate);
				$("#event-form-2").find("#eventName").val(json.eventName);	    			
				$("#event-form-2").find("#description").val(json.description);

            }
		});
	});

}

function eventDomFinishState(id){
	$("#event-form-2").find("input").val("");
	window.personId = id;
}
function transferEventListAction(){
	var singleeventtable1 = $('#single-event-table-1').DataTable( {
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
		    "url": "/Drone/person/QueryEvents",  
		    "data": {  
		        "id": window.personId  
		    }, 
		    "dataSrc": function ( json ) {
		    	var myarray=new Array(json.length);
		    	for (i=0; i <json.length; i++){
		    	    myarray[i]=new Array(6);
		    	}
		    	for(i=0;i<json.length;i++){
	        		var obj = $.parseJSON(json[i]);
	        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
	        		myarray[i][1]=obj.hasOwnProperty("eventId")?obj.eventId:'';
	        		myarray[i][2]=obj.hasOwnProperty("eventName")?obj.eventName:'';
	        		myarray[i][3]=obj.hasOwnProperty("unit")?obj.unit:'';
	        		myarray[i][4]=obj.startDate + "~" + obj.endDate;
	        		myarray[i][5]=obj.hasOwnProperty("description")?obj.description:'';
	        	}
		    	registerEventListEvent();
		        return myarray;
		      }
		    ,
	        dataType: 'json'
		}

	});
}
function registerEventListEvent(){
	var singleeventtable1 = $('#single-event-table-1').DataTable();
	singleeventtable1.on( 'select', function ( e, dt, type, indexes ) {
		var cell = singleeventtable1.cell( indexes ,1);
		var eventId = cell.data();
		alert('您選取了事件編號 :'+ eventId);
		$.ajax({
		  url:"/Drone/person/ViewEventInfo",
		  type:"POST",
		  data:{
			  "personId" : window.personId,
			  "eventId" : eventId
		  },
		  dataType: "json",
		  success: function(data){				  
			var obj = data;
			var form = $("#event-form-2");
			$.each(obj, function(key, value) {
				$("#event-form-2").find("#" + key).val(value);	
				$("#event-form-2").find("input[name='" + key +"']").val(value);
			});
			$("#addEventBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateEventBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}})
	});
	singleeventtable1.on( 'deselect', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {				
			initializeEventMainFormState();
	    }
	});
}
function transferEventMainFormAction(){
	$.post("/Drone/person/ViewPersonProcess", {
		id : window.personId
	}, function(data, status) {
		if (status == 'success') {
			var obj = data;
			var form = $("#event-form");
			$.each(obj, function(key, value) {
				$("#event-form-2").find("#" + key).val(value);
			});

			$("#event-form-2").find("#personId").prop("disabled", true);
			$("#event-form-2").find("#name").prop("disabled", true);
		}

	});
}
function initializeEventListState(){
	
}
function initializeEventMainFormState(){
	$("#event-form-2").find("#eventId").val("1");
	$("#event-form-2").find("#eventType").val("1");
	$("#event-form-2").find("#unit").val("");
	$("#event-form-2").find("#eventName").val("");
	$("#event-form-2").find("#startDate").val("");
	$("#event-form-2").find("#endDate").val("");
	$("#event-form-2").find("#hours").val("");
	$("#addEventBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
	$("#updateEventBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
}

$(function() {		
	prepareEventDomAction();
});

function eventInPerson(id) {
	eventDomFinishState(id);	
	transferEventListAction();
	transferEventMainFormAction();
	initializeEventListState();
	initializeEventMainFormState();		
	$("#dialog-event-form").dialog("open");
}
</script>
<div id="dialog-event-form" title="新增特殊事蹟" >
	<div id = "dialog-event-form-1">
		<table id="single-event-table-1" class="display" style="width: 100%">
			<%@ include file="./pages/EventInPerson_list.jsp" %>
		</table>
	</div>
	<div id = "dialog-event-form-2">
		<form id="event-form-2">
			<table id="single-event-table-2">
				<%@ include file="./pages/EventInPerson_main_form.jsp" %>
			</table>
		</form>
	</div>
</div>