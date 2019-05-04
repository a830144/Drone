<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function addTraining() {
		$("#dialog-add-training-form").dialog({
							autoOpen : false,
							height : 400,
							width : 800,
							modal : true,
							buttons : {
								"新增訓練" : function() {									
								var myJson = JSON.stringify($("#add-training-form").serializeObject());
								$.ajax({
									url : "/Drone/other/AddTrainingProcess",
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

		$("#dialog-add-training-form").dialog("open");
	}
	
	$(document).ready(function() {
		$("#add-training").button().click(function() {
			addTraining();
		});
	});
</script>
<div id="dialog-add-training-form" title="新增訓練">
	<p class="validateTips">*代表必填</p>
	<form id="add-training-form">
		<table id="single-add-training-table-1">
			<tr>
				<td>訓練名稱</td>
				<td><input type="text" name="trainingName" id="trainingName"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>訓練單位</td>
				<td><input type="text" name="unit" id="unit"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>訓練類別</td>
				<td><select name="trainingType" id="trainingType"
					class="text ui-widget-content ui-corner-all">
						<option value="1">學科</option>
						<option value="2">術科</option>
				</select></td>
			</tr>

		</table>
	</form>
</div>