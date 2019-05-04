<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
			<tr>
				<td>設備ID</td>
				<td><input type="text" name="equipmentId" id="equipmentId"
					class="text ui-widget-content ui-corner-all ui-state-disabled"></td>
				<td>製造廠名稱</td>
				<td><input type="text" name="manufactoryName" id="manufactoryName"
					class="text ui-widget-content ui-corner-all ui-state-disabled"></td>
				<td>型式名稱</td>
				<td><input type="text" name="productName" id="productName"
					class="text ui-widget-content ui-corner-all ui-state-disabled"></td>
				
			</tr>

			<tr>
				<td>保養編號</td>
				<td id="maintenanceIdTD"></td>
				<td>保養日期</td>
				<td><input type="text" name="maintenanceDate"
					id="maintenanceDate" class="text ui-widget-content ui-corner-all"></td>
				<td>保養人員</td>
				<td><input type="text" name="maintenancePerson"
					id="maintenancePerson" class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>保養類型</td>
				<td><select name="maintenanceType" id="maintenanceType"
					class="text ui-widget-content ui-corner-all">
						<option value="W">周檢</option>
						<option value="M">月檢</option>
						<option value="Q">季檢</option>
				</select></td>
			</tr>
			<tr>
				<td>結構系統(airframe)</td>
				<td>
				<fieldset>
				<label for="ma-airframe-clean">clean</label>
    			<input type="radio" name="airframe_radio" id="ma-airframe-clean" value="C" checked>
    			<label for="ma-airframe-repair">repair</label>
    			<input type="radio" name="airframe_radio" id="ma-airframe-repair" value="R">
    			<input type="text" name="airframe_comment" id="ma-airframe_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="airframe" id="ma-airframe">
				</fieldset>				 					
				</td>
			
				<td>推進系統(engine, motor, propeller)</td>
				<td>
				<fieldset>
				<label for="ma-propulsion-clean">clean</label>
    			<input type="radio" name="propulsion_radio" id="ma-propulsion-clean" value="C" checked>
    			<label for="ma-propulsion-repair">repair</label>
    			<input type="radio" name="propulsion_radio" id="ma-propulsion-repair" value="R">
    			<input type="text" name="propulsion_comment" id="ma-propulsion_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="propulsion" id="ma-propulsion">
				</fieldset>
				</td>
					
				<td>動力系統(battery)</td>
				<td>
				<fieldset>
				<label for="ma-battery-clean">clean</label>
    			<input type="radio" name="battery_radio" id="ma-battery-clean" value="C" checked>
    			<label for="ma-battery-repair">repair</label>
    			<input type="radio" name="battery_radio" id="ma-battery-repair" value="R">
    			<input type="text" name="battery_comment" id="ma-battery_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="battery" id="ma-battery">
				</fieldset>
				</td>
					
			</tr>
			<tr>
				<td>飛控系統(controller)</td>
				<td>
				<fieldset>
				<label for="ma-controller-clean">clean</label>
    			<input type="radio" name="controller_radio" id="ma-controller-clean" value="C" checked>
    			<label for="ma-controller-repair">repair</label>
    			<input type="radio" name="controller_radio" id="ma-controller-repair" value="R">
    			<input type="text" name="controller_comment" id="ma-controller_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="controller" id="ma-controller">
				</fieldset>
				</td>
				
				<td>酬載裝置(payload) </td>
				<td>
				<fieldset>
				<label for="ma-payload-clean">clean</label>
    			<input type="radio" name="payload_radio" id="ma-payload-clean" value="C" checked>
    			<label for="ma-payload-repair">repair</label>
    			<input type="radio" name="payload_radio" id="ma-payload-repair" value="R">
    			<input type="text" name="payload_comment" id="ma-payload_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="payload" id="ma-payload">
				</fieldset>
				</td>
		
				<td>其他(others)</td>
				<td><input type="text" name="others_comment" id="ma-others_comment" class="text ui-widget-content ui-corner-all">
				<input type="hidden" name="others" id="ma-others" value="N">
				</td>
				
			</tr>

