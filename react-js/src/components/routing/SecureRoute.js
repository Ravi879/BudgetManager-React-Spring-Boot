import React, { useContext } from 'react';
import { Route, Redirect } from 'react-router-dom';
import AuthContext from '../../context/auth/authContext';

const SecureRoute = ({ component: Component, ...rest }) => {
  const authContext = useContext(AuthContext);
  const { isAuthenticated } = authContext;
  return (
    <Route
      {...rest}
      render={props =>
        !isAuthenticated ? (
          <Redirect to='/login' />
        ) : (
            <Component {...props} />
          )
      }
    />
  );
};

export default SecureRoute;
