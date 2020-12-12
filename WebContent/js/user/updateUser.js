var updateUserDialog = React.createClass({
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
    	var dialog = "#updateUserDialog_"+this.props.domId+"_sub";
    	var formName = "#userForm_"+this.props.domId+"_sub";
    	var listBoxName = "#RoleListBox_"+this.props.domId+"_sub";

    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"修改用戶" : function() {
					var form = $(formName);
					var validator =form.validate({
						rules: {
	        	  			userName: {
	        	  				 required: true,
	        	  				 maxlength: 30
	        	  			}	        	  			
	        	  		}
	        	    });
					if (form.valid()) {
						var temp = Array(store_obj.tempRole);
						var i = 0;
						function putValue(values) 
						{ 
							temp[i] = values;
							i++;
						} ;
						store_obj.tempRole.forEach(putValue); 			
						var jsonObject = $(formName).serializeObject();						
						jsonObject.rolesSet = temp;	
						//debugger;
						store_obj.tempRole.clear();						
						var myJson = JSON.stringify(jsonObject);
							$.ajax({
								url : "/"+system_name +"/user/UpdateUserProcess",
								type : "POST",
								data : {
									"data" : myJson
								},
								success : function() {
									alert('修改成功');
									reloadQueryUserAction();
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
    	return  React.createElement("div",  {id:"updateUserDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"userForm_"+this.props.domId}),
        		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
        				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
        				React.createElement("p",  {style:{color: "red"}},'某些角色具備特殊性,所對應的權限跟功能樹有關,請謹慎選擇。選取適合的角色加入此用戶')
        		),		
        		React.createElement("div",  {id:"roleListBox_"+this.props.domId})
        );
    }

});


