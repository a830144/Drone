<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>

$(function() {	
	$( "#dialog-delete-form" ).hide();
})
function deletePerson(id) {
	$( "#dialog-delete-form" ).show();
	$( "#dialog-delete-form" ).dialog({
	      resizable: false,
	      height: "auto",
	      width: 400,
	      modal: true,
	      buttons: {
	        "刪除人員": function() {
	        	$.ajax({
					  url:"/Drone/person/DeletePersonProcess",
					  type:"POST",
					  data:{"id" : id},
					  success : function() {
							alert('刪除成功');
							reloadQueryPersonAction();
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
<div id="dialog-delete-form" title="刪除人員">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>資料將被註記為刪除(但仍會存於資料庫中),確定繼續執行?</p>
</div>