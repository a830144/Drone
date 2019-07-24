var stateButtons = React.createClass({
	getInitialState: function() {
		return {
			state:'', 
        	domId:'',
        	typeId:'',
        	targetId: ''
        };
    },
    notify: function(obj){ 
    	if(obj.domId===this.props.domId && obj.typeId===this.props.typeId){
    		this.setState({ 
    			state:obj.state,
    			domId:obj.domId,
    			typeId:obj.typeId,
    			targetId:obj.targetId
    		});
    	}
    	
    },
    componentWillUnmount(){   	
    },
    componentDidMount() {
    	var stateButton = "#stateButton_"+this.props.domId+"_"+this.props.typeId+"_sub";
    	var checkBtn = "#check_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var approveBtn = "#approve_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var rejectBtn = "#reject_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var domId = this.props.domId;
    	var typeId = this.props.typeId;
    	var typeId_uc_first = typeId.charAt(0).toUpperCase() + typeId.slice(1);
    	
    	//debugger;
    	$(stateButton).find(checkBtn).button({
			icons: { primary: "ui-icon-play" }
		});   	
    	$(stateButton).find(approveBtn).button({
			icons: { primary: "ui-icon-check" }
		});
		$(stateButton).find(rejectBtn).button({
			icons: { primary: "ui-icon-eject" }
		});
		
		$(stateButton).find(checkBtn).button().click(function() {
			$.ajax({
				  url:"/Drone"+"/"+typeId+"/Check"+typeId_uc_first+"Process",
				  type:"POST",
				  data:{
					  targetId : store_obj.targetId[typeId]
				  },
				  success : function() {
						alert('檢核成功');						
				    	$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		    			$(stateButton).find(approveBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
						action_obj.checkButton_click_Action(typeId);
				  }
			})
		});
		$(stateButton).find(approveBtn).button().click(function() {
			$.ajax({
				  url:"/Drone"+"/"+typeId+"/Approve"+typeId_uc_first+"Process",
				  type:"POST",
				  data:{
					  targetId : store_obj.targetId[typeId]
				  },
				  success : function() {
						alert('核可成功');
						$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
						action_obj.approveButton_click_Action(typeId);
				  }
			})
		});
		$(stateButton).find(rejectBtn).button().click(function() {
			$.ajax({
				  url:"/Drone"+"/"+typeId+"/Reject"+typeId_uc_first+"Process",
				  type:"POST",
				  data:{
					  targetId : store_obj.targetId[typeId]
				  },
				  success : function() {
						alert('退回成功');
						$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
						action_obj.rejectButton_click_Action(typeId);
				  }
			})
		});
    },
    componentDidUpdate(prevProps, prevState){
    	var stateButton = "#stateButton_"+this.props.domId+"_"+this.props.typeId+"_sub";
    	var checkBtn = "#check_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var approveBtn = "#approve_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var rejectBtn = "#reject_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	
    	//alert('notify:'+this.state.state+prevState.state+";"+this.state.domId+this.props.domId+";"+this.state.typeId+this.props.typeId);
    	
    	if(this.state.targetId!==prevState.targetId || this.state.state!==prevState.state){
    		if(this.state.state==="PROCESSING"){
    			$(stateButton).find(checkBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}else if(this.state.state==="CHECKED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			$(stateButton).find(approveBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    		}else if(this.state.state==="APPROVED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}else if(this.state.state==="REJECTED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}
    	}
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"stateButton_"+this.props.domId+"_"+this.props.typeId+"_sub"},
        			React.createElement("button",  {id:"check_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget"},"檢核"+this.props.domIdCN),
        			React.createElement("button",  {id:"approve_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget"},"核可"+this.props.domIdCN),
        			React.createElement("button",  {id:"reject_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget"},"退回"+this.props.domIdCN)
        			
        		);
    }

});



