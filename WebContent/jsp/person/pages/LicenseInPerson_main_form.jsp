<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
			<tr>
				<td>人員內部ID</td>
				<td><input type="text" name="personId" id="personId"
					class="text ui-widget-content ui-corner-all"></td>				
			</tr>
			
			<tr>
			<td>名稱</td>
				<td><input type="text" name="name" id="name"
					class="text ui-widget-content ui-corner-all"></td>
			</tr>			
			<tr>
				<td>級別</td>
				<td>
				<div id = "typeTd">
				<select name="type" id="type"
					class="text ui-widget-content ui-corner-all">						
				</select>
				</div>
				</td>
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