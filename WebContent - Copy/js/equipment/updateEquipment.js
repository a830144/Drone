var updateEquipmentDialog = React.createClass({
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
    	var dialog = "#updateEquipmentDialog_"+this.props.domId+"_sub";
    	var formName = "#equipmentForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"修改設備" : function() {
						var form = $(formName);
						var validator =form.validate({
							rules: {
		        	  			manufactoryName: {
	        	  				 	required: true,
	        	  				 	maxlength: 30
	        	  				},
	        	  				productName: {
	        	  				 	required: true,
	        	  				 	maxlength: 30
	        	  				},
	        	  				maxWeight: {
	        	  				 	required: true,
	        	  				 	number: true,
	        	  				 	maxlength: 6
	        	  				},
	        	  				wingWidth: {
	        	  				 	required: true,
	        	  				 	number: true,
	        	  					maxlength: 6
	        	  				},
	        	  				equipmentLength: {
	        	  				 	required: true,
	        	  				 	number: true,
	        	  				 	maxlength: 6
	        	  				},
	        	  				propulsionSystem: {
	        	  				 	required: true,
	        	  				 	maxlength: 10
	        	  				},
	        	  				powerType: {
	        	  				 	required: true,
	        	  				 	maxlength: 20
	        	  				},
	        	  				loading: {
	        	  				 	number: true,
	        	  					maxlength: 6
	        	  				},
	        	  				speed: {
	        	  				 	number: true,
	        	  				 	maxlength: 6
	        	  				},
	        	  				maxHeight: {
	        	  				 	number: true,
	        	  				 	maxlength: 6
	        	  				},
	        	  				airTime: {
	        	  				 	number: true,
	        	  				 	maxlength: 6
	        	  				},
	        	  				maxDistance: {
	        	  				 	number: true,
	        	  					maxlength: 6
	        	  				},
	        	  				antiWind: {
	        	  				 	required: true,
	        	  				 	maxlength: 10
	        	  				}
		        	  			
		        	  		}
	        	    	});
						if (form.valid()) {
							var myJson = JSON.stringify($(formName).serializeObject());
							$.ajax({
								url : "/"+system_name +"/equipment/UpdateEquipmentProcess",
								type : "POST",
								data : {
									data : myJson
								},
								success : function() {
									alert('修改成功');
									reloadQueryEquipmentAction();
								},
								beforeSend:function(){
									$(form).find('#loading_msg').addClass('hide-false').removeClass('hide-true');
					            },
					            complete:function(){
					            	$(form).find('#loading_msg').addClass('hide-true').removeClass('hide-false');
					            }
							})
							
							var fileData = new FormData();
							fileData.append("photo",$(formName).find("#equipmentPhoto").val());
							var ins = document.getElementById("equipmentFile").files.length;
							for (var x = 0; x < ins; x++) {
								fileData.append("file", document.getElementById("equipmentFile").files[x]);
							};
							fileData.append("action","equipments");
							$.ajax({
						         type: "POST",
						         enctype: 'multipart/form-data',
						         url: "/" +system_name +"/other/uploadMultipleFile",
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
							//reloadQueryEquipmentAction();
							//$(this).dialog("close");
							}
						},
						"關閉" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						update_obj.removeReactComponent(domId);
					}
		});
    	$("#updateEquipmentDialog_"+this.props.domId+"_sub").dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"updateEquipmentDialog_"+this.props.domId+"_sub"},
    			React.createElement("div",  {id:"equipmentForm_"+this.props.domId})
        );
    }

});


