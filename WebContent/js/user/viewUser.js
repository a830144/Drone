var userDialog = React.createClass({
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
    	var dialog = "#userDialog_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 700,
			width : 1350,
			modal : true,			
			buttons : [
			    {
		          text: "關閉",
		          icon: "ui-icon-closethick",
		          click: function() {
		             $( this ).dialog( "close" );
		          }		 
		 		}],
			close : function() {
				view_obj.removeReactComponent(domId);
				reloadQueryUserAction();
			}
		});	
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
    	 return  React.createElement("div",  {id:"userDialog_"+this.props.domId+"_sub"},
         		React.createElement("div",  {id:"userForm_"+this.props.domId}),
         		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
         				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
         				React.createElement("p",  {style:{color: "red"}},'某些權限具備特殊性,所對應的功能跟功能樹有關,請謹慎選擇。選取適合的permission加入此角色')
         		),		
         		React.createElement("div",  {id:"roleTable_"+this.props.domId})
         );
    }

});
var userForm = React.createClass({	
	getInitialState: function() {
        return {
        	state:'', 
        	domId:'',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["user"],
    		domId:obj.domId
    	});
    },
	
    componentWillUnmount(){
    	
    },
    componentDidMount() {
    	var form = $("#userForm_"+this.props.domId+"_sub");
    	if(store_obj.userId!==null && store_obj.userId!==''){
    		$.ajax({
    			url:"/"+system_name +"/user/ViewUserProcess",
    			type:"POST",
    			data:{
    				id : store_obj.userId
    			},
    			dataType: "json",
    			success: function(data){
    				var obj = data;
    				form.find("#userIdTD").empty().append("<input type='text' name='userId' id='userId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    				
    				$.each(obj, function(key, value) {
    					form.find("#" + key).val(value).addClass("ui-state-disabled");
    				});	
    			}
    		})
    	};
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#userForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
    		if(this.state.domId===this.props.domId){
    			form.find("#state").val(this.state.state);
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"userForm_"+this.props.domId+"_sub" , className : this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "用戶內部ID"
                                ),
                                React.createElement("td",  {id:"userIdTD"},
                                  	  React.createElement("u",  {} ,"系統自動產生") 
                                )                               
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"用戶名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"userName",name:"userName",size:"30",maxLength:"30"})
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"電子郵件信箱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"EMail",name:"EMail",size:"30",maxLength:"30"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"電話"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"phone",name:"phone",size:"30",maxLength:"30"})
                                    )
                            )
                        )
                    )
                   );

    }

});


var roleTable = React.createClass({
	getInitialState: function() {
        return {
        	
        };
    },
    notify: function(obj){
    	this.setState({ 
    		
    	});
    },
	componentDidMount(){    	
    	var tableName = "#roleTable_"+this.props.domId+"_sub";
        var table = $(tableName).DataTable();
        table.destroy();
        if(store_obj.userId!==null && store_obj.userId!==''){
        	$(tableName).DataTable({
                columnDefs: [ {	
				    className: 'dt-center',
				    targets: '_all'
				}],
                
                order: [[ 1, 'asc' ]],
                data: '',
                ordering: false,
                ajax: {
                	type: "POST",
                	url: "/"+system_name +"/user/ViewUserProcess",   
                	data: {  
                		id : store_obj.userId
                	},   
                	dataSrc: function ( data ) {
                		var json = data.ownRoleSet;

                		var myarray=new Array(json.length);
                		for(i=0; i <json.length; i++){
                			myarray[i]=new Array(3);
                		}
                		
                		for(i=0;i<json.length;i++){
                			var obj = json[i];
                			myarray[i][0]='---';
                			myarray[i][1]=obj.hasOwnProperty("roleId")?obj.roleId:'';
                			myarray[i][2]=obj.hasOwnProperty("roleName")?obj.roleName:'';
                		}
                		
                		return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
        }else{
        $(tableName).DataTable({
                columnDefs: [ {
    				orderable: false,
    				className: 'select-checkbox',
    				targets:   0
    			},{	
				    className: 'dt-center',
				    targets: '_all'
				}],
                select: {
                	style: 'multi',
                	selector: 'td:first-child'
                },
                order: [[ 1, 'asc' ]],
                data: '',
                ordering: false,
                ajax: {
                	type: "POST",
                	url: "/"+system_name +"/role/QueryRoleProcess",   
                	data: {  
         
                	}, 
                	dataSrc: function ( json ) {
                		var myarray=new Array(json.length);
                		for(i=0; i <json.length; i++){
                			myarray[i]=new Array(3);
                		}
                		for(i=0;i<json.length;i++){
                			var obj = $.parseJSON(json[i]);
                			myarray[i][0]='';
                			myarray[i][1]=obj.hasOwnProperty("roleId")?obj.roleId:'';
                			myarray[i][2]=obj.hasOwnProperty("roleName")?obj.roleName:'';
                		}
                		
                		return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
        }
	},
	componentDidUpdate(prevProps, prevState){
		
		
	},
    componentWillUnmount(){
	        $("#roleTable_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#roleTable_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"roleTable_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, '角色編號'),
                             React.createElement('th', {}, '角色名稱')
                             
            )));
   }
});


var roleListBox = React.createClass({
	getInitialState: function() {
        return {
        	
        };
    },
    notify: function(obj){
    	this.setState({ 
    		
    	});
    },
	componentDidMount(){    	
    	var listBoxName = "#roleListBox_"+this.props.domId+"_sub";
    	$.ajax({
    		url: "/"+system_name +"/user/ViewUserWithRolesProcess",   
        	data: {  
        		id : store_obj.userId
        	},   
  			type : "POST",
  			dataType: 'json',
  			success : function(json) {  
  				
  				var myarray=new Array(json.length);
  				var tempRole = new Set();
  				for(i=0; i <json.length; i++){  					
        			if(json[i][2]=='N'){
        				var obj ={text:json[i][1], value:json[i][0]};
        			}else{
        				var obj ={text:json[i][1], value:json[i][0], selected:true};
        				tempRole.add(json[i][0]);
        			};
        			myarray[i]= obj;        			
        		}
  				store_obj.tempRole = tempRole;

  				//console.log(myarray);
  				//var json = data.ownPermissionSet;
  				//var optionsText = '';
  				
  				let dualListbox = new DualListbox(listBoxName,{
  	    		  addEvent: function(value) {
  	    		      console.log(value);
  	    		    console.log(dualListbox.selectedList);
  	    		      //debugger;
  	    		    store_obj.tempRole.add(value);
  	    		      //store_obj.addPermission.add(value);
  	    		  },
  	    		  removeEvent: function(value) {
  	    		      console.log(value);
  	    		    store_obj.tempRole.delete(value);
  	    		      //store_obj.addPermission.delete(value);
  	    		  },
  	    		  availableTitle: '未選擇角色',
  	    		  selectedTitle: '已選擇角色',
  	    		  addButtonText: '>',
  	    		  removeButtonText: '<', addAllButtonText: '>>',
  	    		  removeAllButtonText: '<<',
  	    		  options: myarray
  	    		});
  				
        		
  			}
  		});
    	
	},
	componentDidUpdate(prevProps, prevState){
		
		
	},
    componentWillUnmount(){
	        
	},
	render: function() {
        return  React.createElement("select",  {id:"roleListBox_"+this.props.domId+"_sub" ,className: "display" }
                    
                             
            );
   }
});

