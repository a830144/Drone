

var Steps = React.createClass({

    componentDidMount() {
        var wizard = $("#wizard").steps();
        wizard.steps("add", {
            title: "HTML code2", 
            content: "<div id='firstStep-1'>first step-1</div><div id='firstStep-2'>first step-2</div>"
        });   
       wizard.steps("add", {
            title: "HTML code2", 
            content: "This is a test2"
        });   
       wizard.steps("add", {
            title: "HTML code2", 
            content: "This is a test2"
        });    
     
    },

     componentWillUnmount(){

        
    },


    render: function() {
    return  React.createElement("div",  {id:'wizard'},
            );
  }

});



var Table = React.createClass({
    getInitialState: function() {
         return {
             change:''};
    },
    componentDidUpdate(){
        $( "#ta").unbind( "select" );
        var table = $('#ta').DataTable();
        table.destroy();
        $("#ta").DataTable({
            columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
			"select":true,
           data: this.props.names,
           ordering: false,
           "ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/QueryEquipmentProcess",   
			    "data": {  
			        "type": this.props.change  
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
                    //registerLicenseListViewEvent();
                    var t = $('#ta').DataTable();
		            t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var licenseId = cell.data();
			                alert('您選取了證照編號 :'+ licenseId);
		            });

			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}
        });
    },

    componentDidMount() {
        $("#ta").DataTable({
            columnDefs: [
				{	
				    className: 'dt-center',
				    targets: '_all'
				}],
			"select":true,
           data: this.props.names,
           ordering: false,
           "ajax": {
				"type": "POST",
			    "url": "/Drone/equipment/QueryEquipmentProcess",   
			    "data": {  
			        "id": window.viewId  
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
                    //registerLicenseListViewEvent();
                    var t = $('#ta').DataTable();
		            t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var licenseId = cell.data();
			                alert('您選取了證照編號 :'+ licenseId);
		            });

			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}
        });
    }  ,


    componentWillUnmount(){
        $( "#ta").unbind( "select" );
        var table = $('#ta').DataTable();
        table.destroy();
        
    },



    render: function() {
        var th1 = React.createElement('th', {}, '編號');
        var th2 = React.createElement('th', {}, '設備型號');
        var th3 = React.createElement('th', {}, '設備構型');
        var th4 = React.createElement('th', {}, '設備ID取得日期');
        var th5 = React.createElement('th', {}, '狀態');
        return  React.createElement("table",  {id:'ta'},
            React.createElement("thead",  {},
                React.createElement("tr",  {},th1,th2,th3,th4,th5
               
            )));
  }

});
//module.exports = table;
var Select = React.createClass({
    getInitialState: function() {
         return {
             checked: false,
             name: 'top secret!'};
     },
    
    componentWillUnmount(){

        
    },
   
    render: function() {

    return  React.createElement("select",  {name:"constructionType",onChange: this.props.onChange},
                React.createElement("option",  {value: "A"},'無人飛機(Aircraft)'),
                React.createElement("option",  {value: "H"},'無人直昇機(Helicopter)'),
                React.createElement("option",  {value: "M"},'無人多旋翼機(Muti-Rotors)'),
                React.createElement("option",  {value: "O"},'其他Other')
            );
  }

});
//module.exports = select;
var First = React.createClass({
    getInitialState: function() {
         return {
             changeId:''};
    },
     handleChange: function(event) {
        
        //updateState(event.target.value);
        //window.viewId = event.target.value;
        this.setState({ changeId: event.target.value });
    },

    render: function() {

    return  React.createElement("div",  {id:'parent'},
                React.createElement(Select,{onChange: this.handleChange},),
                React.createElement(Table,{change: this.state.changeId},));
    }

});

var container = document.getElementById('root');

ReactDOM.render(React.createElement(Steps, {}), container);

container = document.getElementById('firstStep-1'); 

ReactDOM.render(React.createElement(First, {}), container);

// container = document.getElementById('firstStep-2');

// ReactDOM.render(React.createElement(Table, {}), container);