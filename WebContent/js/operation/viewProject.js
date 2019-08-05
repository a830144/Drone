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
    		state:obj.state["project"],
    		domId:obj.domId
    	});
    },
	
    componentWillUnmount(){
    	
    },
    componentDidMount() {
    	var form = $("#projectForm_"+this.props.domId+"_sub");
    	if(store_obj.projectId!==null && store_obj.projectId!==''){
    		$.ajax({
    			url:"/Drone/operation/ViewProjectProcess",
    			type:"POST",
    			data:{
    				id : store_obj.projectId
    			},
    			dataType: "json",
    			success: function(data){				  
    				var obj = data;
    				form.find("#projectIdTD").empty().append("<input type='text' name='projectId' id='projectId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
  				  	form.find("#projectStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
  				  	$.each(obj, function(key, value) {
  				  		form.find("#" + key).val(value);
  				  	});	
  				  	action_obj.projectForm_load_Action(form.find("#state").val(),form.find("#projectId").val());
    			}
    		});
		}
    	form.find("#projectEndDate").datepicker();
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
                                React.createElement("td",  {id:"projectIdTD"},
                                  	  React.createElement("u",  {} ,"系統自動產生") 
                                )
                           
                            ),
                            React.createElement("tr",  {},
                                	React.createElement("td",  {},                                      
                                          "專案名稱"
                                    ),
                                    React.createElement("td",  {}, 
                                          React.createElement("input",  {id:"name",name:"name"})                  
                                    )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"委託公司"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"company",name:"company"})
                                ),
                                React.createElement("td",  {},"專案經理"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"projectManager",name:"projectManager"})
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"連絡電話"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"telephone",name:"telephone"})
                                    ),
                                    React.createElement("td",  {},"專案結案日期"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"projectEndDate",name:"projectEndDate"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"專案結案成果"),
                                    React.createElement("td",  {},
                                        React.createElement("textarea",  {id:"result",name:"result",rows:"10", cols:"30" })
                                    )
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"專案區域"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type:"text",id:"areaData",name:"areaData"}),
                                    	React.createElement("input",  {type:"file",name:"file",id:"areaData_file"}),
                                    	React.createElement("input",  {type:"hidden",name:"photo",id:"project-areaData"})
                                    )
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"其他相關資料"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type:"text",id:"otherData",name:"otherData"}),
                                    	React.createElement("input",  {type:"file",name:"file",id:"otherData_file"}),
                                    	React.createElement("input",  {type:"hidden",name:"photo",id:"project-otherData"})
                                    )
                            ),                            
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"projectStateTD"},
                                        	  React.createElement("u",  {} ,"系統自動產生") 
                                    )
                            )
                        )
                    )
                   );

    }

});

