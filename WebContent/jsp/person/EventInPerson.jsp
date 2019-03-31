<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	$(function() {

		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		$("#dialog-event-form").dialog({
					autoOpen : false,
					height : 400,
					width : 1050,
					modal : true,
					buttons : {
						"新增特殊事蹟" : function() {
							$("#event-form").find("#personId").prop("disabled", false);
							$("#event-form").find("#name").prop("disabled",false);
							radioValue();
							var myJson = JSON.stringify($("#license-form").serializeObject());
							$.ajax({
								url : "./TrainingInPersonProcess.jsp",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('新增人員特殊事蹟成功');
								}
							})

							$(this).dialog("close");

						},

						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						var table1 = $('#single-event-table-1').DataTable();
						table1.destroy();
					}
				});
	});
	function eventInPerson(id) {
		$("#event-form").find("select").val("");
		$("#event-form").find("input").val("");
		$.post("./ViewPersonProcess.jsp", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = $.parseJSON(data);
				var form = $("#event-form");
				$.each(obj[0], function(key, value) {
					$("#event-form").find("#" + key).val(value);
				});

				$("#event-form").find("#personId").prop("disabled", true);
				$("#event-form").find("#name").prop("disabled", true);
			}

		});
		
		var table = $('#single-event-table-1').DataTable( {
			"scrollY":        "100px",
	        "scrollCollapse": true,		
			"ajax": {  
			    "url": "./ViewPersonProcess-tab4.jsp",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][1]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][2]=obj.hasOwnProperty("startDate")?obj.startDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("description")?obj.description:'';
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});

		$("#dialog-event-form").dialog("open");
	}
</script>
<div id="dialog-event-form" title="新增特殊事蹟" >
	<div id = "dialog-event-form-1">
		<table id="single-event-table-1" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>事蹟名稱</th>
					<th>事蹟證明單位</th>
					<th>發生特殊事蹟日期</th>
					<th>事蹟說明</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id = "dialog-event-form-2">
		<form id="event-form">
			<table id="single-event-table-2">
			<tr>
				<td>ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>姓名</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>事蹟名稱</td>
				<td><input type="text" name="eventName" id="eventName"
					class="text ui-widget-content ui-corner-all"></td>
				<td>事蹟證明單位</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>發生特殊事蹟日期</td>
				<td><input type="text" name="startDate"
					id="startDate" class="text ui-widget-content ui-corner-all">~<input type="text" name="endDate"
					id="endDate" class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>事蹟說明</td>
				<td><input type="text" name="description" id="description"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>上傳特殊事蹟照片</td>
				<td><input type="file" name="photo" id="photo"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>


		</table>
		</form>
	</div>
</div>