<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var view_obj = {		
		initState : function (id,domId){
			store_obj.userId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.userId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('userDialog_'+action);
			add_obj.userDialog = ReactDOM.render(React.createElement(userDialog, {domId:action}), container);			
						
			container = document.getElementById('userForm_'+action);
			add_obj.userForm = ReactDOM.render(React.createElement(userForm, {domId:action}), container);
			
			container = document.getElementById('roleTable_'+action);
			add_obj.roleTable = ReactDOM.render(React.createElement(roleTable, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('userDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('userForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('roleTable_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });

		},		
};

function viewUser(id){		
	view_obj.initState(id,'view');	
	view_obj.initReactComponent('view');	
}
</script>
<div id="userDialog_view" title="檢視人員" style="display: none;">
</div>
<script src="../../js/User/viewUser.js" charset="utf-8"></script>
