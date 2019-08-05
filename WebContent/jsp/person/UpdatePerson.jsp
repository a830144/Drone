<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var update_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('updatePersonDialog_'+action);
			update_obj.updatePersonDialog = ReactDOM.render(React.createElement(updatePersonDialog, {domId:action}), container);			
						
			container = document.getElementById('personForm_'+action);
			update_obj.personForm = ReactDOM.render(React.createElement(personForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('updatePersonDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('personForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function updatePerson(id){	
	update_obj.initState(id,'update');
	update_obj.initReactComponent('update');	
}
</script>
<div id="updatePersonDialog_update" title="修改人員" style="display: none;">
</div>
<script src="/Drone/js/person/updatePerson.js" charset="utf-8"></script>
<script src="/Drone/js/person/viewPerson.js" charset="utf-8"></script>