<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Add React in One Minute</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="/Drone/js/multiselect.min.js"></script>
		<script type="text/javascript">
jQuery(document).ready(function($) {
    $('#multiselect1').multiselect();
    $('#multiselect2').multiselect();
});
</script>
		
	<!-- Load our React component. -->
<!-- //<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> -->
	
</head>
<body>

	<h4>First multiselect</h4>
 
<div class="row">
    <div class="col-xs-5">
        <select name="from[]" id="multiselect1" class="form-control" size="8" multiple="multiple">
            <option value="1">Item 1</option>
            <option value="2">Item 5</option>
            <option value="2">Item 2</option>
            <option value="2">Item 4</option>
            <option value="3">Item 3</option>
        </select>
    </div>
    
    <div class="col-xs-2">
        <button type="button" id="multiselect1_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
        <button type="button" id="multiselect1_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
        <button type="button" id="multiselect1_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
        <button type="button" id="multiselect1_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
    </div>
    
    <div class="col-xs-5">
        <select name="to[]" id="multiselect1_to" class="form-control" size="8" multiple="multiple"></select>
    </div>
</div>
 
<h4>Second multiselect</h4>
 
<div class="row">
    <div class="col-xs-5">
        <select name="from[]" id="multiselect2" class="form-control" size="8" multiple="multiple">
            <option value="1">Item 1</option>
            <option value="2">Item 5</option>
            <option value="2">Item 2</option>
            <option value="2">Item 4</option>
            <option value="3">Item 3</option>
        </select>
    </div>
    
    <div class="col-xs-2">
        <button type="button" id="multiselect2_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
        <button type="button" id="multiselect2_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
        <button type="button" id="multiselect2_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
        <button type="button" id="multiselect2_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
    </div>
    
    <div class="col-xs-5">
        <select name="to[]" id="multiselect2_to" class="form-control" size="8" multiple="multiple"></select>
    </div>
</div>

</body>
</html>