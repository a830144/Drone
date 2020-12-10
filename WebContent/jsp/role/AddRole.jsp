<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var add_obj = {		
		initState : function (id,domId){
			store_obj.roleId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.roleId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('addRoleDialog_'+action);
			add_obj.addRoleDialog = ReactDOM.render(React.createElement(addRoleDialog, {domId:action}), container);			
						
			container = document.getElementById('roleForm_'+action);
			add_obj.RoleForm = ReactDOM.render(React.createElement(roleForm, {domId:action}), container);
			
			container = document.getElementById('permissionTable_'+action);
			add_obj.RoleTable = ReactDOM.render(React.createElement(permissionTable, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('addRoleDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('roleForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('permissionTable_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function addRole(){	
	add_obj.initState('','add');
	add_obj.initReactComponent('add');	
}
</script>
<div id="addRoleDialog_add" title="新增角色" style="display: none;">
</div>
<script src="../../js/role/addRole.js" charset="utf-8"></script>
<script src="../../js/role/viewRole.js" charset="utf-8"></script>