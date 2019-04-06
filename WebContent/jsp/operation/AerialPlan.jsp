<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script>
$( function() {
	    $( "#dialog-aerialPlan-form" ).tabs();
	    $("[name='checkbox-1']").checkboxradio();

} );
$(document).ready(function() {	 	
    var t = $('#example').DataTable();
    var counter = 1; 
    $('#addRow').on( 'click', function () {
        t.row.add( [
            "<input type='text' maxlength='3' size='3'>",
            "<input type='text' maxlength='2' size='2'>",
            "<input type='text' maxlength='5' size='5'>",
            "<input type='text' maxlength='3' size='3'>",
            "<input type='text' maxlength='2' size='2'>",
            "<input type='text' maxlength='5' size='5'>"
        ] ).draw( false ); 
        counter++;
    } );
    // Automatically add a first row of data
    $('#addRow').click();
    
    var t = $('#chooseEquipment').DataTable();
    var counter = 1; 
    $('#addEquipmentRow').on( 'click', function () {
        t.row.add( [
            "<input type='checkbox'> ",
            "無人多旋翼機(Muti-Rotors)",
            "DJI P4P",
            "xxxxxxx",
            "20min",
            "xxxx/xx/xx"
        ] ).draw( false ); 
        counter++;
    } );
    // Automatically add a first row of data
    $('#addEquipmentRow').click();
    
    
} );
</script>
<div id="dialog-aerialPlan-form" title="航拍計畫">
	<ul>
		<li><a href="#tabs-1">主要資訊</a></li>
		<li><a href="#tabs-2">無人機</a></li>
		<li><a href="#tabs-3">相關人員</a></li>
	</ul>
	<div id="tabs-1">
	<fieldset>
	ID<br><input type="text" name="name" id="projectId" class="text ui-widget-content ui-corner-all"> <br>
	名稱<br><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all"><br>
	專案用途<br><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all"><br>
	<input type ="button" name="addRow" id="addRow" value="addrow">
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>北緯 度</th>
                <th>北緯 分</th>
                <th>北緯 秒</th>
                <th>東經 度</th>
                <th>東經 分</th>
                <th>東經 秒</th>
            </tr>
        </thead>
        
    </table>
    	航拍空域範圍檔案：(KML檔)：(File Upload<10M)<input type="file" name="license" id="license"
					class="text ui-widget-content ui-corner-all"><br>
		作業日期及時間(最多三個月、政府單位可延長至6個月)
	</fieldset>
	</div>
	
	<div id="tabs-2">
	<fieldset>
    <legend>Hotel Ratings: </legend>
    <input type="checkbox" name="checkbox-1" id="checkbox-1">
    <label for="checkbox-1">無人飛機(Aircraft) </label>
    <input type="checkbox" name="checkbox-1" id="checkbox-2">
    <label for="checkbox-2">無人直昇機(Helicopter) </label>
    <input type="checkbox" name="checkbox-1" id="checkbox-3">
    <label for="checkbox-3">無人多旋翼機(Muti-Rotors) </label>
    <input type="checkbox" name="checkbox-1" id="checkbox-4">
    <label for="checkbox-4">其它 Other(char)</label>
    
  </fieldset>
<input type ="button" name="addEquipmentRow" id="addEquipmentRow" value="addEquipmentrow">
	<table id="chooseEquipment" class="display" style="width:100%">
        <thead>
            <tr>
            	<th>選取</th>
                <th>構型</th>
                <th>型號</th>
                <th>ID</th>
                <th>最大作業時間</th>
                <th>有效期限</th>                
            </tr>
        </thead>
        
    </table>
	</div>
	<div id="tabs-3"></div>
</div>
