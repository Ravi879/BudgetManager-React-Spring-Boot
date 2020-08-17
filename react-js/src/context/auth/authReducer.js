import {
  REGISTER_FAIL,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
  SET_AUTH,
  PASSWORD_RESET_FAIL,
  SEND_EMAIL_RESET_PASSWORD_FAIL
} from '../types';
import setAuthToken from '../../utils/setAuthToken';

export default (state, action) => {
  switch (action.type) {

    case LOGIN_SUCCESS:
      if (action.rememberMe) {
        localStorage.setItem('token', action.payload.token);
      }
      setAuthToken(action.payload.token);
      return {
        ...state,
        ...action.payload,
        isAuthenticated: true
      };

    case SET_AUTH:
      return {
        ...state,
        isAuthenticated: true
      };

    case REGISTER_FAIL:
    case LOGIN_FAIL:
    case PASSWORD_RESET_FAIL:
    case SEND_EMAIL_RESET_PASSWORD_FAIL:
    case LOGOUT:
      localStorage.removeItem('token');
      return {
        ...state,
        token: null,
        isAuthenticated: false
      };

    default:
      return state;
  }
};
