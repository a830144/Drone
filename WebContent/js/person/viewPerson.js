var personDialog = React.createClass({
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
    	var dialog = "#personDialog_"+this.props.domId+"_sub";
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
			}
		});	
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"personDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"tabs_"+this.props.domId})
        );
    }

});

var tabs = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
    componentWillUnmount(){
    	$("#tabs_"+this.props.domId+"_sub").tabs('destroy');
    },
    componentDidMount() {
    	$("#tabs_"+this.props.domId+"_sub").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});	
    },
    componentDidUpdate(prevProps, prevState){
        
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"tabs_"+this.props.domId+"_sub"},
        			React.createElement("ul",  {},
        				React.createElement("li",  {},
        					React.createElement("a",  {href:"#tabs_view_1"},"主要資訊")
                        ),
                        React.createElement("li",  {},
            				React.createElement("a",  {href:"#tabs_view_2"},"操作證資訊")
                        ),
                        React.createElement("li",  {},
                			React.createElement("a",  {href:"#tabs_view_3"},"訓練資訊")
                        ),
                        React.createElement("li",  {},
                    		React.createElement("a",  {href:"#tabs_view_4"},"特殊事蹟資訊")
                        )
                    ),
                    React.createElement("div",  {id:"tabs_view_1"},
                    		React.createElement("div",  {id:"stateButtons_"+"person"} ),
            				React.createElement("div",  {id:"personForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_2"},
            				React.createElement("div",  {id:"licenseList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"license"} ),
            				React.createElement("div",  {id:"licenseForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_3"},
            				React.createElement("div",  {id:"trainingList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"training"} ),
            				React.createElement("div",  {id:"trainingForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_4"},
            				React.createElement("div",  {id:"eventList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"event"} ),
            				React.createElement("div",  {id:"eventForm_"+this.props.domId} )
                    )
                );
                   

    }

});


var personForm = React.createClass({	
	getInitialState: function() {
        return {
        	state:'', 
        	domId:'',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state,
    		domId:obj.domId
    	});
    },
	
    componentWillUnmount(){
    	
    },
    componentDidMount() {
    	var form = $("#personForm_"+this.props.domId+"_sub");
    	$.ajax({
			  url:"/Drone/person/ViewPersonProcess",
			  type:"POST",
			  data:{
				  id : store_obj.personId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				$.each(obj, function(key, value) {
					form.find("#" + key).val(value);
				});	
				action_obj.personForm_load_Action(form.find("#state").val(),form.find("#personId").val());
			  }
		})
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#personForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
    		if(this.state.domId===this.props.domId){
    			form.find("#state").val(this.state.state);
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"personForm_"+this.props.domId+"_sub" , className : this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "人員內部ID"
                                ),
                                React.createElement("td",  {}, 
                                      React.createElement("input",  {id:"personId"})                  
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"姓名"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"name"})
                                ),
                                React.createElement("td",  {},"性別"),
                                React.createElement("td",  {},
                                    React.createElement("select",  {id:"sex"},
                                    		React.createElement("option",  {value:"1"},"男"),
                                    		React.createElement("option",  {value:"2"},"女"),
                                    		React.createElement("option",  {value:"3"},"其他")
                                    )
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"國籍"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"nationality"})
                                    ),
                                    React.createElement("td",  {},"身分證號碼"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type :"text",id:"idNumber"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"出身日期"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"dateOfBirth"})
                                    ),
                                    React.createElement("td",  {},"辦公電話"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type :"text",id:"telephone"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"傳真"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"fax"})
                                    ),
                                    React.createElement("td",  {},"通訊處"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type :"text",id:"address"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"電子郵件"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"email"})
                                    )
                                   
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"半身最近照片"),
                                    React.createElement("td",  {},
                                    	React.createElement("file",  {id:"recentPhoto"})
                                    ),
                                    React.createElement("td",  {},"最近體檢資料"),
                                    React.createElement("td",  {},
                                    	React.createElement("file",  {id:"investigation"})
                                    )
                            ),
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"state"})
                                    )
                            )
                        )
                    )
                   );

    }

});

