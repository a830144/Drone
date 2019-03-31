<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	function radioValue() {
		if (document.getElementsByName('airframe_radio')[0].checked) {
			$("#airframe").val('N');
		}
		if (document.getElementsByName('airframe_radio')[1].checked) {
			$("#airframe").val('M');
		}
		if (document.getElementsByName('propulsion_radio')[0].checked) {
			$("#propulsion").val('N');
		}
		if (document.getElementsByName('propulsion_radio')[1].checked) {
			$("#propulsion").val('M');
		}
		if (document.getElementsByName('battery_radio')[0].checked) {
			$("#battery").val('N');
		}
		if (document.getElementsByName('battery_radio')[1].checked) {
			$("#battery").val('M');
		}
		if (document.getElementsByName('controller_radio')[0].checked) {
			$("#controller").val('N');
		}
		if (document.getElementsByName('controller_radio')[1].checked) {
			$("#controller").val('M');
		}
		if (document.getElementsByName('payload_radio')[0].checked) {
			$("#payload").val('N');
		}
		if (document.getElementsByName('payload_radio')[1].checked) {
			$("#payload").val('M');
		}
	}
	$(function() {
		$("#modificationDate").datepicker();
		$("[name='airframe_radio']").checkboxradio({
			icon : false
		});
		$("[name='propulsion_radio']").checkboxradio({
			icon : false
		});
		$("[name='battery_radio']").checkboxradio({
			icon : false
		});
		$("[name='controller_radio']").checkboxradio({
			icon : false
		});
		$("[name='payload_radio']").checkboxradio({
			icon : false
		});
	});
	$(function() {
		$("#dialog-modify-form").dialog(
				{
					autoOpen : false,
					height : 400,
					width : 1050,
					modal : true,
					buttons : {
						"改裝設備" : function() {
							$("#modify-form").find("#equipmentId").prop("disabled", false);
							$("#modify-form").find("#manufactoryName").prop("disabled",false);
							$("#modify-form").find("#constructionType").prop("disabled", false);
							radioValue();
							var myJson = JSON.stringify($("#modify-form").serializeObject());
							$.ajax({
								url : "./ModifyEquipmentProcess.jsp",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('新增改裝資料成功');
								}
							})

							$(this).dialog("close");

						},

						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						var table1 = $('#single-modify-table-1').DataTable();
						table1.destroy();
					}
				});
	});
	function modifyEquipment(id) {
		$("#modify-form").find("select").val("");
		$("#modify-form").find("input").val("");
		$.post("./ViewEquipmentProcess.jsp", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = $.parseJSON(data);
				var form = $("#modify-form");
				$.each(obj, function(key, value) {
					$("#modify-form").find("#" + key).val(value);
				});

				$("#modify-form").find("#equipmentId").prop("disabled", true);
				$("#modify-form").find("#name").prop("disabled", true);
				$("#modify-form").find("#constructionType").prop("disabled",
						true);
			}

		});

		var table = $('#single-modify-table-1').DataTable(
						{
							"scrollY":        "100px",
					        "scrollCollapse": true,
							"ajax" : {
								"url" : "./ViewEquipmentProcess-tab3.jsp",
								"data" : {
									"id" : id
								},
								"dataSrc" : function(json) {
									var myarray=new Array(json.length);
							    	for (i=0; i <json.length; i++){
							    	    myarray[i]=new Array(8);
							    	}
							    	for(i=0;i<json.length;i++){
						        		var obj = $.parseJSON(json[i]);
						        		var obj = $.parseJSON(json[i]);
						        		myarray[i][0]=obj.hasOwnProperty("modificationDate")?obj.modificationDate:'';
						        		myarray[i][1]=obj.hasOwnProperty("modificationPerson")?obj.modificationPerson:'';
						        		myarray[i][2]=obj.hasOwnProperty("airframe")?obj.airframe:'';
						        		myarray[i][3]=obj.hasOwnProperty("propulsion")?obj.propulsion:'';
						        		myarray[i][4]=obj.hasOwnProperty("battery")?obj.battery:'';
						        		myarray[i][5]=obj.hasOwnProperty("controller")?obj.controller:'';
						        		myarray[i][6]=obj.hasOwnProperty("payload")?obj.payload:'';
						        		myarray[i][7]=obj.hasOwnProperty("others")?obj.others:'';
						        	}
							        return myarray;
								},
								dataType : 'json'
							}

						});

		$("#dialog-modify-form").dialog("open");
	}
</script>
<div id="dialog-modify-form" title="改裝設備明細">
	<div id = "dialog-modify-form-1">
		<table id="single-modify-table-1" class="display" style="width: 100%">
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
	<div id = "dialog-modify-form-2">
		<form id="modify-form">
			<table id="single-modify-table-2">
			<tr>
				<td>ID</td>
				<td><input type="text" name="equipmentId" id="equipmentId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>製造廠名稱</td>
				<td><input type="text" name="manufactoryName" id="manufactoryName"
					class="text ui-widget-content ui-corner-all"></td>
				<td>型式名稱</td>
				<td><input type="text" name="productName" id="productName"
					class="text ui-widget-content ui-corner-all"></td>
				
			</tr>

			<tr>
				<td>改裝日期</td>
				<td><input type="text" name="modificationDate"
					id="modificationDate" class="text ui-widget-content ui-corner-all"></td>
				<td>改裝人員</td>
				<td><input type="text" name="modificationPerson"
					id="modificationPerson" class="text ui-widget-content ui-corner-all"></td>
			</tr>

			

			<tr>
				<td>結構系統(airframe)</td>
				<td>
				<fieldset>
				<label for="airframe-none">none</label>
    			<input type="radio" name="airframe_radio" id="airframe-none" value="N" checked>
    			<label for="airframe-modified">modify</label>
    			<input type="radio" name="airframe_radio" id="airframe-modified" value="M">
    			<input type="hidden" name="airframe" id="airframe">
				</fieldset>				 					
				</td>
			
				<td>推進系統(engine, motor, propeller)</td>
				<td>
				<fieldset>
				<label for="propulsion-none">none</label>
    			<input type="radio" name="propulsion_radio" id="propulsion-none" value="N" checked>
    			<label for="propulsion-modified">modify</label>
    			<input type="radio" name="propulsion_radio" id="propulsion-modified" value="M">
    			<input type="hidden" name="propulsion" id="propulsion">
				</fieldset>
				</td>
					
				<td>動力系統(battery)</td>
				<td>
				<fieldset>
				<label for="battery-none">none</label>
    			<input type="radio" name="battery_radio" id="battery-none" value="N" checked>
    			<label for="battery-modified">modify</label>
    			<input type="radio" name="battery_radio" id="battery-modified" value="M">
    			<input type="hidden" name="battery" id="battery">
				</fieldset>
				</td>
					
			</tr>
			<tr>
				<td>飛控系統(controller)</td>
				<td>
				<fieldset>
				<label for="controller-none">none</label>
    			<input type="radio" name="controller_radio" id="controller-none" value="N" checked>
    			<label for="controller-modified">modify</label>
    			<input type="radio" name="controller_radio" id="controller-modified" value="M">
    			<input type="hidden" name="controller" id="controller">
				</fieldset>
				</td>
				
				<td>酬載裝置(payload) </td>
				<td>
				<fieldset>
				<label for="payload-none">none</label>
    			<input type="radio" name="payload_radio" id="payload-none" value="N" checked>
    			<label for="payload-modified">modify</label>
    			<input type="radio" name="payload_radio" id="payload-modified" value="M">
    			<input type="hidden" name="payload" id="payload">
				</fieldset>
				</td>
		
				<td>其他(others)</td>
				<td><input type="text" name="others" id="others"
					class="text ui-widget-content ui-corner-all"></td>
				
			</tr>
			</table>
		</form>
	</div>
</div>