
var Steps_activity = React.createClass({
    componentDidMount() {
        var wizard = $("#wizard-activity").steps({
        	onFinished: function (event, currentIndex)
            {
                var t = $('#single-aerialActivity-table-3-1').DataTable();
                t.row.add( [
                    $("#equipmentForm-activity").find("#equipmentId").val(),
                    $("#equipmentForm-activity").find("#manufactoryName").val(),
                    $("#equipmentForm-activity").find("#constructionType").val(),
                    $("#equipmentForm-activity").find("#productName").val(),
                    $("#equipmentForm-activity").find("#airTime").val(),
                    '',
                    $("#personForm_1-activity").find("#personId").val(),
                    $("#personForm_2-activity").find("#personId").val(),
                ] ).draw( false );
                alert('您設定了一筆設備資料');
                
            }
        });
        wizard.steps("add", {
            title: "選取設備", 
            content: "<div id='firstStep-activity'>first step</div>"
        });      
        wizard.steps("add", {
            title: "選取人員", 
            content: "<div id='secondStep-activity'>second step</div>"
        });  
        wizard.steps("add", {
            title: "最終確認", 
            content: "<div id='thirdStep-activity'>third step</div>"
        });
    },
    render: function() {
        return  React.createElement("div",  {id:'wizard-activity'});
    }

});

var EquipmentTable_activity = React.createClass({
	getInitialState: function() {
        return {
        	aerialPlanId:aerialActivity_obj.aerialPlanId, 
        };
    },
    handleMouseClick: function(){
       this.setState({ aerialPlanId:aerialActivity_obj.aerialPlanId });
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
            data: '',
            ordering: false,
            ajax: {
				type: "POST",
			    url: "/Drone/equipment/QueryEquipmentProcess",   
			    data: {  
			        aerialPlanId: this.state.aerialPlanId 
			    }, 
			    dataSrc: function ( json ) {
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
                            aerialActivity_obj.equipmentId=equipmentId;
                    });
                    t.on( 'deselect', function ( e, dt, type, indexes ) {
			            if ( type === 'row' ) {				
			            	aerialActivity_obj.equipmentId='';
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
            data: '',
            ordering: false,
            ajax: {
				type: "POST",
			    url: "/Drone/equipment/QueryEquipmentProcess",   
			    data: {  
			        aerialPlanId: '1'  
			    }, 
			    dataSrc: function ( json ) {
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
			                aerialActivity_obj.equipmentId=equipmentId;
                    });
                    t.on( 'deselect', function ( e, dt, type, indexes ) {
			            if ( type === 'row' ) {				
			            	aerialActivity_obj.equipmentId='';
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
        	 aerialPlanId:aerialActivity_obj.aerialPlanId,
        	 equipmentId: aerialActivity_obj.equipmentId 
         };
    },
    handleMouseClick: function(){
        this.setState({ 
        	aerialPlanId:aerialActivity_obj.aerialPlanId , 
        	equipmentId: aerialActivity_obj.equipmentId 
        });
    },
    componentDidUpdate(prevProps, prevState){
        if(this.state.equipmentId!==prevState.equipmentId){
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
                    style: 'multi',
                    selector: 'td:first-child'
                },
                ordering: false,
                ajax: {
				    type: "POST",
			        url: "/Drone/person/QueryPersonProcess",   
			        data: {
			        	aerialPlanId : this.state.aerialPlanId,
			            equipmentId: this.state.equipmentId
			        }, 
			        dataSrc: function ( json ) {
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
                            if(aerialActivity_obj.personId_1==''||aerialActivity_obj.personId_1==null){
                                aerialActivity_obj.personId_1=personId;
                            }else{
                                aerialActivity_obj.personId_2=personId;
                            }
                            
                        });
                        t.on( 'deselect', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var personId = cell.data();
                            if(aerialActivity_obj.personId_1==personId){
                                aerialActivity_obj.personId_1='';
                            }else if(aerialActivity_obj.personId_2==personId){
                                aerialActivity_obj.personId_2='';
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
        var table2 = $("#ta2-activity").DataTable();
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
            data: this.props.names,
            ordering: false
        });
        aerialActivity_obj.personId_1='';
        aerialActivity_obj.personId_2='';
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
    render: function() {
        return  React.createElement("div",  {id:'secondStepContainer-activity'},
                    React.createElement(PersonTable_activity,{},)
                );
    }

});

var EquipmentConfirm_activity = React.createClass({
    getInitialState: function() {
         return {
             equipmentId:aerialActivity_obj.equipmentId
         };
    },
    handleMouseClick: function(){
        this.setState({ 
        	equipmentId:aerialActivity_obj.equipmentId 
        });
    },
    componentWillUnmount(){
        window.removeEventListener("click", this.handleMouseClick);   
    },
    componentDidMount() {
        window.addEventListener("click", this.handleMouseClick);
    },
    componentDidUpdate(prevProps, prevState){
        if(this.state.equipmentId!==prevState.equipmentId){
            $.ajax({
			    url:"/Drone/equipment/ViewEquipmentProcess",
			    type:"POST",
			    data:{
			    	id:this.state.equipmentId
			    },
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#equipmentForm-activity").find("#" + key).val(value);
				    });				

			    }
		    })
        }
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"equipmentForm-activity"},
                    React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"設備ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"equipmentId",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"製造廠名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"manufactoryName",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"構造分類"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"constructionType",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"型式名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"productName",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"滯空時間"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"airTime",readOnly:"readOnly"})
                                )
                            )
                        )
                    )
        );
    }
});

var PersonConfirm_1_activity = React.createClass({
    getInitialState: function() {
         return {
             personId_1:aerialActivity_obj.personId_1 
         };
    },
    handleMouseClick: function(){
         this.setState({
        	 personId_1:aerialActivity_obj.personId_1 
         });
    },
    componentWillUnmount(){
        window.removeEventListener("click", this.handleMouseClick);   
    },
    componentDidMount() {
        window.addEventListener("click", this.handleMouseClick);
    },
    componentDidUpdate(prevProps, prevState){
        if(this.state.personId_1!==prevState.personId_1){
            $.ajax({
			    url:"/Drone/person/ViewPersonProcess",
			    type:"POST",
			    data:{
			    	id : this.state.personId_1
			    },
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#personForm_1-activity").find("#" + key).val(value);
				    });				

			    }
		    })
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_1-activity"},
                    React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"姓名"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"name",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"身分證號碼"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"idNumber",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"出身日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"dateOfBirth",readOnly:"readOnly"})
                                )
                            )
                        )
                    )
        );
    }
});

var PersonConfirm_2_activity = React.createClass({
    getInitialState: function() {
         return {
             personId_2:aerialActivity_obj.personId_2, 
         };
    },
    handleMouseClick: function(){
         this.setState({ 
        	 personId_2:aerialActivity_obj.personId_2 
         });
    },
    componentWillUnmount(){
         window.removeEventListener("click", this.handleMouseClick);   
    },
    componentDidMount() {
         window.addEventListener("click", this.handleMouseClick);
    },
    componentDidUpdate(prevProps, prevState){
        if(this.state.personId_2!==prevState.personId_2){
            $.ajax({
            	type:"POST",
			    url:"/Drone/person/ViewPersonProcess",			    
			    data:{
			    	id : this.state.personId_2
			    },
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#personForm_2-activity").find("#" + key).val(value);
				    });				

			    }
		    })
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_2-activity"},
                    React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"姓名"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"name",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"身分證號碼"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"idNumber",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"出身日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"dateOfBirth",readOnly:"readOnly"})
                                )
                            )
                        )
                    )
        );

    }

});

var ThirdStep_activity = React.createClass({
    render: function() {
        return  React.createElement("div",  {id:'ThirdStepContainer-activity'},
        			React.createElement(EquipmentConfirm_activity,{},),
        			React.createElement(PersonConfirm_1_activity,{},),
        			React.createElement(PersonConfirm_2_activity,{},)
                );
    }

});

