var aerialPlanDialog = React.createClass({
	getInitialState: function() {
        return {
        	aerialPlanId:'',
        	crud:'C', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		aerialPlanId:obj.aerialPlanId,
    		crud:obj.crud 
    	});
    },
    componentWillUnmount(){   	
    },
    componentDidMount() {
    	var dialog = "#aerialPlanDialog_"+this.props.domId+"_sub";
    	var formName = "#aerialPlanForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	var typeId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 700,
			width : 1350,
			modal : true,
			buttons : [
				{
				  text: "修改航拍計畫資料",
		          icon: "ui-icon-pencil",
		          id: "updateAerialPlanBtn_"+domId,
		          click: function (){ 
		        	  var form = $("#aerialPlanForm_aerialPlan_sub");
					  var form_other = $("#otherForm_aerialPlan_sub");
					  var validator =form.validate({
		      	  			rules: {
		      	  				aerialPlanStartDate: {
		      	  				 	required: true,
		      	  				 	maxlength: 10
		      	  				},
		      	  				aerialPlanEndDate: {
		      	  				 	required: true,
		      	  				 	maxlength: 10
		      	  				},      	  			      	  			
		      	  			}
		      	      });
					  var validator_other =form_other.validate({
		    	  			rules: {
		    	  				takeOffLocationName: {
		    	  				 	required: true,
		    	  				 	maxlength: 25
		    	  				}      	  			      	  			
		    	  			}
		    	      });	
					  if (form.valid() && form_other.valid()) {			  
					  	var jsonObject = {};
					  	jsonObject.projectId =$(form).find("#projectId").val();
					  	jsonObject.name = $(form).find("#name").val();
					  	jsonObject.aerialPlanId = $(form).find("#aerialPlanId").val();
					  	jsonObject.usage = $(form).find("#usage").val();
					  	jsonObject.aerialPlanStartDate = $(form).find("#aerialPlanStartDate").val();
					  	jsonObject.aerialPlanEndDate = $(form).find("#aerialPlanEndDate").val();
					  	jsonObject.photo = $(form).find("#photo").val();
					  	jsonObject.state = $(form).find("#state").val();
					
					  
					 	var tableName = "#aerialPlanEPList_aerialPlan_sub";
					  	var arr = new Array();
					  	jsonObject.equipmentsArray = arr;
					  	var table = $(tableName).DataTable();			  
					  	table.rows().eq(0).each( function ( index ) {
					    	var row = table.row( index );
					    	var cell = table.cell( index ,3);
							var equipmentId = cell.data();									
							var personId_1 = $("#EProw"+index+"col"+6).val();					
							var personId_2 = $("#EProw"+index+"col"+7).val(); 
							
							jsonObject.equipmentsArray.push({
								"equipmentId":equipmentId,
								"personId_1":personId_1,
								"personId_2":personId_2
							});
					  	} );
					  
					  										  			  
					  	jsonObject.amslFrom =$(form_other).find("#amslFrom").val();
					  	jsonObject.amslTo = $(form_other).find("#amslTo").val();
					  	jsonObject.agl = $(form_other).find("#agl").val();
					  	jsonObject.takeOffNDegree = $(form_other).find("#takeOffNDegree").val();
					  	jsonObject.takeOffNMinute = $(form_other).find("#takeOffNMinute").val();
					  	jsonObject.takeOffNSecond = $(form_other).find("#takeOffNSecond").val();
					  	jsonObject.takeOffEDegree = $(form_other).find("#takeOffEDegree").val();
					  	jsonObject.takeOffEMinute = $(form_other).find("#takeOffEMinute").val();
					  	jsonObject.takeOffESecond = $(form_other).find("#takeOffESecond").val();			  
					  	jsonObject.areaCenterNDegree = $(form_other).find("#areaCenterNDegree").val();
					  	jsonObject.areaCenterNMinute = $(form_other).find("#areaCenterNMinute").val();
					  	jsonObject.areaCenterNSecond = $(form_other).find("#areaCenterNSecond").val();
					  	jsonObject.areaCenterEDegree = $(form_other).find("#areaCenterEDegree").val();
					  	jsonObject.areaCenterEMinute = $(form_other).find("#areaCenterEMinute").val();
					  	jsonObject.areaCenterESecond = $(form_other).find("#areaCenterESecond").val();
					  	jsonObject.operationDiameter = $(form_other).find("#operationDiameter").val();
					  
					 	myJson = JSON.stringify(jsonObject);
					  	console.log(myJson);
					  	$.ajax({
						 	type : "POST", 
						 	url : "/"+ system_name +"/operation/UpdateAerialPlanProcess",
						 	data : {
								data : myJson
						 	},
						 	success : function() {
								alert('修改航拍活動紀錄成功');
								action_obj.updateButton_click_Action(typeId);
						 	}
					  	});
					  	
			
						var fileData = new FormData(document.getElementById("aerialPlanForm_aerialPlan_sub"));				 
					    fileData.append("action","aerialPlan");
							 
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
	      		  text: "新增航拍計畫資料",
	              icon: "ui-icon-plus",
	              id: "addAerialPlanBtn_"+domId,
	              click: function (){ aerialPlan_obj.addOrUpdate('add') }	
	            },
			    {
		          text: "關閉",
		          icon: "ui-icon-closethick",
		          click: function() {
		             $( this ).dialog( "close" );
		          }
		 
		 		}],
			close : function() {
				aerialPlan_obj.removeReactComponent(domId);
			}
		});	
    	$("#addAerialPlanBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateAerialPlanBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.aerialPlanId!==prevState.aerialPlanId)){
    		$("#addAerialPlanBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateAerialPlanBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addAerialPlanBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateAerialPlanBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"aerialPlanDialog_"+this.props.domId+"_sub"},
        				React.createElement("div",  {id:"aerialPlanList_"+this.props.domId}),
        				React.createElement("div",  {id:"aerialPlanTabs_"+this.props.domId})
                    );
                   

    }

});

var aerialPlanList = React.createClass({
	getInitialState: function() {
        return {
        	state:undefined
        };
    },
    notify: function(obj){
    	this.setState({ 
    		state:obj.state["aerialPlan"]
    	});
    },
    componentWillUnmount(){
    	var table = $("#aerialPlanList_"+this.props.domId+"_sub").DataTable();
		table.destroy();
    },
    componentDidMount() {
    	var tableName = "#aerialPlanList_"+this.props.domId+"_sub";
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
			    url: "/"+ system_name +"/operation/QueryAerailPlansProcess",  
			    data: {  
			    	projectId : store_obj.projectId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	};
			    	for (i=0; i <json.length; i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';		        		
		        		myarray[i][1]=obj.hasOwnProperty("aerialPlanId")?obj.aerialPlanId:'';
		        		myarray[i][2]=obj.hasOwnProperty("usage")?obj.usage:'';
		        		myarray[i][3]=obj.aerialPlanStartDate+'~'+obj.aerialPlanEndDate;	        		
		        	};
			    	var table = $(tableName).DataTable();
			    	table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,1);
						var aerialPlanId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();
						alert('您選取了航拍計畫編號 :'+ aerialPlanId);
						action_obj.aerialPlanList_select_Action(aerialPlanId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
						action_obj.aerialPlanList_deselect_Action('');
					});
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
    	$(' .dataTables_scrollBody').height(100);
    },
    componentDidUpdate(prevProps, prevState){
    	var tableName = "#aerialPlanList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable();
		if(this.state.state!==prevState.state){
			if(this.state.state!=null && this.state.state!=undefined && this.state.state!=''){
				var row = table.row('.selected');
				var cell = table.cell( row ,0);
				cell.data(this.state.state).draw();
			}
		}
    },    
   
    render: function() {
        return  React.createElement("table",  {id:"aerialPlanList_"+this.props.domId+"_sub"},
        				React.createElement("thead",  {},
        					React.createElement("tr",  {},
        						React.createElement("th",  {},"狀態"),
        						React.createElement("th",  {},"航拍計畫編號"),
        						React.createElement("th",  {},"用途"),
        						React.createElement("th",  {},"作業日期及時間")
        					)
                        )
                    );
                   

    }

});

var aerialPlanTabs = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
    componentWillUnmount(){
    	$("#aerialPlanTabs_"+this.props.domId+"_sub").tabs('destroy');
    },
    componentDidMount() {
    	
    	$("#aerialPlanTabs_"+this.props.domId+"_sub").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});	
    	
    },
    componentDidUpdate(prevProps, prevState){
        
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"aerialPlanTabs_"+this.props.domId+"_sub"},
        			React.createElement("ul",  {},
        				React.createElement("li",  {},
        					React.createElement("a",  {href:"#tabs_plan_1"},"主要資訊")
                        ),
                        React.createElement("li",  {},
            				React.createElement("a",  {href:"#tabs_plan_2"},"使用設備資訊")
                        ),
                        React.createElement("li",  {},
                				React.createElement("a",  {href:"#tabs_plan_3"},"其他資訊")
                        )
                    ),
                    React.createElement("div",  {id:"tabs_plan_1"},
            				React.createElement("div",  {id:"aerialPlanForm_"+this.props.domId} )				 
                    ),
                    React.createElement("div",  {id:"tabs_plan_2"},
            				React.createElement("div",  {id:"aerialPlanEPList_"+this.props.domId} ),
            				React.createElement("div",  {id:"aerialPlanSteps_"+this.props.domId})
                    ),
                    React.createElement("div",  {id:"tabs_plan_3"},
            				React.createElement("div",  {id:"otherForm_"+this.props.domId} )				 
                    )
                );
                   

    }

});



