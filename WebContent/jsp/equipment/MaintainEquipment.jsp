<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<script>
	function radioValue(){
		if(document.getElementsByName('airframe_radio')[0].checked){
			$("#airframe").val('C');
		}
		if(document.getElementsByName('airframe_radio')[1].checked){
			$("#airframe").val('R');
		}
		if(document.getElementsByName('propulsion_radio')[0].checked){
			$("#propulsion").val('C');
		}
		if(document.getElementsByName('propulsion_radio')[1].checked){
			$("#propulsion").val('R');
		}
		if(document.getElementsByName('battery_radio')[0].checked){
			$("#battery").val('C');
		}
		if(document.getElementsByName('battery_radio')[1].checked){
			$("#battery").val('R');
		}
		if(document.getElementsByName('controller_radio')[0].checked){
			$("#controller").val('C');
		}
		if(document.getElementsByName('controller_radio')[1].checked){
			$("#controller").val('R');
		}
		if(document.getElementsByName('payload_radio')[0].checked){
			$("#payload").val('C');
		}
		if(document.getElementsByName('payload_radio')[1].checked){
			$("#payload").val('R');
		}
	}
	$(function() {
		$("#maintenanceDate").datepicker();
		$("[name='airframe_radio']").checkboxradio({
			  icon: false
		});		
		$("[name='propulsion_radio']").checkboxradio({
			  icon: false
		});
		$("[name='battery_radio']").checkboxradio({
			  icon: false
		});
		$("[name='controller_radio']").checkboxradio({
			  icon: false
		});
		$("[name='payload_radio']").checkboxradio({
			  icon: false
		});
	});
	$(function() {
		$("#dialog-maintain-form").dialog({
			autoOpen : false,
			height : 400,
			width : 1050,
			modal : true,
			buttons : {
						"保養設備" : function() {
							$("#maintain-form").find("#equipmentId").prop("disabled", false);
							$("#maintain-form").find("#manufactoryName").prop("disabled", false);
							$("#maintain-form").find("#constructionType").prop("disabled",false);
							radioValue();							
							var myJson = JSON.stringify($("#maintain-form").serializeObject());

							$.ajax({
								url : "/Drone/equipment/MaintainEquipmentProcess",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('新增保養紀錄成功');
								}
							})

							$(this).dialog("close");

						},

						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						var table1 = $('#single-maintain-table-1').DataTable();
						table1.destroy();
					}
				});
	});
	function maintainEquipment(id) {
		$("#maintain-form").find("select").val("");
		$("#maintain-form").find("input").val("");
		
		$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
			  type:"POST",
			  data:{"id" : id},
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				var form = $("#maintain-form");
				$.each(obj, function(key, value) {
					$("#maintain-form").find("#" + key).val(value);
				});
				if ($("#maintain-form").find("#constructionType").val() == 'A') {
					$("#maintain-form").find('#div-aircraft').show();
				} else {
					$("#maintain-form").find('#div-aircraft').hide();
				}
				$("#maintain-form").find("select").prop("disabled", true);
				$("#maintain-form").find("input").prop("disabled", true);
			}
		})
		
		var table = $('#single-maintain-table-1').DataTable( {
			"scrollY": "100px",
	        "scrollCollapse": true,		
			"ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/ViewMaintenance",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(8);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("maintenanceType")?obj.maintenanceType:'no_data';
		        		myarray[i][1]=obj.hasOwnProperty("maintenanceDate")?obj.maintenanceDate:'no_data';
		        		myarray[i][2]=obj.hasOwnProperty("airframe")?obj.airframe:'no_data';
		        		myarray[i][3]=obj.hasOwnProperty("propulsion")?obj.propulsion:'no_data';
		        		myarray[i][4]=obj.hasOwnProperty("battery")?obj.battery:'no_data';
		        		myarray[i][5]=obj.hasOwnProperty("controller")?obj.controller:'no_data';
		        		myarray[i][6]=obj.hasOwnProperty("payload")?obj.payload:'no_data';
		        		myarray[i][7]=obj.hasOwnProperty("others")?obj.others:'no_data';
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
		
		var table = $('#single-maintain-table-1').DataTable(); 
	    $('#single-maintain-table-1 tbody').on('click', 'tr', function () {
	        var obj = table.row( this ).data();
	        $("#maintain-form").find("#maintenanceType").val(obj.maintenanceType);
	        $("#maintain-form").find("#maintenanceDate").val(obj.maintenanceDate);
	        $("#maintain-form").find("#maintenancePerson").val(obj.maintenancePerson);
	        $("#maintain-form").find("#propulsion").val(obj.propulsion);
	        $("#maintain-form").find("#battery").val(obj.battery);
	        $("#maintain-form").find("#controller").val(obj.controller);
	        $("#maintain-form").find("#payload").val(obj.payload);
	        $("#maintain-form").find("#others").val(obj.others);
	    } );
		

		$("#dialog-maintain-form").dialog("open");
	}
	
	
</script>


<div id="dialog-maintain-form" title="保養維護" >
	<div id = "dialog-maintain-form-1">
		<table id="single-maintain-table-1" class="display" style="width: 100%">
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
	<div id = "dialog-maintain-form-2">
		<form id="maintain-form">
			<table id="single-maintain-table-2">
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
				<label for="airframe-clean">clean</label>
    			<input type="radio" name="airframe_radio" id="airframe-clean" value="C" checked>
    			<label for="airframe-repair">repair</label>
    			<input type="radio" name="airframe_radio" id="airframe-repair" value="R">
    			<input type="text" name="airframe_comment" id="airframe_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="airframe" id="airframe">
				</fieldset>				 					
				</td>
			
				<td>推進系統(engine, motor, propeller)</td>
				<td>
				<fieldset>
				<label for="propulsion-clean">clean</label>
    			<input type="radio" name="propulsion_radio" id="propulsion-clean" value="C" checked>
    			<label for="propulsion-repair">repair</label>
    			<input type="radio" name="propulsion_radio" id="propulsion-repair" value="R">
    			<input type="text" name="propulsion_comment" id="others" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="propulsion" id="propulsion">
				</fieldset>
				</td>
					
				<td>動力系統(battery)</td>
				<td>
				<fieldset>
				<label for="battery-clean">clean</label>
    			<input type="radio" name="battery_radio" id="battery-clean" value="C" checked>
    			<label for="battery-repair">repair</label>
    			<input type="radio" name="battery_radio" id="battery-repair" value="R">
    			<input type="text" name="battery_comment" id="battery_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="battery" id="battery">
				</fieldset>
				</td>
					
			</tr>
			<tr>
				<td>飛控系統(controller)</td>
				<td>
				<fieldset>
				<label for="controller-clean">clean</label>
    			<input type="radio" name="controller_radio" id="controller-clean" value="C" checked>
    			<label for="controller-repair">repair</label>
    			<input type="radio" name="controller_radio" id="controller-repair" value="R">
    			<input type="text" name="controller_comment" id="controller_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="controller" id="controller">
				</fieldset>
				</td>
				
				<td>酬載裝置(payload) </td>
				<td>
				<fieldset>
				<label for="payload-clean">clean</label>
    			<input type="radio" name="payload_radio" id="payload-clean" value="C" checked>
    			<label for="payload-repair">repair</label>
    			<input type="radio" name="payload_radio" id="payload-repair" value="R">
    			<input type="text" name="payload_comment" id="payload_comment" class="text ui-widget-content ui-corner-all">
    			<input type="hidden" name="payload" id="payload">
				</fieldset>
				</td>
		
				<td>其他(others)</td>
				<td><input type="text" name="others_comment" id="others_comment" class="text ui-widget-content ui-corner-all">
				<input type="hidden" name="others" id="others" value="N">
				</td>
				
			</tr>
			</table>
		</form>
	</div>
</div>

