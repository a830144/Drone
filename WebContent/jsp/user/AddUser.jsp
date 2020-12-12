<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var add_obj = {		
		initState : function (id,domId){
			store_obj.userId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.userId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('addUserDialog_'+action);
			add_obj.addUserDialog = ReactDOM.render(React.createElement(addUserDialog, {domId:action}), container);			
						
			container = document.getElementById('userForm_'+action);
			add_obj.UserForm = ReactDOM.render(React.createElement(userForm, {domId:action}), container);
			
			container = document.getElementById('roleTable_'+action);
			add_obj.UserTable = ReactDOM.render(React.createElement(roleTable, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('addUserDialog_'+action);
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

function addUser(){	
	add_obj.initState('','add');
	add_obj.initReactComponent('add');	
}
</script>
<div id="addUserDialog_add" title="新增用戶" style="display: none;">
</div>
<script src="../../js/user/addUser.js" charset="utf-8"></script>
<script src="../../js/user/viewUser.js" charset="utf-8"></script>