<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	function addCode() {
		
		$("#dialog-add-code-form").dialog({
							autoOpen : false,
							height : 400,
							width : 800,
							modal : true,
							buttons : {
								"新增代碼" : function() {
									
								var myJson = JSON.stringify($("#add-code-form").serializeObject());
								$.ajax({
									url : "/Drone/other/AddCodeProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
										reloadQueryCode();
										$(this).dialog("close");
									}
								})
								

								},
								"取消" : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								
							}
						});
		$("#dialog-add-code-form").dialog("open");
	}
	
	$(document).ready(function() {
		$("#add-code").button().click(function() {
			addCode();
		});
	});
</script>
<div id="dialog-add-code-form" title="新增代碼">
	<p class="validateTips">*代表必填</p>
	<form id="add-code-form">
		<table id="single-add-code-table-1">
			<tr>
				<td>Node id</td>
				<td><input type="text" name="nodeId" id="nodeId"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>Code Type</td>
				<td><input type="text" name="codeType" id="codeType"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>Code</td>
				<td><input type="text" name="code" id="code"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>Code content</td>
				<td><input type="text" name="codeContent" id="codeContent"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>parentNodeId</td>
				<td><input type="text" name="parentNodeId" id="parentNodeId"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

		</table>
	</form>
</div>