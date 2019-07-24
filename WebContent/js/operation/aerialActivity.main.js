var aerialActivityDialog = React.createClass({
	getInitialState: function() {
        return {
        	aerialActivityId:null,
        	crud:'C', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		aerialActivityId:obj.aerialActivityId,
    		crud:obj.crud 
    	});
    },
    componentWillUnmount(){   	
    },
    componentDidMount() {
    	var dialog = "#aerialActivityDialog_"+this.props.domId+"_sub";
    	var formName = "#aerialActivityForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 700,
			width : 1350,
			modal : true,
			buttons : [
				{
				  text: "修改航拍活動資料",
		          icon: "ui-icon-pencil",
		          id: "updateAerialActivityBtn_"+domId,
		          click: function (){ aerialActivity_obj.addOrUpdate('update') }
	 		    },
	    		{
	      		  text: "新增航拍活動資料",
	              icon: "ui-icon-plus",
	              id: "addAerialActivityBtn_"+domId,
	              click: function (){ aerialActivity_obj.addOrUpdate('add') }	
	            },
			    {
		          text: "關閉",
		          icon: "ui-icon-closethick",
		          click: function() {
		             $( this ).dialog( "close" );
		          }
		 
		 		}],
			close : function() {
				aerialActivity_obj.removeReactComponent(domId);
			}
		});	
    	$("#addAerialActivityBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateAerialActivityBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$(dialog).dialog("open");
    },
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.aerialActivityId!==prevState.aerialActivityId)){
    		$("#addAerialActivityBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateAerialActivityBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addAerialActivityBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateAerialActivityBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"aerialActivityDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"aerialActivityList_"+this.props.domId}),
        			React.createElement("div",  {id:"aerialActivityTabs_"+this.props.domId})
        );
    }

});

var aerialActivityList = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
    componentDidMount() {
    	var tableName = "#aerialActivityList_"+this.props.domId+"_sub";
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
			    url: "/Drone/operation/QueryAerailActivitiesProcess",  
			    data: {  
			    	projectId : store_obj.projectId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for (i=0; i <json.length; i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]='';		        		
		        		myarray[i][1]=obj.hasOwnProperty("aerialPlanId")?obj.aerialPlanId:'';
		        		myarray[i][2]=obj.hasOwnProperty("aerialActivityId")?obj.aerialActivityId:'';
		        		myarray[i][3]=obj.aerialActivityStartDate+'~'+obj.aerialActivityEndDate;
		        	}
			    	var table = $(tableName).DataTable();
					table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,2);
						var aerialActivityId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();
						alert('您選取了航拍活動編號 :'+ aerialActivityId);
						action_obj.aerialActivityList_select_Action(aerialActivityId,state);
					});
					table.on( 'deselect', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,2);
						var aerialActivityId = cell.data();
						if ( type === 'row' ) {
							action_obj.aerialActivityList_deselect_Action(aerialActivityId);
						}
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
    componentWillUnmount(){
    	$("#aerialActivityList_"+this.props.domId+"_sub").unbind( "select" );
    	var table = $("#aerialActivityList_"+this.props.domId+"_sub").DataTable();
		table.destroy();
    },
    render: function() {
        return  React.createElement("table",  {id:"aerialActivityList_"+this.props.domId+"_sub"},
        				React.createElement("thead",  {},
        					React.createElement("tr",  {},
        						React.createElement("th",  {},"狀態"),
        						React.createElement("th",  {},"航拍計畫編號"),
        						React.createElement("th",  {},"航拍活動編號"),
        						React.createElement("th",  {},"航拍活動編號")
        					)
                        )
                    );
    }

});

var aerialActivityTabs = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
    componentDidMount() {
    	$("#aerialActivityTabs_"+this.props.domId+"_sub").tabs({
			  activate: function( event, ui ) {
				  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			  }
		});	
    },  
    componentDidUpdate(prevProps, prevState){
        
    }, 
    componentWillUnmount(){
    	$("#aerialActivityTabs_"+this.props.domId+"_sub").tabs('destroy');
    }, 
    
    render: function() {
        return  React.createElement("div",  {id:"aerialActivityTabs_"+this.props.domId+"_sub"},
        			React.createElement("ul",  {},
        				React.createElement("li",  {},
        					React.createElement("a",  {href:"#tabs_activity_1"},"主要資訊")
                        ),
                        React.createElement("li",  {},
            				React.createElement("a",  {href:"#tabs_activity_2"},"使用設備資訊")
                        )
                    ),
                    React.createElement("div",  {id:"tabs_activity_1"},
            				React.createElement("div",  {id:"aerialActivityForm_"+this.props.domId} )
                    ),
                    React.createElement("div",  {id:"tabs_activity_2"},
            				React.createElement("div",  {id:"aerialActivityEPList_"+this.props.domId} ),
            				React.createElement("div",  {id:"aerialActivitySteps_"+this.props.domId} )
                    )
                );
                   

    }

});



