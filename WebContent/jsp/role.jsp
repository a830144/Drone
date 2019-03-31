<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#equipments {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#equipments td, #equipments th {
	border: 1px solid black;
	padding: 8px;
}

#equipments tr:nth-child(even) {
	background-color: #f2f2f2;
}

#equipments tr:hover {
	background-color: #ddd;
}

#equipments th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
	border: 1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>角色權限管理</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<style>
body {
	font-size: 62.5%;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
	$(function() {
		$('#div-person').hide();
		$('#div-equipment').hide();
		$("#person").change(function() {
		    if($(this).prop('checked')) {
		    	$('#div-person').show();
		    } else {
		    	$('#div-person').hide();
		    }
		});
		$("#equipment").change(function() {
		    if($(this).prop('checked')) {
		    	$('#div-equipment').show();
		    } else {
		    	$('#div-equipment').hide();
		    }
		});
		
		$.ajaxSetup({
		    beforeSend:function(){
		        // show image here
		        $("#busy").show();
		    },
		    complete:function(){
		        // hide image here
		        $("#busy").hide();
		    }
		});
		
		var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
				[]).add(name).add(email).add(password), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("" + n + " 的长度必须在 " + min + " 和 " + max + " 之间。");
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

		$("#dialog-form").dialog(
				{
					autoOpen : false,
					height : 500,
					width : 550,
					modal : true,
					buttons : {
						"新增角色" : function() {
							var bValid = true;
							allFields.removeClass("ui-state-error");

							bValid = bValid
									&& checkLength(name, "username", 3, 16);

							if (bValid) {
								$("#users tbody").append(
										"<tr>" + "<td>" + name.val() + "</td>"
												+ "<td>" + email.val()
												+ "</td>" + "<td>"
												+ password.val() + "</td>"
												+ "</tr>");
								$(this).dialog("close");
							}
						},
						"取消" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
				});

		$("#add").button().click(function() {
			$("#dialog-form").dialog("open");
		});
	});
</script>
</head>
<body>

	<button id="add" name="add">新增角色</button>
	<%@ include file = "./AddRole.jsp" %>

	<table id="equipments">
		<tr>
			<th>NO</th>
			<th>角色名稱</th>
			<th>最後核定修改日期</th>
			<th>權限</th>
			<th>狀態</th>
			<th>動作</th>
		<tr>
		<tr>
			<td>1</td>
			<td></td>
			<td></td>
			<td></td>
			<td>可用</td>
			<td></td>
		<tr>
		<tr>
			<td>2</td>
			<td></td>
			<td></td>
			<td></td>
			<td>可用</td>
			<td></td>
		<tr>
		<tr>
			<td>3</td>
			<td></td>
			<td></td>
			<td></td>
			<td>可用</td>
			<td></td>
		<tr>
	</table>

</body>
</html>