var container   = document.getElementById('root');

// h is short for hyperscript and it makes everything a little bit easier
var h           = React.createElement('div', {},
                        React.createElement('h1', {}, "Hello, world!", 
                            React.createElement('a', {href: 'mailto:mark@ifelse.io'}, React.createElement('h1', {}, "React In Action"),React.createElement('em', {}, "...and now it really is!") )
                        )
                    );


ReactDOM.render(h, container);