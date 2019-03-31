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
				var table4 = $('#single-view-table-4').DataTable();
				table4.destroy();
			}
		});
	});
	function viewPerson(id) {
		$( "#view-form" ).find("select").val("");
		$( "#view-form" ).find("input").val("");
		$.post("./ViewPersonProcess.jsp", {
			id : id
		}, function(data, status) {
			if (status == 'success') {
				var obj = $.parseJSON(data);
				var form = $("#view-form");
				$.each(obj, function(key, value){
				    $("#view-form").find("#"+key).val(value);
				});
				
				$( "#view-form" ).find("select").prop( "disabled", true );
				$( "#view-form" ).find("input").prop( "disabled", true );
			}

		});
		
		var table = $('#single-view-table-2').DataTable( {
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
		
		var table = $('#single-view-table-3').DataTable( {
			"ajax": {  
			    "url": "./ViewPersonProcess-tab3.jsp",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(5);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][1]=obj.hasOwnProperty("unit")?obj.unit:'';
		        		myarray[i][2]=obj.hasOwnProperty("startDate")?obj.startDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("hours")?obj.hours:'';
		        		myarray[i][4]=obj.hasOwnProperty("trainingType")?obj.trainingType:'';		   
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
		
		var table = $('#single-view-table-4').DataTable( {
			"ajax": {  
			    "url": "./ViewPersonProcess-tab4.jsp",  
			    "data": {  
			        "id": id  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][1]=obj.hasOwnProperty("name")?obj.name:'';
		        		myarray[i][2]=obj.hasOwnProperty("startDate")?obj.startDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("description")?obj.description:'';
		        	}
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});

		$("#dialog-view-form").dialog("open");
	}
</script>
<div id="dialog-view-form" title="檢視設備資料">
	<ul>
		<li><a href="#dialog-view-form-1">主要資訊</a></li>
		<li><a href="#dialog-view-form-2">操作證資訊</a></li>
		<li><a href="#dialog-view-form-3">訓練紀錄資訊</a></li>
		<li><a href="#dialog-view-form-4">特殊事蹟資訊</a></li>
	</ul>
	<div id="dialog-view-form-1">
		<form id="view-form">
			<table id="single-view-table-1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>
				<td>姓名</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>性別</td>
				<td><select name="sex" id="sex"
					class="text ui-widget-content ui-corner-all">
						<option value="1">男</option>
						<option value="2">女</option>
						<option value="3">其他</option>
				</select></td>
			</tr>

			<tr>
				<td>國籍</td>
				<td><input type="text" name="nationality" id="nationality"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>身分證號碼</td>
				<td><input type="text" name="idNumber" id="idNumber"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>

			<tr>
				<td>出身日期</td>
				<td><input type="text" name="dateOfBirth" id="dateOfBirth"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>辦公電話</td>
				<td><input type="text" name="telephone" id="telephone"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr></tr>
			<tr>
				<td>傳真</td>
				<td><input type="text" name="fax" id="fax"
					class="text ui-widget-content ui-corner-all"></td>
			
				<td>通訊處</td>
				<td><input type="text" name="address" id="address"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr></tr>
			<tr>
				<td>電子郵件</td>
				<td><input type="text" name="email" id="email"
					class="text ui-widget-content ui-corner-all"></td>
			
			</tr>
			<tr>
				<td>半身 最近照片</td>
				<td><input type="file" name="recentPhoto" id="recentPhoto"
					class="text ui-widget-content ui-corner-all"></td>
				<td>最近體檢資料</td>
				<td><input type="file" name="investigation" id="investigation"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="dialog-view-form-2">
		<table id="single-view-table-2" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>操作證編號</th>
					<th>操作證取得日期</th>
					<th>操作證類別</th>
				</tr>
			</thead>

		</table>
	</div>
	<div id="dialog-view-form-3">
		<table id="single-view-table-3" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>訓練名稱</th>
					<th>訓練單位</th>
					<th>訓練日期</th>
					<th>訓練時數</th>
					<th>訓練類型</th>
				</tr>
			</thead>

		</table>
	</div>
	<div id="dialog-view-form-4">
		<table id="single-view-table-4" class="display" style="width: 100%">
			<thead>
				<tr>
					<th>事蹟名稱</th>
					<th>事蹟證明單位</th>
					<th>發生特殊事蹟日期</th>
					<th>事蹟說明</th>
				</tr>
			</thead>

		</table>
	</div>
</div>