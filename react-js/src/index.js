import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css'

ReactDOM.render(
  /* gives error for bootstrap modal pop-up if its in strict mode */
  /*<React.StrictMode> */
    <App />,
  /* <React.StrictMode>, */
  document.getElementById('root')
);
