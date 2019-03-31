<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

	function radioValue() {
		if (document.getElementsByName('subType_PB_radio')[0].checked) {
			$("#subType").val('1');
		}
		if (document.getElementsByName('subType_PB_radio')[1].checked) {
			$("#subType").val('2');
		}
		if (document.getElementsByName('subType_PB_radio')[2].checked) {
			$("#subType").val('3');
		}
		if (document.getElementsByName('subType_PB_radio')[3].checked) {
			$("#subType").val('4');
		}
		if (document.getElementsByName('subType_PH_radio')[0].checked) {
			$("#subType").val('A1');
		}
		if (document.getElementsByName('subType_PH_radio')[1].checked) {
			$("#subType").val('A2');
		}
		if (document.getElementsByName('subType_PH_radio')[2].checked) {
			$("#subType").val('B');
		}
		if (document.getElementsByName('subType_PH_radio')[3].checked) {
			$("#subType").val('C');
		}
		if (document.getElementsByName('subType_PH_radio')[4].checked) {
			$("#subType").val('D');
		}
	}
	$(function() {
		$('#subtype_PB').show();
		$('#subtype_PH').hide();
		$("#gotDate").datepicker();
		$("[name='subType_PB_radio']").checkboxradio();
		$("[name='subType_PH_radio']").checkboxradio();
		$('#type').change(function() {
			if ($('#type').val() == 'PB') {
				$('#subtype_PB').show();
				$('#subtype_PH').hide();
			} else {
				$('#subtype_PH').show();
				$('#subtype_PB').hide();
			}
		});
		$("#dialog-license-form").dialog({
					autoOpen : false,
					height : 400,
					width : 1050,
					modal : true,
					buttons : {
						"新增操作證" : function() {
							$("#license-form").find("#personId").prop("disabled", false);
							$("#license-form").find("#name").prop("disabled",false);
							radioValue();
							var myJson = JSON.stringify($("#license-form").serializeObject());
							$.ajax({
								url : "./LicenseInPersonProcess.jsp",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('新增人員操作證成功');
								}
							})

							$(this).dialog("close");

						},

						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						var table1 = $('#single-license-table-1').DataTable();
						table1.destroy();
					}
				});
	});
	function licenseInPerson(id) {
		$("#license-form").find("select").val("");
		$("#license-form").find("input").val("");
		$.post("./ViewPersonProcess.jsp", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = $.parseJSON(data);
				var form = $("#license-form");
				$.each(obj[0], function(key, value) {
					$("#license-form").find("#" + key).val(value);
				});

				$("#license-form").find("#personId").prop("disabled", true);
				$("#license-form").find("#name").prop("disabled", true);
			}

		});
		
		var table = $('#single-license-table-1').DataTable( {
			"scrollY":        "100px",
	        "scrollCollapse": true,		
			"ajax": {  
			    "url": "./ViewPersonProcess-tab2.jsp",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(3);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("licenseId")?obj.licenseId:'';
		        		myarray[i][1]=obj.hasOwnProperty("gotDate")?obj.gotDate:'';
		        		myarray[i][2]=obj.hasOwnProperty("type")?obj.type:'';
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});

		$("#dialog-license-form").dialog("open");
	}
</script>
<div id="dialog-license-form" title="新增操作證" >
	<div id = "dialog-license-form-1">
		<table id="single-license-table-1" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>操作證編號</th>
					<th>操作證取得日期</th>
					<th>操作證類別</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id = "dialog-license-form-2">
		<form id="license-form">
			<table id="single-license-table-2">
			<tr>
				<td>ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			
			<tr>
				<td>級別</td>
				<td>
				<select name="type" id="type"
					class="text ui-widget-content ui-corner-all">
						<option value="PB">基本級</option>
						<option value="PH">高級</option>
						
				</select>
		
			</tr>		
			<tr>
				<td>重量</td>
				<td>				
				<fieldset id = "subtype_PB">
				<label for="subType_PB_1">2 公斤以下 </label>
    			<input type="radio" name="subType_PB_radio" id="subType_PB_1" value="1" >
    			<label for="subType_PB_2">I</label>
    			<input type="radio" name="subType_PB_radio" id="subType_PB_2" value="2" >
    			<label for="subType_PB_3">II</label>
    			<input type="radio" name="subType_PB_radio" id="subType_PB_3" value="3" >
    			<label for="subType_PB_4">III</label>
    			<input type="radio" name="subType_PB_radio" id="subType_PB_4" value="4" >
				</fieldset>
				<fieldset id = "subtype_PH">
				<label for="subType_PH_A1">2 公斤以下 </label>
    			<input type="radio" name="subType_PH_radio" id="subType_PH_A1" value="A1" >
    			<label for="subType_PH_A2">2-15(不含)公斤</label>
    			<input type="radio" name="subType_PH_radio" id="subType_PH_A2" value="A2" >
    			<label for="subType_PH_B">15-25(不含)公斤</label>
    			<input type="radio" name="subType_PH_radio" id="subType_PH_B" value="B" >
    			<label for="subType_PH_C">25-150(不含)公斤</label>
    			<input type="radio" name="subType_PH_radio" id="subType_PH_C" value="C" >
    			<label for="subType_PH_D">150 公斤以上></label>
    			<input type="radio" name="subType_PH_radio" id="subType_PH_D" value="D" >    			
				</fieldset>	
				<input type="hidden" name="subType" id="subType">
			</tr>
			<tr>
				<td>操作證取得日期</td>
				<td><input type="text" name="gotDate"
					id="gotDate" class="text ui-widget-content ui-corner-all"></td>
			</tr>
			
			
			<tr>
				<td>上傳操作證照片</td>
				<td><input type="file" name="photo" id="photo"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			
			
		</table>
		</form>
	</div>
</div>