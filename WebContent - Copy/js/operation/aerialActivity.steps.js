var aerialActivitySteps = React.createClass({
	getInitialState: function() {
        return {
        	stepHide:'true', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		stepHide:obj.stepHide
    	});
    },
    componentWillUnmount(){
    },

    componentDidMount() {
    	var stepsName = "#aerialActivitySteps_"+this.props.domId+"_sub";
    	var equipmentId =this.props.equipmentId;
        var stepsWizard = $(stepsName).steps({
        	onFinished: function (event, currentIndex)
            {
        		if(equipmentId ==""){
        			alert('您未完整設定資料,請重新選取');
        		}else{
        			action_obj.aerialActivitySteps_clickFinish_Action();
                    alert('您設定了一筆設備資料'); 
                    $(stepsName).addClass('hide-true').removeClass('hide-false');
        		}
        		
            }
        });

        stepsWizard.steps("add", {
            title: "選取設備", 
            content: "<div id='firstStep_"+this.props.domId+"'>first step</div>"
        });   

        stepsWizard.steps("add", {
            title: "選取人員", 
            content: "<div id='secondStep_"+this.props.domId+"'>second step</div>"
        }); 

        stepsWizard.steps("add", {
            title: "最終確認", 
            content: "<div id='thirdStep_"+this.props.domId+"'>third step</div>"
        });
    },
    componentDidUpdate(prevProps, prevState){
    	if(this.state.stepHide!==prevState.stepHide && this.state.stepHide==='false'){
    		$("#aerialActivitySteps_"+this.props.domId+"_sub").addClass('hide-false').removeClass('hide-true');    		
    	};
    },    
    render: function() {
        return  React.createElement("div",  {id:"aerialActivitySteps_"+this.props.domId+"_sub",className:'hide-true'});
    }

});
var firstStep = React.createClass({
	getInitialState: function() {
        return {
       	   aerialPlanId:'',
        };
    },
    notify: function(obj){
       this.setState({ 
       	  aerialPlanId: obj.aerialPlanId 
       });
    },
    render: function() {
        return  React.createElement("div",  {id:"firstStep_"+this.props.domId},
                    React.createElement(equipmentList,{aerialPlanId:this.state.aerialPlanId})
                );
    }

});


var equipmentList = React.createClass({
	getDefaultProps: function() {
        return {
        	aerialPlanId:'',
        };
    },
	getInitialState: function() {
        return {
        	aerialPlanId:store_obj.aerialPlanId, 
        };
    },
    componentDidUpdate(prevProps, prevState){
    	var tableName = "#equipmentList";
        if(this.props.aerialPlanId!==prevProps.aerialPlanId){
           $(tableName).unbind( "select" );
           var table = $(tableName).DataTable();
           table.destroy();
           $(tableName).DataTable({
                columnDefs: [ {
    				orderable: false,
    				className: 'select-checkbox',
    				targets:   0
    			},{	
				    className: 'dt-center',
				    targets: '_all'
				},{
	                targets: [ 3 ],
	                visible: false
	            }],
                select: {
                	style:    'os',
                	selector: 'td:first-child'
                },
                order: [[ 1, 'asc' ]],
                data: '',
                ordering: false,
                ajax: {
                	type: "POST",
                	url: "/"+system_name +"/equipment/QueryEquipmentProcess",   
                	data: {  
                		aerialPlanId: this.props.aerialPlanId 
                	}, 
                	dataSrc: function ( json ) {
                		var myarray=new Array(json.length);
                		for(i=0; i <json.length; i++){
                			myarray[i]=new Array(6);
                		}
                		for(i=0;i<json.length;i++){
                			var obj = $.parseJSON(json[i]);
                			myarray[i][0]='';
                			myarray[i][1]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';
                			myarray[i][2]=obj.hasOwnProperty("productName")?obj.productName:'';		        			
                			myarray[i][3]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
                			myarray[i][4]=obj.hasOwnProperty("sendDate")?obj.sendDate:'';
                			myarray[i][5]=obj.hasOwnProperty("state")?obj.state:'';
                			if(obj.hasOwnProperty("constructionType")){
            					switch(obj.constructionType){
            					case 'A':
            						myarray[i][6] = '無人飛機';
            						break;
            					case 'H':
            						myarray[i][6] = '無人直昇機';
            						break;
            					case 'M':
            						myarray[i][6] = '無人多旋翼機';
            						break;
            					case 'O':
            						myarray[i][6] = '其它';
            						break;
            					default:
            						myarray[i][6] = '';
            					}
            				}
                		}
                		var t = $(tableName).DataTable();
                		t.on( 'select', function ( e, dt, type, indexes ) {		            		
			                var cell =t.cell( indexes ,1);
			                var equipmentId = cell.data();
			                action_obj.equipmentList_select_Action(equipmentId);
                		});
                		t.on( 'deselect', function ( e, dt, type, indexes ) {
                			var cell =t.cell( indexes ,1);
            				var equipmentId = cell.data();
                			if ( type === 'row' ) {				                				
                				action_obj.equipmentList_deselect_Action(equipmentId);
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
    	var tableName = "#equipmentList";
        $(tableName).unbind( "select" );
        var table = $(tableName).DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#equipmentList").DataTable({
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
            ordering: false 
        });
    },
    render: function() {
        return  React.createElement("table",  {id:"equipmentList" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, '編號'),
                             React.createElement('th', {}, '設備型號英文'),
                             React.createElement('th', {}, '設備構型'),
                             React.createElement('th', {}, '設備ID取得日期'),
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '設備型號')
               
            )));
  }

});

var secondStep = React.createClass({
	getInitialState: function() {
        return {
       	 	equipmentId: '' 
        };
    },
    notify: function(obj){
       this.setState({ 
    	   	aerialPlanId: obj.aerialPlanId,
       		equipmentId: obj.equipmentId 
       });
       
    },

    render: function() {
        return  React.createElement("div",  {id:"secondStep_"+this.props.domId},
                    React.createElement(personList,{aerialPlanId: this.state.aerialPlanId,equipmentId:this.state.equipmentId})
                );
    }

});
var personList = React.createClass({
	getDefaultProps: function() {
        return {
        	aerialPlanId:'',
        	equipmentId:''
        };
    },
    getInitialState: function() {
         return {
        	 aerialPlanId:store_obj.aerialPlanId,
        	 equipmentId: store_obj.equipmentId 
         };
    },
    componentDidUpdate(prevProps, prevState){
    	var tableName = "#personList";
        if((this.props.equipmentId!==prevProps.equipmentId)&&(this.props.equipmentId!=='-'&&(this.props.equipmentId!==null))){
            $(tableName).unbind( "select" );
            var table = $(tableName).DataTable();
            table.destroy();
            $(tableName).DataTable({
                columnDefs: [
                	{	
    				    orderable: false,
                        className: 'select-checkbox',
                        targets:   0
    				},{	
    				    className: 'dt-center',
    				    targets: '_all'
    				}],
		        select: {
                    style: 'multi',
                    selector: 'td:first-child'
                },
                ordering: false,
                ajax: {
				    type: "POST",
			        url: "/"+system_name +"/person/QueryPersonProcess",   
			        data: {
			        	aerialPlanId : this.props.aerialPlanId,
			            equipmentId: this.props.equipmentId
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

                        var t = $(tableName).DataTable();
		                t.on( 'select', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
                            var personId = cell.data();
                            action_obj.personList_select_Action(personId);                            
                        });
                        t.on( 'deselect', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var personId = cell.data();
			                action_obj.personList_deselect_Action(personId);
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
    	var tableName = "#personList";
        $(tableName).unbind( "select" );
        var table = $(tableName).DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#personList").DataTable({
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
        store_obj.personId_1='';
        store_obj.personId_2='';
    },
    render: function() {
        return  React.createElement("table",  {id:'personList'},
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



var thirdStep = React.createClass({
	getInitialState: function() {
        return {
       	 	equipmentId: '',
       	 	personId_1:'',
       	 	personId_2:''
        };
    },
    notify: function(obj){
       this.setState({ 
    	    equipmentId:obj.equipmentId,
      	 	personId_1:obj.personId_1,
      	 	personId_2:obj.personId_2
       });
    },
	render: function() {
        return  React.createElement("div",  {id:"thirdStep_"+this.props.domId},
        			React.createElement(equipmentForm,{equipmentId: this.state.equipmentId}),
        			React.createElement(personForm_1,{personId_1 : this.state.personId_1}),
        			React.createElement(personForm_2,{personId_2 : this.state.personId_2})
                );
    }

});
var equipmentForm = React.createClass({
	getDefaultProps: function() {
        return {
        	equipmentId:''
        };
    },
    getInitialState: function() {
         return {
             equipmentId:store_obj.equipmentId
         };
    },
    componentWillUnmount(){   
    },
    componentDidMount() {
    },
    componentDidUpdate(prevProps, prevState){
    	var form = "#equipmentForm";
        if((this.props.equipmentId!==prevProps.equipmentId)&&(this.props.equipmentId!=='-')&&(this.props.equipmentId!==null)){
            $.ajax({
			    url:"/"+system_name +"/equipment/ViewEquipmentProcess",
			    type:"POST",
			    data:{
			    	id:this.props.equipmentId
			    },
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $(form).find("#" + key).val(value);
				    });							     
			        store_obj.aerialActivityEPList.equipmentId = $(form).find("#equipmentId").val();
			        store_obj.aerialActivityEPList.manufactoryName = $(form).find("#manufactoryName").val();
			        store_obj.aerialActivityEPList.constructionType = $(form).find("#constructionType").val();
			        store_obj.aerialActivityEPList.productName = $(form).find("#productName").val();
			        store_obj.aerialActivityEPList.airTime = $(form).find("#airTime").val();
			    }
		    })
		    
        	
        }
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"equipmentForm"},
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

var personForm_1 = React.createClass({
	getDefaultProps: function() {
        return {
        	personId_1:''
        };
    },
    getInitialState: function() {
         return {
             personId_1:store_obj.personId_1 
         };
    },
    componentWillUnmount(){   
    },
    componentDidMount() {
    },
    componentDidUpdate(prevProps, prevState){
    	var form = "#personForm_1";
        if(this.props.personId_1!==prevProps.personId_1){
        	if((this.props.personId_1!=='-')&&(this.props.personId_1!==null)){
            	$.ajax({
            		url:"/"+system_name +"/person/ViewPersonProcess",
            		type:"POST",
            		data:{
            			id : this.props.personId_1
            		},
            		dataType: "json",
            		success: function(data){				  
            			var obj = data;
            			$.each(obj, function(key, value) {
            				$(form).find("#" + key).val(value);
            			});				
            			store_obj.aerialActivityEPList.personId_1 = $(form).find("#personId").val();
            		}
            	});
        	}else{
        		$(form).find("#personId").val("");
        		$(form).find("#name").val("");
        		$(form).find("#idNumber").val("");
        		$(form).find("#dateOfBirth").val("");
        		store_obj.aerialActivityEPList.personId_1 = "";
        	}
		    
        }
	},   
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_1"},
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

var personForm_2 = React.createClass({
	getDefaultProps: function() {
        return {
        	personId_2:''
        };
    },
    getInitialState: function() {
         return {
             personId_2:store_obj.personId_2, 
         };
    },
    componentWillUnmount(){  
    },
    componentDidMount() {
    },
    componentDidUpdate(prevProps, prevState){
    	var form = "#personForm_2"; 
        if(this.props.personId_2!==prevProps.personId_2){
        	if((this.props.personId_2!=='-')&&(this.props.personId_2!==null)){
        		$.ajax({
        			type:"POST",
        			url:"/"+system_name +"/person/ViewPersonProcess",			    
        			data:{
        				id : this.props.personId_2
        			},
        			dataType: "json",
        			success: function(data){				  
        				var obj = data;
        				$.each(obj, function(key, value) {
        					$(form).find("#" + key).val(value);
        				});				
        				store_obj.aerialActivityEPList.personId_2 = $(form).find("#personId").val();
        			}
        		})
        	}else{
        		$(form).find("#personId").val("");
        		$(form).find("#name").val("");
        		$(form).find("#idNumber").val("");
        		$(form).find("#dateOfBirth").val("");
        		store_obj.aerialActivityEPList.personId_2 = "";
        	}
		    
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_2"},
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



