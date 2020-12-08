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
    			state:obj.state[this.props.typeId],
    			domId:obj.domId,
    			typeId:obj.typeId,
    			targetId:obj.targetId[this.props.typeId]
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
    	var canReject = this.props.canReject;
    	var canApprove = this.props.canApprove;
    	var domId = this.props.domId;
    	var typeId = this.props.typeId;
    	var typeId_uc_first = typeId.charAt(0).toUpperCase() + typeId.slice(1);

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
				  url:"/"+system_name+"/"+typeId+"/Check"+typeId_uc_first+"Process",
				  type:"POST",
				  data:{
					  targetId : store_obj.targetId[typeId]
				  },
				  success : function() {
						alert('檢核成功');	
						$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		    			if(canReject){
		    				$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		    			};
		    			if(canApprove){
		    				$(stateButton).find(approveBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		    			};
		    			action_obj.checkButton_click_Action(typeId);
				  }
			})
		});
		$(stateButton).find(approveBtn).button().click(function() {
			$.ajax({
				  url:"/"+system_name+"/"+typeId+"/Approve"+typeId_uc_first+"Process",
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
				  url:"/"+system_name+"/"+typeId+"/Reject"+typeId_uc_first+"Process",
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
		if(this.props.canCheck){
			$(stateButton).find(checkBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else{
			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		};
		if(this.props.canApprove){
			$(stateButton).find(approveBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else{
			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		};
		if(this.props.canReject){
			$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
		}else{
			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
		}
    },
    componentDidUpdate(prevProps, prevState){
    	var stateButton = "#stateButton_"+this.props.domId+"_"+this.props.typeId+"_sub";
    	var checkBtn = "#check_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var approveBtn = "#approve_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	var rejectBtn = "#reject_"+this.props.domId+"_"+this.props.typeId+"_btn";
    	
    	if(this.state.targetId!==prevState.targetId || this.state.state!==prevState.state){
    		if(this.state.state==="PROCESSING"){
    			if(this.props.canCheck){
    				$(stateButton).find(checkBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			};
    			if(this.props.canReject){
    				$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			};
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}else if(this.state.state==="CHECKED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			if(this.props.canReject){
    				$(stateButton).find(rejectBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			};
    			if(this.props.canApprove){
    				$(stateButton).find(approveBtn).prop("disabled", false).removeClass("ui-state-disabled").addClass("ui-state-enabled");
    			};
    		}else if(this.state.state==="APPROVED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}else if(this.state.state==="REJECTED"){
    			$(stateButton).find(checkBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(rejectBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    			$(stateButton).find(approveBtn).prop("disabled", true).removeClass("ui-state-enabled").addClass("ui-state-disabled");
    		}
    	};
    	
    },    
   
    render: function() {
        return  React.createElement("div",  {id:"stateButton_"+this.props.domId+"_"+this.props.typeId+"_sub"},
        			React.createElement("button",  {id:"check_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget ui-state-disabled"},"檢核"+this.props.domIdCN),
        			React.createElement("button",  {id:"approve_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget ui-state-disabled"},"核可"+this.props.domIdCN),
        			React.createElement("button",  {id:"reject_"+this.props.domId+"_"+this.props.typeId+"_btn",className:"ui-button ui-corner-all ui-widget ui-state-disabled"},"退回"+this.props.domIdCN)
        			
        		);
    }

});



