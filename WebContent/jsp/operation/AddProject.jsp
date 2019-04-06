<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	$(function() {
		$(function() {
			$("#endDate").datepicker();
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

		$("#dialog-add-form")
				.dialog(
						{
							autoOpen : false,
							height : 500,
							width : 550,
							modal : true,
							buttons : {
								"新增專案" : function() {
									var bValid = true;
									allFields.removeClass("ui-state-error");

									bValid = bValid
											&& checkLength(name, "name", 3, 16);

									if (bValid) {
										var myJson = JSON.stringify($("#add-form").serializeObject());
										$.ajax({
											  url:"/Drone/operation/AddProjectProcess",
											  type:"POST",
											  data:{"data" : myJson},
											  success: function(){
												  alert('新增成功');
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
								allFields.val("").removeClass("ui-state-error");
							}
						});

		$("#add").button().click(function() {
			$("#dialog-add-form").dialog("open");
		});
	});
</script>
<div id="dialog-add-form" title="新增專案">
	<p class="validateTips">*代表必填</p>
	<form id="add-form">
		<table id="single-table">
			<tr>
				<td>名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>委託公司</td>
				<td><input type="text" name="company" id="company"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案經理</td>
				<td><input type="text" name="projectManager" id="projectManager"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>連絡電話</td>
				<td><input type="text" name="telephone" id="telephone"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案結案日期</td>
				<td><input type="text" name="endDate" id="endDate"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>專案結案成果</td>
				<td><textarea name="result" id="result" rows="20" cols="40" 
					class="text ui-widget-content ui-corner-all"></textarea></td>
			</tr>
			<tr>
				<td>專案區域</td>
				<td><input type="file" name="areaData" id="areaData"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
			<tr>
				<td>其他相關資料</td>
				<td><input type="file" name="otherData" id="otherData"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>
		</table>
	</form>
</div>