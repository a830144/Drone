

var modifyForm_modify = React.createClass({
	getInitialState: function() {
        return {
        	modificationId:'', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		modificationId:obj.modificationId
    	});
    },
    componentWillUnmount(){
		var form = $("#modifyForm_modify_sub"); 
		form.find("#modificationDate").datepicker("destroy");
    	form.find("input[name*='radio']").checkboxradio("destroy");  
	},
	componentDidUpdate(prevProps, prevState){	
		if(this.state.modificationId!==prevState.modificationId){
			if(this.state.modificationId==='-'){
    			var form = $("#modifyForm_modify_sub");
    			form.find("#modificationIdTD").empty();
    			form.find("#modifyStateTD").empty();
    			form.find("#modificationDate").val("");
    			form.find("#modificationPerson").val("");
    			form.find("input[id*='clean']").prop("checked", true );
    			form.find("input[name*='radio']").button("enable").button("refresh");
    			form.find("input[name*='comment']").val("");	
    		}else{
    			$.ajax({
    				url:"/Drone/equipment/ViewModificationByModificationId",
    				type:"POST",
    				data:{
    					id : this.state.modificationId
    				},
    				dataType: "json",
    				success: function(data){				  
    					var obj = data;
    					var form = $("#modifyForm_modify_sub");
    					form.find("#modificationIdTD").empty().append("<input type='text' name='modificationId' id='modificationId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					form.find("#modifyStateTD").empty().append("<input type='text' name='modifyState' id='modifyState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
    						form.find("#" + key).val(value);	
    						form.find("input[name='" + key +"']").val(value);
    					});
    					window.reverseRadioValue("#modifyForm_modify_sub","mo");
    				}
    			})
		   }
	   }
	},

	componentDidMount(){
    	var form = $("#modifyForm_modify_sub");    	

    	form.find("input[type='radio']").checkboxradio();

	},
	
	render: function() {
        return  React.createElement("form",{ id:"modifyForm_modify_sub"},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"設備ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"equipmentId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                ),
                                React.createElement("td",  {},"製造廠名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"manufactoryName",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                ),
                                React.createElement("td",  {},"型式名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"productName",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"改裝編號"),
                                React.createElement("td",  {id:"modificationIdTD"}),
                                React.createElement("td",  {},"改裝日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"modificationDate"})
                                ),
                                React.createElement("td",  {},"改裝人員"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"modificationPerson"})
                                )
                            ),
                            React.createElement("tr",  {},                       
                                React.createElement("td",  {},"資料狀態"),
                                React.createElement("td",  {id:"modifyStateTD"})
                            ),
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},"結構系統(airframe)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"mo-airframe-clean"} ,"none"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"mo-airframe-clean",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"mo-airframe-repair"} ,"modify"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"mo-airframe-repair",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"airframe_comment",id:"mo-airframe_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"airframe",id:"mo-airframe"})
                                	 )
                                ),
                                React.createElement("td",  {},"推進系統(engine, motor, propeller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"mo-propulsion-clean"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"mo-propulsion-clean",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"mo-propulsion-repair"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"mo-propulsion-repair",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"propulsion_comment",id:"mo-propulsion_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"propulsion",id:"mo-propulsion"})
                                	 )
                                ),
                                React.createElement("td",  {},"動力系統(battery)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"mo-battery-clean"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"mo-battery-clean",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"mo-battery-repair"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"mo-battery-repair",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"battery_comment",id:"mo-battery_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"battery",id:"mo-battery"})
                                	 )
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"飛控系統(controller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"mo-controller-clean"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"mo-controller-clean",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"mo-controller-repair"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"mo-controller-repair",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"controller_comment",id:"mo-controller_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"controller",id:"mo-controller"})
                                	 )
                                ),
                                React.createElement("td",  {},"酬載裝置(payload)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"mo-payload-clean"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"mo-payload-clean",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"mo-payload-repair"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"mo-payload-repair",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"payload_comment",id:"mo-payload_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"payload",id:"mo-payload"})
                                	 )
                                ),
                                React.createElement("td",  {},"其他(others)"),	
                                React.createElement("td",  {},
                                	 React.createElement("input",  {type:"text",name:"others_comment",id:"mo-others_comment"}),
                           			 React.createElement("input",  {type:"hidden",name:"others",id:"mo-others",value:"N"})
                                )
                                
                            )
                        )
                    )
                  )
                   

    }
})



