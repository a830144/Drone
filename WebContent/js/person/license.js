var licenseDialog = React.createClass({	
	getInitialState: function() {
        return {
        	licenseId:null,
        	crud:'C',
        };
    },
    notify: function(obj){
    	this.setState({ 
    		licenseId:obj.licenseId,
    		crud:obj.crud 
    	});
    },
    componentDidMount(){
    	var dialog = "#licenseDialog_"+this.props.domId+"_sub";
    	var formName = "#licenseForm_"+this.props.domId+"_sub";
    	var domId = this.props.domId;
    	$(dialog).dialog({
			autoOpen : false,
			height : 600,
			width : 1250,
			modal : true,
			buttons : [
						{
						  text: "修改操作證資料",
				          icon: "ui-icon-pencil",
				          id: "updateLicenseBtn_"+domId,
				          click: function() {
				        	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {				        	  			
				        	  			gotDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		var form = $(formName);
				        	  		var jsonObject = {};
				        	  		jsonObject.personId =form.find("#personId").val();
				        	  		jsonObject.name =form.find("#name").val();
				        	  		jsonObject.constructionType = form.find("#constructionType").val();
				        	  		jsonObject.type = form.find("#type").val();
				        	  		jsonObject.gotDate = form.find("#gotDate").val();
				        	  		jsonObject.photo = form.find("#photo").val();
				        	  		jsonObject.state = form.find("#state").val();
				        	  		jsonObject.licenseId = form.find("#licenseId").val();
				        	  		jsonObject.personsLicensesId = form.find("#personsLicensesId").val();
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/Drone/person/UpdateLicenseInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('修改操作證紀錄成功');
				        	  			}
				        	  		});
				        	  			
									var fileData = new FormData(document.getElementById("licenseForm_license_sub"));				 
								    fileData.append("action","license");
										 
								    $.ajax({
										            type: "POST",
										            enctype: 'multipart/form-data',
										            url: "/Drone/other/uploadMultipleFile",
										            data: fileData,
										            processData: false,
										            contentType: false,
										            cache: false,
										            timeout: 600000,
										            success: function (data) {
										                console.log("SUCCESS : ", data);
										            },
										            error: function (e) {
										                console.log("ERROR : ", e);
										            }
								    });
				        	  	}
				           }			 
			 		    },
			    		{
			      		  text: "新增操作證資料",
			              icon: "ui-icon-plus",
			              id: "addLicenseBtn_"+domId,
			              click: function() {
			            	  	var form = $(formName);
				        	  	var validator =form.validate({
				        	  		rules: {				        	  			
				        	  			gotDate: {
				        	  				 required: true
				        	  			}
				        	  		}
				        	    });				        	  	
				        	  	
				        	  	if(form.valid()){
				        	  		var form = $(formName);
				        	  		var jsonObject = {};
				        	  		jsonObject.personId =form.find("#personId").val();
				        	  		jsonObject.name =form.find("#name").val();
				        	  		jsonObject.constructionType = form.find("#constructionType").val();
				        	  		jsonObject.type = form.find("#type").val();
				        	  		jsonObject.gotDate = form.find("#gotDate").val();
				        	  		jsonObject.photo = form.find("#photo").val();
				        	  		jsonObject.state = form.find("#state").val();
							  
				        	  		var myJson = JSON.stringify(jsonObject);
				        	  		$.ajax({
				        	  			url : "/Drone/person/LicenseInPersonProcess",
				        	  			type : "POST",
				        	  			data : {
				        	  				data : myJson
				        	  			},
				        	  			success : function() {
				        	  				alert('新增操作證紀錄成功');
				        	  			}
				        	  		});
				        	  		
				        	  		var fileData = new FormData(document.getElementById("licenseForm_license_sub"));				 
								    fileData.append("action","license");										 
								    $.ajax({
										            type: "POST",
										            enctype: 'multipart/form-data',
										            url: "/Drone/other/uploadMultipleFile",
										            data: fileData,
										            processData: false,
										            contentType: false,
										            cache: false,
										            timeout: 600000,
										            success: function (data) {
										                console.log("SUCCESS : ", data);
										            },
										            error: function (e) {
										                console.log("ERROR : ", e);
										            }
								    });
			              		}
			    			}
			            },
					    {
				          text: "關閉",
				          icon: "ui-icon-closethick",
				          click: function() {
				             $( this ).dialog( "close" );
				          }
				 
				 		}],
			close : function() {
				license_obj.removeReactComponent(domId);		
			}		
		});
    	$("#addLicenseBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		$("#updateLicenseBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	$("#licenseDialog_"+this.props.domId+"_sub").dialog("open");
    },
    
    componentDidUpdate(prevProps, prevState){
    	if((this.state.crud!==prevState.crud && this.state.crud==='C')||(this.state.crud==='C' && this.state.licenseId!==prevState.licenseId)){
    		$("#addLicenseBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
			$("#updateLicenseBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    	};
    	if(this.state.crud!==prevState.crud && this.state.crud==='U'){
    		$("#addLicenseBtn_"+this.props.domId).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
			$("#updateLicenseBtn_"+this.props.domId).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    	};
    }, 
    
    componentWillUnmount(){

    },

    render: function() {
        return  React.createElement("div",  {id:"licenseDialog_"+this.props.domId+"_sub"},
        			React.createElement("div",  {id:"licenseList_"+this.props.domId}),
        			React.createElement("div",  {id:"licenseForm_"+this.props.domId})
                );
    }

});

var licenseList = React.createClass({
	getInitialState: function() {
        return {
        };
    },
    notify: function(obj){
    	this.setState({  
    	});
    },
	componentDidMount(){
    	var tableName = "#licenseList_"+this.props.domId+"_sub";
    	var table = $(tableName).DataTable( {
			columnDefs: [
				{
	                targets: [ 4 ],
	                visible: false
	            },
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
			    url: "/Drone/person/QueryLicenses",  
			    data: {  
			        id: store_obj.personId 
			    }, 
			    dataSrc: function ( json ) {
			    	var myarray=new Array(json.length);
			    	for (i=0; i <json.length; i++){
			    	    myarray[i]=new Array(6);
			    	}
			    	for (i=0; i <json.length; i++){
		        		var obj = $.parseJSON(json[i]);
		        		myarray[i][0]=obj.hasOwnProperty("state")?obj.state:'';
		        		myarray[i][1]=obj.hasOwnProperty("licenseId")?obj.licenseId:'';
		        		myarray[i][2]=obj.hasOwnProperty("gotDate")?obj.gotDate:'';
		        		myarray[i][3]=obj.hasOwnProperty("codeContent")?obj.codeContent:'';
		        		myarray[i][4]=obj.hasOwnProperty("personsLicensesId")?obj.personsLicensesId:'';
		        		myarray[i][5]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
		        	}
			    	var table = $(tableName).DataTable();
			    	table.on( 'select', function ( e, dt, type, indexes ) {
						var cell = table.cell( indexes ,1);
						var licenseId = cell.data();
						cell = table.cell( indexes ,4);
						var mappingId = cell.data();
						cell = table.cell( indexes ,0);
						var state = cell.data();						
						alert('您選取了操作證編號 :'+ licenseId);
						action_obj.licenseList_select_Action(licenseId,mappingId,state);
					});
			    	table.on( 'deselect', function ( e, dt, type, indexes ) {
			    		var cell = table.cell( indexes ,1);
						var licenseId = cell.data();
						if ( type === 'row' ) {				
							action_obj.licenseList_deselect_Action(licenseId);
					    }
					});
			        return myarray;
			      }
			    ,
		        dataType: 'json'
			}

		});
	},
	componentDidUpdate(prevProps, prevState){
	      
	},
    componentWillUnmount(){
	        $("#licenseList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#licenseList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"licenseList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, '狀態'),
                             React.createElement('th', {}, '操作證類別編號'),
                             React.createElement('th', {}, '操作證取得日期'),
                             React.createElement('th', {}, '操作證級別'),
                             React.createElement('th', {}, 'mapping資料表編號'),
                             React.createElement('th', {}, '操作證類別')
            )));
   }
});

var licenseForm = React.createClass({
	getInitialState: function() {
        return {
        	licenseId:'',
        	state:''
        };
    },
    notify: function(obj){
    	this.setState({ 
    		targetId:obj.targetId["license"],
    		state:obj.state["license"]
    	});
    },
    
    handleTypeChange: function(){
    	
    	var form = $("#licenseForm_"+this.props.domId+"_sub");
        var type = $(form).find("#type").val();
        
        $.ajax({
			url : "/Drone/person/ViewLicenseInfo",
			type : "POST",	
			data : {
				type : type
			},
			success : function(json) {  
				$(form).find("#licenseId").val(json.licenseId);
				$(form).find("#licenseIdshow").html(json.licenseId);
	        }
		});
    },

	componentDidMount(){
    	var form = $("#licenseForm_"+this.props.domId+"_sub");    	
    	$.ajax({
			  url:"/Drone/person/ViewPersonProcess",
			  type:"POST",
			  data:{
				  id : store_obj.personId
			  },
			  dataType: "json",
			  success: function(data){				  
				var obj = data;				
				$.each(obj, function(key, value) {
					form.find("#" + key).val(value);
				});
			}
		});
    	
    	$.ajax({
			url : "/Drone/other/QueryCodes",
			type : "POST",
			data : {
				type : 'License_type',
				code : ''
			},
			success : function(tag) {  
				form.find("#type").append(tag);
            }
		});
		
		form.find("#gotDate").datepicker();
		form.find("#licenseStateTD").empty();
		this.setState({ 
    		licenseId:""
    	});
    	
	},
	componentDidUpdate(prevProps, prevState){	
		var form = $("#licenseForm_"+this.props.domId+"_sub");
		if(this.state.state!==prevState.state){
			form.find("#state").val(this.state.state);
		}
		
		if(this.state.targetId!==prevState.targetId){
			if(this.state.targetId==='-'){
    			form.find("#licenseStateTD").empty();	
    			form.find("#type").val("1");
    			form.find("#gotDate").val("");
    			form.find("#photo").val("");
    			form.find("#personsLicensesId").val("");
    			form.find("#licenseId").val("");
    		}else{
    			$.ajax({
    				url:"/Drone/person/ViewLicenseInfo",
    				type:"POST",
    				data:{
    					targetId : this.state.targetId
    				},
    				dataType: "json",
    				success: function(data){				  
    					var obj = data;
    					form.find("#licenseStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
    					$.each(obj, function(key, value) {
    						form.find("#" + key).val(value);	
    						form.find("input[name='" + key +"']").val(value);
    					});
    					form.find("#licenseIdshow").html(obj.licenseId);
				}})
		   }
	   }
	},
    componentWillUnmount(){
		var form = $("#licenseForm_"+this.props.domId+"_sub");
		form.find("#gotDate").datepicker("destroy");
	},
	render: function() {
        return  React.createElement("form",{ id:"licenseForm_"+this.props.domId+"_sub",className:this.props.extraClass},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"人員內部ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"personId",name:"personId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},                                    
                                    React.createElement("td",  {},"人員名稱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type:"text",id:"name",name:"name",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"類別"),
                                    React.createElement("td",  {},
                                            React.createElement("select",  {id:"constructionType",name:"constructionType"},
                                            		React.createElement("option",  {value:"A"},"無人飛機(Aircraft)"),
                                            		React.createElement("option",  {value:"H"},"無人直昇機(Helicopter)"),
                                            		React.createElement("option",  {value:"M"},"無人多旋翼機(Muti-Rotors)"),
                                            		React.createElement("option",  {value:"O"},"其他Other")
                                            )
                                    )
                            ),
                            
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"級別"),
                                    React.createElement("td",  {},
                                        React.createElement("div",  {id:"typeTd"},
                                        		React.createElement("u",{id:"licenseIdshow"}," 3 "),
                                        		React.createElement("select",{id:"type",className:"text ui-widget-content ui-corner-all",onChange:this.handleTypeChange})
                                        )
                                    )
                            ),
                            React.createElement("tr",  {},
                                     React.createElement("td",  {},"操作證取得日期"),
                                     React.createElement("td",  {},
                                             React.createElement("input",  {type:"text",id:"gotDate",name:"gotDate",className:"text ui-widget-content ui-corner-all"})
                                     )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"上傳操作證照片"),
                                    React.createElement("td",  {},
                                            React.createElement("input",  {type:"text",id:"photo",name:"photo",className:"text ui-widget-content ui-corner-all"}),
                                            React.createElement("input",  {type:"file",name:"file",id:"license_file"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"licenseStateTD"})
                            ),
                            React.createElement("tr",  {className:'hide-true'},
                                    React.createElement("td",  {},"mapping資料表編號"),
                                    React.createElement("td",  {},
                                    		React.createElement("input",  {id:"personsLicensesId"})
                                    )
                            ),
                            React.createElement("tr",  {className:'hide-true'},
                                    React.createElement("td",  {},"licenseId"),
                                    React.createElement("td",  {},
                                    		React.createElement("input",  {id:"licenseId"})
                                    )
                            )
                            
                                
                          )
                       )
                    )
                  
    }
})

