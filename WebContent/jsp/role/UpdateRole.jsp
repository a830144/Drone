<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var update_obj = {		
		initState : function (id,domId){
			store_obj.roleId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.roleId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('updateRoleDialog_'+action);
			update_obj.updateRoleDialog = ReactDOM.render(React.createElement(updateRoleDialog, {domId:action}), container);			
						
			container = document.getElementById('roleForm_'+action);
			update_obj.roleForm = ReactDOM.render(React.createElement(roleForm, {domId:action}), container);
			
			container = document.getElementById('permissionListBox_'+action);
			add_obj.RoleTable = ReactDOM.render(React.createElement(permissionListBox, {domId:action}), container);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('updateRoleDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('roleForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('permissionListBox_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function updateRole(id){	
	update_obj.initState(id,'update');
	update_obj.initReactComponent('update');	
}
</script>
<div id="updateRoleDialog_update" title="修改角色" style="display: none;">
</div>
<script src="../../js/role/updateRole.js" charset="utf-8"></script>
<script src="../../js/role/viewRole.js" charset="utf-8"></script>