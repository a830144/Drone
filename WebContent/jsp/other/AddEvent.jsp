<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function addEvent() {
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		$("#dialog-add-event-form").dialog({
							autoOpen : false,
							height : 400,
							width : 800,
							modal : true,
							buttons : {
								"新增事件" : function() {
									
								var myJson = JSON.stringify($("#add-event-form").serializeObject());
								$.ajax({
									url : "/" + system_name +"/other/AddEventProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
									}
								})

								$(this).dialog("close");

								},
								"取消" : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								
							}
						});
		$("#dialog-add-event-form").dialog("open");
	}
	
	$(document).ready(function() {
		$("#add-event").button().click(function() {
			addEvent();
		});
	});
</script>
<div id="dialog-add-event-form" title="新增訓練">
	<p class="validateTips">*代表必填</p>
	<form id="add-event-form">
		<table id="single-add-event-table-1">
			<tr>
				<td>事蹟名稱</td>
				<td><input type="text" name="eventName" id="eventName"
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
				<td>事蹟證明單位</td>
				<td><input type="text" name="" id=""
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

		</table>
	</form>
</div>