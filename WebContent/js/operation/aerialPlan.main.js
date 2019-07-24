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
		          click: function (){ aerialPlan_obj.addOrUpdate('update') }
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
        };
    },
    notify: function(obj){
    	this.setState({  
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
			    url: "/Drone/operation/QueryAerailPlansProcess",  
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
		        		myarray[i][0]='';		        		
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



