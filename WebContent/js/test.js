var container   = document.getElementById('root');

// h is short for hyperscript and it makes everything a little bit easier
var h           = React.createElement;

// This is how we inherit methods like setState from React.Component....
Timer.prototype = Object.create(React.Component.prototype);

function Timer(props) {
  React.Component.constructor.call(this);
  var self = this;

  self.state = { seconds: 0 };

  self.tick = function() {
    self.setState(function(prevState) {
      return { seconds: prevState.seconds + 1 };
    });
  };

  self.componentDidMount = function() {
    self.interval = setInterval(self.tick, 1000);
  };

  self.componentWillUnmount = function() {
     clearInterval(self.interval);
  };

  self.render = function() {
    return h('div', null, 'seconds: ', self.state.seconds);
  }
}

ReactDOM.render(h(Timer), container);