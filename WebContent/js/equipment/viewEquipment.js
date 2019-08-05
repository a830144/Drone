var equipmentDialog = React.createClass({
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
    	var dialog = "#equipmentDialog_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 850,
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
        return  React.createElement("div",  {id:"equipmentDialog_"+this.props.domId+"_sub"},
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
            				React.createElement("a",  {href:"#tabs_view_2"},"保養資訊")
                        ),
                        React.createElement("li",  {},
                			React.createElement("a",  {href:"#tabs_view_3"},"改裝資訊")
                        )
                    ),
                    React.createElement("div",  {id:"tabs_view_1"},
                    		React.createElement("div",  {id:"stateButtons_"+"equipment"} ),
            				React.createElement("div",  {id:"equipmentForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_2"},
            				React.createElement("div",  {id:"maintainList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"maintain"} ),
            				React.createElement("div",  {id:"maintainForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_view_3"},
            				React.createElement("div",  {id:"modifyList_"+this.props.domId} ),
            				React.createElement("div",  {id:"stateButtons_"+"modify"} ),
            				React.createElement("div",  {id:"modifyForm_"+this.props.domId} )
                    )
                );
                   

    }

});


var equipmentForm = React.createClass({	
	getInitialState: function() {
        return {
        	state:'', 
        	domId:'',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["equipment"],
    		domId:obj.domId
    	});
    },
	
    componentWillUnmount(){
    	
    },
    componentDidMount() {
    	var form = $("#equipmentForm_"+this.props.domId+"_sub");
    	if(store_obj.equipmentId!==null && store_obj.equipmentId!==''){
    		$.ajax({
    			url:"/Drone/equipment/ViewEquipmentProcess",
    			type:"POST",
    			data:{
    				id : store_obj.equipmentId
    			},
    			dataType: "json",
    			success: function(data){				  
    				var obj = data;
    				form.find("#equipmentIdTD").empty().append("<input type='text' name='equipmentId' id='equipmentId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    				form.find("#equipmentStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    				$.each(obj, function(key, value) {
    					form.find("#" + key).val(value);
    				});	
    				action_obj.equipmentForm_load_Action(form.find("#state").val(),form.find("#equipmentId").val());
    			}
    		})
    	}
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#equipmentForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
    		if(this.state.domId===this.props.domId){
    			form.find("#state").val(this.state.state);
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"equipmentForm_"+this.props.domId+"_sub" , className : this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "設備ID"
                                ),
                                React.createElement("td",  {id:"equipmentIdTD"},
                                	  React.createElement("u",  {} ,"系統自動產生") 
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"製造廠名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"manufactoryName",name:"manufactoryName"})
                                ),
                                React.createElement("td",  {},"構造分類"),
                                React.createElement("td",  {},
                                    React.createElement("select",  {id:"constructionType",name:"constructionType"},
                                    		React.createElement("option",  {value:"A"},"無人飛機(Aircraft)"),
                                    		React.createElement("option",  {value:"H"},"無人直昇機(Helicopter)"),
                                    		React.createElement("option",  {value:"M"},"無人多旋翼機(Muti-Rotors)"),
                                    		React.createElement("option",  {value:"O"},"其他Other")
                                    )
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"型式名稱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"productName",name:"productName"})
                                    ),
                                    React.createElement("td",  {},"最大起飛重量"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type :"number",id:"maxWeight",name:"maxWeight"})
                                    )
                            ),
                            React.createElement("tr",  {},                                   
                                    React.createElement("td",  {},"設備照片"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type:"text",id:"equipmentPhoto",name:"equipmentPhoto"}),
                                    	React.createElement("input",  {type:"file",id:"equipmentFile",name:"equipmentFile"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"翼展"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type: "number" ,id:"wingWidth",name:"wingWidth"})
                                    ),
                                    React.createElement("td",  {},"長度"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type: "number" ,id:"equipmentLength",name:"equipmentLength"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"推進系統"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {id:"propulsionSystem",name:"propulsionSystem"})
                                    ),
                                    React.createElement("td",  {},"動力系統"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {id:"powerType",name:"powerType"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"有效荷載"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type: "number" ,id:"loading",name:"loading"})
                                    ),
                                    React.createElement("td",  {},"巡航速度"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type: "number" ,id:"speed",name:"speed"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"最大航高"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type: "number" ,id:"maxHeight",name:"maxHeight"})
                                    ),
                                    React.createElement("td",  {},"滯空時間"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type: "number" ,id:"airTime",name:"airTime"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"最大航程"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type: "number" ,id:"maxDistance",name:"maxDistance"})
                                    ),
                                    React.createElement("td",  {},"抗風能力"),
                                    React.createElement("td",  {},
                                    	React.createElement("input",  {type: "number" ,id:"antiWind",name:"antiWind"})
                                    )
                            ),
                            React.createElement("tr",  {},
                            		React.createElement("td",  {},"其他附註"),
                                    React.createElement("td",  {},
                                    	React.createElement("textarea",  {id:"note",name:"note",rows:"2",cols:"40"})
                                    )
                                
                            ),
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"equipmentStateTD"},
                                      	  React.createElement("u",  {} ,"系統自動產生") 
                                    )

                            )
                        )
                    )
                   );

    }

});

