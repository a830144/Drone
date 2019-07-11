<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
var aerialActivity_obj = {
		projectId : '',
		aerialPlanId : '',
		equipmentId : '',
		personId_1 : '',
		personId_2 : '',

		addOrUpdate : function (action){
			  $("#aerialActivity-form").find("#projectId").prop("disabled", false);
			  $("#aerialActivity-form").find("#name").prop("disabled", false);
			  $("#aerialActivity-form").find("#aerialPlanId").prop("disabled",false);
			  $("#aerialActivity-form").find("#usage").prop("disabled",false);							
			  var myJson = JSON.stringify($("#aerialActivity-form").serializeObject());
			  var jsonObject = JSON.parse(myJson);
			  var arr = new Array();
			  jsonObject.equipmentPersonArray = arr;
			  var table = $('#single-aerialActivity-table-3-1').DataTable();
			  table.rows().eq(0).each( function ( index ) {
			    	var row = table.row( index );
			    	var cell = table.cell( index ,0);
					var equipmentId = cell.data();
					cell = table.cell( index ,6);
					var personId_1 = cell.data();
					cell = table.cell( index ,7);
					var personId_2 = cell.data();
					jsonObject.equipmentPersonArray.push({
						"equipmentId":equipmentId,
						"personId_1":personId_1,
						"personId_2":personId_2
					});
			  } );
			  myJson = JSON.stringify(jsonObject);
			  console.log(myJson);
			  $.ajax({
				 type : "POST", 
				 url : action==='update'?"/Drone/operation/UpdateAerialActivityProcess":"/Drone/operation/AddAerialActivityProcess",
				 data : {
						data : myJson
				 },
				 success : function() {
					alert(action==='update'?'修改航拍活動紀錄成功':'新增航拍活動紀錄成功');
				 }
			  })
		},
		prepareAerialActivityDomAction : function (){		
			$("#dialog-aerialActivity-form").dialog({
				autoOpen : false,
				height : 500,
				width : 1050,
				modal : true,
				buttons : [
					{
					  text: "修改航拍活動資料",
			          icon: "ui-icon-pencil",
			          id: "updateAerialActivityBtn",
			          click: function (){ aerialActivity_obj.addOrUpdate('update') }
		 		    },
		    		{
		      		  text: "新增航拍活動資料",
		              icon: "ui-icon-plus",
		              id: "addAerialActivityBtn",
		              click: function (){ aerialActivity_obj.addOrUpdate('add') }	
		            },
				    {
			          text: "關閉",
			          icon: "ui-icon-closethick",
			          click: function() {
			             $( this ).dialog( "close" );
			          }
			 
			 		}],
				close : function() {
					var table1 = $('#single-aerialActivity-table-1').DataTable();
					table1.destroy();
					
					var table3 = $('#single-aerialActivity-table-3-1').DataTable();
					$( "#single-aerialActivity-table-3-1").unbind( "select" );
					table3.destroy();
				}
			});	
			$("#dialog-aerialActivity-tabs").tabs({
				  activate: function( event, ui ) {
					  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				  }
			});
			 
			
		},
		viewDomFinishState : function (id){
			aerialActivity_obj.projectId = id;
		},
		transferAerialActivityListAction : function (){
			var singleaerialActivitytable1 = $('#single-aerialActivity-table-1').DataTable( {
				columnDefs: [
				{	
					className: 'dt-center',
					targets: '_all'
				}],
				select:true,
		        order: [[ 1, 'asc' ]],
				scrollY: "100px",
		        scrollCollapse: true,		
				ajax: {
					type: "POST",
				    url: "/Drone/operation/QueryAerailActivitiesProcess",  
				    data: {  
				    	projectId : aerialActivity_obj.projectId 
				    }, 
				    dataSrc: function ( json ) {
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
				    	//aerialActivity_obj.registerAerialActivityListEvent();
				        return myarray;
				      }
				    ,
			        dataType: 'json'
				}

			});
		},
		registerAerialActivityListEvent : function (){

		},
		transferAerialActivityMainFormAction : function (){
			$.post("/Drone/operation/ViewProjectProcess", {
				id : aerialActivity_obj.projectId
			}, function(data, status) {
				if (status == 'success') {
					var obj = data;
					var form = $("#dialog-aerialActivity-form-2");
					$.each(obj, function(key, value) {
						form.find("#" + key).val(value);
					});
					form.find("#projectId").prop("disabled", true);
					form.find("#name").prop("disabled", true);
				}

			});
			
			var container = document.getElementById('root-activity');
			ReactDOM.render(React.createElement(Steps_activity, {}), container);
			container = document.getElementById('firstStep-activity');
			ReactDOM.render(React.createElement(FirstStep_activity, {}), container);
			container = document.getElementById('secondStep-activity');
			ReactDOM.render(React.createElement(SecondStep_activity, {}), container);
			container = document.getElementById('thirdStep-activity');
			ReactDOM.render(React.createElement(ThirdStep_activity, {}), container);
			
			$.ajax({
				url : "/Drone/operation/QueryAerailPlansIDsByProjectId",
				type : "POST",
				data: {  
			    	projectId: aerialActivity_obj.projectId 
			    }, 
				success : function(tag) {  
					$("#dialog-aerialActivity-form-2").find("#aerialPlanId").append(tag);
					aerialActivity_obj.registerAerialPlanIdEvent();
		        }
			});	
			
			
		},
		registerAerialPlanIdEvent : function (){
			$("#dialog-aerialActivity-form-2").find("#aerialPlanId").change(function() {		
				var id = $("#dialog-aerialActivity-form-2").find("#aerialPlanId").val();
				//aerialPlanId change,ajax bring other data in form2
				$.ajax({
					url : "/Drone/operation/ViewAerialPlanByAerialPlanId",
					type : "POST",	
					data : {
						"id" : id
					},
					success : function(json) {   
						$("#dialog-aerialActivity-form-2").find("#usage").val(json.usage);
						$("#dialog-aerialActivity-form-2").find("#startDate").val(json.startDate);
						$("#dialog-aerialActivity-form-2").find("#endDate").val(json.endDate);
		            }
				});
				//aerialPlanId change,ajax refresh the table in form3
				aerialActivity_obj.aerialPlanId = id;
			});
		},
		
		transferEquipmentInPlanListAction : function(){
			var singleaerialActivitytable31 = $('#single-aerialActivity-table-3-1').DataTable( {
				"select":true,
		        "order": [[ 1, 'asc' ]],
				"scrollY": "100px",
		        "scrollCollapse": true
			});
			
		},
		
		initializeAerialActivityListState : function (){
			
		},

		initializeAerialActivityMainFormState : function (){
			
		}	
}

$(function() {
	aerialActivity_obj.prepareAerialActivityDomAction();
});


function aerialActivity(id){	
	aerialActivity_obj.viewDomFinishState(id);	
	aerialActivity_obj.transferAerialActivityListAction();
	aerialActivity_obj.transferAerialActivityMainFormAction();
	aerialActivity_obj.transferEquipmentInPlanListAction();
	aerialActivity_obj.initializeAerialActivityListState();		
	$("#dialog-aerialActivity-form").dialog("open");

}
</script>
<div id="dialog-aerialActivity-form" title="檢視航拍活動資料" style="display: none;">
	<div id="dialog-aerialActivity-form-1">
		<table id="single-aerialActivity-table-1">
			<%@ include file="./pages/AerialActivity_list.jsp"%>
		</table>
	</div>
	<div id="dialog-aerialActivity-tabs">
		<ul>
			<li><a href="#dialog-aerialActivity-form-2">主要資訊</a></li>
			<li><a href="#dialog-aerialActivity-form-3">使用設備資訊</a></li>
		</ul>
		<form id="aerialActivity-form">
			<div id="dialog-aerialActivity-form-2">
				<table id="single-aerialActivity-table-2-1" class="display" 
					style="width: 100%">
					<%@ include file="./pages/AerialActivity_main_form.jsp"%>
				</table>
			</div>
			<div id="dialog-aerialActivity-form-3">
				<table id="single-aerialActivity-table-3-1" class="display"
					style="width: 100%">
					<%@ include file="./pages/EquipmentInActivity_list.jsp"%>
				</table>
				<div id="root-activity"></div>
			</div>
		</form>
	</div>	
</div>
<script src="/Drone/js/aerialActivity.steps.js" charset="utf-8"></script>