

var container = document.getElementById('root');

var table = React.createClass({
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
			    "url": "/Drone/person/QueryLicenses",  
			    "data": {  
			        "id": 1  
			    }, 
			    "dataSrc": function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(4);
			    	}
			    	for(i=0;i<json.length;i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]='';
		        		myarray[i][1]=obj.hasOwnProperty("licenseId")?obj.licenseId:'';
		        		myarray[i][2]=obj.hasOwnProperty("gotDate")?obj.gotDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("type")?obj.type:'';
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



render: function() {
    var th1 = React.createElement('th', {id:'status'}, '狀態');
    var th2 = React.createElement('th', {id:'licenseId'}, '操作證類別編號');
    var th3 = React.createElement('th', {id:'gotDate'}, '操作證取得日期');
    var th4 = React.createElement('th', {id:'type'}, '操作證類別');
    return  React.createElement("table",  {id:'ta'},
            React.createElement("thead",  {},
                React.createElement("tr",  {},th1,th2,th3,th4
               
            )));
  }

});

ReactDOM.render(React.createElement(table, {}), container);


