<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var update_obj = {		
		initState : function (id,domId){
			store_obj.userId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.userId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('updateUserDialog_'+action);
			update_obj.updateUserDialog = ReactDOM.render(React.createElement(updateUserDialog, {domId:action}), container);			
						
			container = document.getElementById('userForm_'+action);
			update_obj.userForm = ReactDOM.render(React.createElement(userForm, {domId:action}), container);
			
			container = document.getElementById('roleListBox_'+action);
			add_obj.roleListBox = ReactDOM.render(React.createElement(roleListBox, {domId:action}), container);
		},
		removeReactComponent : function (action){
			var container = document.getElementById('updateUserDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('userForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('roleListBox_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function updateUser(id){	
	update_obj.initState(id,'update');
	update_obj.initReactComponent('update');	
}
</script>
<div id="updateUserDialog_update" title="修改用戶" style="display: none;">
</div>
<script src="../../js/user/updateUser.js" charset="utf-8"></script>
<script src="../../js/user/viewUser.js" charset="utf-8"></script>