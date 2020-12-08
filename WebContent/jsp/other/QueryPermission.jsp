<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
		function queryPermission() {
				$("#queryEventDiv").hide();
				$("#queryTrainingDiv").hide();
				$("#queryCodeDiv").hide();
				$("#queryPermissionDiv").show();
				$.ajax({  
					 "type": "POST",
			   		 "url": "/" + system_name +"/other/QueryPermissionProcess",
			   		 "success": function(json) {  
	                    $("#single-query-permission-table-1").empty();
	                    $("#single-query-permission-table-1").append('<thead><tr><th>permission ID</th><th>permission名稱</th><th>操作</th></tr></thead>');
	                    var content= "<tbody>"
	                    
		    			for(i=0;i<json.length;i++){
	        				var obj = $.parseJSON(json[i]);
	        				content = content + "<tr>";
	        				var col_1 = obj.hasOwnProperty("permissionId")?obj.permissionId:'';
	        				content = content + "<td>" + col_1 +"</td>";
	        				var col_2= obj.hasOwnProperty("permissionName")?obj.permissionName:'';
	        				content = content + "<td>" + col_2 +"</td>";
	        				
	        				var view_btn = "<button name='view"+ obj.permissionId +"' id='" + obj.permissionId +"' class='view ui-button ui-corner-all ui-widget ui-state-disabled' value='view'>檢視</button>";
	        				var update_btn = "<button name='update"+ obj.permissionId +"' id='" + obj.permissionId +"' class='update ui-button ui-corner-all ui-widget ui-state-disabled' value='update'>修改</button>";
	        				var delete_btn = "<button name='delete"+ obj.permissionId +"' id='" + obj.permissionId+"' class='delete ui-button ui-corner-all ui-widget ui-state-disabled' value='delete'>刪除</button>";		        		
	        				var col_5="<td>" + view_btn + update_btn + delete_btn+"</td>";
	        				content = content + col_5 + "</tr>";
	        				
	        			}
		    			content = content + "</tbody>";
		    			$("#single-query-permission-table-1").append(content);
		    			
		    			$('#single-query-permission-table-1').dataTable();
	                },
		        	"dataType": 'json'
				});
		}
		$(document).ready(function() {
			$("#query-permission").button().click(function() {
				queryPermission();
			});
		});
</script>
<div id="dialog-query-permission-form" title="查詢">
	<table id="single-query-permission-table-1" class="display" style="width: 100%">
	</table>
</div>