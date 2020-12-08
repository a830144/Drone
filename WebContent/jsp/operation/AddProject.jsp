<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var add_obj = {		
		initState : function (id,domId){
			store_obj.projectId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.projectId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('addProjectDialog_'+action);
			add_obj.addProjectDialog = ReactDOM.render(React.createElement(addProjectDialog, {domId:action}), container);			
					
			container = document.getElementById('projectForm_'+action);
			add_obj.projectForm = ReactDOM.render(React.createElement(projectForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('addProjectDialog_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			container = document.getElementById('projectForm_'+action);
			ReactDOM.unmountComponentAtNode(container);
			
			Object.keys(store_obj).forEach(function(index) {
				if(typeof store_obj[index] !='object'){
					store_obj[index] = null
				}
		    });
		},		
};

function addProject(){	
	add_obj.initState('','add');
	add_obj.initReactComponent('add');	
}
</script>
<div id="addProjectDialog_add" title="新增專案" style="display: none;">
</div>
<script src="../../js/operation/addProject.js" charset="utf-8"></script>
<script src="../../js/operation/viewProject.js" charset="utf-8"></script>