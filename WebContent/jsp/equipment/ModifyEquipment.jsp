<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
	function prepareModifyDomAction(){
		$("#dialog-modify-form").dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改改裝資料",
				          icon: "ui-icon-pencil",
				          id: "updateModificationBtn",
				          click: function() {
				    	      $("#modify-form-2").find("#equipmentId").prop("disabled", false);
							  $("#modify-form-2").find("#manufactoryName").prop("disabled", false);
							  $("#modify-form-2").find("#constructionType").prop("disabled",false);
							  radioValue('#modify-form-2');							
							  var myJson = JSON.stringify($("#modify-form-2").serializeObject());
							  $.ajax({
								 url : "/Drone/equipment/UpdateModifyEquipmentProcess",
								 type : "POST",
								 data : {
									"data" : myJson
								 },
								 success : function() {
									alert('修改改裝紀錄成功');
								  }
							   })
							   $(this).dialog("close");
				           }			 
			 		    },
			    		{
			      		  text: "新增改裝資料",
			              icon: "ui-icon-plus",
			              id: "addModificationBtn",
			              click: function() {
			    	         $("#modify-form-2").find("#equipmentId").prop("disabled", false);
						     $("#modify-form-2").find("#manufactoryName").prop("disabled", false);
						     $("#modify-form-2").find("#constructionType").prop("disabled",false);
						     radioValue('#modify-form-2');							
						     var myJson = JSON.stringify($("#modify-form-2").serializeObject());
						     $.ajax({
							    url : "/Drone/equipment/ModifyEquipmentProcess",
							    type : "POST",
							    data : {
								  "data" : myJson
							    },
							    success : function() {
								  alert('新增改裝紀錄成功');
							    }
						    })
						    $(this).dialog("close");
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
						$( "#single-modify-table-1").unbind( "select" );
						var table1 = $('#single-modify-table-1').DataTable();
						table1.destroy();
			}		
		});
		$("#modify-form-2").find("#modificationDate").datepicker();
		$("#modify-form-2").find("input[name*='radio']").checkboxradio();
	}
	function modifyDomFinishState(id){
		window.equipmentId = id;
	}	
	function transferModifyListAction(){
		var singlemodifytable1 = $('#single-modify-table-1').DataTable({
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
					"id" : window.equipmentId
				},
				"dataSrc" : function(json) {
					var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(10);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("modifyState")?obj.modifyState:'';			        		
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
			    	registerModifyListEvent();
			        return myarray;
				},
				dataType : 'json'
			}

		});
		
	}
	function registerModifyListEvent(){
		var singlemodifytable1 = $('#single-modify-table-1').DataTable();
		singlemodifytable1.on( 'select', function ( e, dt, type, indexes ) {
			var cell = singlemodifytable1.cell( indexes ,1);
			var modificationId = cell.data();
			alert('您選取了改裝編號 :'+ modificationId);
			$.ajax({
			  url:"/Drone/equipment/ViewModificationByModificationId",
			  type:"POST",
			  data:{"id" : modificationId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#modify-form-2");
				$("#modify-form-2").find("#modificationIdTD").empty().append("<input type='text' name='modificationId' id='modificationId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$("#modify-form-2").find("#modifyStateTD").empty().append("<input type='text' name='modifyState' id='modifyState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
				$.each(obj, function(key, value) {
					$("#modify-form-2").find("#" + key).val(value);	
					$("#modify-form-2").find("input[name='" + key +"']").val(value);
				});
				reverseRadioValue("#modify-form-2","mo");
				$("#addModificationBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
				$("#updateModificationBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			}})
		});
		singlemodifytable1.on( 'deselect', function ( e, dt, type, indexes ) {
			if ( type === 'row' ) {				
				initializeModifyMainFormState();
		    }
		});
	}
	function transferModifyMainFormAction(){
		$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
			  type:"POST",
			  data:{"id" : window.equipmentId},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#modify-form-2");
				$.each(obj, function(key, value) {
					$("#modify-form-2").find("#" + key).val(value);
				});
			}
		})
	}
	function initializeModifyListState(){
		
	}
	function initializeModifyMainFormState(){
		$("#modify-form-2").find("#modificationIdTD").empty();
		$("#modify-form-2").find("#modifyStateTD").empty();
		$("#modify-form-2").find("#modificationIdDate").val("");
		$("#modify-form-2").find("#modificationIdPerson").val("");
		$("#modify-form-2").find("input[id*='clean']").prop("checked", true );
		$("#modify-form-2").find("input[name*='radio']").button("enable").button("refresh");
		$("#modify-form-2").find("input[name*='comment']").val("");
		$("#addModificationBtn").prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateModificationBtn").prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
	}
	$(function() {
		prepareModifyDomAction();
	});

	function modifyEquipment(id) {
		modifyDomFinishState(id);
		transferModifyListAction();
		transferModifyMainFormAction();
		initializeModifyListState();
		initializeModifyMainFormState();
		$("#dialog-modify-form").dialog("open");
	}
</script>
<div id="dialog-modify-form" title="改裝設備明細">
	<div id = "dialog-modify-form-1">
		<table id="single-modify-table-1" class="display" style="width: 100%">
			<%@ include file="./pages/ModifyEquipment_list.jsp" %>
		</table>
	</div>
	<div id = "dialog-modify-form-2">
		<form id="modify-form-2">
			<table id="single-modify-table-2">
				<%@ include file="./pages/ModifyEquipment_main_form.jsp" %>				
			</table>
		</form>
	</div>
</div>