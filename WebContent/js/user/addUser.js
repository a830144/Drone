var addUserDialog = React.createClass({
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
    	var dialog = "#addUserDialog_"+this.props.domId+"_sub";
    	var formName = "#userForm_"+this.props.domId+"_sub";
    	var tableName = "#roleTable_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"新增用戶" : function() {
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
						
						var table = $(tableName).DataTable();
						
						var selected = table.rows({ selected: true });
						var temp = Array(selected.count);
						var i = 0;
	                	selected.every(function() {
	                        var row = this.node();
	                        var roleId = this.data()[1];
	                        temp[i] = roleId;
	                        i++;
	                    });


						var jsonObject = $(formName).serializeObject();
						
						jsonObject.rolesSet = temp;
						var myJson = JSON.stringify(jsonObject);
						$.ajax({
									url : "/"+ system_name +"/user/AddUserProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
										reloadQueryUserAction();
									}
						})
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
        return  React.createElement("div",  {id:"addUserDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"userForm_"+this.props.domId}),
        		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
        				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
        				React.createElement("p",  {style:{color: "red"}},'某些角色具備特殊性,所對應的權限跟功能樹有關,請謹慎選擇。選取適合的角色加入此用戶')
        		),		
        		React.createElement("div",  {id:"roleTable_"+this.props.domId})
        );
    }

});


