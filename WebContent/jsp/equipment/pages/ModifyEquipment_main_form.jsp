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
				<td>改裝編號</td>
				<td id="modificationIdTD"></td>
				<td>改裝日期</td>
				<td><input type="text" name="modificationDate"
					id="modificationDate" class="text ui-widget-content ui-corner-all"></td>
				<td>改裝人員</td>
				<td><input type="text" name="modificationPerson"
					id="modificationPerson" class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>資料狀態</td>
				<td id="modifyStateTD"></td>
			</tr>
			<tr>
				<td>結構系統(airframe)</td>
				<td>
				<fieldset>
				<label for="mo-airframe-clean">none</label>
    			<input type="radio" name="airframe_radio" id="mo-airframe-clean" value="C" checked>
    			<label for="mo-airframe-repair">modify</label>
    			<input type="radio" name="airframe_radio" id="mo-airframe-repair" value="R">
    			<input type="text" name="airframe_comment" id="mo-airframe_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="airframe" id="mo-airframe">
				</fieldset>				 					
				</td>
			
				<td>推進系統(engine, motor, propeller)</td>
				<td>
				<fieldset>
				<label for="mo-propulsion-clean">none</label>
    			<input type="radio" name="propulsion_radio" id="mo-propulsion-clean" value="C" checked>
    			<label for="mo-propulsion-repair">modify</label>
    			<input type="radio" name="propulsion_radio" id="mo-propulsion-repair" value="R">
    			<input type="text" name="propulsion_comment" id="mo-propulsion_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="propulsion" id="mo-propulsion">
				</fieldset>
				</td>
					
				<td>動力系統(battery)</td>
				<td>
				<fieldset>
				<label for="mo-battery-clean">none</label>
    			<input type="radio" name="battery_radio" id="mo-battery-clean" value="C" checked>
    			<label for="mo-battery-repair">modify</label>
    			<input type="radio" name="battery_radio" id="mo-battery-repair" value="R">
    			<input type="text" name="battery_comment" id="mo-battery_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="battery" id="mo-battery">
				</fieldset>
				</td>
					
			</tr>
			<tr>
				<td>飛控系統(controller)</td>
				<td>
				<fieldset>
				<label for="mo-controller-clean">none</label>
    			<input type="radio" name="controller_radio" id="mo-controller-clean" value="C" checked>
    			<label for="mo-controller-repair">modify</label>
    			<input type="radio" name="controller_radio" id="mo-controller-repair" value="R">
    			<input type="text" name="controller_comment" id="mo-controller_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="controller" id="mo-controller">
				</fieldset>
				</td>
				
				<td>酬載裝置(payload) </td>
				<td>
				<fieldset>
				<label for="mo-payload-clean">none</label>
    			<input type="radio" name="payload_radio" id="mo-payload-clean" value="C" checked>
    			<label for="mo-payload-repair">modify</label>
    			<input type="radio" name="payload_radio" id="mo-payload-repair" value="R">
    			<input type="text" name="payload_comment" id="mo-payload_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="payload" id="mo-payload">
				</fieldset>
				</td>
		
				<td>其他(others)</td>
				<td><input type="text" name="others_comment" id="mo-others_comment" class="text ui-widget-content ui-corner-all">
				<input type="hidden" name="others" id="mo-others" value="N">
				</td>
				
			</tr>
			