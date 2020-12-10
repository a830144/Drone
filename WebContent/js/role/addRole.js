var addRoleDialog = React.createClass({
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
    	var dialog = "#addRoleDialog_"+this.props.domId+"_sub";
    	var formName = "#roleForm_"+this.props.domId+"_sub";
    	var tableName = "#permissionTable_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 500,
			width : 1200,
			modal : true,
			buttons : {
				"新增角色" : function() {
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
						
						var table = $(tableName).DataTable();
						
						var selected = table.rows({ selected: true });
						var temp = Array(selected.count);
						var i = 0;
	                	selected.every(function() {
	                        var row = this.node();
	                        var permissionId = this.data()[1];
	                        temp[i] = permissionId;
	                        i++;
	                    });


						var jsonObject = $(formName).serializeObject();
						
						jsonObject.permissionsSet = temp;
						var myJson = JSON.stringify(jsonObject);
						$.ajax({
									url : "/"+ system_name +"/role/AddRoleProcess",
									type : "POST",
									data : {
										"data" : myJson
									},
									success : function() {
										alert('新增成功');
										reloadQueryRoleAction();
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
        return  React.createElement("div",  {id:"addRoleDialog_"+this.props.domId+"_sub"},
        		React.createElement("div",  {id:"roleForm_"+this.props.domId}),
        		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
        				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
        				React.createElement("p",  {style:{color: "red"}},'某些權限具備特殊性,所對應的功能跟功能樹有關,請謹慎選擇。選取適合的permission加入此角色')
        		),		
        		React.createElement("div",  {id:"permissionTable_"+this.props.domId})
        );
    }

});


