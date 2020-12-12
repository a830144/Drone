<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function queryEvent() {
		$("#queryEventDiv").show();
		$("#queryTrainingDiv").hide();
		$("#queryCodeDiv").hide();
		$("#queryPermissionDiv").hide();
				$.ajax({  
					 "type": "POST",
			   		 "url": "/" + system_name +"/other/QueryEventProcess",  
			   		 "success": function(json) {            
	                    $("#single-query-event-table-1").empty();
	                    $("#single-query-event-table-1").append('<thead><tr><th>事件ID</th><th>事件名稱</th><th>開始時間</th><th>結束時間</th><th>事件描述</th><th>系統功能</th></tr></thead>');
	                    var content= "<tbody>"
	                    
		    			for(i=0;i<json.length;i++){
	        				var obj = $.parseJSON(json[i]);
	        				content = content + "<tr>";
	        				var col_1 = obj.hasOwnProperty("eventId")?obj.eventId:'';
	        				content = content + "<td>" + col_1 +"</td>";
	        				var col_2= obj.hasOwnProperty("eventName")?obj.eventName:'';
	        				content = content + "<td>" + col_2 +"</td>";
	        				var col_3=obj.hasOwnProperty("startDate")?obj.startDate:'';
	        				content = content + "<td>" + col_3 +"</td>";
	        				var col_4=obj.hasOwnProperty("endDate")?obj.endDate:'';
	        				content = content + "<td>" + col_4 +"</td>";
	        				var col_5=obj.hasOwnProperty("description")?obj.description:'';
	        				content = content + "<td>" + col_5 +"</td>";
	        				var view_btn = "<button name='view"+ obj.eventId +"' id='" + obj.eventId +"' class='view ui-button ui-corner-all ui-widget ui-state-disabled' value='view' >檢視</button>";
	        				var update_btn = "<button name='update"+ obj.eventId +"' id='" + obj.eventId +"' class='update ui-button ui-corner-all ui-widget ui-state-disabled' value='update' >修改</button>";
	        				var delete_btn = "<button name='delete"+ obj.eventId +"' id='" + obj.eventId+"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";		        		
	        				var col_6="<td>" + view_btn + update_btn + delete_btn+"</td>";
	        				content = content + col_6 + "</tr>";
	        				
	        			}
		    			content = content + "</tbody>";
		    			$("#single-query-event-table-1").append(content);
		    			$("#single-query-event-table-1").append('<tfoot><tr><th>事件ID</th><th>事件名稱</th><th>開始時間</th><th>結束時間</th><th>事件描述</th><th>系統功能</th></tr></tfoot>');
		    			$('#single-query-event-table-1').dataTable();
	                },
		        	"dataType": 'json'
				});
		}

	$(document).ready(function() {
		$("#query-event").button().click(function() {
			queryEvent();
		});
	});
</script>
<div id="dialog-query-event-form" title="查詢">
	<table id="single-query-event-table-1" class="display" style="width: 100%">
	</table>
</div>