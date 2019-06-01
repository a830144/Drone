
var Steps = React.createClass({
    componentDidMount() {
        var wizard = $("#wizard").steps({
                onFinished: function (event, currentIndex)
                {
                    var t = $('#single-aerialPlan-table-3-1').DataTable();
                    t.row.add( [
                        $("#equipmentForm").find("#equipmentId").val(),
                        $("#equipmentForm").find("#manufactoryName").val(),
                        $("#equipmentForm").find("#constructionType").val(),
                        $("#equipmentForm").find("#productName").val(),
                        $("#equipmentForm").find("#airTime").val(),
                        '',
                        $("#personForm_1").find("#personId").val(),
                        $("#personForm_2").find("#personId").val(),
                    ] ).draw( false );
                    alert('您選擇了一筆設備資料');
                    window.equipmentId='';
                    window.personId_1='';
                    window.personId_2='';
                }

        });
        wizard.steps("add", {
            title: "選取設備", 
            content: "<div id='firstStep'>first step</div>"
        });   
        wizard.steps("add", {
            title: "選取排外條件", 
            content: "<div id='secondStep'>second step</div>"
        });   
        wizard.steps("add", {
            title: "選取人員", 
            content: "<div id='thirdStep'>third step</div>"
        }); 
        wizard.steps("add", {
            title: "最終確認", 
            content: "<div id='fourthStep'>fourth step</div>"
        });   
     
    },
    render: function() {
        return  React.createElement("div",  {id:'wizard'},
        );
    }

});

var SelectConstruction = React.createClass({
   
    render: function() {
        return  React.createElement("select",  {name:"constructionType",onChange: this.props.onChange},
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
            type:'',
        };
    },
    componentDidUpdate(){
        $("#ta").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
        $("#ta").DataTable({
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
			        "type": this.props.type  
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
                    var t = $('#ta').DataTable();
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
    componentWillUnmount(){
        $("#ta").unbind( "select" );
        var table = $("#ta").DataTable();
        table.destroy();
    },
    componentDidMount() {
        $("#ta").DataTable({
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
			        "type": 'A'  
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
                    var t = $('#ta').DataTable();
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
        return  React.createElement("table",  {id:'ta' },
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


var FirstStep = React.createClass({
    getInitialState: function() {
         return {
             type: '',
         };
    },
    handleChange: function(event) {
        this.setState({ type: event.target.value });
    },

    render: function() {
        return  React.createElement("div",  {id:'firstStepContainer'},
                    React.createElement(SelectConstruction,{onChange: this.handleChange},),
                    React.createElement(EquipmentTable,{type:this.state.type},)
                );
    }

});

var OperationLimitCheckBox = React.createClass({
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
            window.operationLimit= 'true';
        }else{
            $("input[type='checkbox']").prop("checked", false ).checkboxradio('refresh');
            window.operationLimit= 'false';
        }
    },
    
    render: function() {
        return  React.createElement("fieldset",  {},
                         React.createElement("legend",  {}, "例外排除項目"),
                         React.createElement("label",  {htmlFor:'operationlimit-y'} ,'是'),
                         React.createElement('input', {onClick:this.handleClick, type:'radio', name:'operationlimit-radio', id:'operationlimit-y',value:'y'},),
                         React.createElement("label",  {htmlFor:'operationlimit-n'} ,'否'),
                         React.createElement('input', {onClick:this.handleClick,type:'radio', name:'operationlimit-radio', id:'operationlimit-n',value:'n'},),
                         React.createElement("br",  {} , ),
                         React.createElement("div",  {id:'limitCheckboxContainer'} , 
                            React.createElement("label",  {htmlFor:'checkbox-1'},'A. 遙控無人機飛航活動之實際高度不得逾距地面或水面四百呎'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-1', id:'checkbox-1',value:'1'},),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-2'},'B. 不得以遙控無人機投擲或噴灑任何物件'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-2', id:'checkbox-2',value:'2'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-3'},'C. 不得裝載依第四十三條第三項公告之危險物品。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-3', id:'checkbox-3',value:'3'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-4'},'D. 依第九十九條之十七所定規則之操作限制：(char)XXXXXXXXXX'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-4', id:'checkbox-4',value:'4'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-5'},'E. 不得於人群聚集或室外集會遊行上空活動。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-5', id:'checkbox-5',value:'5'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-6'},'F. 不得於日落後至日出前之時間飛航。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-6', id:'checkbox-6',value:'6'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-7'},'G. 在目視範圍內操作，不得以除矯正鏡片外之任何工具延伸飛航作業距離'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-7', id:'checkbox-7',value:'7'}, ),
                            React.createElement("br",  {} , ),
                            React.createElement("label",  {htmlFor:'checkbox-8'},'H. 操作人不得在同一時間控制二架以上遙控無人機。'),
                            React.createElement('input', {type:'checkbox', name:'checkbox-8', id:'checkbox-8',value:'8'}, ),
                            React.createElement("br",  {} , )
                         )
            );
  }

});

var SecondStep = React.createClass({
    render: function() {
        return  React.createElement("div",  {id:'SecondStepContainer'},
                    React.createElement(OperationLimitCheckBox,{},)
                );
    }

});

var PersonTable = React.createClass({

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
            $("#ta3").unbind( "select" );
            var table3 = $("#ta3").DataTable();
            table3.destroy();
            $("#ta3").DataTable({
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

                        var t = $('#ta3').DataTable();
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
        $("#ta3").unbind( "select" );
        var table3 = $("#ta3").DataTable();
        table3.destroy();
    },
    componentDidMount() {
        window.addEventListener("click", this.handleMouseClick);
        $("#ta3").DataTable({
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
        return  React.createElement("table",  {id:'ta3'},
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

var ThirdStep = React.createClass({
    getInitialState: function() {
         return {
             type: '',
             id: ''
         };
    },

    render: function() {
        return  React.createElement("div",  {id:'ThirdStepContainer'},
                    React.createElement(PersonTable,{equipmentId:window.equipmentId},)
                );
    }

});

var EquipmentConfirm = React.createClass({
    getInitialState: function() {
         return {
             equipmentId:window.equipmentId, 
         };
    },
    handleMouseClick: function(){
        this.setState({ equipmentId:window.equipmentId });
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
			    data:{"id" : window.equipmentId},
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#equipmentForm").find("#" + key).val(value);
				    });				

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
                                    React.createElement("input",  {type:"text",id:"equipmentId"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"製造廠名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"manufactoryName"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"構造分類"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"constructionType"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"型式名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"productName"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"滯空時間"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"airTime"})
                                )
                            )
                        )
                    )
        );

    }

});

var PersonConfirm_1 = React.createClass({
    getInitialState: function() {
         return {
             personId_1:window.personId_1, 
         };
    },
    handleMouseClick: function(){
        this.setState({ personId_1:window.personId_1 });
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
			    data:{"id" : window.personId_1},
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#personForm_1").find("#" + key).val(value);
				    });				

			    }
		    })
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_1"},
                    React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"姓名"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"name"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"身分證號碼"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"idNumber"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"出身日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"dateOfBirth"})
                                )
                            )
                        )
                    )
        );

    }

});

var PersonConfirm_2 = React.createClass({
    getInitialState: function() {
         return {
             personId_2:window.personId_2, 
         };
    },
    handleMouseClick: function(){
        this.setState({ personId_2:window.personId_2 });
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
			    url:"/Drone/person/ViewPersonProcess",
			    type:"POST",
			    data:{"id" : window.personId_2},
			    dataType: "json",
			    success: function(data){				  
				    var obj = data;
			        $.each(obj, function(key, value) {
					    $("#personForm_2").find("#" + key).val(value);
				    });				

			    }
		    })
        } 
    },    
   
    render: function() {
        return  React.createElement("form",  {id:"personForm_2"},
                    React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"姓名"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"name"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"身分證號碼"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"idNumber"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"出身日期"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"dateOfBirth"})
                                )
                            )
                        )
                    )
        );

    }

});


var FourthStep = React.createClass({
    getInitialState: function() {
         return {
             type: '',
             id: ''
         };
    },

    render: function() {
        return  React.createElement("div",  {id:'FourthStepContainer'},
                    React.createElement(EquipmentConfirm,{},),
                    React.createElement(PersonConfirm_1,{},),
                    React.createElement(PersonConfirm_2,{},)
                );
    }

});



var container = document.getElementById('root');

ReactDOM.render(React.createElement(Steps, {}), container);

container = document.getElementById('firstStep');

ReactDOM.render(React.createElement(FirstStep, {}), container);

container = document.getElementById('secondStep');

ReactDOM.render(React.createElement(SecondStep, {}), container);

container = document.getElementById('thirdStep');

ReactDOM.render(React.createElement(ThirdStep, {}), container);

container = document.getElementById('fourthStep');

ReactDOM.render(React.createElement(FourthStep, {}), container); 

