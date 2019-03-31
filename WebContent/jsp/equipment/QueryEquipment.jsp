<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(document).ready(
		$(function() {
			var name = $('#query_name').val();
			var table = $('#single-query-table-1').DataTable( {		
				"ajax": {  
			   		 "url": "./QueryEquipmentProcess.jsp",  
			   		 "data": {  
			       		 "name": name  
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
		        				myarray[i][2]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
		        				myarray[i][3]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
		        				myarray[i][4]=obj.hasOwnProperty("status")?obj.status:'';
		        				var view_btn = "<button name='view"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='view' value='view'>檢視</button>";
		        				var update_btn = "<button name='update"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='update' value='update'>修改</button>";
		        				var maintain_btn = "<button name='maintain"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='maintain' value='maintain'>保養</button>";
		        				var modify_btn = "<button name='modify"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='modify' value='modify'>改裝</button>";
		        				var delete_btn = "<button name='delete"+ obj.equipmentId +"' id='" + obj.equipmentId +"' class='delete' value='delete'>刪除</button>";		        		
		        				myarray[i][5]=view_btn+update_btn+maintain_btn+modify_btn+delete_btn;
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
				<th>編號</th>
				<th>設備型號</th>
				<th>設備構型</th>
				<th>設備ID取得日期</th>
				<th>狀態</th>
				<th>動作</th>
			</tr>
		</thead>
	</table>
</div>