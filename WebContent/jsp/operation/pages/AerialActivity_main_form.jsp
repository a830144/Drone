<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

			<tr>
				<td>專案ID</td>
				<td><input type="text" name="projectId" id="projectId"
					class="text ui-widget-content ui-corner-all ui-state-disabled"></td>
				<td>專案名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all ui-state-disabled"></td>
			</tr>
			<tr>
				<td>計畫ID</td>
				<td><select name="aerialPlanId" id="aerialPlanId"
					class="text ui-widget-content ui-corner-all">
					</select></td>
				<td>計畫用途</td>
				<td><input type="text" name="usage" id="usage"
					class="text ui-widget-content ui-corner-all"></td>
				
			</tr>
			<tr>
				<td>計畫航拍空域範圍檔案：(KML檔)：(File Upload&lt;10M) </td>
				<td><input type="text" name="productName" id="productName"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>計畫核准作業日期及時間(最多三個月、政府單位可延長至6個月)</td>
				<td>開始<input type="text" name="aerialPlanStartDate" id="aerialPlanStartDate"
					class="text ui-widget-content ui-corner-all">
					~<br>結束<input type="text" name="aerialPlanEndDate" id="aerialPlanEndDate"
					class="text ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr>
				<td>計畫核准資料</td>
				<td>
				</td>
				
			</tr>
			<tr>
				<td>航拍活動編號</td>
				<td id="aerialActivityIdTD"></td>
				<td>航拍活動資料狀態</td>
				<td id="aerialActivityStateTD"></td>
			</tr>
			<tr>
				<td>活動作業日期及時間(須於核准計畫最業日期內)</td>
				<td>開始<input type="text" name="aerialActivityStartDate" id="aerialActivityStartDate"
					class="text ui-widget-content ui-corner-all">
					~<br>結束<input type="text" name="aerialActivityEndDate" id="aerialActivityEndDate"
					class="text ui-widget-content ui-corner-all">
				</td>
			</tr>
			