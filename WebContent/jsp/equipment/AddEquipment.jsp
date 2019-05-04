<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	var tips = $(".validateTips");
	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}
	function checkLength(o, n, min, max) {
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips("" + n + " 的長度必須在 " + min + " 和 " + max + " 之間。");
			return false;
		} else {
			return true;
		}
	}
	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			updateTips(n);
			return false;
		} else {
			return true;
		}
	}
	function prepareQueryDomAction(){
		$("#dialog-add-form").dialog({
					autoOpen : false,
					height : 500,
					width : 800,
					modal : true,
					buttons : {
						"新增設備" : function() {
							var bValid = true;
							if (bValid) {
								var myJson = JSON.stringify($("#add-form").serializeObject());
								$.ajax({
											url : "/Drone/equipment/AddEquipmentProcess",
											type : "POST",
											data : {
												"data" : myJson
											},
											success : function() {
												alert('新增成功');
												reloadQueryEquipment();
											}
										})

								$(this).dialog("close");
							}
						},
						"取消" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
					}
				});
	}
	function queryDomFinishState(){
		
	}
	function initializeAddFormState(){
		
	}
	$(function() {		
		prepareQueryDomAction();
	});
	function addEquipment(){
		queryDomFinishState();
		initializeAddFormState();
		$("#dialog-add-form").dialog("open");
	}
</script>

<div id="dialog-add-form" title="新增設備">
	<p class="validateTips">*代表必填</p>
	<form id="add-form">
		
		<table id="single-add-table-1">
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
				<td><input type="file" name="equipmentPhoto" id="equipmentPhoto"
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
		</table>
	</form>
</div>