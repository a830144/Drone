<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<tr>
				<td>人員內部ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>姓名</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>訓練編號</td>
				<td><select name="trainingId" id="trainingId"
					class="text ui-widget-content ui-corner-all">
				</select></td>

			</tr>

			<tr>

				<td>訓練單位</td>
				<td><input type="text" name="unit" id="unit"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>訓練名稱</td>
				<td><input type="text" name="trainingName" id="trainingName"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>訓練類型</td>
				<td><select name="trainingType" id="trainingType"
					class="text ui-widget-content ui-corner-all">
						<option value="1">學科</option>
						<option value="2">術科</option>

				</select></td>

			</tr>
			<tr>
				<td>訓練日期</td>
				<td><input type="text" name="startDate"
					id="startDate" class="text ui-widget-content ui-corner-all">~<input type="text" name="endDate"
					id="endDate" class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>時數</td>
				<td><input type="text" name="hours" id="hours"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			
			<tr>
				<td>上傳結訓證明照片</td>
				<td><input type="file" name="photo" id="photo"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>資料狀態</td>
				<td id="trainingStateTD"></td>
			</tr>