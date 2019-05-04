

var container = document.getElementById('root');

var table = React.createClass({
render: function() {
    return  React.createElement('div', {},
React.createElement('h1', {}, "Hello, world!", 
React.createElement('a', {href: 'mailto:mark@ifelse.io'}, 
React.createElement('h1', {}, "React In Action"),
React.createElement('em', {}, "...and now it SH!") 
)
)
);
  }

});

ReactDOM.render(React.createElement(table, {}), container);


