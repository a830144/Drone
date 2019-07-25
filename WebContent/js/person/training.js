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
						      var jsonObject = {};
							  jsonObject.personId =form.find("#personId").val();
							  jsonObject.trainingId =form.find("#trainingId").val();
							  jsonObject.trainingType = form.find("#trainingType").val();
							  jsonObject.startDate = form.find("#startDate").val();
							  jsonObject.endDate = form.find("#endDate").val();
							  jsonObject.hours = form.find("#hours").val();
							  
							  var myJson = JSON.stringify(jsonObject);
							  $.ajax({
								 url : "/Drone/person/UpdateTrainingInPersonProcess",
								 type : "POST",
								 data : {
									data : myJson
								 },
								 success : function() {
									alert('修改訓練紀錄成功');
								  }
							   })
				           }			 
			 		    },
			    		{
			      		  text: "新增訓練資料",
			              icon: "ui-icon-plus",
			              id: "addTrainingBtn_"+domId,
			              click: function() {
			            	  var form = $(formName);
						      var jsonObject = {};
						      jsonObject.personId =form.find("#personId").val();
							  jsonObject.trainingId =form.find("#trainingId").val();
							  jsonObject.trainingType = form.find("#trainingType").val();
							  jsonObject.startDate = form.find("#startDate").val();
							  jsonObject.endDate = form.find("#endDate").val();
							  jsonObject.hours = form.find("#hours").val();
							  
							  var myJson = JSON.stringify(jsonObject);
							  $.ajax({
							     url : "/Drone/person/TrainingInPersonProcess",
							     type : "POST",
							     data : {
								   data : myJson
							     },
							     success : function() {
								   alert('新增訓練紀錄成功');
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
        };
    },
    notify: function(obj){
    	this.setState({  
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
			    url: "/Drone/person/QueryTrainings",  
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
    		trainingId:obj.trainingId,
    		state:obj.state
    	});
    },
    
    handleTrainingIdChange : function(event){
    	var form = $("#trainingForm_"+this.props.domId+"_sub");
    	$.ajax({
			url : "/Drone/other/ViewTrainingProcess",
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
    		url : "/Drone/other/QueryTrainingIDs",
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
		if(this.state.trainingId!=prevState.trainingId){
			if(this.state.trainingId==='-'){
    			form.find("#trainingStateTD").empty();	
    			form.find("#trainingId").val("1");
    			form.find("#trainingType").val("1");
    			form.find("#unit").val("");
    			form.find("#trainingName").val("");
    			form.find("#startDate").val("");
    			form.find("#endDate").val("");
    			form.find("#hours").val("");
    		}else{
    			$.ajax({
    				url:"/Drone/person/ViewTrainingInfo",
    				type:"POST",
    				data:{
    					personId : store_obj.personId,
    					trainingId : this.state.trainingId
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
                                    React.createElement("td",  {},"訓練編號"),
                                    React.createElement("td",  {},
                                        React.createElement("select",  {type:"text",id:"trainingId",onChange:this.handleTrainingIdChange})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練單位"),
                                    React.createElement("td",  {},
                                           React.createElement("input",  {type:"text",id:"unit",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練名稱"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"trainingName",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練類型"),
                                    React.createElement("td",  {},                       
                                           React.createElement("select",{id:"trainingType",className:"text ui-widget-content ui-corner-all"},
                                        		   React.createElement("option",{value:"1"},"學科"),
                                        		   React.createElement("option",{value:"2"},"術科")
                                           )
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"訓練日期"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"startDate",className:"text ui-widget-content ui-corner-all"}),
                                            "~",
                                            React.createElement("input",  {type:"text",id:"endDate",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"時數"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"hours",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),                          
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"上傳結訓證明照片"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"file",id:"photo",className:"text ui-widget-content ui-corner-all"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"trainingStateTD"})
                            )
                                
                          )
                       )
                    )
                  
    }
})

