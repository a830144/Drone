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
	   		 "url": "/Drone/equipment/QueryEquipmentProcess",  
	   		 "data": {  
	       		 "name": window.queryString  
	   		 }, 
    		 "dataSrc": function ( json ) {
    				var myarray=new Array(json.length);
	    			for (i=0; i <json.length; i++){
	    	   			 myarray[i]=new Array(5);
	    			}
	    			for(i=0;i<json.length;i++){
        				var obj = $.parseJSON(json[i]);
        				myarray[i][0]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';
        				myarray[i][1]=obj.hasOwnProperty("productName")?obj.productName:'';	
        				if(obj.hasOwnProperty("constructionType")){
        					switch(obj.constructionType){
        					case 'A':
        						myarray[i][2] = '無人飛機';
        						break;
        					case 'H':
        						myarray[i][2] = '無人直昇機';
        						break;
        					case 'M':
        						myarray[i][2] = '無人多旋翼機';
        						break;
        					case 'O':
        						myarray[i][2] = '其它';
        						break;
        					default:
        						myarray[i][2] = '';
        					}
        				}

        				myarray[i][3]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
        				myarray[i][4]=obj.hasOwnProperty("state")?obj.state:'';
        				var maintain_btn = "<button name='maintain"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='maintain ui-button ui-corner-all ui-widget' value='maintain'>保養</button>";
        				var modify_btn = "<button name='modify"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='modify ui-button ui-corner-all ui-widget' value='modify'>改裝</button>";
        				var view_btn = "<button name='view"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
        				var update_btn = "<button name='update"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";            				
        				var delete_btn = "<button name='delete"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";
        				var buttons = stateMachineInQuery(obj);      		
        				myarray[i][5]=buttons;
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
		var maintain_btn = "<button name='maintain"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='maintain ui-button ui-corner-all ui-widget' value='maintain'>保養</button>";
		var modify_btn = "<button name='modify"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='modify ui-button ui-corner-all ui-widget' value='modify'>改裝</button>";
	}else{
		var maintain_btn = "<button name='maintain"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='maintain ui-button ui-corner-all ui-widget ui-state-disabled' value='maintain' disabled>保養</button>";
		var modify_btn = "<button name='modify"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='modify ui-button ui-corner-all ui-widget ui-state-disabled' value='modify' disabled>改裝</button>";
	}
	if(obj.state=="DELETED"){
		var view_btn = "<button name='view"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='view ui-button ui-corner-all ui-widget ui-state-disabled' value='view' disabled>檢視</button>";
		var update_btn = "<button name='update"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='update ui-button ui-corner-all ui-widget ui-state-disabled' value='update' disabled>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='delete ui-button ui-corner-all ui-widget ui-state-disabled' value='delete' disabled>刪除</button>";
	}else{
		var view_btn = "<button name='view"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
		var update_btn = "<button name='update"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";
	}
	buttons=view_btn+update_btn+maintain_btn+modify_btn+delete_btn;
	return buttons;
}

function reloadQueryEquipmentAction(){
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
				<th>編號</th>
				<th>設備型號</th>
				<th>設備構型</th>
				<th>設備ID取得日期</th>
				<th>-狀態</th>
				<th>動作</th>
			</tr>
		</thead>
	</table>
</div>