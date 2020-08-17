import React, { Fragment } from 'react';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import NavBar from './components/layout/NavBar';
import Home from './components/pages/Home';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Footer from './components/layout/Footer';
import Dashboard from './components/pages/Dashboard';
import AuthState from './context/auth/AuthState';
import AlertMessage from './components/layout/AlertMessage';
import ItemState from './context/item/ItemState';
import SecureRoute from './components/routing/SecureRoute';
import Loading from './components/layout/Loading';
import ForgotPassword from './components/pages/ForgotPassword';
import LoadingState from './context/loading/LoadingState';
import MessageState from './context/message/MessageState';
import ResetPassword from './components/pages/ResetPassword';

function App() {


  return (

    <LoadingState>
      <MessageState>
        <AuthState>
          <ItemState>
            <AlertMessage />
            <Router>
              <Fragment>
                <Loading />
                <NavBar />
                <Switch>
                  <SecureRoute exact path='/dashboard' component={Dashboard} />
                  <Route exact path='/' component={Home} />
                  <Route exact path='/register' component={Register} />
                  <Route exact path='/login' component={Login} />
                  <Route exact path='/forgot-password' component={ForgotPassword} />
                  <Route exact path='/reset-password' component={ResetPassword} />
                </Switch>
                <Footer />
              </Fragment>
            </Router>
          </ItemState>
        </AuthState>
      </MessageState>
    </LoadingState>
  );
}

export default App;
