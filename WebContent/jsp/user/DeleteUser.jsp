<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

$(function() {	
	$( "#dialog-delete-form" ).hide();
})
function deleteUser(id) {
	$( "#dialog-delete-form" ).show();
	$( "#dialog-delete-form" ).dialog({
	      resizable: false,
	      height: "auto",
	      width: 400,
	      modal: true,
	      buttons: {
	        "刪除用戶": function() {
	        	$.ajax({
					  url:"/" + system_name +"/user/DeleteUserProcess",
					  type:"POST",
					  data:{"id" : id},
					  success : function() {
							alert('刪除成功');
							reloadQueryUserAction();
					  }
				})
	          $( this ).dialog( "close" );
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	 });
}

</script>
<div id="dialog-delete-form" title="刪除用戶">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>資料庫中資料將被永久刪除且不可回復,確定繼續執行?</p>
</div>