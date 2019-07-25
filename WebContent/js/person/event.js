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
						      var jsonObject = {};
							  jsonObject.personId =form.find("#personId").val();
							  jsonObject.eventId =form.find("#eventId").val();
							  
							  
							  var myJson = JSON.stringify(jsonObject);
							  $.ajax({
								 url : "/Drone/person/UpdateEventInPersonProcess",
								 type : "POST",
								 data : {
									data : myJson
								 },
								 success : function() {
									alert('修改特殊事蹟紀錄成功');
								  }
							   })
				           }			 
			 		    },
			    		{
			      		  text: "新增特殊事蹟資料",
			              icon: "ui-icon-plus",
			              id: "addEventBtn_"+domId,
			              click: function() {
			            	  var form = $(formName);
						      var jsonObject = {};
						      jsonObject.personId =form.find("#personId").val();
							  jsonObject.eventId =form.find("#eventId").val();						  
							  
							  var myJson = JSON.stringify(jsonObject);
							  $.ajax({
							     url : "/Drone/person/EventInPersonProcess",
							     type : "POST",
							     data : {
								   data : myJson
							     },
							     success : function() {
								   alert('新增特殊事蹟紀錄成功');
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
        };
    },
    notify: function(obj){
    	this.setState({  
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
			    url: "/Drone/person/QueryEvents",  
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
    		eventId:obj.eventId,
    		state:obj.state
    	});
    },
    
    handleEventIdChange : function(event){
    	var form = $("#eventForm_"+this.props.domId+"_sub");
    	$.ajax({
			url : "/Drone/other/ViewEventProcess",
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
			}
		});  
    	
    	$.ajax({
    		url : "/Drone/other/QueryEventIDs",
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
		
		if(this.state.eventId!==prevState.eventId){
			if(this.state.eventId==='-'){
    			form.find("#eventStateTD").empty();	
    			form.find("#eventId").val("1");
    			form.find("#eventName").val("");
    			form.find("#startDate").val("");
    			form.find("#endDate").val("");
    		}else{
    			$.ajax({
    				url:"/Drone/person/ViewEventInfo",
    				type:"POST",
    				data:{
    					personId : store_obj.personId,
    					eventId : this.state.eventId
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
                                    React.createElement("input",  {type:"text",id:"personId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},                                    
                                    React.createElement("td",  {},"人員名稱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type:"text",id:"name",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟編號"),
                                    React.createElement("td",  {},
                                        React.createElement("select",  {type:"text",id:"eventId",onChange:this.handleEventIdChange})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟名稱"),
                                    React.createElement("td",  {},
                                           React.createElement("input",  {type:"text",id:"eventName",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"發生特殊事蹟日期"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"startDate",className:"text ui-widget-content ui-corner-all"}),
                                            "~",
                                            React.createElement("input",  {type:"text",id:"endDate",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟說明"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"description",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ), 
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"事蹟證明單位"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),  
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"上傳特殊事蹟照片"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"file",id:"photo",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"eventStateTD"})
                            )
                                
                          )
                       )
                    )
                  
    }
})

