var trainingDialog = React.createClass({	
	getInitialState: function() {
        return {
        	trainingId:null,
        	crud:'C',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		trainingId:obj.trainingId,
    		crud:obj.crud 
    	});
    },
    componentDidMount(){
    	var dialog = "#trainingDialog_"+this.props.domId+"_sub";
    	var formName = "#trainingForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	var typeId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改訓練資料",
				          icon: "ui-icon-pencil",
				          id: "updateTrainingBtn_"+domId,
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
				        	  		jsonObject.trainingId =form.find("#trainingId").val();
				        	  		jsonObject.trainingType = form.find("#trainingType").val();
				        	  		jsonObject.startDate = form.find("#startDate").val();
				        	  		jsonObject.endDate = form.find("#endDate").val();
				        	  		jsonObject.hours = form.find("#hours").val();
				        	  		jsonObject.photo = form.find("#photo").val();
				        	  		jsonObject.certificateId = form.find("#certificateId").val();
				        	  		jsonObject.state = form.find("#state").val();
							  
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/"+ system_name +"/person/UpdateTrainingInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('修改訓練紀錄成功');
				        	  				store_obj.training["trainingId"] =form.find("#trainingId option:selected").text();
				        	  				store_obj.training["trainingName"] = form.find("#trainingName").val();
				        	  				store_obj.training["unit"] = form.find("#unit").val();
				        	  				store_obj.training["trainingDate"] =form.find("#startDate").val()+"~"+form.find("#endDate").val();
				        	  				store_obj.training["hours"] =form.find("#hours").val();
				        	  				store_obj.training["trainingType"] = form.find("#trainingType option:selected").text();  	
				        	  				action_obj.updateButton_click_Action(typeId);
				        	  			}
				        	  		});
				        	  		
				        	  		var fileData = new FormData(document.getElementById("trainingForm_training_sub"));				 
								    fileData.append("action","training");
										 
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
			      		  text: "新增訓練資料",
			              icon: "ui-icon-plus",
			              id: "addTrainingBtn_"+domId,
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
				        	  		jsonObject.trainingId =form.find("#trainingId").val();
				        	  		jsonObject.trainingType = form.find("#trainingType").val();
				        	  		jsonObject.startDate = form.find("#startDate").val();
				        	  		jsonObject.endDate = form.find("#endDate").val();
				        	  		jsonObject.hours = form.find("#hours").val();
				        	  		jsonObject.photo = form.find("#photo").val();
				        	  		
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/"+ system_name +"/person/TrainingInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('新增訓練紀錄成功');
				        	  			}
				        	  		});
				        	  		
				        	  		var fileData = new FormData(document.getElementById("trainingForm_training_sub"));				 
								    fileData.append("action","training");
										 
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
				training_obj.removeReactComponent(domId);		
			}		
		});
    	$("#addTrainingBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateTrainingBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$("#trainingDialog_"+this.props.domId+"_sub").dialog("open");
    },
    
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.trainingId!==prevState.trainingId)){
    		$("#addTrainingBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateTrainingBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addTrainingBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateTrainingBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    }, 
    
    componentWillUnmount(){

    },

    render: function() {
        return  React.createElement("div",  {id:"trainingDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"trainingList_"+this.props.domId}),
        			React.createElement("div",  {id:"trainingForm_"+this.props.domId})
                );
    }

});

var trainingList = React.createClass({
	getInitialState: function() {
        return {
        	state:undefined,
        	trainingId:'',
        	trainingName:'',
        	unit:'',
        	trainingDate:'',
        	hours:'',
        	trainingType:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["training"],
    		trainingId:obj.training["trainingId"],
    		trainingName:obj.training["trainingName"],
    		unit:obj.training["unit"],
    		trainingDate:obj.training["trainingDate"],
    		hours:obj.training["hours"],
    		trainingType:obj.training["trainingType"]
    	});
    },
	componentDidMount(){
    	var tableName = "#trainingList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable( {
			columnDefs: [
				{
	                targets: [ 7 ],
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
			    url: "/"+ system_name +"/person/QueryTrainings",  
			    data: {  
			        id: store_obj.personId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(8);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("trainingId")?obj.trainingId:'';
		        		myarray[i][2]=obj.hasOwnProperty("trainingName")?obj.trainingName:'';
		        		myarray[i][3]=obj.hasOwnProperty("unit")?obj.unit:'';
		        		myarray[i][4]=obj.startDate + "~" + obj.endDate;
		        		myarray[i][5]=obj.hasOwnProperty("hours")?obj.hours:'';
		        		myarray[i][6]=obj.hasOwnProperty("trainingType")?obj.trainingType:'';
		        		myarray[i][7]=obj.hasOwnProperty("certificateId")?obj.certificateId:'';
		        	}
			    	var table = $(tableName).DataTable();
			    	table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,1);
						var trainingId = cell.data();
						cell = table.cell( indexes ,7);
						var mappingId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();						
						alert('您選取了訓練編號 :'+ trainingId);
						action_obj.trainingList_select_Action(trainingId,mappingId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
			    		var cell = table.cell( indexes ,1);
						var trainingId = cell.data();
						if ( type === 'row' ) {				
							action_obj.trainingList_deselect_Action(trainingId);
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
		var tableName = "#trainingList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable();
		if(this.state.state!==prevState.state){
			if(this.state.state!=null && this.state.state!=undefined && this.state.state!=''){
				var row = table.row('.selected');
				var cell = table.cell( row ,0);
				cell.data(this.state.state).draw();
			}
		}
		if(this.state.state==='PROCESSING'){
			if(this.state.trainingId!=null && this.state.trainingId!=''){
				var cell = table.cell( row ,1);
				cell.data(this.state.trainingId).draw();
			}
			if(this.state.trainingName!=null && this.state.trainingName!=''){
				var cell = table.cell( row ,2);				
				cell.data(this.state.trainingName).draw();
			}			
			if(this.state.unit!=null && this.state.unit!=''){
				var cell = table.cell( row ,3);
				cell.data(this.state.unit).draw();
			}
			if(this.state.trainingDate!=null && this.state.trainingDate!=''){
				var cell = table.cell( row ,4);
				cell.data(this.state.trainingDate).draw();
			}
			if(this.state.hours!=null && this.state.hours!=''){
				var cell = table.cell( row ,5);
				cell.data(this.state.hours).draw();
			}
			if(this.state.trainingType!=null && this.state.trainingType!=''){
				var cell = table.cell( row ,6);
				cell.data(this.state.trainingType).draw();
			}
		}
	},
    componentWillUnmount(){
	        $("#trainingList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#trainingList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"trainingList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '訓練編號'),
                             React.createElement('th', {}, '訓練名稱'),
                             React.createElement('th', {}, '訓練單位'),
                             React.createElement('th', {}, '訓練日期'),
                             React.createElement('th', {}, '訓練時數'),
                             React.createElement('th', {}, '訓練類別'),
                             React.createElement('th', {}, 'mapping table編號')
            )));
   }
});

var trainingForm = React.createClass({
	getInitialState: function() {
        return {
        	trainingId:'',
        	state:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		targetId:obj.targetId["training"],
    		state:obj.state["training"]
    	});
    },
    
    handleTrainingIdChange : function(event){
    	var form = $("#trainingForm_"+this.props.domId+"_sub");
    	$.ajax({
			url : "/"+ system_name +"/other/ViewTrainingProcess",
			type : "POST",	
			data : {
				id : event.target.value
			},
			success : function(json) {   
				form.find("#trainingName").val(json.trainingName);
				form.find("#unit").val(json.unit);
				form.find("#trainingType").val(json.trainingType);
            }
		});
    },

	componentDidMount(){
    	var form = $("#trainingForm_"+this.props.domId+"_sub");    	
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
    		url : "/"+ system_name +"/other/QueryTrainingIDs",
    		type : "POST",
    		success : function(tag) {  
    			form.find("#trainingId").append(tag);
            }
    	});
    	
    	form.find("#startDate").datepicker();
    	form.find("#endDate").datepicker();
    	form.find("#trainingStateTD").empty();
    	
	},
	componentDidUpdate(prevProps, prevState){	
		var form = $("#trainingForm_"+this.props.domId+"_sub");
		if(this.state.state!==prevState.state){
			form.find("#state").val(this.state.state);
		}
		if(this.state.targetId!=prevState.targetId){
			if(this.state.targetId==='-'){
    			form.find("#trainingStateTD").empty();	
    			form.find("#trainingId").val("1");
    			form.find("#trainingType").val("1");
    			form.find("#unit").val("");
    			form.find("#trainingName").val("");
    			form.find("#startDate").val("");
    			form.find("#endDate").val("");
    			form.find("#hours").val("");
    			form.find("#photo").val("");
    			form.find("#certificateId").val("");
    		}else{
    			$.ajax({
    				url:"/"+ system_name +"/person/ViewTrainingInfo",
    				type:"POST",
    				data:{
    					targetId : this.state.targetId
    				},
    				dataType: "json",
    				success: function(data){				  
    					var obj = data;
    					form.find("#trainingStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
    						form.find("#" + key).val(value);	
    						form.find("input[name='" + key +"']").val(value);
    					});
				}})
		   }
	   }
	},
    componentWillUnmount(){
		var form = $("#trainingForm_"+this.props.domId+"_sub");
		form.find("#gotDate").datepicker("destroy");
	},
	render: function() {
        return  React.createElement("form",{ id:"trainingForm_"+this.props.domId+"_sub",className:this.props.extraClass},
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
                                    React.createElement("td",  {},"訓練編號"),
                                    React.createElement("td",  {},
                                        React.createElement("select",  {type:"text",id:"trainingId",name:"trainingId",onChange:this.handleTrainingIdChange})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練單位"),
                                    React.createElement("td",  {},
                                           React.createElement("input",  {type:"text",id:"unit",name:"unit",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練名稱"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"trainingName",name:"trainingName",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練類型"),
                                    React.createElement("td",  {},                       
                                           React.createElement("select",{id:"trainingType",name:"trainingType",className:"text ui-widget-content ui-corner-all"},
                                        		   React.createElement("option",{value:"1"},"學科"),
                                        		   React.createElement("option",{value:"2"},"術科")
                                           )
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練日期"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"startDate",name:"startDate",className:"text ui-widget-content ui-corner-all"}),
                                            "~",
                                            React.createElement("input",  {type:"text",id:"endDate",name:"endDate",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"時數"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"hours",name:"hours",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),                          
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"上傳結訓證明照片"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"photo",name:"photo",className:"text ui-widget-content ui-corner-all"}),
                                            React.createElement("input",  {type:"file",name:"file",id:"training_file"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"trainingStateTD"})
                            ),
                            React.createElement("tr",  {className:'hide-true'},
                                    React.createElement("td",  {},"mapping資料表編號"),
                                    React.createElement("td",  {},
                                    		React.createElement("input",  {id:"certificateId"})
                                    )
                            )
                                
                          )
                       )
                    )
                  
    }
})

