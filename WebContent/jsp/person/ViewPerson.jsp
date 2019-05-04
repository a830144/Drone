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
				$( "#single-view-table-2-1").unbind( "select" );
				var table2 = $('#single-view-table-2-1').DataTable();
				table2.destroy();
				$( "#single-view-table-3-1").unbind( "select" );
				var table3 = $('#single-view-table-3-1').DataTable();
				table3.destroy();
				$( "#single-view-table-4-1").unbind( "select" );
				var table4 = $('#single-view-table-4-1').DataTable();
				table4.destroy();
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
				//stateMachine("#view-form-1");	
				//registerStateMachineEvent();
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
		        		myarray[i][0]='';
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
			var licenseId = cell.data();
			alert('您選取了證照編號 :'+ licenseId);
			$.ajax({
			  url:"/Drone/person/ViewLicenseInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "licenseId" : licenseId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-2-2");
				$("#view-form-2-2").find("#licenseIdTD").empty().append("<input type='text' name='licenseId' id='licenseId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-2-2").find("#" + key).val(value);	
					$("#view-form-2-2").find("input[name='" + key +"']").val(value);
				});
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
		        		myarray[i][0]='';
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
			var trainingId = cell.data();
			alert('您選取了訓練紀錄編號 :'+ trainingId);
			$.ajax({
			  url:"/Drone/person/ViewTrainingInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "trainingId" : trainingId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-3-2");
				$("#view-form-3-2").find("#trainingIdTD").empty().append("<input type='text' name='trainingId' id='trainingId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-3-2").find("#" + key).val(value);	
					$("#view-form-3-2").find("input[name='" + key +"']").val(value);
				});
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
		        		myarray[i][0]='';
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
			var eventId = cell.data();
			alert('您選取了事件編號 :'+ eventId);
			$.ajax({
			  url:"/Drone/person/ViewEventInfo",
			  type:"POST",
			  data:{
				  "personId" : window.viewId,
				  "eventId" : eventId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form-4-2");
				$("#view-form-4-2").find("#eventIdIdTD").empty().append("<input type='text' name='eventId' id='eventId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#view-form-4-2").find("#" + key).val(value);	
					$("#view-form-4-2").find("input[name='" + key +"']").val(value);
				});
			}})
		});
		singleviewtable41.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeEventMainFormViewState();
		    }
		});
	}
	function initializePersonMainFormViewState(){
		$("#view-form-1").find("select").prop("disabled", true).addClass("ui-state-disabled");
		$("#view-form-1").find("input").prop("disabled", true).addClass("ui-state-disabled");
	}
	function initializeLicenseListViewState(){
		
	}
	function initializeLicenseMainFormViewState(){
		
	}
	function initializeTrainingListViewState(){
		
	}
	function initializeTrainingMainFormViewState(){
		
	}
	function initializeEventListViewState(){
		
	}
	function initializeEventMainFormViewState(){
		
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
		<form id="view-form-4-2">
			<table id="single-view-table-4-2">		
				<%@ include file="./pages/EventInPerson_main_form.jsp" %>	
			</table>
		</form>
	</div>
</div>