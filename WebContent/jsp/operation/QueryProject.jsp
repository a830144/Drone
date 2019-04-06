<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(document).ready(
		$(function() {
			var name = $('#query_name').val();
			var table = $('#single-query-table-1').DataTable( {		
				"ajax": {  					
					 "type": "POST",
			   		 "url": "/Drone/operation/QueryProjectProcess",  
			   		 "data": {  
			       		 "name": name  
			   		 }, 
		    		 "dataSrc": function ( json ) {
		    				var myarray=new Array(json.length);
			    			for (i=0; i <json.length; i++){
			    	   			 myarray[i]=new Array(7);
			    			}
			    			for(i=0;i<json.length;i++){
		        				var obj = json[i];
		        				myarray[i][0]=obj.hasOwnProperty("projectId")?obj.projectId:'';
		        				myarray[i][1]=obj.hasOwnProperty("name")?obj.name:'';		        			
		        				myarray[i][2]=obj.hasOwnProperty("company")?obj.company:'';
		        				myarray[i][3]=obj.hasOwnProperty("endDate")?obj.endDate:'';
		        				myarray[i][4]=obj.hasOwnProperty("projectManager")?obj.projectManager:'';
		        				myarray[i][5]=obj.hasOwnProperty("status")?obj.status:'';
		        				var view_btn = "<button name='view"+ obj.projectId +"' id='" + obj.projectId +"' class='view' value='view'>檢視</button>";
		        				var update_btn = "<button name='update"+ obj.projectId +"' id='" + obj.projectId +"' class='update' value='update'>修改</button>";
		        				var aerial_plan_btn = "<button name='aerialPlan"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialPlan' value='aerialPlan'>航拍計畫</button>";
		        				var aerial_activity_btn = "<button name='aerialActivity"+ obj.projectId +"' id='" + obj.projectId +"' class='aerialActivity' value='aerialActivity'>航拍活動</button>";
		        				var delete_btn = "<button name='delete"+ obj.projectId +"' id='" + obj.projectId +"' class='delete' value='delete'>刪除</button>";		        		
		        				myarray[i][6]=view_btn+update_btn+aerial_plan_btn+aerial_activity_btn+delete_btn;
		        			}
			       			 return myarray;
			      		},
		        	 "dataType": 'json'
				}

		});
		})
)
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