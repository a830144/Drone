<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var view_obj = {		
		initState : function (id,domId){
			store_obj.roleId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.roleId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('roleDialog_'+action);
			add_obj.roleDialog = ReactDOM.render(React.createElement(roleDialog, {domId:action}), container);			
						
			container = document.getElementById('roleForm_'+action);
			add_obj.roleForm = ReactDOM.render(React.createElement(roleForm, {domId:action}), container);
			
			container = document.getElementById('permissionTable_'+action);
			add_obj.permissionTable = ReactDOM.render(React.createElement(permissionTable, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('roleDialog_'+action);
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

function viewRole(id){		
	view_obj.initState(id,'view');	
	view_obj.initReactComponent('view');	
}
</script>
<div id="roleDialog_view" title="檢視人員" style="display: none;">
</div>
<script src="../../js/role/viewRole.js" charset="utf-8"></script>
