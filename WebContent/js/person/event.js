var eventDialog = React.createClass({	
	getInitialState: function() {
        return {
        	eventId:null,
        	crud:'C',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		eventId:obj.eventId,
    		crud:obj.crud 
    	});
    },
    componentDidMount(){
    	var dialog = "#eventDialog_"+this.props.domId+"_sub";
    	var formName = "#eventForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	var typeId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改特殊事蹟資料",
				          icon: "ui-icon-pencil",
				          id: "updateEventBtn_"+domId,
				          click: function() {
				        	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {				        	  			
				        	  			unit: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		var form = $(formName);
				        	  		var jsonObject = {};
				        	  		jsonObject.personId =form.find("#personId").val();
				        	  		jsonObject.eventId =form.find("#eventId").val();
				        	  		jsonObject.participationId =form.find("#participationId").val();
				        	  		jsonObject.unit =form.find("#unit").val();
				        	  		jsonObject.photo = form.find("#photo").val();
				        	  		jsonObject.state = form.find("#state").val();
							  
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/"+ system_name +"/person/UpdateEventInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('修改特殊事蹟紀錄成功');
				        	  				store_obj.event["eventId"] =form.find("#eventId option:selected").text();
				        	  				store_obj.event["eventName"] = form.find("#eventName").val();
				        	  				store_obj.event["unit"] = form.find("#unit").val();
				        	  				store_obj.event["eventDate"] =form.find("#startDate").val()+"~"+form.find("#endDate").val();
				        	  				store_obj.event["description"] =form.find("#description").val();  	
				        	  				action_obj.updateButton_click_Action(typeId);
				        	  			}
				        	  		});
				        	  		
				        	  		var fileData = new FormData(document.getElementById("eventForm_event_sub"));				 
								    fileData.append("action","event");
										 
								    $.ajax({
										            type: "POST",
										            enctype: 'multipart/form-data',
										            url: "/"+ system_name +"/other/uploadMultipleFile",
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
			      		  text: "新增特殊事蹟資料",
			              icon: "ui-icon-plus",
			              id: "addEventBtn_"+domId,
			              click: function() {
			            	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {				        	  			
				        	  			startDate: {
				        	  				 required: true
				        	  			},
				        	  			endDate: {
				        	  				 required: true
				        	  			},
				        	  			hours: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		var form = $(formName);
				        	  		var jsonObject = {};
				        	  		jsonObject.personId =form.find("#personId").val();
				        	  		jsonObject.eventId =form.find("#eventId").val();						  
				        	  		jsonObject.unit =form.find("#unit").val();
				        	  		jsonObject.photo = form.find("#photo").val();
							  
							  
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/"+ system_name +"/person/EventInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('新增特殊事蹟紀錄成功');
				        	  				store_obj.event["eventId"] =form.find("#eventId option:selected").text();
				        	  				store_obj.event["eventName"] = form.find("#eventName").val();
				        	  				store_obj.event["unit"] = form.find("#unit").val();
				        	  				store_obj.event["eventDate"] =form.find("#startDate").val()+"~"+form.find("#endDate").val();
				        	  				store_obj.event["description"] =form.find("#description").val();  	
				        	  				action_obj.addButton_click_Action(typeId);
				        	  			}
				        	  		});
				        	  		
				        	  		var fileData = new FormData(document.getElementById("eventForm_event_sub"));				 
								    fileData.append("action","event");
										 
								    $.ajax({
										            type: "POST",
										            enctype: 'multipart/form-data',
										            url: "/"+ system_name +"/other/uploadMultipleFile",
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
				          text: "關閉",
				          icon: "ui-icon-closethick",
				          click: function() {
				             $( this ).dialog( "close" );
				          }
				 
				 		}],
			close : function() {
				event_obj.removeReactComponent(domId);		
			}		
		});
    	$("#addEventBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateEventBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$("#eventDialog_"+this.props.domId+"_sub").dialog("open");
    },
    
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.eventId!==prevState.eventId)){
    		$("#addEventBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateEventBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addEventBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateEventBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    }, 
    
    componentWillUnmount(){

    },

    render: function() {
        return  React.createElement("div",  {id:"eventDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"eventList_"+this.props.domId}),
        			React.createElement("div",  {id:"eventForm_"+this.props.domId})
                );
    }

});

var eventList = React.createClass({
	getInitialState: function() {
        return {
        	state:undefined,
        	eventID:'',
        	eventName:'',
        	unit:'',
        	eventDate:'',
        	description:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["event"],
    		eventID:obj.event["eventID"],
        	eventName:obj.event["eventName"],
        	unit:obj.event["unit"],
        	eventDate:obj.event["eventDate"],
        	description:obj.event["description"]
    	});
    },
	componentDidMount(){
    	var tableName = "#eventList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable( {
			columnDefs: [
				{
	                targets: [ 6 ],
	                visible: false
	            },
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
			    url: "/"+system_name +"/person/QueryEvents",  
			    data: {  
			        id: store_obj.personId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(7);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("eventId")?obj.eventId:'';
		        		myarray[i][2]=obj.hasOwnProperty("eventName")?obj.eventName:'';
		        		myarray[i][3]=obj.hasOwnProperty("unit")?obj.unit:'';
		        		myarray[i][4]=obj.startDate + "~" + obj.endDate;
		        		myarray[i][5]=obj.hasOwnProperty("description")?obj.description:'';
		        		myarray[i][6]=obj.hasOwnProperty("participationId")?obj.participationId:'';
		        	}
			    	var table = $(tableName).DataTable();
			    	table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,1);
						var eventId = cell.data();
						cell = table.cell( indexes ,6);
						var mappingId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();						
						alert('您選取了事件編號 :'+ eventId);
						action_obj.eventList_select_Action(eventId,mappingId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
			    		var cell = table.cell( indexes ,1);
						var eventId = cell.data();
						if ( type === 'row' ) {				
							action_obj.eventList_deselect_Action(eventId);
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
		var tableName = "#eventList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable();
		if(this.state.state!==prevState.state){
			if(this.state.state!=null && this.state.state!=undefined && this.state.state!=''&& this.state.state!='ADD'){
				var row = table.row('.selected');
				var cell = table.cell( row ,0);
				cell.data(this.state.state).draw();
			}
		}
		
		if(this.state.state==='PROCESSING'){
			if(this.state.eventId!=null && this.state.eventId!=''){
				var cell = table.cell( row ,1);
				cell.data(this.state.eventId).draw();
			}
			if(this.state.eventName!=null && this.state.eventName!=''){
				var cell = table.cell( row ,2);				
				cell.data(this.state.eventName).draw();
			}			
			if(this.state.unit!=null && this.state.unit!=''){
				var cell = table.cell( row ,3);
				cell.data(this.state.unit).draw();
			}
			if(this.state.eventDate!=null && this.state.eventDate!=''){
				var cell = table.cell( row ,4);
				cell.data(this.state.eventDate).draw();
			}
			if(this.state.description!=null && this.state.description!=''){
				var cell = table.cell( row ,5);
				cell.data(this.state.description).draw();
			}
			
		}
		
	},
    componentWillUnmount(){
	        $("#eventList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#eventList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"eventList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '事蹟編號'),
                             React.createElement('th', {}, '事蹟名稱'),
                             React.createElement('th', {}, '事蹟證明單位'),
                             React.createElement('th', {}, '發生特殊事蹟日期'),
                             React.createElement('th', {}, '事蹟說明'),
                             React.createElement('th', {}, 'mapping table編號')
            )));
   }
});

var eventForm = React.createClass({
	getInitialState: function() {
        return {
        	eventId:'',
        	state:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		targetId:obj.targetId["event"],
    		state:obj.state["event"]
    	});
    },
    
    handleEventIdChange : function(event){
    	var form = $("#eventForm_"+this.props.domId+"_sub");
    	$.ajax({
			url : "/"+ system_name +"/other/ViewEventProcess",
			type : "POST",	
			data : {
				id : event.target.value
			},
			success : function(json) {   
				form.find("#eventName").val(json.eventName);
				form.find("#startDate").val(json.startDate);
				form.find("#endDate").val(json.endDate);
				form.find("#description").val(json.description);
            }
		});
    },

	componentDidMount(){
    	var form = $("#eventForm_"+this.props.domId+"_sub");    	
    	$.ajax({
			  url:"/"+ system_name +"/person/ViewPersonProcess",
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
			}
		});  
    	
    	$.ajax({
    		url : "/"+ system_name +"/other/QueryEventIDs",
    		type : "POST",
    		success : function(tag) {  
    			form.find("#eventId").append(tag);
            }
    	});
    	
    	form.find("#startDate").datepicker();
    	form.find("#endDate").datepicker();
    	form.find("#eventStateTD").empty();
    	
	},
	componentDidUpdate(prevProps, prevState){	
		var form = $("#eventForm_"+this.props.domId+"_sub");
		if(this.state.state!==prevState.state){
			form.find("#state").val(this.state.state);
		}
		
		if(this.state.targetId!==prevState.targetId){
			if(this.state.targetId==='-'){
    			form.find("#eventStateTD").empty();	
    			form.find("#eventId").val("1");
    			form.find("#eventName").val("");
    			form.find("#startDate").val("");
    			form.find("#endDate").val("");
    			form.find("#photo").val("");
    			form.find("#participationId").val("");
    		}else{
    			$.ajax({
    				url:"/"+ system_name +"/person/ViewEventInfo",
    				type:"POST",
    				data:{
    					targetId : this.state.targetId
    				},
    				dataType: "json",
    				success: function(data){				  
    					var obj = data;
    					form.find("#eventStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
    						form.find("#" + key).val(value);	
    						form.find("input[name='" + key +"']").val(value);
    					});
				}})
		   }
	   }
	},
    componentWillUnmount(){
		var form = $("#eventForm_"+this.props.domId+"_sub");
		form.find("#gotDate").datepicker("destroy");
	},
	render: function() {
        return  React.createElement("form",{ id:"eventForm_"+this.props.domId+"_sub",className:this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員內部ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId",name:"personId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},                                    
                                    React.createElement("td",  {},"人員名稱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type:"text",id:"name",name:"name",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟編號"),
                                    React.createElement("td",  {},
                                        React.createElement("select",  {type:"text",id:"eventId",name:"eventId",onChange:this.handleEventIdChange})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟名稱"),
                                    React.createElement("td",  {},
                                           React.createElement("input",  {type:"text",id:"eventName",name:"eventName",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"發生特殊事蹟日期"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"startDate",name:"startDate",className:"text ui-widget-content ui-corner-all"}),
                                            "~",
                                            React.createElement("input",  {type:"text",id:"endDate",name:"endDate",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟說明"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"description",name:"description",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ), 
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟證明單位"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"unit",name:"unit",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),  
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"上傳特殊事蹟照片"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"photo",name:"photo",className:"text ui-widget-content ui-corner-all"}),
                                            React.createElement("input",  {type:"file",name:"file",id:"event_file"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"eventStateTD"})
                            ),
                            React.createElement("tr",  {className:'hide-true'},
                                    React.createElement("td",  {},"participation Id"),
                                    React.createElement("td",  {},
                                    		React.createElement("input",  {id:"participationId"})
                                    )
                            )
                                
                          )
                       )
                    )
                  
    }
})

