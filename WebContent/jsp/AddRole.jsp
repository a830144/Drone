<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="dialog-form" title="new role">
	<p class="validateTips">*代表必填</p>
	<form action="/action_page.php">
		角色名稱<input type="text" name="name" id="name"
			class="text ui-widget-content ui-corner-all"> <input
			type="checkbox" id="person" name="person">人員管理<br>
		<div id="div-person">

			<table>
				<tr>
					<td><input type="checkbox" name="p-approve"></td>
					<td>核准</td>
					<td><input type="checkbox" name="p-check"></td>
					<td>檢核</td>
					<td><input type="checkbox" name="p-maker"></td>
					<td>資料管理</td>
				<tr>
			</table>
		</div>
		<input type="checkbox" id="equipment" name="equipment">設備管理<br>
		<div id="div-equipment">
			<table>
				<tr>
					<td><input type="checkbox" name="e-approve"></td>
					<td>核准</td>
					<td><input type="checkbox" name="e-check"></td>
					<td>檢核</td>
					<td><input type="checkbox" name="e-maker"></td>
					<td>資料管理</td>
				<tr>
			</table>
		</div>
		<br> <input type="submit" value="Submit">
	</form>
</div>