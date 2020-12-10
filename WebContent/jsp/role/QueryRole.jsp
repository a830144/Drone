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
		ajax: {  
			 type: "POST",
	   		 url: "/" +system_name +"/role/QueryRoleProcess",  
	   		 data: {   
	   		 }, 
    		 dataSrc: function ( json ) {
    				var myarray=new Array(json.length);
	    			for (i=0; i <json.length; i++){
	    	   			 myarray[i]=new Array(2);
	    			}
	    			for(i=0;i<json.length;i++){
        				var obj = $.parseJSON(json[i]);
        				myarray[i][0]=obj.hasOwnProperty("roleId")?obj.roleId:'';
        				myarray[i][1]=obj.hasOwnProperty("roleName")?obj.roleName:'';		        			
        				
        				var view_btn = "<button name='view"+ obj.roleId +"' id='" + obj.roleId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
        				var update_btn = "<button name='update"+ obj.roleId +"' id='" + obj.roleId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";        				
        				var delete_btn = "<button name='delete"+ obj.roleId +"' id='" + obj.roleId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";		        		 
        				buttons=view_btn+update_btn+delete_btn;
        				myarray[i][2]=buttons;
        			}
	       			 return myarray;
	      		},
        	 dataType: 'json'
		}

});
}
function initializeQueryListState(){
	
}
function stateMachineInQuery(obj){
	
}


function reloadQueryRoleAction(){
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
				<th>角色內部ID</th>
				<th>角色</th>
				<th>系統功能</th>
			</tr>
		</thead>
	</table>
</div>