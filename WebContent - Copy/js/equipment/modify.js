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
    	var typeId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 750,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改改裝資料",
				          icon: "ui-icon-pencil",
				          id: "updateModificationBtn_"+domId,
				          click: function() {
				        	  var form = $(formName);
				        	  var validator =form.validate({
				        	  		rules: {
				        	  			modificationPerson: {
				        	  				 required: true,
				        	  				 minlength: 2
				        	  			},
				        	  			modificationDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	  });
				        	  if(form.valid()){
				        		  var form = $(formName);
				        		  window.radioValue(form);			        		 
				        		  var obj = form.serializeObject();
				        		  obj.equipmentId = $(form).find("#equipmentId").val();
				        		  obj.modificationDate = $(form).find("#modificationDate").val();
				        		  obj.modificationPerson = $(form).find("#modificationPerson").val();
				        		  var myJson = JSON.stringify(obj);
				        		  $.ajax({
				        			  url : "/"+ system_name +"/equipment/UpdateModifyEquipmentProcess",
				        			  type : "POST",
				        			  data : {
				        				  data : myJson
				        			  },
				        			  success : function() {
				        				  alert('修改改裝紀錄成功');
				        				  store_obj.modify["modificationDate"]=$(form).find("#modificationDate").val();
										  store_obj.modify["modificationPerson"]=$(form).find("#modificationPerson").val();
											
										  store_obj.modify["airframe"]=$(form).find("input[name='airframe']").val();
										  store_obj.modify["propulsion"]=$(form).find("input[name='propulsion']").val();
										  store_obj.modify["battery"]=$(form).find("input[name='battery']").val();
										  store_obj.modify["controller"]=$(form).find("input[name='controller']").val();
										  store_obj.modify["payload"]=$(form).find("input[name='payload']").val();
										  store_obj.modify["other"]=$(form).find("input[name='other']").val();
				        				  action_obj.updateButton_click_Action(typeId);
				        			  },
				        			  beforeSend:function(){
										  $(form).find('#loading_msg').addClass('hide-false').removeClass('hide-true');
							          },
							          complete:function(){
							              $(form).find('#loading_msg').addClass('hide-true').removeClass('hide-false');
							          }
				        		  });
				        		  
				        		  window.fileValue(form);
				        		  var fileData = new FormData(document.getElementById("modifyForm_modify_sub"));				 
								  fileData.append("action","modify");
								 
								  $.ajax({
								            type: "POST",
								            enctype: 'multipart/form-data',
								            url: "/" +system_name +"/other/uploadMultipleFile",
								            data: fileData,
								            processData: false,
								            contentType: false,
								            cache: false,
								            timeout: 600000,
								            success: function (data) {
								                console.log("SUCCESS : ", data);
								            },
								            error: function (e) {
								                console.log("ERROR : ", e);
								            }
								   });
				        	  }	
				          }
			 		    },
			    		{
			      		  text: "新增改裝資料",
			              icon: "ui-icon-plus",
			              id: "addModificationBtn_"+this.props.domId,
			              click: function() {
			            	  var form = $(formName);
				        	  var validator =form.validate({
				        	  		rules: {
				        	  			modificationPerson: {
				        	  				 required: true,
				        	  				 minlength: 2
				        	  			},
				        	  			modificationDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	  });
				        	  if(form.valid()){
				        		  var form = $(formName);
				        		  window.radioValue(form);
				        		  var obj = form.serializeObject();
				        		  obj.equipmentId = $(form).find("#equipmentId").val();
				        		  obj.modificationDate = $(form).find("#modificationDate").val();
				        		  obj.modificationPerson = $(form).find("#modificationPerson").val();
				        		  var myJson = JSON.stringify(obj);
				        		  $.ajax({
				        			  url : "/" + system_name +"/equipment/ModifyEquipmentProcess",
				        			  type : "POST",
				        			  data : {
				        				  data : myJson
				        			  },
				        			  success : function() {
				        				  alert('新增改裝紀錄成功');
				        			  },
				        			  beforeSend:function(){
										  $(form).find('#loading_msg').addClass('hide-false').removeClass('hide-true');
							          },
							          complete:function(){
							              $(form).find('#loading_msg').addClass('hide-true').removeClass('hide-false');
							          }
				        		  })
				        	  };
				        	  
				        	  window.fileValue(form);
			        		  var fileData = new FormData(document.getElementById("modifyForm_modify_sub"));				 
							  fileData.append("action","modify");
							 
							  $.ajax({
							            type: "POST",
							            enctype: 'multipart/form-data',
							            url: "/"+system_name +"/other/uploadMultipleFile",
							            data: fileData,
							            processData: false,
							            contentType: false,
							            cache: false,
							            timeout: 600000,
							            success: function (data) {
							                console.log("SUCCESS : ", data);
							            },
							            error: function (e) {
							                console.log("ERROR : ", e);
							            }
							   });
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
        	state:undefined,
        	modificationDate:'',
    		modificationPerson:'',
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
    		state:obj.state["modify"],    		
    		modificationDate:obj.modify["modificationDate"],
    		modificationPerson:obj.modify["modificationPerson"],
    		airframe:obj.modify["airframe"],
    		propulsion:obj.modify["propulsion"],
    		battery:obj.modify["battery"],
    		controller:obj.modify["controller"],
    		payload:obj.modify["payload"],
    		other:obj.modify["other"]
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
			    url: "/"+system_name +"/equipment/ViewModification",  
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
		var tableName = "#modifyList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable();
		if(this.state.state!==prevState.state){
			if(this.state.state!=null && this.state.state!=undefined && this.state.state!=''){
				var row = table.row('.selected');
				var cell = table.cell( row ,0);
				cell.data(this.state.state).draw();
			}
		}
		if(this.state.state==='PROCESSING'){
			
			if(this.state.modificationDate!=null && this.state.modificationDate!=''){
				var cell = table.cell( row ,2);				
				cell.data(this.state.modificationDate).draw();
			}			
			if(this.state.modificationPerson!=null && this.state.modificationPerson!=''){
				var cell = table.cell( row ,3);
				cell.data(this.state.modificationPerson).draw();
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
	        $("#modifyList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#modifyList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"modifyList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '改裝編號'),
                             React.createElement('th', {}, '改裝日期'),
                             React.createElement('th', {}, '改裝人員'),
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
    		state:obj.state["modify"]
    	});
    },

	componentDidMount(){
    	var form = $("#modifyForm_"+this.props.domId+"_sub");    	
    	$.ajax({
			  url:"/"+system_name +"/equipment/ViewEquipmentProcess",
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
    			if(this.state.modificationId!=null && this.state.modificationId!=''){
    			$.ajax({
    				url:"/"+system_name +"/equipment/ViewModificationByModificationId",
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
	   }
	},
    componentWillUnmount(){
		var form = $("#modifyForm_"+this.props.domId+"_sub"); 
		form.find("#modificationDate").datepicker("destroy");
    	form.find("input[name*='radio']").checkboxradio("destroy");  
	},
	render: function() {
        return  React.createElement("form",{ id:"modifyForm_"+this.props.domId+"_sub" ,className:this.props.extraClass},
        		React.createElement("div",  { id:"loading_msg",className:'hide-true'},
        				React.createElement("img",  {src:"../../images/loading.gif", className:"center"}
        		)),
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"設備ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"equipmentId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"製造廠名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"manufactoryName",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"型式名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"productName",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"改裝編號"),
                                React.createElement("td",  {id:"modificationIdTD"})
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"改裝日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"modificationDate",name:"modificationDate"})
                                )
                            ),
                           React.createElement("tr",  {},
                                React.createElement("td",  {},"改裝人員"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"modificationPerson",name:"modificationPerson"})
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
                                			 React.createElement("input",  {type:"hidden",name:"airframe",id:"mo-airframe"}),
                                			 "照片",                  	 
                                			 React.createElement("input",  {type:"text",name:"airframe_photo",id:"airframe_photo"}),                             	 
                                			 React.createElement("input",  {type:"file",name:"file",id:"airframe_file"}),
                                			 React.createElement("input",  {type:"hidden",name:"photo",id:"mo-airframe_photo"}))                                	
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"推進系統(engine, motor, propeller)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"propulsion_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"propulsion_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"propulsion_radio",id:"propulsion_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"propulsion_comment",id:"mo-propulsion_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"propulsion",id:"mo-propulsion"}),
                                			 "照片",                                			 
                                			 React.createElement("input",  {type:"text",name:"propulsion_photo",id:"propulsion_photo"}),
                                			 React.createElement("input",  {type:"file",name:"file",id:"propulsion_file"}),
                                			 React.createElement("input",  {type:"hidden",name:"photo",id:"mo-propulsion_photo"})
                                			 
                                	 )
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"動力系統(battery)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"battery_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"battery_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"battery_radio",id:"battery_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"battery_comment",id:"mo-battery_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"battery",id:"mo-battery"}),
                                			 "照片", 
                                			 React.createElement("input",  {type:"text",name:"battery_photo",id:"battery_photo"}),
                                			 React.createElement("input",  {type:"file",name:"file",id:"battery_file"}),
                                			 React.createElement("input",  {type:"hidden",name:"photo",id:"mo-battery_photo"})
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
                                			 React.createElement("input",  {type:"hidden",name:"controller",id:"mo_controller"}),
                                			 "照片",
                                			 React.createElement("input",  {type:"text",name:"controller_photo",id:"controller_photo"}),
                                			 React.createElement("input",  {type:"file",name:"file",id:"controller_file"}),
                                			 React.createElement("input",  {type:"hidden",name:"photo",id:"mo-controller_photo"})
                                	 )
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"酬載裝置(payload)"),	
                                React.createElement("td",  {},
                                	 React.createElement("fieldset",  {},
                                			 React.createElement("label",  {htmlFor:"payload_n"},"none"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_n",value:"N",defaultChecked:"defaultChecked"}),
                                			 React.createElement("label",  {htmlFor:"payload_m"},"modify"),
                                			 React.createElement("input",  {type:"radio",name:"payload_radio",id:"payload_m",value:"M"}),
                                			 React.createElement("input",  {type:"text",name:"payload_comment",id:"mo_payload_comment"}),
                                			 React.createElement("input",  {type:"hidden",name:"payload",id:"mo_payload"}),
                                			 "照片",
                                			 React.createElement("input",  {type:"text",name:"payload_photo",id:"payload_photo"}),
                                			 React.createElement("input",  {type:"file",name:"file",id:"payload_file"}),
                                			 React.createElement("input",  {type:"hidden",name:"photo",id:"mo-payload_photo"})
                                	 )
                                )
                           ),
                           React.createElement("tr",  {},
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

