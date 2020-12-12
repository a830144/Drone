var updateRoleDialog = React.createClass({
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
    	var dialog = "#updateRoleDialog_"+this.props.domId+"_sub";
    	var formName = "#roleForm_"+this.props.domId+"_sub";
    	var listBoxName = "#permissionListBox_"+this.props.domId+"_sub";

    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"修改角色" : function() {
					var form = $(formName);
					var validator =form.validate({
						rules: {
	        	  			roleName: {
	        	  				 required: true,
	        	  				 maxlength: 30
	        	  			}	        	  			
	        	  		}
	        	    });
					if (form.valid()) {
						var temp = Array(store_obj.tempPermission);
						var i = 0;
						function putValue(values) 
						{ 
							temp[i] = values;
							i++;
						} ;
						store_obj.tempPermission.forEach(putValue); 			
						var jsonObject = $(formName).serializeObject();						
						jsonObject.permissionsSet = temp;	
						//debugger;
						store_obj.tempPermission.clear();						
						var myJson = JSON.stringify(jsonObject);
							$.ajax({
								url : "/"+system_name +"/role/UpdateRoleProcess",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('修改成功');
									reloadQueryRoleAction();
								}
							})
							
							$(this).dialog("close");

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
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
    	return  React.createElement("div",  {id:"updateRoleDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"roleForm_"+this.props.domId}),
        		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
        				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
        				React.createElement("p",  {style:{color: "red"}},'某些權限具備特殊性,所對應的功能跟功能樹有關,請謹慎選擇。選取適合的permission加入此角色')
        		),		
        		React.createElement("div",  {id:"permissionListBox_"+this.props.domId})
        );
    }

});


