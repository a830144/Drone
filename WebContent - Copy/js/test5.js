var container = document.getElementById('root');

var table = React.createClass({
    getInitialState: function() {
         return {
             checked: false,
             name: 'top secret!'};
     },
    
    handleClick: function() {
        this.setState({ name: 'jim' });

    },
   
    render: function() {

    return  React.createElement("div",  {},
                React.createElement("input",  {type: "text",readOnly: false, value:'My name is '+ this.state.name},),
                React.createElement("button",  {onClick: this.handleClick},"reveal the secret!")
               
            );
  }

});
ReactDOM.render(React.createElement(table, {}), container);