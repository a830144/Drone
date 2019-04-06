<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	$(function() {
		$("#dialog-view-form").tabs();
	});
	$(function() {
		$("#dialog-view-form").dialog({
			autoOpen : false,
			height : 400,
			width : 1050,
			modal : true,
			buttons : {
				"關閉" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				var table2 = $('#single-view-table-2').DataTable();
				table2.destroy();
				var table3 = $('#single-view-table-3').DataTable();
				table3.destroy();
			}
		});
	});

	function viewEquipment(id) {
		$("#view-form").find("select").val("");
		$("#view-form").find("input").val("");
		
		$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
			  type:"POST",
			  data:{"id" : id},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#view-form");
				$.each(obj, function(key, value) {
					$("#view-form").find("#" + key).val(value);
				});
				if ($("#view-form").find("#constructionType").val() == 'A') {
					$("#view-form").find('#div-aircraft').show();
				} else {
					$("#view-form").find('#div-aircraft').hide();
				}
				$("#view-form").find("select").prop("disabled", true);
				$("#view-form").find("input").prop("disabled", true);
			}
		})

		var table = $('#single-view-table-2')
				.DataTable(
						{
							"ajax" : {
								"type" : "POST",
								"url" : "/Drone/equipment/ViewMaintenance",
								"data" : {
									"id" : id
								},
								"dataSrc" : function(json) {
									var myarray = new Array(json.length);
									for (i = 0; i < json.length; i++) {
										myarray[i] = new Array(8);
									}
									for (i = 0; i < json.length; i++) {
										var obj = $.parseJSON(json[i]);
										myarray[i][0] = obj
												.hasOwnProperty("maintenanceType") ? obj.maintenanceType
												: '';
										myarray[i][1] = obj
												.hasOwnProperty("maintenanceDate") ? obj.maintenanceDate
												: '';
										myarray[i][2] = obj
												.hasOwnProperty("airframe") ? obj.airframe
												: '';
										myarray[i][3] = obj
												.hasOwnProperty("propulsion") ? obj.propulsion
												: '';
										myarray[i][4] = obj
												.hasOwnProperty("battery") ? obj.battery
												: '';
										myarray[i][5] = obj
												.hasOwnProperty("controller") ? obj.controller
												: '';
										myarray[i][6] = obj
												.hasOwnProperty("payload") ? obj.payload
												: '';
										myarray[i][7] = obj
												.hasOwnProperty("others") ? obj.others
												: '';
									}
									return myarray;
								},
								dataType : 'json'
							}

						});

		var table = $('#single-view-table-3')
				.DataTable(
						{
							"ajax" : {
								"type" : "POST",
								"url" : "/Drone/equipment/ViewModification",
								"data" : {
									"id" : id
								},
								"dataSrc" : function(json) {
									var myarray = new Array(json.length);
									for (i = 0; i < json.length; i++) {
										myarray[i] = new Array(8);
									}
									for (i = 0; i < json.length; i++) {
										var obj = $.parseJSON(json[i]);
										myarray[i][0] = obj
												.hasOwnProperty("modificationDate") ? obj.modificationDate
												: '';
										myarray[i][1] = obj
												.hasOwnProperty("modificationPerson") ? obj.modificationPerson
												: '';
										myarray[i][2] = obj
												.hasOwnProperty("airframe") ? obj.airframe
												: '';
										myarray[i][3] = obj
												.hasOwnProperty("propulsion") ? obj.propulsion
												: '';
										myarray[i][4] = obj
												.hasOwnProperty("battery") ? obj.battery
												: '';
										myarray[i][5] = obj
												.hasOwnProperty("controller") ? obj.controller
												: '';
										myarray[i][6] = obj
												.hasOwnProperty("payload") ? obj.payload
												: '';
										myarray[i][7] = obj
												.hasOwnProperty("others") ? obj.others
												: '';
									}
									return myarray;
								},
								dataType : 'json'
							}

						});

		$("#dialog-view-form").dialog("open");
	}
</script>

<div id="dialog-view-form" title="檢視設備資料">
	<ul>
		<li><a href="#dialog-view-form-1">主要資訊</a></li>
		<li><a href="#dialog-view-form-2">保養資訊</a></li>
		<li><a href="#dialog-view-form-3">改裝資訊</a></li>
	</ul>
	<div id="dialog-view-form-1">
		<form id="view-form">
			<table id="single-view-table-1">
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
	<div id="dialog-view-form-2">
		<table id="single-view-table-2" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>維護種類</th>
					<th>日期</th>
					<th>結構系統</th>
					<th>推進系統</th>
					<th>動力系統</th>
					<th>飛控系統</th>
					<th>酬載裝置</th>
					<th>其他</th>
				</tr>
			</thead>

		</table>
	</div>
	<div id="dialog-view-form-3">
		<table id="single-view-table-3" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>改裝日期</th>
					<th>改裝人員</th>
					<th>結構系統</th>
					<th>推進系統</th>
					<th>動力系統</th>
					<th>飛控系統</th>
					<th>酬載裝置</th>
					<th>其他</th>
				</tr>
			</thead>

		</table>
	</div>
</div>