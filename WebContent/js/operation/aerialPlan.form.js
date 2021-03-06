var aerialPlanForm = React.createClass({	
	getInitialState: function() {
        return {
        	aerialPlanId:'', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		aerialPlanId:obj.aerialPlanId,
    		state:obj.state["aerialPlan"]
    	});
    },
	
    componentWillUnmount(){
    	var form = $("#aerialPlanForm_"+this.props.domId+"_sub");
    	$(form).find("#aerialPlanId").empty();
    	$(form).find("#aerialPlanStartDate").datepicker("destroy");
 		$(form).find("#aerialPlanEndDate").datepicker("destroy");
    },
    componentDidMount() {
         var form = $("#aerialPlanForm_"+this.props.domId+"_sub");
         $.ajax({
 			url:"/"+system_name +"/operation/ViewProjectProcess",
 			type:"POST",
 			data:{
 				id : store_obj.projectId
 			},
 			dataType: "json",
 			success: function(data){				  
 				var obj = data;				
 				$.each(obj, function(key, value) {
 					form.find("#" + key).val(value);
 				});
 			}
 		});
         
        $(form).find("#aerialPlanStartDate").datepicker();
 		$(form).find("#aerialPlanEndDate").datepicker();
    },
    componentDidUpdate(prevProps, prevState){    	
    	var form = $("#aerialPlanForm_"+this.props.domId+"_sub");
    	if(this.state.state!==prevState.state){
			form.find("#state").val(this.state.state);
		}
    	if(this.state.aerialPlanId!==prevState.aerialPlanId){
    		if(this.state.aerialPlanId==='-'){
    			form.find("#aerialPlanIdTD").empty();
    			form.find("#aerialPlanStateTD").empty();
    			form.find("#usage").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#aerialPlanStartDate").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#aerialPlanEndDate").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    		}else{
    			if(this.state.aerialPlanId!=null && this.state.aerialPlanId!=''){
    			$.ajax({
  				  type:"POST",
  				  url:"/" +system_name +"/operation/ViewAerialPlanByAerialPlanId",				  
  				  data:{
  					  id : this.state.aerialPlanId
  				  },
  				  dataType: "json",
  				  success: function(data){	
  					  var obj = data;
  					  form.find("#aerialPlanIdTD").empty().append("<input type='text' name='aerialPlanId' id='aerialPlanId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
  					  form.find("#aerialPlanStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
  					  $.each(obj, function(key, value) {
  						  form.find("#" + key).val(value);	
  						  form.find("input[name='" + key +"']").val(value);
  					  });
  					  form.find("#aerialPlanId").removeClass("ui-state-enabled").addClass("ui-state-disabled");
  					  form.find("#usage").removeClass("ui-state-enabled").addClass("ui-state-disabled");
  					  form.find("#aerialPlanStartDate").removeClass("ui-state-enabled").addClass("ui-state-disabled");
  					  form.find("#aerialPlanEndDate").removeClass("ui-state-enabled").addClass("ui-state-disabled");
  				  }})
    			}
    		}
    		
    	};
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"aerialPlanForm_"+this.props.domId+"_sub"},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"專案ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"projectId",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"專案名稱"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type:"text",id:"name",size:"25",className:"text ui-widget-content ui-corner-all ui-state-disabled"})
                                    )
                                ),
                            React.createElement("tr",  {},
                            		React.createElement("td",  {},"計畫ID"),
                                    React.createElement("td",  {id:"aerialPlanIdTD"})
                            ),
                            
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"計畫航拍空域範圍檔案：(KML檔)：(File Upload < 10M) "),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",name:"photo",id:"photo"}),
                                    React.createElement("input",  {type:"file",name:"file",id:"aerialPlan_file"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"作業日期及時間(最多三個月、政府單位可延長至6個月)"),            
                                React.createElement("td",  {},
                                    "開始",
                                    React.createElement("input",  {id:"aerialPlanStartDate",name:"aerialPlanStartDate"}),
                                    "~結束",
                                    React.createElement("input",  {id:"aerialPlanEndDate",name:"aerialPlanEndDate"})
                                )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"資料狀態"),
                                    React.createElement("td",  {id:"aerialPlanStateTD"})
                            )
                        )
                    )
                   );

    }

});

var otherForm = React.createClass({	
	getInitialState: function() {
        return {
        	aerialPlanId:'', 
        };
    },
    notify: function(obj){
    	this.setState({ 
    		aerialPlanId:obj.aerialPlanId
    	});
    },
	
    componentWillUnmount(){
    	var form = $("#otherForm_"+this.props.domId+"_sub");
    	$(form).find("#aerialPlanId").empty();
    	$(form).find("#aerialPlanStartDate").datepicker("destroy");
 		$(form).find("#aerialPlanEndDate").datepicker("destroy");
    },
    componentDidMount() {
    	 var form = $("otherForm_"+this.props.domId+"_sub");
    	 $.ajax({
  			url:"/"+system_name +"/operation/ViewProjectProcess",
  			type:"POST",
  			data:{
  				id : store_obj.projectId
  			},
  			dataType: "json",
  			success: function(data){				  
  				var obj = data;				
  				$.each(obj, function(key, value) {
  					form.find("#" + key).val(value);
  				});
  			}
  		});
    },
    componentDidUpdate(prevProps, prevState){
    	var form = $("#otherForm_"+this.props.domId+"_sub");
    	if(this.state.aerialPlanId!==prevState.aerialPlanId){
    		if(this.state.aerialPlanId==='-'){    			
    			form.find("#amslFrom").val("");
    			form.find("#amslTo").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#agl").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#takeOffLocationName").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");    			
    		}else{
    			if(this.state.aerialPlanId!=null && this.state.aerialPlanId!=''){
    			$.ajax({
  				  type:"POST",
  				  url:"/"+system_name +"/operation/ViewAerialPlanByAerialPlanId",				  
  				  data:{
  					  id : this.state.aerialPlanId
  				  },
  				  dataType: "json",
  				  success: function(data){	
  					  var obj = data;  					  
  					  $.each(obj, function(key, value) {
  						  form.find("#" + key).val(value);	
  						  form.find("input[name='" + key +"']").val(value);
  					  });  					  
  				  }})
    			}
    		}
    		
    	};
    },    
   
    render: function() {
        return  React.createElement("form",{ id:"otherForm_"+this.props.domId+"_sub"},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                            	React.createElement("td",  {},                                      
                                      "作業高度"
                                ),
                                React.createElement("td",  {}, "自",
                                      React.createElement("input",  {type:"number",id:"amslFrom",min:"1", max:"10000",defaultValue:"1"}),
                                      "英呎至",      
                                      React.createElement("input",  {type:"number",id:"amslTo",min:"1", max:"10000",defaultValue:"1"}),
                                      "英呎(AMSL, Above Mean Sea Level)"
                                 )
                            ),
                            React.createElement("tr",  {},
                                     React.createElement("td",  {},                                      
                                             "實際高度"
                                     ),
                                     React.createElement("td",  {},                                      
                                           React.createElement("input",  {type:"number",id:"agl",min:"1", max:"10000",defaultValue:"1"}),
                                           "英呎(AGL, Above Ground Level)"
                                     )
                                ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"起飛/降落地點名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {id:"takeOffLocationName",name:"takeOffLocationName"})
                                )
                            ),
                            React.createElement("tr",  {},
                                	React.createElement("td",  {},                                      
                                          "北緯"
                                    ),
                                    React.createElement("td",  {}, 
                                          React.createElement("input",  {type:"number",id:"takeOffNDegree",min:"0", max:"90",defaultValue:"0"}),
                                          "度",      
                                          React.createElement("input",  {type:"number",id:"takeOffNMinute",min:"0", max:"59",defaultValue:"0"}),
                                          "分",      
                                          React.createElement("input",  {type:"number",id:"takeOffNSecond",min:"0", max:"59",defaultValue:"0"}),
                                          "秒"
                                     ),
                                     React.createElement("td",  {},                                      
                                             "東經"
                                     ),
                                     React.createElement("td",  {}, 
                                             React.createElement("input",  {type:"number",id:"takeOffEDegree",min:"0", max:"180",defaultValue:"0"}),
                                             "度",      
                                             React.createElement("input",  {type:"number",id:"takeOffEMinute",min:"0", max:"59",defaultValue:"0"}),
                                             "分",      
                                             React.createElement("input",  {type:"number",id:"takeOffESecond",min:"0", max:"59",defaultValue:"0"}),
                                             "秒"
                                      )
                            ),
                            
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"作業範圍中心點座標 ")
                                
                            ),
                            React.createElement("tr",  {},
                                	React.createElement("td",  {},                                      
                                          "北緯"
                                    ),
                                    React.createElement("td",  {}, 
                                          React.createElement("input",  {type:"number",id:"areaCenterNDegree",min:"0", max:"90",defaultValue:"0"}),
                                          "度",      
                                          React.createElement("input",  {type:"number",id:"areaCenterNMinute",min:"0", max:"59",defaultValue:"0"}),
                                          "分",      
                                          React.createElement("input",  {type:"number",id:"areaCenterNSecond",min:"0", max:"59",defaultValue:"0"}),
                                          "秒"
                                     ),
                                     React.createElement("td",  {},                                      
                                             "東經"
                                     ),
                                     React.createElement("td",  {}, 
                                             React.createElement("input",  {type:"number",id:"areaCenterEDegree",min:"0", max:"180",defaultValue:"0"}),
                                             "度",      
                                             React.createElement("input",  {type:"number",id:"areaCenterEMinute",min:"0", max:"59",defaultValue:"0"}),
                                             "分",      
                                             React.createElement("input",  {type:"number",id:"areaCenterESecond",min:"0", max:"59",defaultValue:"0"}),
                                             "秒"
                                        )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"作業半徑"),
                                    React.createElement("td",  {},
                                        React.createElement("input",  {type:"number",id:"operationDiameter",min:"1", max:"1000",defaultValue:"0"})
                                    )
                            )
                        )
                    )
                   );

    }

});


var aerialPlanEPList = React.createClass({
	getInitialState: function() {
        return {
        	aerialPlanId:store_obj.aerialPlanId,
        	stepIn:store_obj.stepIn, 
        };
    },
    notify: function(obj){
       this.setState({ 
    	   aerialPlanId:obj.aerialPlanId,
    	   stepIn:obj.stepIn 
       });
       
    },
	componentDidMount(){
    	var tableName = "#aerialPlanEPList_"+this.props.domId+"_sub";
		var table = $(tableName).DataTable( {
			columnDefs: [
				{
	                aTargets: [ 6,7 ],
	                sType: "html",	                	                
	                render : function(data, type, row,meta) {
	                	var output = "<select id='EProw"+meta.row+"col"+meta.col+"'>";
	                	var tagArr = data.split(",");
	                	for(i=0;i< tagArr.length;i++){
	                		if(tagArr[i].startsWith("selected")){
	                			var tempArr = tagArr[i].split(" ");
	                			output+="<option value='"+tempArr[1]+"' selected>"+tempArr[1];
	                		}else{
	                			output+="<option value='"+tagArr[i]+"'>"+tagArr[i];
	                		}
	                	}
	                	output+="</select>";
	                    return output;
	                }
	            },
			{	
				className: 'dt-center',
				targets: '_all'
			}],
			dom: 'Bfrtip',
			buttons: [
	            {
	                text: '新增使用設備',
	                action: function ( e, dt, node, config ) {
	                    action_obj.aerialPlanEPList_add_Action();
	                }
	            }
	        ],
	        searching: false,
			select:true,
	        order: [[ 1, 'asc' ]],
			scrollY: "100px",
	        scrollCollapse: true
		});
	},
	componentDidUpdate(prevProps, prevState){
		 var tableName = "#aerialPlanEPList_"+this.props.domId+"_sub";
	     if(this.state.stepIn!==prevState.stepIn && this.state.stepIn==='finish'){
	    	  var table = $(tableName).DataTable();	    	  
	    	  table.row.add( [
	    		  '',
	    		  store_obj.aerialPlanEPList.constructionType,
	    		  store_obj.aerialPlanEPList.manufactoryName,
	    		  store_obj.aerialPlanEPList.equipmentId,	        	
	    		  store_obj.aerialPlanEPList.productName,
	    		  store_obj.aerialPlanEPList.airTime,
	    		  store_obj.aerialPlanEPList.personId_1,
	    		  store_obj.aerialPlanEPList.personId_2
	    	  ] ).draw();
	      };
	      if(this.state.aerialPlanId!==prevState.aerialPlanId){
	    	  if(this.state.aerialPlanId==='-'){
	    		  $(tableName).unbind( "select" );
			       var table = $(tableName).DataTable();
			       table.clear().draw();
	    	  }else{
	    		  $(tableName).unbind( "select" );
		          var table = $(tableName).DataTable();
		          table.destroy();
		        
		          var table = $(tableName).DataTable( {
		        	aoColumnDefs: [
		        		{
			                aTargets: [ 6,7 ],
			                sType: "html",			                			                
			                render : function(data, type, row,meta) {
			                	var output = "<select id='EProw"+meta.row+"col"+meta.col+"'>";
			                	var tagArr = data.split(",");
			                	for(i=0;i< tagArr.length;i++){
			                		if(tagArr[i].startsWith("selected")){
			                			var tempArr = tagArr[i].split(" ");
			                			output+="<option value='"+tempArr[1]+"' selected>"+tempArr[1];
			                		}else{
			                			output+="<option value='"+tagArr[i]+"'>"+tagArr[i];
			                		}
			                	}
			                	output+="</select>";
			                    return output;
			                }
			            },
			            {	
			            	className: 'dt-center',
			            	targets: '_all'
			            }],	
		    		dom: 'Bfrtip',
					buttons: [
			            {
			                text: '新增使用設備',
			                action: function ( e, dt, node, config ) {
			                	action_obj.aerialPlanEPList_add_Action();
			                }
			            }
			        ],
			        searching: false,
		        	select:true,
			        order: [[ 1, 'asc' ]],
					scrollY: "100px",
			        scrollCollapse: true,
			        ajax: {
						type: "POST",
					    url: "/"+system_name +"/operation/ViewAerialPlanByAerialPlanId",  
					    data: {  
					    	id : this.state.aerialPlanId 
					    }, 
					    dataSrc: function ( json ) {
					    	console.log(json.equipmentsArray);
					    	var myarray=new Array(json.equipmentsArray.length);
					    	for (i=0; i <json.equipmentsArray.length; i++){
					    	    myarray[i]=new Array(8);
					    	}
					    	for (i=0; i <json.equipmentsArray.length; i++){
				        		var obj = json.equipmentsArray[i];
				        		myarray[i][0]='';		
				        		myarray[i][1]=obj.hasOwnProperty("constructionType")?obj.constructionType:'';
				        		myarray[i][2]=obj.hasOwnProperty("manufactoryName")?obj.manufactoryName:'';
				        		myarray[i][3]=obj.hasOwnProperty("equipmentId")?obj.equipmentId:'';				        			
				        		myarray[i][4]=obj.hasOwnProperty("productName")?obj.manufactoryName:'';
				        		myarray[i][5]=obj.hasOwnProperty("airTime")?obj.manufactoryName:'';
				        		myarray[i][6]=obj.hasOwnProperty("personId_1")?obj.personId_1:'';
				        		myarray[i][7]=obj.hasOwnProperty("personId_2")?obj.personId_2:'';
				        	}
					    	
					        return myarray;
					      }
					    ,
				        dataType: 'json'
					}
				});		          
		          
	    	  }
	      };
	},
    componentWillUnmount(){			
	        $("#aerialPlanEPList_"+this.props.domId+"_sub").unbind( "select" );
	        var table = $("#aerialPlanEPList_"+this.props.domId+"_sub").DataTable();
	        table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"aerialPlanEPList_"+this.props.domId+"_sub" },
                    React.createElement("thead",  {},
                         React.createElement("tr",  {},
                             React.createElement('th', {}, ''),
                             React.createElement('th', {}, '設備構型'),
                             React.createElement('th', {}, '設備型號'),
                             React.createElement('th', {}, '編號'),
                             React.createElement('th', {}, '滯空時間'),
                             React.createElement('th', {}, '有效期限'),
                             React.createElement('th', {}, '配署人員1'),
                             React.createElement('th', {}, '配署人員2')
               
            )));
   }
})

