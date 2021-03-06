var roleDialog = React.createClass({
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
    	var dialog = "#roleDialog_"+this.props.domId+"_sub";
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
				reloadQueryRoleAction();
			}
		});	
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
    	 return  React.createElement("div",  {id:"roleDialog_"+this.props.domId+"_sub"},
         		React.createElement("div",  {id:"roleForm_"+this.props.domId}),
         		React.createElement("div",  {style:{color: "red", backgroundColor: "#DCDCDC"}},
         				React.createElement("img",  {src: "../../images/exclamation-mark.png"}),
         				React.createElement("p",  {style:{color: "red"}},'某些權限具備特殊性,所對應的功能跟功能樹有關,請謹慎選擇。選取適合的permission加入此角色')
         		),		
         		React.createElement("div",  {id:"permissionTable_"+this.props.domId})
         );
    }

});
var roleForm = React.createClass({	
	getInitialState: function() {
        return {
        	state:'', 
        	domId:'',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["role"],
    		domId:obj.domId
    	});
    },
	
    componentWillUnmount(){
    	
    },
    componentDidMount() {
    	var form = $("#roleForm_"+this.props.domId+"_sub");
    	if(store_obj.roleId!==null && store_obj.roleId!==''){
    		$.ajax({
    			url:"/"+system_name +"/role/ViewRoleProcess",
    			type:"POST",
    			data:{
    				id : store_obj.roleId
    			},
    			dataType: "json",
    			success: function(data){
    				var obj = data;
    				form.find("#roleIdTD").empty().append("<input type='text' name='roleId' id='roleId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    				
    				$.each(obj, function(key, value) {
    					form.find("#" + key).val(value).addClass("ui-state-disabled");
    				});	
    			}
    		})
    	};
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#roleForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
    		if(this.state.domId===this.props.domId){
    			form.find("#state").val(this.state.state);
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"roleForm_"+this.props.domId+"_sub" , className : this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "角色內部ID"
                                ),
                                React.createElement("td",  {id:"roleIdTD"},
                                  	  React.createElement("u",  {} ,"系統自動產生") 
                                )                               
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"角色名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"roleName",name:"roleName",size:"30",maxLength:"30"})
                                )
                            )
                        )
                    )
                   );

    }

});


var permissionTable = React.createClass({
	getInitialState: function() {
        return {
        	
        };
    },
    notify: function(obj){
    	this.setState({ 
    		
    	});
    },
	componentDidMount(){    	
    	var tableName = "#permissionTable_"+this.props.domId+"_sub";
        var table = $(tableName).DataTable();
        table.destroy();
        if(store_obj.roleId!==null && store_obj.roleId!==''){
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
                	url: "/"+system_name +"/role/ViewRoleProcess",   
                	data: {  
                		id : store_obj.roleId
                	},   
                	dataSrc: function ( data ) {
                		var json = data.ownPermissionSet;

                		var myarray=new Array(json.length);
                		for(i=0; i <json.length; i++){
                			myarray[i]=new Array(3);
                		}
                		
                		for(i=0;i<json.length;i++){
                			//var obj = JSON.parse(json[i]); 
                			var obj = json[i];
                			myarray[i][0]='---';
                			myarray[i][1]=obj.hasOwnProperty("permissionId")?obj.permissionId:'';
                			myarray[i][2]=obj.hasOwnProperty("permissionName")?obj.permissionName:'';
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
                	url: "/"+system_name +"/other/QueryPermissionProcess",   
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
                			myarray[i][1]=obj.hasOwnProperty("permissionId")?obj.permissionId:'';
                			myarray[i][2]=obj.hasOwnProperty("permissionName")?obj.permissionName:'';
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
	        $("#permissionTable_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#permissionTable_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"permissionTable_"+this.props.domId+"_sub" ,className: "display" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, 'permission編號'),
                             React.createElement('th', {}, 'permission名稱')
                             
            )));
   }
});



var permissionListBox = React.createClass({
	getInitialState: function() {
        return {
        	
        };
    },
    notify: function(obj){
    	this.setState({ 
    		
    	});
    },
	componentDidMount(){    	
    	var listBoxName = "#permissionListBox_"+this.props.domId+"_sub";
    	$.ajax({
    		url: "/"+system_name +"/role/ViewRoleWithPermissionsProcess",   
        	data: {  
        		id : store_obj.roleId
        	},   
  			type : "POST",
  			dataType: 'json',
  			success : function(json) {  
  				
  				var myarray=new Array(json.length);
  				var tempPermission = new Set();
  				for(i=0; i <json.length; i++){  					
        			if(json[i][2]=='N'){
        				var obj ={text:json[i][1], value:json[i][0]};
        			}else{
        				var obj ={text:json[i][1], value:json[i][0], selected:true};
        				tempPermission.add(json[i][0]);
        			};
        			myarray[i]= obj;        			
        		}
  				store_obj.tempPermission = tempPermission;

  				//console.log(myarray);
  				//var json = data.ownPermissionSet;
  				//var optionsText = '';
  				
  				let dualListbox = new DualListbox(listBoxName,{
  	    		  addEvent: function(value) {
  	    		      console.log(value);
  	    		    console.log(dualListbox.selectedList);
  	    		      //debugger;
  	    		    store_obj.tempPermission.add(value);
  	    		      //store_obj.addPermission.add(value);
  	    		  },
  	    		  removeEvent: function(value) {
  	    		      console.log(value);
  	    		    store_obj.tempPermission.delete(value);
  	    		      //store_obj.addPermission.delete(value);
  	    		  },
  	    		  availableTitle: '未選擇權限',
  	    		  selectedTitle: '已選擇權限',
  	    		  addButtonText: '>',
  	    		  removeButtonText: '<', addAllButtonText: '>>',
  	    		  removeAllButtonText: '<<',
  	    		  options: myarray
  	    		});
  				dualListbox.remove_all_button.remove();
  				
        		
  			}
  		});
    	
	},
	componentDidUpdate(prevProps, prevState){
		
		
	},
    componentWillUnmount(){
	        
	},
	render: function() {
        return  React.createElement("select",  {id:"permissionListBox_"+this.props.domId+"_sub" ,className: "display" }
                    
                             
            );
   }
});

