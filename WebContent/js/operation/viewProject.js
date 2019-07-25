var projectDialog = React.createClass({
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
    	var dialog = "#projectDialog_"+this.props.domId+"_sub";
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
        return  React.createElement("div",  {id:"projectDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"projectTabs_"+this.props.domId})
        );
    }

});

var projectTabs = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
    componentWillUnmount(){
    	$("#projectTabs_"+this.props.domId+"_sub").tabs('destroy');
    },
    componentDidMount() {
    	$("#projectTabs_"+this.props.domId+"_sub").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});	
    },
    componentDidUpdate(prevProps, prevState){
        
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"projectTabs_"+this.props.domId+"_sub"},
        			React.createElement("ul",  {},
        				React.createElement("li",  {},
        					React.createElement("a",  {href:"#tabs_view_1"},"主要資訊")
                        ),
                        React.createElement("li",  {},
            				React.createElement("a",  {href:"#tabs_view_2"},"航拍計畫資訊")
                        ),
                        React.createElement("li",  {},
                			React.createElement("a",  {href:"#tabs_view_3"},"航拍活動資訊")
                        )
                    ),
                    React.createElement("div",  {id:"tabs_view_1"},
                    		React.createElement("div",  {id:"stateButtons_"+"project"} ),
            				React.createElement("div",  {id:"projectForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_2"},
            				React.createElement("div",  {id:"aerialPlanList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"aerialPlan"} ),
            				React.createElement("div",  {id:"aerialPlanForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_3"},
            				React.createElement("div",  {id:"aerialActivityList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"aerialActivity"} ),
            				React.createElement("div",  {id:"aerialActivityForm_"+this.props.domId} )
                    )
                );
                   

    }

});


var projectForm = React.createClass({	
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
    	var form = $("#projectForm_"+this.props.domId+"_sub");
    	$.ajax({
			  url:"/Drone/operation/ViewProjectProcess",
			  type:"POST",
			  data:{
				  id : store_obj.projectId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;
				$.each(obj, function(key, value) {
					form.find("#" + key).val(value);
				});	
				action_obj.projectForm_load_Action(form.find("#state").val(),form.find("#projectId").val());
			  }
		})
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#projectForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
    		if(this.state.domId===this.props.domId){
    			form.find("#state").val(this.state.state);
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"projectForm_"+this.props.domId+"_sub" , className : this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "專案ID"
                                ),
                                React.createElement("td",  {}, 
                                      React.createElement("input",  {id:"projectId"})                  
                                )
                            ),
                            React.createElement("tr",  {},
                                	React.createElement("td",  {},                                      
                                          "專案名稱"
                                    ),
                                    React.createElement("td",  {}, 
                                          React.createElement("input",  {id:"name"})                  
                                    )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"委託公司"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"company"})
                                ),
                                React.createElement("td",  {},"專案經理"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"projectManager"})
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"連絡電話"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"telephone"})
                                    ),
                                    React.createElement("td",  {},"專案結案日期"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"projectEndDate"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"專案結案成果"),
                                    React.createElement("td",  {},
                                        React.createElement("textarea",  {id:"result",rows:"10", cols:"30" })
                                    )
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"專案區域"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type:"file",id:"areaData"})
                                    )
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"其他相關資料"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type:"file",id:"otherData"})
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

