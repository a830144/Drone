var addPersonDialog = React.createClass({
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
    	var dialog = "#addPersonDialog_"+this.props.domId+"_sub";
    	var formName = "#personForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1000,
			modal : true,
			buttons : {
				"新增人員" : function() {
					var form = $(formName);
					var validator =form.validate({
	        	  		rules: {
	        	  			name: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			sex: {
	        	  				 required: true,
	        	  				 maxlength: 1
	        	  			},
	        	  			idNumber: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			nationality: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			dateOfBirth: {
	        	  				 required: true,
	        	  				 maxlength: 10
	        	  			},
	        	  			telephone: {
	        	  				 required: true,
	        	  				 minlength: 8,
	        	  				 maxlength: 10
	        	  			},
	        	  			fax: {
	        	  				 required: true,
	        	  				 minlength: 8,
	        	  				 maxlength: 10
	        	  			},
	        	  			address: {
	        	  				 required: true,
	        	  				 minlength: 3,
	        	  				 maxlength: 22
	        	  			},
	        	  			email: {
	        	  				 required: true,
	        	  				 maxlength: 20,
	        	  				 email: true
	        	  			}
	        	  			
	        	  		}
	        	    });
					if (form.valid()) {
						var myJson = JSON.stringify($(formName).serializeObject());
						$.ajax({
									url : "/Drone/person/AddPersonProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
										reloadQueryPersonAction();
									}
						})
						
						$(form).find("#person-recentPhoto").val($(form).find("#recentPhoto").val());
						$(form).find("#person-investigation").val($(form).find("#investigation").val());	
						var fileData = new FormData(document.getElementById("personForm_add_sub"));				 
					    fileData.append("action","person");
							 
					    $.ajax({
							   type: "POST",
							   enctype: 'multipart/form-data',
							   url: "/Drone/other/uploadMultipleFile",
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
        return  React.createElement("div",  {id:"addPersonDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"personForm_"+this.props.domId})
        );
    }

});


