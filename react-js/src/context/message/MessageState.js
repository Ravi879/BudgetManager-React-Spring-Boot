import React, { useReducer } from 'react';
import MessageContext from './messageContext';
import messageReducer from './messageReducer';

import {
  CLEAR_MESSAGES,
  SUCCESS_MESSAGE,
  INFO_MESSAGE,
  ERROR_MESSAGE
} from '../types';

const MessageState = props => {
  const initialState = {
    success: null,
    error: null,
    info: null
  };

  const [state, dispatch] = useReducer(messageReducer, initialState);

  // Success message
  const showSuccessMsg = (msg) => dispatch({ type: SUCCESS_MESSAGE, payload: msg });

  // Error message
  const showErrorMsg = (type, err) => {
    let message = err;
    if (err instanceof Array) {
      err.forEach(e => dispatch({ type: ERROR_MESSAGE, payload: e }));
    } else {
      dispatch({ type: ERROR_MESSAGE, payload: message });
    }

  }

  // Info message
  const showInfoMsg = (msg) => dispatch({ type: INFO_MESSAGE, payload: msg });

  // Clear all messages
  const clearMessages = (msg) => dispatch({ type: CLEAR_MESSAGES, });

  return (
    <MessageContext.Provider
      value={{
        success: state.success,
        error: state.error,
        info: state.info,
        showSuccessMsg,
        showInfoMsg,
        showErrorMsg,
        clearMessages
      }}
    >
      {props.children}
    </MessageContext.Provider>
  );
};

export default MessageState;
