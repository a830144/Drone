<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
function prepareQueryDomAction(){
	$("#query_form").find("#query").button();
}
function queryDomFinishState(){
	window.queryString = $('#query_name').val();
}
function transferQueryListAction(){
	var table = $('#single-query-table-1').DataTable( {	
		columnDefs: [
			{	
			    className: 'dt-center',
			    targets: '_all'
			}],		
		"ajax": {  
			 "type": "POST",
	   		 "url": "/Drone/person/QueryPersonProcess",  
	   		 "data": {  
	       		 "name": 'empty' 
	   		 }, 
    		 "dataSrc": function ( json ) {
    				var myarray=new Array(json.length);
	    			for (i=0; i <json.length; i++){
	    	   			 myarray[i]=new Array(4);
	    			}
	    			for(i=0;i<json.length;i++){
        				var obj = $.parseJSON(json[i]);
        				myarray[i][0]=obj.hasOwnProperty("personId")?obj.personId:'';
        				myarray[i][1]=obj.hasOwnProperty("name")?obj.name:'';		        			
        				myarray[i][2]=obj.hasOwnProperty("sex")?obj.sex:'';
        				myarray[i][3]=obj.hasOwnProperty("state")?obj.state:'';
        				var view_btn = "<button name='view"+ obj.personId +"' id='" + obj.personId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
        				var update_btn = "<button name='update"+ obj.personId +"' id='" + obj.personId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";
        				var license_btn = "<button name='license"+ obj.personId +"' id='" + obj.personId +"' class='license ui-button ui-corner-all ui-widget' value='license'>操作證</button>";
        				var training_btn = "<button name='training"+ obj.personId +"' id='" + obj.personId +"' class='training ui-button ui-corner-all ui-widget' value='training'>訓練紀錄</button>";
        				var event_btn = "<button name='event"+ obj.personId +"' id='" + obj.personId +"' class='event ui-button ui-corner-all ui-widget' value='event'>特殊事蹟</button>";
        				var delete_btn = "<button name='delete"+ obj.personId +"' id='" + obj.personId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";		        		
        				var buttons = stateMachineInQuery(obj);   
        				myarray[i][4]=buttons;
        			}
	       			 return myarray;
	      		},
        	 "dataType": 'json'
		}

});
}
function initializeQueryListState(){
	
}
function stateMachineInQuery(obj){
	var buttons='';
	if(obj.state=="APPROVED"){
		var license_btn = "<button name='license"+ obj.personId +"' id='" + obj.personId +"' class='license ui-button ui-corner-all ui-widget' value='license'>操作證</button>";
		var training_btn = "<button name='training"+ obj.personId +"' id='" + obj.personId +"' class='training ui-button ui-corner-all ui-widget' value='training'>訓練紀錄</button>";
		var event_btn = "<button name='event"+ obj.personId +"' id='" + obj.personId +"' class='event ui-button ui-corner-all ui-widget' value='modify'>特殊事蹟</button>";
	}else{
		var license_btn = "<button name='license"+ obj.personId +"' id='" + obj.personId +"' class='license ui-button ui-corner-all ui-widget ui-state-disabled' value='license' disabled>操作證</button>";
		var training_btn = "<button name='training"+ obj.personId +"' id='" + obj.personId +"' class='training ui-button ui-corner-all ui-widget ui-state-disabled' value='training' disabled>訓練紀錄</button>";
		var event_btn = "<button name='event"+ obj.personId +"' id='" + obj.personId +"' class='event ui-button ui-corner-all ui-widget ui-state-disabled' value='event' disabled>特殊事蹟</button>";
	}
	if(obj.state=="DELETED"){
		var view_btn = "<button name='view"+ obj.personId +"' id='" + obj.personId +"' class='view ui-button ui-corner-all ui-widget ui-state-disabled' value='view' disabled>檢視</button>";
		var update_btn = "<button name='update"+ obj.personId +"' id='" + obj.personId +"' class='update ui-button ui-corner-all ui-widget ui-state-disabled' value='update' disabled>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.personId +"' id='" + obj.personId +"' class='delete ui-button ui-corner-all ui-widget ui-state-disabled' value='delete' disabled>刪除</button>";
	}else{
		var view_btn = "<button name='view"+ obj.personId +"' id='" + obj.personId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
		var update_btn = "<button name='update"+ obj.personId +"' id='" + obj.personId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.personId +"' id='" + obj.personId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";
	}
	buttons=view_btn+update_btn+license_btn+training_btn+event_btn+delete_btn;
	return buttons;
}


function reloadQueryPersonAction(){
	var table = $('#single-query-table-1').DataTable();
	table.destroy();
	transferQueryListAction();
}

$(function() {
	prepareQueryDomAction();
	queryDomFinishState();
	transferQueryListAction();
	initializeQueryListState();
})
</script>
<div id="dialog-query-form" title="查詢">
	<table id="single-query-table-1" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>人員內部ID</th>
				<th>姓名</th>
				<th>具備操作證類別</th>
				<th>狀態</th>
				<th>系統功能</th>
			</tr>
		</thead>
	</table>
</div>