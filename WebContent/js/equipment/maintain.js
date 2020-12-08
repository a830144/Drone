var maintainDialog = React.createClass({	
	getInitialState: function() {
        return {
        	maintenanceId:null,
        	crud:'C',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		maintenanceId:obj.maintenanceId,
    		crud:obj.crud 
    	});
    },
    componentDidMount(){
    	var dialog = "#maintainDialog_"+this.props.domId+"_sub";
    	var formName = "#maintainForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	var typeId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改保養資料",
				          icon: "ui-icon-pencil",
				          id: "updateMaintenanceBtn_"+domId,
				          click: function() {
				        	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {
				        	  			maintenancePerson: {
				        	  				 required: true,
				        	  				 minlength: 2
				        	  			},
				        	  			maintenanceDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		 var form = $(formName);
						        	 window.radioValue(form);
									 var obj = form.serializeObject();
									 obj.equipmentId = $(form).find("#equipmentId").val();
									 obj.maintenanceDate = $(form).find("#maintenanceDate").val();
									 obj.maintenancePerson = $(form).find("#maintenancePerson").val();
									 obj.maintenanceType = $(form).find("#maintenanceType").val();
									 var myJson = JSON.stringify(obj);
													 
									 $.ajax({
										 url : "/" +system_name +"/equipment/UpdateMaintainEquipmentProcess",
										 type : "POST",
										 data : {
											data : myJson
										 },
										 success : function() {
											alert('修改保養紀錄成功');
											store_obj.maintain["maintenanceType"]=$(form).find("#maintenanceType option:selected").text();
											store_obj.maintain["maintenanceDate"]=$(form).find("#maintenanceDate").val();
											
											store_obj.maintain["airframe"]=$(form).find("input[name='airframe']").val();
											store_obj.maintain["propulsion"]=$(form).find("input[name='propulsion']").val();
											store_obj.maintain["battery"]=$(form).find("input[name='battery']").val();
											store_obj.maintain["controller"]=$(form).find("input[name='controller']").val();
											store_obj.maintain["payload"]=$(form).find("input[name='payload']").val();
											store_obj.maintain["other"]=$(form).find("input[name='other']").val();
											action_obj.updateButton_click_Action(typeId);
										 },
										 beforeSend:function(){
											 $(form).find('#loading_msg').addClass('hide-false').removeClass('hide-true');
							             },
							             complete:function(){
							            	 $(form).find('#loading_msg').addClass('hide-true').removeClass('hide-false');
							             }
									 });
									
				        	  	}
				        	  
				        	  }
				           			 
			 		    },
			    		{
			      		  text: "新增保養資料",
			              icon: "ui-icon-plus",
			              id: "addMaintenanceBtn_"+domId,
			              click: function() {
			            	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {
				        	  			maintenancePerson: {
				        	  				 required: true,
				        	  				 minlength: 2
				        	  			},
				        	  			maintenanceDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		var form = $(formName);			          			          		
				        	  		window.radioValue(form);
				        	  		var obj = form.serializeObject();
				        	  		obj.equipmentId = $(form).find("#equipmentId").val();
				        	  		obj.maintenanceDate = $(form).find("#maintenanceDate").val();
				        	  		obj.maintenancePerson = $(form).find("#maintenancePerson").val();
				        	  		obj.maintenanceType = $(form).find("#maintenanceType").val();
				        	  		var myJson = JSON.stringify(obj);
				        	  		$.ajax({
				        	  			url : "/" +system_name +"/equipment/MaintainEquipmentProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('新增保養紀錄成功');
				        	  			}
				        	  		})
				        	  	}
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
				maintain_obj.removeReactComponent(domId);		
			}		
		});
    	$("#addMaintenanceBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateMaintenanceBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$("#maintainDialog_"+this.props.domId+"_sub").dialog("open");
    },
    
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.maintenanceId!==prevState.maintenanceId)){
    		$("#addMaintenanceBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateMaintenanceBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addMaintenanceBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateMaintenanceBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    }, 
    
    componentWillUnmount(){

    },

    render: function() {
        return  React.createElement("div",  {id:"maintainDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"maintainList_"+this.props.domId}),
        			React.createElement("div",  {id:"maintainForm_"+this.props.domId})
                );
    }

});

var maintainList = React.createClass({
	getInitialState: function() {
        return {
        	state:undefined,
        	maintenanceType:'',
    		maintenanceDate:'',
    		airframe:'',
    		propulsion:'',
    		battery:'',
    		controller:'',
    		payload:'',
    		other:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["maintain"],
    		maintenanceType:obj.maintain["maintenanceType"],
    		maintenanceDate:obj.maintain["maintenanceDate"],
    		airframe:obj.maintain["airframe"],
    		propulsion:obj.maintain["propulsion"],
    		battery:obj.maintain["battery"],
    		controller:obj.maintain["controller"],
    		payload:obj.maintain["payload"],
    		other:obj.maintain["other"]
    	});
    },
	componentDidMount(){
    	var tableName = "#maintainList_"+this.props.domId+"_sub";
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
			    url: "/" +system_name +"/equipment/ViewMaintenance",  
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
		        		myarray[i][0]=obj.hasOwnProperty("maintainState")?obj.maintainState:'';		        		
		        		myarray[i][1]=obj.hasOwnProperty("maintenanceId")?obj.maintenanceId:'';
		        		myarray[i][2]=obj.hasOwnProperty("maintenanceType")?obj.maintenanceType:'';
		        		myarray[i][3]=obj.hasOwnProperty("maintenanceDate")?obj.maintenanceDate:'';
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
						var maintenanceId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();						
						alert('您選取了保養編號 :'+ maintenanceId);
						action_obj.maintainList_select_Action(maintenanceId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
			    		var cell = table.cell( indexes ,1);
						var maintenanceId = cell.data();
						if ( type === 'row' ) {				
							action_obj.maintainList_deselect_Action(maintenanceId);
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
		var tableName = "#maintainList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable();
		if(this.state.state!==prevState.state){
			if(this.state.state!=null && this.state.state!=undefined && this.state.state!=''){
				var row = table.row('.selected');
				var cell = table.cell( row ,0);
				cell.data(this.state.state).draw();
			}
		}
		if(this.state.state==='PROCESSING'){
			
			if(this.state.maintenanceType!=null && this.state.maintenanceType!=''){
				var cell = table.cell( row ,2);				
				cell.data(this.state.maintenanceType).draw();
			}			
			if(this.state.maintenanceDate!=null && this.state.maintenanceDate!=''){
				var cell = table.cell( row ,3);
				cell.data(this.state.maintenanceDate).draw();
			}
			if(this.state.airframe!=null && this.state.airframe!=''){
				var cell = table.cell( row ,4);
				cell.data(this.state.airframe).draw();
			}
			if(this.state.propulsion!=null && this.state.propulsion!=''){
				var cell = table.cell( row ,5);
				cell.data(this.state.propulsion).draw();
			}
			if(this.state.battery!=null && this.state.battery!=''){
				var cell = table.cell( row ,6);
				cell.data(this.state.battery).draw();
			}
			if(this.state.controller!=null && this.state.controller!=''){
				var cell = table.cell( row ,7);
				cell.data(this.state.controller).draw();
			}
			if(this.state.payload!=null && this.state.payload!=''){
				var cell = table.cell( row ,8);
				cell.data(this.state.payload).draw();
			}
			if(this.state.other!=null && this.state.other!=''){
				var cell = table.cell( row ,9);
				cell.data(this.state.other).draw();
			}
		}
	},
    componentWillUnmount(){
	        $("#maintainList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#maintainList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"maintainList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '保養編號'),
                             React.createElement('th', {}, '保養種類'),
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

var maintainForm = React.createClass({
	getInitialState: function() {
        return {
        	maintenanceId:'',
        	state:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		maintenanceId:obj.maintenanceId,
    		state:obj.state["maintain"]
    	});
    },

	componentDidMount(){
    	var form = $("#maintainForm_"+this.props.domId+"_sub");    	
    	$.ajax({
			  url:"/" +system_name +"/equipment/ViewEquipmentProcess",
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
    	form.find("#maintenanceDate").datepicker();
    	form.find("input[name*='radio']").checkboxradio();
    	form.find("#maintenanceIdTD").empty();
    	form.find("#maintainStateTD").empty();
    	form.find("#maintenanceDate").val("");
    	form.find("#maintenancePerson").val("");
    	form.find("#maintenanceType").val("W");
    	
	},
	componentDidUpdate(prevProps, prevState){	
		var form = $("#maintainForm_"+this.props.domId+"_sub");
		if(this.state.state!==prevState.state){
			form.find("#maintainState").val(this.state.state);
		}
		if(this.state.maintenanceId!==prevState.maintenanceId){
			if(this.state.maintenanceId==='-'){
    			form.find("#maintenanceIdTD").empty();
    			form.find("#maintainStateTD").empty();
    			form.find("#maintenanceDate").val("");
    			form.find("#maintenancePerson").val("");
    			form.find("#maintenanceType").val("W");
    			form.find("input[id*='_c']").prop("checked", true );
    			form.find("input[name*='radio']").button("enable").button("refresh");
    			form.find("input[name*='comment']").val("");	
    		}else{
    			if(this.state.maintenanceId!=null && this.state.maintenanceId!=''){
    			$.ajax({
    				url:"/"+system_name +"/equipment/ViewMaintenanceByMaintenanceId",
    				type:"POST",
    				data:{
    					id : this.state.maintenanceId
    				},
    				dataType: "json",
    				success: function(data){				  
    					var obj = data;
    					form.find("#maintenanceIdTD").empty().append("<input type='text' name='maintenanceId' id='maintenanceId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					form.find("#maintainStateTD").empty().append("<input type='text' name='maintainState' id='maintainState' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
						form.find("#" + key).val(value);	
						form.find("input[name='" + key +"']").val(value);
					});
					window.reverseRadioValue(form,"ma");
				}})
    			}
		   }
	   }
	},
    componentWillUnmount(){
		var form = $("#maintainForm_"+this.props.domId+"_sub");
		form.find("#maintenanceDate").datepicker("destroy");
    	form.find("input[name*='radio']").checkboxradio("destroy");
	},
	render: function() {
        return  React.createElement("form",{ id:"maintainForm_"+this.props.domId+"_sub",className:this.props.extraClass},
        		React.createElement("div",  { id:"loading_msg",className:'hide-true'},
        				React.createElement("img",  {src:"../../images/loading.gif", className:"center"}
        		)),
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
                                React.createElement("td",  {},"保養編號"),
                                React.createElement("td",  {id:"maintenanceIdTD"}),
                                React.createElement("td",  {},"保養日期"),
                                React.createElement("td",  {},                              	
                                    React.createElement("input",  {type:"text",id:"maintenanceDate",name:"maintenanceDate"})
                                ),
                                React.createElement("td",  {},"保養人員"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"maintenancePerson",name:"maintenancePerson"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"保養類型"),
                                React.createElement("td",  {},
                                    React.createElement("select",  {id:"maintenanceType"},
                                    		React.createElement("option",  {value:"W"},"周檢"),
                                    		React.createElement("option",  {value:"M"},"月檢"),
                                    		React.createElement("option",  {value:"Q"},"季檢")
                                    )
                                ),
                                React.createElement("td",  {},"資料狀態"),
                                React.createElement("td",  {id:"maintainStateTD"})
                            ),
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},"結構系統(airframe)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"airframe_c"} ,"clean"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"airframe_c",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"airframe_r"} ,"repair"),
                                			 React.createElement("input",  {type:"radio",name:"airframe_radio",id:"airframe_r",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"airframe_comment",id:"ma_airframe_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"airframe",id:"ma-airframe"})
                                	 )
                                ),
                                React.createElement("td",  {},"推進系統(engine, motor, propeller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"propulsion_c"},"clean"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_c",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"propulsion_r"},"repair"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_r",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"propulsion_comment",id:"ma_propulsion_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"propulsion",id:"ma_propulsion"})
                                	 )
                                ),
                                React.createElement("td",  {},"動力系統(battery)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"battery_c"},"clean"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_c",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"battery_r"},"repair"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_r",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"battery_comment",id:"ma-battery_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"battery",id:"ma-battery"})
                                	 )
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"飛控系統(controller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"controller_c"},"clean"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"controller_c",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"controller_r"},"repair"),
                                			 React.createElement("input",  {type:"radio",name:"controller_radio",id:"controller_r",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"controller_comment",id:"ma-controller_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"controller",id:"ma-controller"})
                                	 )
                                ),
                                React.createElement("td",  {},"酬載裝置(payload)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"payload_c"},"clean"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_c",value:"C",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"payload_r"},"repair"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_r",value:"R"}),
                                			 React.createElement("input",  {type:"text",name:"payload_comment",id:"ma_payload_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"payload",id:"ma_payload"})
                                	 )
                                ),
                                React.createElement("td",  {},"其他(others)"),	
                                React.createElement("td",  {},
                                	 React.createElement("input",  {type:"text",name:"others_comment",id:"ma_others_comment"}),
                           			 React.createElement("input",  {type:"hidden",name:"others",id:"ma_others",value:"N"})
                                )
                                
                            )
                        )
                    )
                  )
                   

    }
})

