var aerialActivityForm = React.createClass({	
	getInitialState: function() {
        return {
        	aerialActivityId:'', 
        };
    },
    notify: function(obj){    	
    	this.setState({ 
    		aerialActivityId:obj.aerialActivityId,
    		state:obj.state["aerialActivity"]
    	});
    },
	
    handleAerialPlanIdChange: function(){
    	var form = $("#aerialActivityForm_"+this.props.domId+"_sub");
        var id = $(form).find("#aerialPlanId").val();
		$.ajax({
			url : "/"+system_name +"/operation/ViewAerialPlanByAerialPlanId",
			type : "POST",	
			data : {
				id : id
			},
			success : function(json) {   
				$(form).find("#usage").val(json.usage);
				$(form).find("#aerialPlanStartDate").val(json.aerialPlanStartDate);
				$(form).find("#aerialPlanEndDate").val(json.aerialPlanEndDate);
	        }
		});
		action_obj.aerialActivityForm_planIdChange_Action(id);
    },
    
    componentDidMount() {
    	var form = $("#aerialActivityForm_"+this.props.domId+"_sub");
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
        
        $.ajax({
			url : "/"+ system_name +"/operation/QueryAerailPlansIDsByProjectId",
			type : "POST",
			data: {  
		    	projectId: store_obj.projectId
		    }, 
			success : function(tag) {  
				$(form).find("#aerialPlanId").append(tag);
	        }
		});
        $(form).find("#aerialActivityStartDate").datepicker();
 		$(form).find("#aerialActivityEndDate").datepicker();
    },
    componentDidUpdate(prevProps, prevState){
    	
    	var form = $("#aerialActivityForm_"+this.props.domId+"_sub");
		if(this.state.state!==prevState.state){
			form.find("#state").val(this.state.state);
		}

    	if(this.state.aerialActivityId!==prevState.aerialActivityId){
    		if(this.state.aerialActivityId==='-'){
    			form.find("#aerialActivityId").val("");
    			form.find("#aerialPlanId").val("1").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#usage").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#aerialPlanStartDate").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#aerialPlanEndDate").val("").removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			form.find("#aerialActivityStartDate").val("");
    			form.find("#aerialActivityEndDate").val("");
    		}else{
    			if(this.state.aerialActivityId!=null && this.state.aerialActivityId!=''){
    			$.ajax({
  				  type:"POST",
  				  url:"/"+system_name +"/operation/ViewAerialActivityByAerialActivityId",				  
  				  data:{
  					  id : this.state.aerialActivityId
  				  },
  				  dataType: "json",
  				  success: function(data){	
  					  var obj = data;
  					  form.find("#aerialActivityIdTD").empty().append("<input type='text' name='aerialActivityId' id='aerialActivityId' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
  					  form.find("#aerialActivityStateTD").empty().append("<input type='text' name='state' id='state' class='text ui-widget-content ui-corner-all ui-state-disabled'>");
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
    
    componentWillUnmount(){
    	var form = $("#aerialActivityForm_"+this.props.domId+"_sub");
    	$(form).find("#aerialPlanId").empty();
    	$(form).find("#aerialPlanId").unbind("change");
    	$(form).find("#aerialActivityStartDate").datepicker("destroy");
 		$(form).find("#aerialActivityEndDate").datepicker("destroy");
    },
   
    render: function() {
        return  React.createElement("form",{ id:"aerialActivityForm_"+this.props.domId+"_sub"},
        		React.createElement("table",  {},
                        React.createElement("tbody",  {},
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"專案ID"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"projectId",readOnly:"readOnly"})
                                ),
                                React.createElement("td",  {},"專案名稱"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"name",size:"25",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"計畫ID"),
                                React.createElement("td",  {},
                                    React.createElement("select",  {id:"aerialPlanId",onChange:this.handleAerialPlanIdChange})
                                ),
                                React.createElement("td",  {},"計畫用途"),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"usage",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"計畫航拍空域範圍檔案：(KML檔)：(File Upload < 10M) "),
                                React.createElement("td",  {},
                                    React.createElement("input",  {type:"text",id:"productName",readOnly:"readOnly"})
                                )
                            ),
                            React.createElement("tr",  {},
                                React.createElement("td",  {},"計畫核准作業日期及時間(最多三個月、政府單位可延長至6個月)")               
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},
                                    	"開始",
                                        React.createElement("input",  {id:"aerialPlanStartDate",readOnly:"readOnly"}),
                                        "~結束",
                                        React.createElement("input",  {id:"aerialPlanEndDate",readOnly:"readOnly"})
                                    )
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"計畫核准資料"),
                                    React.createElement("td",  {})
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"航拍活動編號"),
                                    React.createElement("td",  {id: "aerialActivityIdTD"}),
                                    React.createElement("td",  {},"航拍活動資料狀態"),
                                    React.createElement("td",  {id: "aerialActivityStateTD"})
                            ),
                            React.createElement("tr",  {},
                                    React.createElement("td",  {},"活動作業日期及時間(須於核准計畫最業日期內)")
                                    
                            ),
                            React.createElement("tr",  {},                       
                                    React.createElement("td",  {},
                                    	"開始",
                                        React.createElement("input",  {type:"text",id:"aerialActivityStartDate",name:"aerialActivityStartDate"}),
                                        "~結束",
                                        React.createElement("input",  {type:"text",id:"aerialActivityEndDate",name:"aerialActivityEndDate"})
                                    )
                            )
                            
                        )
                    )
                   );

    }

});

var aerialActivityEPList = React.createClass({
	getInitialState: function() {
        return {
        	aerialActivityId:store_obj.aerialActivityId,
        	stepIn:store_obj.stepIn
        };
    },
    notify: function(obj){
       this.setState({ 
    	   aerialActivityId:obj.aerialActivityId,
    	   stepIn:obj.stepIn 
       });
    },
	componentDidMount(){
		var table = $("#aerialActivityEPList_"+this.props.domId+"_sub").DataTable( {
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
	                    action_obj.aerialActivityEPList_add_Action();
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
		  var tableName = "#aerialActivityEPList_"+this.props.domId+"_sub";
	      if(this.state.stepIn!==prevState.stepIn && this.state.stepIn==='finish'){
	    	  var table = $(tableName).DataTable();	    	  
	          table.row.add( [
	        	  '',
	    		  store_obj.aerialActivityEPList.constructionType,
	    		  store_obj.aerialActivityEPList.manufactoryName,
	    		  store_obj.aerialActivityEPList.equipmentId,	        	
	    		  store_obj.aerialActivityEPList.productName,
	    		  store_obj.aerialActivityEPList.airTime,
	    		  store_obj.aerialActivityEPList.personId_1,
	    		  store_obj.aerialActivityEPList.personId_2
              ] ).draw();
	        };
	        if(this.state.aerialActivityId!==prevState.aerialActivityId){
	    	  if(this.state.aerialActivityId==='-'){
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
			                	action_obj.aerialActivityEPList_add_Action();
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
					    url: "/"+system_name +"/operation/ViewAerialActivityByAerialActivityId",  
					    data: {  
					    	id : this.state.aerialActivityId 
					    }, 
					    dataSrc: function ( json ) {
					    	console.log(json.equipmentPersonArray);
					    	var myarray=new Array(json.equipmentPersonArray.length);
					    	for (i=0; i <json.equipmentPersonArray.length; i++){
					    	    myarray[i]=new Array(8);
					    	}
					    	for (i=0; i <json.equipmentPersonArray.length; i++){
				        		var obj = json.equipmentPersonArray[i];
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
		var tableName = "#aerialActivityEPList_"+this.props.domId+"_sub";
	    $(tableName).unbind( "select" );
	    var table = $(tableName).DataTable();
	    table.destroy();
	},
	render: function() {
        return  React.createElement("table",  {id:"aerialActivityEPList_"+this.props.domId+"_sub" },
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

