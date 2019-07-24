<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	$(function() {
		$("#dialog-update-form").dialog({
			autoOpen : false,
			height : 500,
			width : 800,
			modal : true,
			buttons : {
				"修改人員" : function() {
							$("#update-form").find("#personId").prop("disabled", false);
							$("#update-form").find("#name").prop("disabled", false);
							var myJson = JSON.stringify($("#update-form").serializeObject());
							$.ajax({
								url : "/Drone/person/UpdatePersonProcess",
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
		$("#update-form").find("#dateOfBirth").datepicker();
	});
	function updatePerson(id) {
		$("#update-form").find("select").val("");
		$("#update-form").find("input").val("");
		$.post("/Drone/person/ViewPersonProcess", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = data;
				var form = $("#update-form");
				$.each(obj, function(key, value) {
					$("#update-form").find("#" + key).val(value);
				});

				$("#update-form").find("#personId").prop("disabled", true);
				$("#update-form").find("#name").prop("disabled", true);
			}

		});

		$("#dialog-update-form").dialog("open");
	}
</script>
<div id="dialog-update-form" title="修改人員資料">
	<form id="update-form">
		<table id="single-update-table-1">
			<tr>
				<td>人員內部ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>姓名</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>性別</td>
				<td><select name="sex" id="sex"
					class="text ui-widget-content ui-corner-all">
						<option value="1">男</option>
						<option value="2">女</option>
						<option value="3">其他</option>
				</select></td>
			</tr>

			<tr>
				<td>國籍</td>
				<td><input type="text" name="nationality" id="nationality"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>身分證號碼</td>
				<td><input type="text" name="idNumber" id="idNumber"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>出身日期</td>
				<td><input type="text" name="dateOfBirth" id="dateOfBirth"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>辦公電話</td>
				<td><input type="text" name="telephone" id="telephone"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr></tr>
			<tr>
				<td>傳真</td>
				<td><input type="text" name="fax" id="fax"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>通訊處</td>
				<td><input type="text" name="address" id="address"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr></tr>
			<tr>
				<td>電子郵件</td>
				<td><input type="text" name="email" id="email"
					class="text ui-widget-content ui-corner-all"></td>
			
			</tr>
			<tr>
				<td>半身 最近照片</td>
				<td><input type="file" name="recentPhoto" id="recentPhoto"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>最近體檢資料</td>
				<td><input type="file" name="investigation" id="investigation"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>資料狀態</td>
				<td><input type="text" name="state" id="state"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
		</table>
	</form>
</div>