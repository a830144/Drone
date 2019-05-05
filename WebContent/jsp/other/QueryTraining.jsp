<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
		function queryTraining() {
				$("#queryEventDiv").hide();
				$("#queryTrainingDiv").show();
				$("#queryCodeDiv").hide();
				$.ajax({  
					 "type": "POST",
			   		 "url": "/Drone/other/QueryTrainingProcess",
			   		 "success": function(json) {  
	                    $("#single-query-training-table-1").empty();
	                    $("#single-query-training-table-1").append('<thead><tr><th>訓練ID</th><th>訓練名稱</th><th>訓練單位</th><th>訓練類別</th><th>系統功能</th></tr></thead>');
	                    var content= "<tbody>"
	                    
		    			for(i=0;i<json.length;i++){
	        				var obj = $.parseJSON(json[i]);
	        				content = content + "<tr>";
	        				var col_1 = obj.hasOwnProperty("trainingId")?obj.trainingId:'';
	        				content = content + "<td>" + col_1 +"</td>";
	        				var col_2= obj.hasOwnProperty("trainingName")?obj.trainingName:'';
	        				content = content + "<td>" + col_2 +"</td>";
	        				var col_3=obj.hasOwnProperty("unit")?obj.unit:'';
	        				content = content + "<td>" + col_3 +"</td>";
	        				var col_4=obj.hasOwnProperty("trainingType")?obj.trainingType:'';
	        				content = content + "<td>" + col_4 +"</td>";
	        				var view_btn = "<button name='view"+ obj.trainingId +"' id='" + obj.trainingId +"' class='view ui-button ui-corner-all ui-widget' value='view'>檢視</button>";
	        				var update_btn = "<button name='update"+ obj.trainingId +"' id='" + obj.trainingId +"' class='update ui-button ui-corner-all ui-widget' value='update'>修改</button>";
	        				var delete_btn = "<button name='delete"+ obj.trainingId +"' id='" + obj.trainingId+"' class='delete ui-button ui-corner-all ui-widget' value='delete'>刪除</button>";		        		
	        				var col_5="<td>" + view_btn + update_btn + delete_btn+"</td>";
	        				content = content + col_5 + "</tr>";
	        				
	        			}
		    			content = content + "</tbody>";
		    			$("#single-query-training-table-1").append(content);
		    			$("#single-query-training-table-1").append('<tfoot><tr><th>訓練ID</th><th>訓練名稱</th><th>訓練單位</th><th>訓練類別</th><th>系統功能</th></tr></tfoot>');
		    			$('#single-query-training-table-1').dataTable();
	                },
		        	"dataType": 'json'
				});
		}
		$(document).ready(function() {
			$("#query-training").button().click(function() {
				queryTraining();
			});
		});
</script>
<div id="dialog-query-training-form" title="查詢">
	<table id="single-query-training-table-1" class="display" style="width: 100%">
	</table>
</div>