<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function prepareLicenseDomAction(){
		$("#dialog-license-form").dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
				{
					  text: "修改人員操作證資料",
			          icon: "ui-icon-pencil",
			          id: "updateLicenseBtn",
			          click: function() {
			    	      $("#license-form-2").find("#personId").prop("disabled", false);
						  $("#license-form-2").find("#name").prop("disabled", false);						
						  var myJson = JSON.stringify($("#license-form-2").serializeObject());
						  $.ajax({
							 url : "/Drone/person/UpdateLicenseInPersonProcess",
							 type : "POST",
							 data : {
								"data" : myJson
							 },
							 success : function() {
								alert('修改人員操作證資料成功');
							  }
						   })
						   //$(this).dialog("close");
			           }			 
		 		    },
		    		{
		      		  text: "新增人員操作證資料",
		              icon: "ui-icon-plus",
		              id: "addLicenseBtn",
		              click: function() {
		            	 $("#license-form-2").find("#personId").prop("disabled", false);
						 $("#license-form-2").find("#name").prop("disabled", false);						
					     var myJson = JSON.stringify($("#license-form-2").serializeObject());
					     $.ajax({
						    url : "/Drone/person/LicenseInPersonProcess",
						    type : "POST",
						    data : {
							  "data" : myJson
						    },
						    success : function() {
							  alert('新增人員操作證紀錄成功');
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
				$( "#single-license-table-1").unbind( "select" );
				var table1 = $('#single-license-table-1').DataTable();
				table1.destroy();
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
				$("#license-form-2").find("#type").append(tag);
            }
		});
		
		$("#license-form-2").find("#gotDate").datepicker();
	}
	function licenseDomFinishState(id){
		$("#license-form-2").find("input").val("");
		window.personId = id;
	}
	function transferLicenseListAction(){
		var singlelicensetable1 = $('#single-license-table-1').DataTable( {
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
			        "id": window.personId  
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
			    	registerLicenseListEvent();
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
	}
	function registerLicenseListEvent(){
		var singlelicensetable1 = $('#single-license-table-1').DataTable();
		singlelicensetable1.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singlelicensetable1.cell( indexes ,1);
			var licenseId = cell.data();
			alert('您選取了證照編號 :'+ licenseId);
			$.ajax({
			  url:"/Drone/person/ViewLicenseInfo",
			  type:"POST",
			  data:{
				  "personId" : window.personId,
				  "licenseId" : licenseId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#license-form-2");
				$("#license-form-2").find("#licenseIdTD").empty().append("<input type='text' name='licenseId' id='licenseId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#license-form-2").find("#" + key).val(value);	
					$("#license-form-2").find("input[name='" + key +"']").val(value);
				});
				$("#addLicenseBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#updateLicenseBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			}})
		});
		singlelicensetable1.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeLicenseMainFormState();
		    }
		});
	}
	function transferLicenseMainFormAction(){
		$.post("/Drone/person/ViewPersonProcess", {
			id : window.personId
		}, function(data, status) {
			if (status == 'success') {
				var obj = data;
				var form = $("#license-form");
				$.each(obj, function(key, value) {
					$("#license-form-2").find("#" + key).val(value);
				});

				$("#license-form-2").find("#personId").prop("disabled", true);
				$("#license-form-2").find("#name").prop("disabled", true);
			}

		});
	}
	function initializeLicenseListState(){
		
	}
	function initializeLicenseMainFormState(){
		$("#license-form-2").find("#type").val("1");
		$("#license-form-2").find("#gotDate").val("");
		$("#addLicenseBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateLicenseBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
	}
	
	$(function() {		
		prepareLicenseDomAction();
	});
	
	function licenseInPerson(id) {
		licenseDomFinishState(id);	
		transferLicenseListAction();
		transferLicenseMainFormAction();
		initializeLicenseListState();
		initializeLicenseMainFormState();		
		$("#dialog-license-form").dialog("open");
	}
</script>
<div id="dialog-license-form" title="新增操作證" >
	<div id = "dialog-license-form-1">
		<table id="single-license-table-1" class="display" style="width: 100%">
			<%@ include file="./pages/LicenseInPerson_list.jsp" %>
		</table>
	</div>
	<div id = "dialog-license-form-2">
		<form id="license-form-2">
			<table id="single-license-table-2">
					<%@ include file="./pages/LicenseInPerson_main_form.jsp" %>	
			</table>
		</form>
	</div>
</div>