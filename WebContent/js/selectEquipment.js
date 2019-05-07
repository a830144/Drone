
var Steps = React.createClass({

    componentDidMount() {
        var wizard = $("#wizard").steps();
        wizard.steps("add", {
            title: "選取設備", 
            content: "<div id='firstStep-1'>first step-1</div>"
        });   
       wizard.steps("add", {
            title: "選取排外條件", 
            content: "<div id='firstStep-2'>first step-2</div>"
        });   
       wizard.steps("add", {
            title: "選取人員", 
            content: "<div id='firstStep-3'>first step-3</div>"
        });    
     
    }  ,

    render: function() {
        return  React.createElement("div",  {id:'wizard'},);
    }

});

var SelectConstruction = React.createClass({
    getInitialState: function() {
         return {
             checked: false,
             name: 'top secret!'};
     },
    
    handleChange: function(event) {
        updateState(event.target.value);
    },
   
    render: function() {
        return  React.createElement("select",  {name:"constructionType"},
                React.createElement("option",  {value: "A"},'無人飛機(Aircraft)'),
                React.createElement("option",  {value: "H"},'無人直昇機(Helicopter)'),
                React.createElement("option",  {value: "M"},'無人多旋翼機(Muti-Rotors)'),
                React.createElement("option",  {value: "O"},'其他Other')
        );
    }

});


var EquipmentTable = React.createClass({
    getDefaultProps: function() {
        return {
            type=''
        };
    },
    componentDidUpdate(){
        $("#ta").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
        $("#ta").DataTable({
            columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
		    "select":true,
            "data": this.props.names,
            "ordering": false,
            "ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/QueryEquipmentProcess",   
			    "data": {  
			        "type": this.props.type  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';
        				myarray[i][1]=obj.hasOwnProperty("productName")?obj.productName:'';		        			
        				myarray[i][2]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
        				myarray[i][3]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
        				myarray[i][4]=obj.hasOwnProperty("state")?obj.state:'';
		        	}
                    var t = $('#ta').DataTable();
		            t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,0);
			                var equipmentId = cell.data();
			                alert('您選取了設備編號 :'+ equipmentId);
		            });

			        return myarray;
			      }
			    ,
		        dataType: 'json'
			} 
        });
    },
    componentWillUnmount(){
        $("#ta").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#ta").DataTable({
            columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
		    "select":true,
            "data": this.props.names,
            "ordering": false
        });
    },
    render: function() {
        return  React.createElement("table",  {id:'ta'},
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '編號'),
                             React.createElement('th', {}, '設備型號'),
                             React.createElement('th', {}, '設備構型'),
                             React.createElement('th', {}, '設備ID取得日期'),
                             React.createElement('th', {}, '狀態')
               
            )));
  }

});


var FirstStep = React.createClass({
    getInitialState: function() {
         return {
             type: ''};
    },
    handleChange: function(event) {
        this.setState({ type: event.target.value });
    },
    render: function() {
        return  React.createElement("",  {},
                    React.createElement(Select,{onChange: this.handleChange},),
                    React.createElement(Table,{type:this.state.type},)
                );
    }

});

var container = document.getElementById('root');

ReactDOM.render(React.createElement(Steps, {}), container);

container = document.getElementById('firstStep-1');

ReactDOM.render(React.createElement(FirstStep, {}), container);

