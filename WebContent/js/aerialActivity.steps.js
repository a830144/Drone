
var Steps_activity = React.createClass({
    componentDidMount() {
        var wizard = $("#wizard-activity").steps({

        });
        wizard.steps("add", {
            title: "選取設備", 
            content: "<div id='firstStep-activity'>first step</div>"
        });      
        wizard.steps("add", {
            title: "選取人員", 
            content: "<div id='secondStep-activity'>second step</div>"
        });  
     
    },
    render: function() {
        return  React.createElement("div",  {id:'wizard-activity'});
    }

});

var EquipmentTable_activity = React.createClass({
	getInitialState: function() {
        return {
        	aerialPlanId:window.aerialPlanId, 
        };
    },
    handleMouseClick: function(){
       this.setState({ aerialPlanId:window.aerialPlanId });
    },

    componentDidUpdate(prevProps, prevState){
      if(this.state.aerialPlanId!==prevState.aerialPlanId){
        $("#ta-activity").unbind( "select" );
        var table = $("#ta-activity").DataTable();
        table.destroy();
        $("#ta-activity").DataTable({
            columnDefs: [ {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
             } ],
            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            order: [[ 1, 'asc' ]],
            "data": '',
            "ordering": false,
            "ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/QueryEquipmentProcess",   
			    "data": {  
			        "aerialPlanId": this.state.aerialPlanId 
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for(i=0; i <json.length; i++){
			    	    myarray[i]=new Array(5);
			    	}
			    	for(i=0;i<json.length;i++){
                        var obj = $.parseJSON(json[i]);
                        myarray[i][0]='';
		        		myarray[i][1]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';
        				myarray[i][2]=obj.hasOwnProperty("productName")?obj.productName:'';		        			
        				myarray[i][3]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
        				myarray[i][4]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
        				myarray[i][5]=obj.hasOwnProperty("state")?obj.state:'';
		        	}
                    var t = $('#ta-activity').DataTable();
		            t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var equipmentId = cell.data();
                            window.equipmentId=equipmentId;
                    });
                    t.on( 'deselect', function ( e, dt, type, indexes ) {
			            if ( type === 'row' ) {				
                            window.equipmentId='';
		                }
		            });
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
      }
    },
    componentWillUnmount(){
    	window.removeEventListener("click", this.handleMouseClick);
        $("#ta-activity").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
    },
    componentDidMount() {
    	window.addEventListener("click", this.handleMouseClick);
        $("#ta-activity").DataTable({
            columnDefs: [ {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
             } ],
            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            order: [[ 1, 'asc' ]],
            "data": '',
            "ordering": false,
            "ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/QueryEquipmentProcess",   
			    "data": {  
			        "aerialPlanId": '1'  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for(i=0; i <json.length; i++){
			    	    myarray[i]=new Array(5);
			    	}
			    	for(i=0;i<json.length;i++){
                        var obj = $.parseJSON(json[i]);
                        myarray[i][0]='';
		        		myarray[i][1]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';
        				myarray[i][2]=obj.hasOwnProperty("productName")?obj.productName:'';		        			
        				myarray[i][3]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
        				myarray[i][4]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
        				myarray[i][5]=obj.hasOwnProperty("state")?obj.state:'';
		        	}
                    var t = $('#ta-activity').DataTable();
		            t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var equipmentId = cell.data();
                            window.equipmentId=equipmentId;
                    });
                    t.on( 'deselect', function ( e, dt, type, indexes ) {
			            if ( type === 'row' ) {				
                            window.equipmentId='';
		                }
		            });
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
    },
    render: function() {
        return  React.createElement("table",  {id:'ta-activity' },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, '編號'),
                             React.createElement('th', {}, '設備型號'),
                             React.createElement('th', {}, '設備構型'),
                             React.createElement('th', {}, '設備ID取得日期'),
                             React.createElement('th', {}, '狀態')
               
            )));
  }

});


var FirstStep_activity = React.createClass({
    render: function() {
        return  React.createElement("div",  {id:'firstStepContainer-activity'},
                    React.createElement(EquipmentTable_activity,{},)
                );
    }

});

var PersonTable_activity = React.createClass({
    getInitialState: function() {
         return {
             operationLimit:window.operationLimit, 
         };
    },
    handleMouseClick: function(){
        this.setState({ operationLimit: window.operationLimit });
    },
    componentDidUpdate(prevProps, prevState){
        if(this.state.operationLimit!==prevState.operationLimit){
            $("#ta2-activity").unbind( "select" );
            var table2 = $("#ta2-activity").DataTable();
            table2.destroy();
            $("#ta2-activity").DataTable({
                columnDefs: [
				{	
				    orderable: false,
                    className: 'select-checkbox',
                    targets:   0
				}],
		        select: {
                    'style': 'multi',
                    selector: 'td:first-child'
                },
                "data": this.props.equipmentId,
                "ordering": false,
                "ajax": {
				    "type": "POST",
			        "url": "/Drone/person/QueryPersonProcess",   
			        "data": {  
			           "operationLimit": this.state.operationLimit
			        }, 
			        "dataSrc": function ( json ) {
			    	    var myarray=new Array(json.length);
	    			    for (i=0; i <json.length; i++){
	    	   			    myarray[i]=new Array(4);
	    			    }
	    			    for(i=0;i<json.length;i++){
                            var obj = $.parseJSON(json[i]);
                            myarray[i][0]='';
        				    myarray[i][1]=obj.hasOwnProperty("personId")?obj.personId:'';
        				    myarray[i][2]=obj.hasOwnProperty("name")?obj.name:'';		        			
        				    myarray[i][3]=obj.hasOwnProperty("sex")?obj.sex:'';
        				    myarray[i][4]=obj.hasOwnProperty("status")?obj.status:'';
        			    }

                        var t = $('#ta2-activity').DataTable();
		                t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
                            var personId = cell.data();
                            if(window.personId_1==''||window.personId_1===null){
                                window.personId_1=personId;
                            }else{
                                window.personId_2=personId;
                            }
                            
                        });
                        t.on( 'deselect', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var personId = cell.data();
                            if(window.personId_1==personId){
                                window.personId_1='';
                            }else if(window.personId_2==personId){
                                window.personId_2='';
                            }
		                });

			        return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
        }
    },
    componentWillUnmount(){
        window.removeEventListener("click", this.handleMouseClick);
        $("#ta2-activity").unbind( "select" );
        var table3 = $("#ta2-activity").DataTable();
        table2.destroy();
    },
    componentDidMount() {
        window.addEventListener("click", this.handleMouseClick);
        $("#ta2-activity").DataTable({
            columnDefs: [
				{	
				    orderable: false,
                    className: 'select-checkbox',
                    targets:   0
				}],
		    select: {
                style:    'os',
                selector: 'td:first-child'
            },
            "data": this.props.names,
            "ordering": false
        });
        window.personId_1='';
        window.personId_2='';
    },
    render: function() {
        return  React.createElement("table",  {id:'ta2-activity'},
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, '人員內部ID'),
                             React.createElement('th', {}, '姓名'),
                             React.createElement('th', {}, '具備操作證類別'),
                             React.createElement('th', {}, '狀態')
               
            )));
  }

});

var SecondStep_activity = React.createClass({
    getInitialState: function() {
         return {
             type: '',
             id: ''
         };
    },

    render: function() {
        return  React.createElement("div",  {id:'secondStepContainer-activity'},
                    React.createElement(PersonTable_activity,{equipmentId:window.equipmentId},)
                );
    }

});



