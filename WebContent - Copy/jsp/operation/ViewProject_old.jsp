<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	$(function() {
		$("#dialog-view-form").dialog({
			autoOpen : false,
			height : 500,
			width : 550,
			modal : true,
			buttons : {
				"關閉" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {

			}
		});
	});
	function viewProject(id) {
		$( "#view-form" ).find("select").val("");
		$( "#view-form" ).find("input").val("");
		$.post("/Drone/operation/ViewProjectProcess", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = data;
				var form = $("#view-form");
				$.each(obj, function(key, value){
				    $("#view-form").find("#"+key).val(value);
				});
				
				$( "#view-form" ).find("select").prop( "disabled", true );
				$( "#view-form" ).find("input").prop( "disabled", true );
			}

		});

		$("#dialog-view-form").dialog("open");
	}
</script>
<div id="dialog-view-form" title="檢視專案明細">
	<p class="validateTips">*代表必填</p>
	<form id="view-form">
		<table id="single-table">
			<tr>
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
				<td><input type="text" name="projectEndDate" id="projectEndDate"
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