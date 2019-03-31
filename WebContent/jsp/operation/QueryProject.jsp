<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(document).ready(
		function() {
			$("#query").button().click(
					function() {
						var name = $('#query_name').val();
						$.post("./QueryProjectProcess.jsp", {
							name : name
						}, function(data, status) {
							data = data.trim();
							if (data !== 'undefined' && data !== '') {
								var json = jQuery.parseJSON(data);
								var cityTable = makeTable($(document.body),
										json, true);
							} else {
								console.log('data is empty');
								var cityTable = makeTable($(document.body),
										null, false);
							}

						});
					});

		});
</script>