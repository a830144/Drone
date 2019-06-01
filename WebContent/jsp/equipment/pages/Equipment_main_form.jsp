<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

			<tr>
				<td>設備ID</td>
				<td><input type="text" name="equipmentId" id="equipmentId"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>製造廠名稱</td>
				<td><input type="text" name="manufactoryName" id="manufactoryName"
					class="text ui-widget-content ui-corner-all"></td>
				<td>構造分類</td>
				<td><select name="constructionType" id="constructionType"
					class="text ui-widget-content ui-corner-all">
						<option value="A">無人飛機(Aircraft)</option>
						<option value="H">無人直昇機(Helicopter)</option>
						<option value="M">無人多旋翼機(Muti-Rotors)</option>
						<option value="O">其他Other</option>
				</select></td>
			</tr>
			<tr>
				<td>型式名稱</td>
				<td><input type="text" name="productName" id="productName"
					class="text ui-widget-content ui-corner-all"></td>
				<td>最大起飛重量</td>
				<td><input type="number" name="maxWeight" id="maxWeight"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			</tr>
			<tr>
				<td>設備照片</td>
				<td><input type="text" name="equipmentPhoto" id="equipmentPhoto"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>翼展</td>
				<td><input type="number" name="wingWidth" id="wingWidth"
					class="text ui-widget-content ui-corner-all" value="0"></td>
				<td>長度</td>
				<td><input type="number" name="equipmentLength" id="equipmentLength"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			</tr>
			<tr>
				<td>推進系統</td>
				<td><input type="text" name="propulsionSystem" id="propulsionSystem"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>動力系統</td>
				<td><input type="text" name="powerType" id="powerType"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>有效荷載</td>
				<td><input type="number" name="loading" id="loading"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			
				<td>巡航速度</td>
				<td><input type="number" name="speed" id="speed"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			</tr>
			<tr>
				<td>最大航高</td>
				<td><input type="number" name="maxHeight" id="maxHeight"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			
				<td>滯空時間</td>
				<td><input type="number" name="airTime" id="airTime"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			</tr>
			<tr>
				<td>最大航程</td>
				<td><input type="number" name="maxDistance" id="maxDistance"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			
				<td>抗風能力</td>
				<td><input type="number" name="antiWind" id="antiWind"
					class="text ui-widget-content ui-corner-all" value="0"></td>
			</tr>
			
			<tr>
				<td>其他附註</td>
				<td><textarea name="note" id="note" rows="2" cols="40" 
					class="text ui-widget-content ui-corner-all"></textarea></td>
			</tr>
			<tr>
				<td>資料狀態</td>
				<td><input type="text" name="state" id="state"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			