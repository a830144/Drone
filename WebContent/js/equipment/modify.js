var modifyDialog = React.createClass({	
	getInitialState: function() {
        return {
        	modificationId:null,
        	crud:'C',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		modificationId:obj.modificationId,
    		crud:obj.crud 
    	});
    },
    componentDidMount(){
    	var dialog = "#modifyDialog_"+this.props.domId+"_sub";
    	var formName = "#modifyForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改改裝資料",
				          icon: "ui-icon-pencil",
				          id: "updateModificationBtn_"+domId,
				          click: function() {
				        	  var form = $(formName);
				        	  window.radioValue(form);
							  var obj = form.serializeObject();
							  obj.equipmentId = $(form).find("#equipmentId").val();
							  obj.modificationDate = $(form).find("#modificationDate").val();
							  obj.modificationPerson = $(form).find("#modificationPerson").val();
							  var myJson = JSON.stringify(obj);
							  $.ajax({
								 url : "/Drone/equipment/UpdateModifyEquipmentProcess",
								 type : "POST",
								 data : {
									data : myJson
								 },
								 success : function() {
									alert('修改改裝紀錄成功');
								  }
							   })
				           }			 
			 		    },
			    		{
			      		  text: "新增改裝資料",
			              icon: "ui-icon-plus",
			              id: "addModificationBtn_"+this.props.domId,
			              click: function() {
			            	  var form = $(formName);
				        	  window.radioValue(form);
							  var obj = form.serializeObject();
							  obj.equipmentId = $(form).find("#equipmentId").val();
							  obj.modificationDate = $(form).find("#modificationDate").val();
							  obj.modificationPerson = $(form).find("#modificationPerson").val();
						     var myJson = JSON.stringify(obj);
						     $.ajax({
							    url : "/Drone/equipment/ModifyEquipmentProcess",
							    type : "POST",
							    data : {
								  data : myJson
							    },
							    success : function() {
								  alert('新增改裝紀錄成功');
							    }
						    })
			              }
			            },
					    {
				          text: "關閉",
				          icon: "ui-icon-closethick",
				          click: function() {
				             $( this ).dialog( "close" );
				          }
				 
				 		}],
			close : function() {
				modify_obj.removeReactComponent(domId);		
			}		
		});
    	$("#addModificationBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateModificationBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$("#modifyDialog_"+this.props.domId+"_sub").dialog("open");
    },
    
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.modificationId!==prevState.modificationId)){
    		$("#addModificationBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateModificationBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addModificationBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateModificationBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    }, 
    
    componentWillUnmount(){

    },

    render: function() {
        return  React.createElement("div",  {id:"modifyDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"modifyList_"+this.props.domId}),
        			React.createElement("div",  {id:"modifyForm_"+this.props.domId})
                );
    }

});

var modifyList = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
	componentDidMount(){
    	var tableName = "#modifyList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable( {
			columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
			select:true,
	        order: [[ 1, 'asc' ]],
			scrollY: "100px",
	        scrollCollapse: true,		
			ajax: {
				type: "POST",
			    url: "/Drone/equipment/ViewModification",  
			    data: {  
			        id: store_obj.equipmentId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(10);
			    	}
			    	for (i=0; i <json.length; i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("modifyState")?obj.modifyState:'';		        		
		        		myarray[i][1]=obj.hasOwnProperty("modificationId")?obj.modificationId:'';
		        		myarray[i][2]=obj.hasOwnProperty("modificationDate")?obj.modificationDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("modificationPerson")?obj.modificationPerson:'';
		        		myarray[i][4]=obj.hasOwnProperty("airframe")?obj.airframe:'';
		        		myarray[i][5]=obj.hasOwnProperty("propulsion")?obj.propulsion:'';
		        		myarray[i][6]=obj.hasOwnProperty("battery")?obj.battery:'';
		        		myarray[i][7]=obj.hasOwnProperty("controller")?obj.controller:'';
		        		myarray[i][8]=obj.hasOwnProperty("payload")?obj.payload:'';
		        		myarray[i][9]=obj.hasOwnProperty("others")?obj.others:'';
		        	}
			    	var table = $(tableName).DataTable();
			    	table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,1);
						var modificationId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();
						alert('您選取了改裝編號 :'+ modificationId);
						action_obj.modifyList_select_Action(modificationId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
			    		var cell = table.cell( indexes ,1);
						var modificationId = cell.data();
						if ( type === 'row' ) {				
							action_obj.modifyList_deselect_Action(modificationId);
					    }
					});
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
	},
	componentDidUpdate(prevProps, prevState){
	      
	},
    componentWillUnmount(){
	        $("#modifyList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#modifyList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"modifyList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '維護編號'),
                             React.createElement('th', {}, '維護種類'),
                             React.createElement('th', {}, '日期'),
                             React.createElement('th', {}, '結構系統'),
                             React.createElement('th', {}, '推進系統'),
                             React.createElement('th', {}, '動力系統'),
                             React.createElement('th', {}, '飛控系統'),
                             React.createElement('th', {}, '酬載裝置'),
                             React.createElement('th', {id:"stateTH"}, '其他')
                             
               
            )));
   }
});

var modifyForm = React.createClass({
	getInitialState: function() {
        return {
        	modificationId:'', 
        	state:''
        };
    },
    notify: function(obj){    	
    	this.setState({ 
    		modificationId:obj.modificationId,
    		state:obj.state
    	});
    },

	componentDidMount(){
    	var form = $("#modifyForm_"+this.props.domId+"_sub");    	
    	$.ajax({
			  url:"/Drone/equipment/ViewEquipmentProcess",
			  type:"POST",
			  data:{
				  id : store_obj.equipmentId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;				
				$.each(obj, function(key, value) {
					form.find("#" + key).val(value);
				});
			}
		});
    	form.find("#modificationDate").datepicker();
    	form.find("input[name*='radio']").checkboxradio();
    	form.find("#modificationIdTD").empty();
    	form.find("#modifyStateTD").empty();
    	form.find("#modificationDate").val("");
    	form.find("#modificationPerson").val("");
	},
	componentDidUpdate(prevProps, prevState){	
		var form = $("#modifyForm_"+this.props.domId+"_sub");
		
		if(this.state.state!==prevState.state){
			form.find("#modifyState").val(this.state.state);
		}
		if(this.state.modificationId!==prevState.modificationId){
			if(this.state.modificationId==='-'){    			
    			form.find("#modificationIdTD").empty();
    			form.find("#modifyStateTD").empty();
    			form.find("#modificationDate").val("");
    			form.find("#modificationPerson").val("");
    			form.find("input[id*='_n']").prop("checked", true );
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
    					form.find("#modificationIdTD").empty().append("<input type='text' name='modificationId' id='modificationId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					form.find("#modifyStateTD").empty().append("<input type='text' name='modifyState' id='modifyState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
    						form.find("#" + key).val(value);	
    						form.find("input[name='" + key +"']").val(value);
    					});
    					window.reverseRadioValue(form,"mo");
    				}
    			})
		   }
	   }
	},
    componentWillUnmount(){
		var form = $("#modifyForm_"+this.props.domId+"_sub"); 
		form.find("#modificationDate").datepicker("destroy");
    	form.find("input[name*='radio']").checkboxradio("destroy");  
	},
	render: function() {
        return  React.createElement("form",{ id:"modifyForm_"+this.props.domId+"_sub" ,className:this.props.extraClass},               
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
                                			 React.createElement("label",  {htmlFor:"airframe_n"} ,"none"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"airframe_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"airframe_m"} ,"modify"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"airframe_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"airframe_comment",id:"mo-airframe_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"airframe",id:"mo-airframe"})
                                	 )
                                ),
                                React.createElement("td",  {},"推進系統(engine, motor, propeller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"propulsion_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"propulsion_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"propulsion_comment",id:"mo-propulsion_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"propulsion",id:"mo-propulsion"})
                                	 )
                                ),
                                React.createElement("td",  {},"動力系統(battery)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"battery_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"battery_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"battery_comment",id:"mo-battery_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"battery",id:"mo-battery"})
                                	 )
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"飛控系統(controller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"controller_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"controller_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"controller_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"controller_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"controller_comment",id:"mo_controller_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"controller",id:"mo_controller"})
                                	 )
                                ),
                                React.createElement("td",  {},"酬載裝置(payload)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"payload_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"payload_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"payload_comment",id:"mo_payload_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"payload",id:"mo_payload"})
                                	 )
                                ),
                                React.createElement("td",  {},"其他(others)"),	
                                React.createElement("td",  {},
                                	 React.createElement("input",  {type:"text",name:"others_comment",id:"mo_others_comment"}),
                           			 React.createElement("input",  {type:"hidden",name:"others",id:"mo_others",value:"N"})
                                )
                                
                            )
                        )
                    )
                  )
                   

    }
})

