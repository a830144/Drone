<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
		function reloadQueryCode(){
			var table = $('#single-query-code-table-1').DataTable();
			table.destroy();
			queryCode();
		}
		function queryCode() {
				$("#queryEventDiv").hide();
				$("#queryTrainingDiv").hide();
				$("#queryCodeDiv").show();
				$.ajax({  
					 "type": "POST",
			   		 "url": "/Drone/other/QueryCodeProcess",
			   		 "success": function(json) {  
	                    $("#single-query-code-table-1").empty();
	                    $("#single-query-code-table-1").append('<thead><tr><th>CODE ID</th><th>NODE ID</th><th>TYPE</th><th>CODE</th><th>CONTENT</th><th>PARENT NODE ID</th><th>操作</th></tr></thead>');
	                    var content= "<tbody>";
	                    
		    			for(i=0;i<json.length;i++){
	        				var obj = $.parseJSON(json[i]);
	        				content = content + "<tr>";
	        				var col_1 = obj.hasOwnProperty("codeId")?obj.codeId:'';
	        				content = content + "<td>" + col_1 +"</td>";
	        				var col_2= obj.hasOwnProperty("nodeId")?obj.nodeId:'';
	        				content = content + "<td>" + col_2 +"</td>";
	        				var col_3=obj.hasOwnProperty("codeType")?obj.codeType:'';
	        				content = content + "<td>" + col_3 +"</td>";
	        				var col_4=obj.hasOwnProperty("code")?obj.code:'';
	        				content = content + "<td>" + col_4 +"</td>";
	        				var col_5=obj.hasOwnProperty("codeContent")?obj.codeContent:'';
	        				content = content + "<td>" + col_5 +"</td>";
	        				var col_6=obj.hasOwnProperty("parentNodeId")?obj.parentNodeId:'';
	        				content = content + "<td>" + col_6 +"</td>";
	        				var view_btn = "<button name='view"+ obj.codeId +"' id='" + obj.codeId +"' class='view' value='view'>檢視</button>";
	        				var update_btn = "<button name='update"+ obj.codeId +"' id='" + obj.codeId +"' class='update' value='update'>修改</button>";
	        				var delete_btn = "<button name='delete"+ obj.codeId +"' id='" + obj.codeId+"' class='delete' value='delete'>刪除</button>";		        		
	        				var col_7="<td>" + view_btn + update_btn + delete_btn+"</td>";
	        				content = content + col_7 + "</tr>";
	        				
	        			}
		    			content = content + "</tbody>";
		    			$("#single-query-code-table-1").append(content);		    			
		    			$('#single-query-code-table-1').dataTable();
	                },
		        	"dataType": 'json'
				});
		}
		$(document).ready(function() {
			$("#query-code").button().click(function() {
				queryCode();
			});
			$("#query-code").button().click();
		});
</script>
<div id="dialog-query-code-form" title="查詢">
	<table id="single-query-code-table-1" class="display" style="width: 100%">
	</table>
</div>