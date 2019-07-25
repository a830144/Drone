var aerialPlanSteps = React.createClass({
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
    	var stepsName = "#aerialPlanSteps_"+this.props.domId+"_sub";
        var wizard = $(stepsName).steps({
        	onFinished: function (event, currentIndex)
            {
        		action_obj.aerialPlanSteps_clickFinish_Action();
                alert('您設定了一筆設備資料'); 
                $(stepsName).addClass('hide-true').removeClass('hide-false');
            }
        });
        wizard.steps("add", {
            title: "選取設備", 
            content: "<div id='p_firstStep_"+this.props.domId+"'>first step</div>"
        });      
        wizard.steps("add", {
            title: "例外排除條件", 
            content: "<div id='p_secondStep_"+this.props.domId+"'>second step</div>"
        });  
        wizard.steps("add", {
            title: "選取人員", 
            content: "<div id='p_thirdStep_"+this.props.domId+"'>third step</div>"
        });
        wizard.steps("add", {
            title: "最終確認", 
            content: "<div id='p_fourthStep_"+this.props.domId+"'>fourth step</div>"
        });
    },
    componentDidUpdate(prevProps, prevState){
    	if(this.state.stepHide!==prevState.stepHide && this.state.stepHide==='false'){
    		$("#aerialPlanSteps_"+this.props.domId+"_sub").addClass('hide-false').removeClass('hide-true');
    		
    	};
    },    
    render: function() {
        return  React.createElement("div",  {id:"aerialPlanSteps_"+this.props.domId+"_sub",className:'hide-true'});
    }

});
var p_firstStep = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    onConstructionChange: function(event) {
        this.setState({ type: event.type });
    },

    notify: function(obj){
       this.setState({  
       });
    },
    render: function() {
        return  React.createElement("div",  {id:"p_firstStep_"+this.props.domId},
        			React.createElement(selectConstruction,{onConstructionChange: this.onConstructionChange}),
                    React.createElement(p_equipmentList,{type:this.state.type })
                );
    }

    


});


var selectConstruction = React.createClass({
	getInitialState: function() {
        return {
            type: '',
        };
    },
    handleChange: function(event){
    	this.setState({ type: event.target.value },function(){
    		this.props.onConstructionChange({ type: this.state.type })     		
    	});   	
    },
    render: function() {
        return  React.createElement("select",  {name:"constructionType",onChange: this.handleChange},
                React.createElement("option",  {value: "A"},'無人飛機(Aircraft)'),
                React.createElement("option",  {value: "H"},'無人直升機(Helicopter)'),
                React.createElement("option",  {value: "M"},'無人多旋翼機(Muti-Rotors)'),
                React.createElement("option",  {value: "O"},'其他Other')
        );
    }

});

var p_equipmentList = React.createClass({
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
    	var tableName = "#p_equipmentList";
    	if(this.props.type!==prevProps.type){
    		$(tableName).unbind( "select" );
    		var table = $(tableName).DataTable();
    		table.destroy();
    		$(tableName).DataTable({
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
    					type: this.props.type 
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
    					var t = $(tableName).DataTable();
    					t.on( 'select', function ( e, dt, type, indexes ) {		            		
			                var cell =t.cell( indexes ,1);
			                var equipmentId = cell.data();
			                cell =t.cell( indexes ,3);
			                var constructionType = cell.data();
			                action_obj.equipmentAPList_select_Action(equipmentId,constructionType);
    					});
    					t.on( 'deselect', function ( e, dt, type, indexes ) {
    						if ( type === 'row' ) {				
    							var cell =t.cell( indexes ,1);
    							var equipmentId = cell.data();
    							action_obj.equipmentAPList_deselect_Action(equipmentId);
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
        $("#p_equipmentList").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#p_equipmentList").DataTable({
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
        return  React.createElement("table",  {id:'p_equipmentList' },
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

var p_secondStep = React.createClass({
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
        return  React.createElement("div",  {id:'p_secondStep_'+this.props.domId},
                    React.createElement(operationLimitCheckBox,{aerialPlanId: this.state.aerialPlanId,equipmentId:this.state.equipmentId})
                );
    }

});

var operationLimitCheckBox = React.createClass({
    getDefaultProps: function() {
        return {
            equipmentId:'',
        };
    },
    componentDidMount() {
        $("input[type='radio']").checkboxradio(); 
        $("input[type='checkbox']").checkboxradio();   
    },
    handleClick: function(event) {
        if(event.target.value==='y'){
        	action_obj.operationLimit_check_Action(true);
        }else{
            $("input[type='checkbox']").prop("checked", false ).checkboxradio('refresh');
            action_obj.operationLimit_check_Action(false);
        }
    },
    
    render: function() {
        return  React.createElement("fieldset",  {id:'operationLimitCheckBox'},
                         React.createElement("legend",  {}, "例外排除項目"),
                         React.createElement("label",  {htmlFor:'operationlimit-y'} ,'是'),
                         React.createElement('input', {onClick:this.handleClick, type:'radio', name:'operationlimit-radio', id:'operationlimit-y',value:'y'}),
                         React.createElement("label",  {htmlFor:'operationlimit-n'} ,'否'),
                         React.createElement('input', {onClick:this.handleClick,type:'radio', name:'operationlimit-radio', id:'operationlimit-n',value:'n'}),
                         React.createElement("br",  {}  ),
                         React.createElement("div",  {id:'limitCheckboxContainer'} , 
                            React.createElement("label",  {htmlFor:'checkbox-1'},'A. 遙控無人機飛航活動之實際高度不得逾距地面或水面四百呎'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-1', id:'checkbox-1',value:'1'}),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-2'},'B. 不得以遙控無人機投擲或噴灑任何物件'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-2', id:'checkbox-2',value:'2'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-3'},'C. 不得裝載依第四十三條第三項公告之危險物品。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-3', id:'checkbox-3',value:'3'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-4'},'D. 依第九十九條之十七所定規則之操作限制：(char)XXXXXXXXXX'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-4', id:'checkbox-4',value:'4'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-5'},'E. 不得於人群聚集或室外集會遊行上空活動。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-5', id:'checkbox-5',value:'5'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-6'},'F. 不得於日落後至日出前之時間飛航。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-6', id:'checkbox-6',value:'6'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-7'},'G. 在目視範圍內操作，不得以除矯正鏡片外之任何工具延伸飛航作業距離'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-7', id:'checkbox-7',value:'7'} ),
                            React.createElement("br",  {}  ),
                            React.createElement("label",  {htmlFor:'checkbox-8'},'H. 操作人不得在同一時間控制二架以上遙控無人機。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-8', id:'checkbox-8',value:'8'} ),
                            React.createElement("br",  {}  )
                         )
            );
  }

});





var p_thirdStep = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
       this.setState({
    	   constructionType: obj.constructionType,
       	   operationLimit: obj.operationLimit
       });
    },
	render: function() {
        return  React.createElement("div",  {id:'p_thirdStep_'+this.props.domId},
        		React.createElement(p_personList,{constructionType: this.state.constructionType,operationLimit:this.state.operationLimit})
                );
    }

});

var p_personList = React.createClass({
	getDefaultProps: function() {
        return {
        	constructionType:'',
        	operationLimit:false
        };
    },
    getInitialState: function() {
         return {
         };
    },
    componentDidUpdate(prevProps, prevState){
    	var tableName = "#p_personList";
        if((this.props.constructionType!==prevProps.constructionType)||(this.props.operationLimit!==prevProps.operationLimit)){
            $(tableName).unbind( "select" );
            var table = $(tableName).DataTable();
            table.destroy();
            $(tableName).DataTable({
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
			        	constructionType : this.props.constructionType,
			        	operationLimit : this.props.operationLimit
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
                            action_obj.personAPList_select_Action(personId);                            
                        });
                        t.on( 'deselect', function ( e, dt, type, indexes ) {
			                var cell =t.cell( indexes ,1);
			                var personId = cell.data();
			                action_obj.personAPList_deselect_Action(personId);
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
        $("#p_personList").unbind( "select" );
        var table = $("#p_ersonList").DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#p_personList").DataTable({
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
        return  React.createElement("table",  {id:'p_personList'},
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

var p_fourthStep = React.createClass({
	getInitialState: function() {
        return {
       	 	equipmentId: store_obj.equipmentId,
       	 	personId_1:store_obj.personId_1,
       	 	personId_2:store_obj.personId_2
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
        return  React.createElement("div",  {id:'p_fourthStep_'+this.props.domId},
        			React.createElement(p_equipmentForm,{equipmentId: this.state.equipmentId}),
        			React.createElement(p_personForm_1,{personId_1 : this.state.personId_1}),
        			React.createElement(p_personForm_2,{personId_2 : this.state.personId_2})
                );
    }

});

var p_equipmentForm = React.createClass({
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
    	var form = "#p_equipmentForm";
        if((this.props.equipmentId!==prevProps.equipmentId)&&(this.props.equipmentId!=='-')&&(this.props.equipmentId!==null)){
            $.ajax({
			    url:"/Drone/equipment/ViewEquipmentProcess",
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
			        store_obj.aerialPlanEPList.equipmentId = $(form).find("#equipmentId").val();
			        store_obj.aerialPlanEPList.manufactoryName = $(form).find("#manufactoryName").val();
			        store_obj.aerialPlanEPList.constructionType = $(form).find("#constructionType").val();
			        store_obj.aerialPlanEPList.productName = $(form).find("#productName").val();
			        store_obj.aerialPlanEPList.airTime = $(form).find("#airTime").val();
			    }
		    })
		    
        	
        }
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"p_equipmentForm"},
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

var p_personForm_1 = React.createClass({
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
    	var form = "#p_personForm_1";
        if((this.props.personId_1!==prevProps.personId_1)&&(this.props.personId_1!=='-')&&(this.props.personId_1!==null)){
            $.ajax({
			    url:"/Drone/person/ViewPersonProcess",
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
			        store_obj.aerialPlanEPList.personId_1 = $(form).find("#personId").val();
			    }
		    });
		    
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"p_personForm_1"},
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

var p_personForm_2 = React.createClass({
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
    	var form = "#p_personForm_2";
        if((this.props.personId_2!==prevProps.personId_2)&&(this.props.personId_2!=='-')&&(this.props.personId_2!==null)){
            $.ajax({
            	type:"POST",
			    url:"/Drone/person/ViewPersonProcess",			    
			    data:{
			    	id : this.props.personId_2
			    },
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $(form).find("#" + key).val(value);
				    });				
			        store_obj.aerialPlanEPList.personId_2 = $(form).find("#personId").val();
			    }
		    })
		    
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"p_personForm_2"},
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



