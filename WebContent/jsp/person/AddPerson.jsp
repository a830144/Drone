<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
	$(function() {
		$("#dateOfBirth").datepicker();
	});
	$(function() {
		
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
							height : 400,
							width : 800,
							modal : true,
							buttons : {
								"新增人員" : function() {
									var bValid = true;
									allFields.removeClass("ui-state-error");

									bValid = bValid
											&& checkLength(name, "name", 3, 16);

									if (bValid) {
										var myJson = JSON.stringify($("#add-form").serializeObject());
										$.ajax({
											  url:"./AddPersonProcess.jsp",
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
<div id="dialog-add-form" title="新增設備">
	<p class="validateTips">*代表必填</p>
	<form id="add-form">
		<table id="single-add-table-1">
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