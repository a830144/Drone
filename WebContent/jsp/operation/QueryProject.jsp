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
			var name = $('#query_name').val();
			var table = $('#single-query-table-1').DataTable( {
				columnDefs: [
					{	
					    className: 'dt-center',
					    targets: '_all'
					}],				
				ajax: {  					
					 type: "POST",
			   		 url: "/" +system_name +"/operation/QueryProjectProcess",  
			   		 data: {  
			       		 name: name  
			   		 }, 
		    		 dataSrc: function ( json ) {
		    				var myarray=new Array(json.length);
			    			for (i=0; i <json.length; i++){
			    	   			 myarray[i]=new Array(7);
			    			}
			    			for(i=0;i<json.length;i++){
		        				var obj = $.parseJSON(json[i]);
		        				myarray[i][0]=obj.hasOwnProperty("projectId")?obj.projectId:'';
		        				myarray[i][1]=obj.hasOwnProperty("name")?obj.name:'';		        			
		        				myarray[i][2]=obj.hasOwnProperty("company")?obj.company:'';
		        				myarray[i][3]=obj.hasOwnProperty("projectEndDate")?obj.projectEndDate:'';
		        				myarray[i][4]=obj.hasOwnProperty("projectManager")?obj.projectManager:'';
		        				myarray[i][5]=obj.hasOwnProperty("state")?obj.state:'';
		        				var view_btn = "<button name='view"+ obj.projectId +"' id='" + obj.projectId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
		        				var update_btn = "<button name='update"+ obj.projectId +"' id='" + obj.projectId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";
		        				var aerial_plan_btn = "<button name='aerialPlan"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialPlan ui-button ui-corner-all ui-widget' value='aerialPlan'>航拍計畫</button>";
		        				var aerial_activity_btn = "<button name='aerialActivity"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialActivity ui-button ui-corner-all ui-widget' value='aerialActivity'>航拍活動</button>";
		        				var delete_btn = "<button name='delete"+ obj.projectId +"' id='" + obj.projectId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";		        		
		        				var buttons = stateMachineInQuery(obj);  
		        				myarray[i][6]=buttons;
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
		var aerialPlan_btn = "<button name='aerialPlan"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialPlan ui-button ui-corner-all ui-widget' value='aerialPlan'>航拍計畫</button>";
		var aerialActivity_btn = "<button name='aerialActivity"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialActivity ui-button ui-corner-all ui-widget' value='aerialActivity'>航拍活動</button>";
	}else{
		var aerialPlan_btn = "<button name='aerialPlan"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialPlan ui-button ui-corner-all ui-widget ui-state-disabled' value='aerialPlan' disabled>航拍計畫</button>";
		var aerialActivity_btn = "<button name='aerialActivity"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialActivity ui-button ui-corner-all ui-widget ui-state-disabled' value='aerialActivity' disabled>航拍活動</button>";
	}
	if(obj.state=="DELETED"){
		var view_btn = "<button name='view"+ obj.projectId +"' id='" + obj.projectId +"' class='view ui-button ui-corner-all ui-widget ui-state-disabled' value='view' disabled>檢視</button>";
		var update_btn = "<button name='update"+ obj.projectId +"' id='" + obj.projectId +"' class='update ui-button ui-corner-all ui-widget ui-state-disabled' value='update' disabled>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.projectId +"' id='" + obj.projectId +"' class='delete ui-button ui-corner-all ui-widget ui-state-disabled' value='delete' disabled>刪除</button>";
	}else{
		var view_btn = "<button name='view"+ obj.projectId +"' id='" + obj.projectId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
		var update_btn = "<button name='update"+ obj.projectId +"' id='" + obj.projectId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";            				
		var delete_btn = "<button name='delete"+ obj.projectId +"' id='" + obj.projectId +"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";
	}
	buttons=view_btn+update_btn+aerialPlan_btn+aerialActivity_btn+delete_btn;
	return buttons;
}

function reloadQueryProjectAction(){
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
				<th>專案編號</th>
				<th>專案名稱</th>
				<th>委託公司</th>
				<th>委託結案日期</th>
				<th>專案經理</th>
				<th>狀態</th>
				<th>系統功能</th>
			</tr>
		</thead>
	</table>
</div>