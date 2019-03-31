<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	$(function() {

		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		$("#dialog-training-form").dialog({
					autoOpen : false,
					height : 400,
					width : 1050,
					modal : true,
					buttons : {
						"新增訓練時數" : function() {
							$("#training-form").find("#personId").prop("disabled", false);
							$("#training-form").find("#name").prop("disabled",false);
							radioValue();
							var myJson = JSON.stringify($("#training-form").serializeObject());
							$.ajax({
								url : "./TrainingInPersonProcess.jsp",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('新增人員訓練時數成功');
								}
							})

							$(this).dialog("close");

						},

						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						var table1 = $('#single-training-table-1').DataTable();
						table1.destroy();
					}
				});
	});
	function trainingInPerson(id) {
		$("#training-form").find("select").val("");
		$("#training-form").find("input").val("");
		$.post("./ViewPersonProcess.jsp", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = $.parseJSON(data);
				var form = $("#training-form");
				$.each(obj[0], function(key, value) {
					$("#training-form").find("#" + key).val(value);
				});

				$("#training-form").find("#personId").prop("disabled", true);
				$("#training-form").find("#name").prop("disabled", true);
			}

		});
		
		var table = $('#single-training-table-1').DataTable( {
			"scrollY":        "100px",
	        "scrollCollapse": true,		
			"ajax": {  
			    "url": "./ViewPersonProcess-tab3.jsp",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(5);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][1]=obj.hasOwnProperty("unit")?obj.unit:'';
		        		myarray[i][2]=obj.hasOwnProperty("startDate")?obj.startDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("hours")?obj.hours:'';
		        		myarray[i][4]=obj.hasOwnProperty("trainingType")?obj.trainingType:'';		   
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});

		$("#dialog-training-form").dialog("open");
	}
</script>
<div id="dialog-training-form" title="新增訓練" >
	<div id = "dialog-training-form-1">
		<table id="single-training-table-1" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>訓練名稱</th>
					<th>訓練單位</th>
					<th>訓練日期</th>
					<th>訓練時數</th>
					<th>訓練類型</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id = "dialog-training-form-2">
		<form id="training-form">
			<table id="single-training-table-2">
			<tr>
				<td>ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>姓名</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>

				<td>訓練單位</td>
				<td><input type="text" name="unit" id="unit"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>訓練日期</td>
				<td><input type="text" name="startDate"
					id="startDate" class="text ui-widget-content ui-corner-all">~<input type="text" name="endDate"
					id="endDate" class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>時數</td>
				<td><input type="text" name="hours" id="hours"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>訓練類型</td>
				<td><select name="trainingType" id="trainingType"
					class="text ui-widget-content ui-corner-all">
						<option value="1">學科</option>
						<option value="2">術科</option>

				</select></td>

			</tr>
			<tr>
				<td>上傳結訓證明照片</td>
				<td><input type="file" name="photo" id="photo"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
		</table>
		</form>
	</div>
</div>