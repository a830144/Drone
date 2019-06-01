<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

			<tr>
				<td>專案ID</td>
				<td><input type="text" name="projectId" id="projectId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>專案名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>用途</td>
				<td><input type="text" name="usage" id="usage"
					class="text ui-widget-content ui-corner-all"></td>
				<td>空域範圍各點連線(WGS-84/可視 需要增加欄位）</td>
				<td></td>
			</tr>
			<tr>
				<td>航拍空域範圍檔案：(KML檔)：(File Upload<10M) </td>
				<td><input type="text" name="productName" id="productName"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>作業日期及時間(最多三個月、政府單位可延長至6個月)</td>
				<td>開始<input type="text" name="startDate" id="startDate"
					class="text ui-widget-content ui-corner-all">
					~<br>結束<input type="text" name="endDate" id="endDate"
					class="text ui-widget-content ui-corner-all">
				</td>
			</tr>
			