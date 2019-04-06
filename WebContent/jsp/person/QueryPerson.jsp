<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(document).ready(
		$(function() {
			var name = $('#query_name').val();
			var table = $('#single-query-table-1').DataTable( {		
				"ajax": {  
					 "type": "POST",
			   		 "url": "/Drone/person/QueryPersonProcess",  
			   		 "data": {  
			       		 "name": name  
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
		        				myarray[i][3]=obj.hasOwnProperty("status")?obj.status:'';
		        				var view_btn = "<button name='view"+ obj.personId +"' id='" + obj.personId +"' class='view' value='view'>檢視</button>";
		        				var update_btn = "<button name='update"+ obj.personId +"' id='" + obj.personId +"' class='update' value='update'>修改</button>";
		        				var license_btn = "<button name='maintain"+ obj.personId +"' id='" + obj.personId +"' class='license' value='license'>新增操作證</button>";
		        				var training_btn = "<button name='modify"+ obj.personId +"' id='" + obj.personId +"' class='training' value='training'>新增訓練紀錄</button>";
		        				var event_btn = "<button name='modify"+ obj.personId +"' id='" + obj.personId +"' class='event' value='event'>新增特殊事蹟</button>";
		        				var delete_btn = "<button name='delete"+ obj.personId +"' id='" + obj.personId +"' class='delete' value='delete'>刪除</button>";		        		
		        				myarray[i][4]=view_btn + update_btn + license_btn + training_btn + event_btn + delete_btn;
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
				<th>人員內部ID</th>
				<th>姓名</th>
				<th>具備操作證類別</th>
				<th>狀態</th>
				<th>系統功能</th>
			</tr>
		</thead>
	</table>
</div>