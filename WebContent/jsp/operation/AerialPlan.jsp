<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
function prepareAerialPlanDomAction(){
	$("#dialog-aerialPlan-form").dialog({
		autoOpen : false,
		height : 600,
		width : 1250,
		modal : true,
		buttons : {
			"關閉" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			var table1 = $('#single-aerialPlan-table-1').DataTable();
			table1.destroy();

			var table3 = $('#single-aerialPlan-table-3-1').DataTable();
			table3.destroy();
		}
	});	
	$("#dialog-aerialPlan-tabs").tabs({
		  activate: function( event, ui ) {
			  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
		  }
	});
	$("#wizard").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "slideLeft"
    });

}

function viewDomFinishState(id){
	window.projectId = id;
}

function transferAerialPlanListAction(){
	
	var singleaerialPlantable1 = $('#single-aerialPlan-table-1').DataTable( {
		"select":true,
        "order": [[ 1, 'asc' ]],
		//"scrollY": "100px",
        "scrollCollapse": true
	});
	$(' .dataTables_scrollBody').height(100);
}

function transferAerialPlanMainFormAction(){
	
	
}

function transferEquipmentInPlanListAction(){
	var singleaerialPlantable31 = $('#single-aerialPlan-table-3-1').DataTable( {
		columnDefs: [
		{	
		    className: 'dt-center',
		    targets: '_all'
		}],
		"order": [[ 1, 'asc' ]],
		"scrollY": "100px",
	    "scrollCollapse": true

});
	
}

function initializeAerialPlanListState(){
	
}

$(function() {
	prepareAerialPlanDomAction();
});


function aerialPlan(id){
	viewDomFinishState(id);	
	transferAerialPlanListAction();
	transferAerialPlanMainFormAction();
	transferEquipmentInPlanListAction();
	initializeAerialPlanListState();	
	$("#dialog-aerialPlan-form").dialog("open");

}
</script>
<div id="dialog-aerialPlan-form" title="檢視設備資料">
	<div id="dialog-aerialPlan-form-1">
		<form id="aerialPlan-form-1">
			<table id="single-aerialPlan-table-1">
				<%@ include file="./pages/AerialPlan_list.jsp"%>
			</table>
		</form>
	</div>


	<div id="dialog-aerialPlan-tabs">
		<ul>
			<li><a href="#dialog-aerialPlan-form-2">主要資訊</a></li>
			<li><a href="#dialog-aerialPlan-form-3">使用設備資訊</a></li>
			<li><a href="#dialog-aerialPlan-form-4">其他資訊</a></li>
		</ul>
		<div id="dialog-aerialPlan-form-2">
			
			<table id="single-aerialPlan-table-2-1" class="display"style="width: 100%">
				<%@ include file="./pages/AerialPlan_main_form.jsp"%>
			</table>
			
		</div>
		<div id="dialog-aerialPlan-form-3">
			<table id="single-aerialPlan-table-3-1" class="display"
				style="width: 100%">
				<%@ include file="./pages/EquipmentInPlan_list.jsp"%>
			</table>
			<div id="wizard">
				<%@ include file="./pages/wizard.jsp"%>
				<%-- <form id="aerialPlan-form-3-2">
					<table id="single-aerialPlan-table-3-2">
						<%@ include file="./pages/wizard.jsp"%>
					</table>
				</form> --%>
			</div>
		</div>
		<div id="dialog-aerialPlan-form-4">
			<table id="single-aerialPlan-table-4-1" class="display" style="width: 100%">
				<%@ include file="./pages/AerialPlan_other_form.jsp"%>
			</table>
		</div>
	</div>
</div>