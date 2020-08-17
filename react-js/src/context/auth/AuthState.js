import React, { useReducer, useContext } from 'react';
import AuthContext from './authContext';
import authReducer from './authReducer';
import setAuthToken from '../../utils/setAuthToken';
import LoadingContext from '../loading/loadingContext';
import MessageContext from '../message/messageContext';
import axios from 'axios';
import {
  REGISTER_FAIL,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
  SET_AUTH,
  SEND_EMAIL_RESET_PASSWORD_FAIL,
  PASSWORD_RESET_FAIL,
} from '../types';
import { MSG_REGISTER_SUCCESS, MSG_PASSWORD_RESET_EMAIL, MSG_PASSWORD_RESET_SUCCESSFUL } from '../../utils/messages';
import { baseURL } from '../baseUrl';

const AuthState = props => {
  const loadingContext = useContext(LoadingContext);
  const { showLoading, hideLoading } = loadingContext;

  const messageContext = useContext(MessageContext);
  const { showSuccessMsg, showErrorMsg } = messageContext;

  const initialState = {
    token: localStorage.getItem('token'),
    isAuthenticated: null,
  };

  const [state, dispatch] = useReducer(authReducer, initialState);

  // Register User
  const register = async formData => {
    showLoading();
    let isSuccess = false;
    try {
      await axios.post(baseURL + '/api/register', formData);

      showSuccessMsg(MSG_REGISTER_SUCCESS);
      isSuccess = true;

    } catch (err) {
      showErrorMsg(REGISTER_FAIL, err);
      dispatch({
        type: REGISTER_FAIL
      });
    }

    hideLoading();
    return isSuccess;
  };

  // Login User
  const login = async (formData, rememberMe) => {
    showLoading()

    try {
      const res = await axios.post(baseURL + '/api/login', formData);

      dispatch({
        type: LOGIN_SUCCESS,
        payload: res.data,
        rememberMe
      });

    } catch (err) {

      showErrorMsg(LOGIN_FAIL, err);
      dispatch({
        type: LOGIN_FAIL
      });
    }

    hideLoading();
  };

  // Send password reest link
  const sendPasswordResetEmail = async (formData) => {
    showLoading()
    let isSuccess = false;

    try {
      await axios.post(baseURL + '/api/request-password-reset', formData);

      showSuccessMsg(MSG_PASSWORD_RESET_EMAIL)
      isSuccess = true;
    } catch (err) {

      showErrorMsg(SEND_EMAIL_RESET_PASSWORD_FAIL, err);
      dispatch({
        type: SEND_EMAIL_RESET_PASSWORD_FAIL
      });
    }

    hideLoading();
    return isSuccess;
  }

  // Reset password
  const resetPassword = async (formData) => {
    showLoading()
    let isSuccess = false;

    try {
      await axios.post(baseURL + '/api/password-reset', formData);

      showSuccessMsg(MSG_PASSWORD_RESET_SUCCESSFUL)
      isSuccess = true;
    } catch (err) {

      showErrorMsg(PASSWORD_RESET_FAIL, err);
      dispatch({
        type: PASSWORD_RESET_FAIL
      });
    }

    hideLoading();
    return isSuccess;
  }

  const setAuthentication = () => {
    if (!localStorage.getItem('token'))
      return;

    setAuthToken(localStorage.token);
    dispatch({
      type: SET_AUTH
    })

  }

  // Logout
  const logout = () => dispatch({ type: LOGOUT });

  return (
    <AuthContext.Provider
      value={{
        token: state.token,
        isAuthenticated: state.isAuthenticated,
        register,
        login,
        setAuthentication,
        logout,
        sendPasswordResetEmail,
        resetPassword
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthState;
