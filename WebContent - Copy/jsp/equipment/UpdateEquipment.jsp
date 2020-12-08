<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var update_obj = {		
		initState : function (id,domId){
			store_obj.equipmentId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.equipmentId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('updateEquipmentDialog_'+action);
			update_obj.updateEquipmentDialog = ReactDOM.render(React.createElement(updateEquipmentDialog, {domId:action}), container);			
						
			container = document.getElementById('equipmentForm_'+action);
			update_obj.equipmentForm = ReactDOM.render(React.createElement(equipmentForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('updateEquipmentDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('equipmentForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function updateEquipment(id){	
	update_obj.initState(id,'update');
	update_obj.initReactComponent('update');	
}
</script>
<div id="updateEquipmentDialog_update" title="修改設備" style="display: none;">
</div>
<script src="../../js/equipment/updateEquipment.js" charset="utf-8"></script>
<script src="../../js/equipment/viewEquipment.js" charset="utf-8"></script>