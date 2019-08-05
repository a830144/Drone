<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var add_obj = {		
		initState : function (id,domId){
			store_obj.personId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.personId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('addPersonDialog_'+action);
			add_obj.addPersonDialog = ReactDOM.render(React.createElement(addPersonDialog, {domId:action}), container);			
						
			container = document.getElementById('personForm_'+action);
			add_obj.personForm = ReactDOM.render(React.createElement(personForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('addPersonDialog_'+action);
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

function addPerson(){	
	add_obj.initState('','add');
	add_obj.initReactComponent('add');	
}
</script>
<div id="addPersonDialog_add" title="新增人員" style="display: none;">
</div>
<script src="/Drone/js/person/addPerson.js" charset="utf-8"></script>
<script src="/Drone/js/person/viewPerson.js" charset="utf-8"></script>