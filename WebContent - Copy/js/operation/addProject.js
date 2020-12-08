var addProjectDialog = React.createClass({
	getInitialState: function() {
        return {
        	 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		
    	});
    },
    componentWillUnmount(){   	
    },
    componentDidMount() {
    	var dialog = "#addProjectDialog_"+this.props.domId+"_sub";
    	var formName = "#projectForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"新增專案" : function() {
					var form = $(formName);
					var validator =form.validate({
	        	  		rules: {
	        	  			name: {
	        	  				 required: true,
	        	  				 maxlength: 40
	        	  			},
	        	  			company: {
	        	  				 required: true,
	        	  				 maxlength: 40
	        	  			},
	        	  			telephone: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			projectManager: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			projectEndDate: {
	        	  				 maxlength: 10
	        	  			}
	        	  			
	        	  		}
	        	    });		
					if (form.valid()) {
						var myJson = JSON.stringify($(formName).serializeObject());
						$.ajax({
									url : "/"+system_name +"/operation/AddProjectProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
										reloadQueryProjectAction();
									}
						});
						
						$(form).find("#project-areaData").val($(form).find("#areaData").val());
						$(form).find("#project-otherData").val($(form).find("#otherData").val());	
						var fileData = new FormData(document.getElementById("projectForm_add_sub"));				 
					    fileData.append("action","project");
							 
					    $.ajax({
							   type: "POST",
							   enctype: 'multipart/form-data',
							   url: "/"+system_name +"/other/uploadMultipleFile",
							   data: fileData,
							   processData: false,
							   contentType: false,
							   cache: false,
							   timeout: 600000,
							   success: function (data) {
							         console.log("SUCCESS : ", data);
							   },
							   error: function (e) {
							         console.log("ERROR : ", e);
							   }
					    });					
						$(this).dialog("close");
					}
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				add_obj.removeReactComponent(domId);
			}
		});
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"addProjectDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"projectForm_"+this.props.domId})
        );
    }

});


