<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(function() {
	$("#dialog-update-form").dialog({
		autoOpen : false,
		height : 500,
		width : 550,
		modal : true,
		buttons : {
			"修改專案" : function() {
						$("#update-form").find("#projectId").prop("disabled", false);
						$("#update-form").find("#name").prop("disabled", false);
						var myJson = JSON.stringify($("#update-form").serializeObject());
						$.ajax({
							url : "/Drone/operation/UpdateProjectProcess",
							type : "POST",
							data : {
								"data" : myJson
							},
							success : function() {
								alert('修改成功');
							}
						})

						$(this).dialog("close");

					},

					"關閉" : function() {
						$(this).dialog("close");
					}
				},
				close : function() {

				}
			});
});
	function updateProject(id) {
		$( "#update-form" ).find("select").val("");
		$( "#update-form" ).find("input").val("");
		$.post("/Drone/operation/ViewProjectProcess", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = data;
				var form = $("#update-form");
				$.each(obj, function(key, value){
				    $("#update-form").find("#"+key).val(value);
				});
				
				$( "#update-form" ).find("#projectId").prop( "disabled", true );
				$( "#update-form" ).find("#name").prop( "disabled", true );
			}

		});

		$("#dialog-update-form").dialog("open");
	}
</script>
<div id="dialog-update-form" title="修改專案資料">
	<p class="validateTips">*代表必填</p>
	<form id="update-form">
		<table id="single-table">
			<tr>
				<td>ID</td>
				<td><input type="text" name="projectId" id="projectId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>委託公司</td>
				<td><input type="text" name="company" id="company"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案經理</td>
				<td><input type="text" name="projectManager" id="projectManager"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>連絡電話</td>
				<td><input type="text" name="telephone" id="telephone"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案結案日期</td>
				<td><input type="text" name="endDate" id="endDate"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案結案成果</td>
				<td><textarea name="result" id="result" rows="20" cols="40" 
					class="text ui-widget-content ui-corner-all"></textarea></td>
			</tr>
			<tr>
				<td>專案區域</td>
				<td><input type="file" name="areaData" id="areaData"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>其他相關資料</td>
				<td><input type="file" name="otherData" id="otherData"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
		</table>
	</form>
</div>