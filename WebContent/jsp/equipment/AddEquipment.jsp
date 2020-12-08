<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var add_obj = {		
		initState : function (id,domId){
			store_obj.equipmentId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.equipmentId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('addEquipmentDialog_'+action);
			add_obj.addEquipmentDialog = ReactDOM.render(React.createElement(addEquipmentDialog, {domId:action}), container);			
					
			container = document.getElementById('equipmentForm_'+action);
			add_obj.equipmentForm = ReactDOM.render(React.createElement(equipmentForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('addEquipmentDialog_'+action);
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

function addEquipment(){	
	add_obj.initState('','add');
	add_obj.initReactComponent('add');	
}
</script>
<div id="addEquipmentDialog_add" title="新增設備" style="display: none;">
</div>
<script src="../../js/equipment/addEquipment.js" charset="utf-8"></script>
<script src="../../js/equipment/viewEquipment.js" charset="utf-8"></script>