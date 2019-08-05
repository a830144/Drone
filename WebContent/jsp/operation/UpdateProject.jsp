<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
var update_obj = {		
		initState : function (id,domId){
			store_obj.projectId = id;
			store_obj.domId = domId;
		},
		removeState : function (){
			store_obj.projectId = '';
			store_obj.domId = '';
		},
		
		initReactComponent : function (action){	
			var container = document.getElementById('updateProjectDialog_'+action);
			update_obj.updateProjectDialog = ReactDOM.render(React.createElement(updateProjectDialog, {domId:action}), container);			
						
			container = document.getElementById('projectForm_'+action);
			update_obj.projectForm = ReactDOM.render(React.createElement(projectForm, {domId:action}), container);
			
		},
		removeReactComponent : function (action){
			var container = document.getElementById('updateProjectDialog_'+action);
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

function updateProject(id){	
	update_obj.initState(id,'update');
	update_obj.initReactComponent('update');	
}
</script>
<div id="updateProjectDialog_update" title="修改設備" style="display: none;">
</div>
<script src="/Drone/js/operation/updateProject.js" charset="utf-8"></script>
<script src="/Drone/js/operation/viewProject.js" charset="utf-8"></script>