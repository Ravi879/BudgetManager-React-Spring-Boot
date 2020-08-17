import React, { useReducer } from 'react';
import LoadingContext from './loadingContext';
import loadingReducer from './loadingReducer';

import {
  SHOW_LOADING,
  HIDE_LOADING
} from '../types';

const LoadingState = props => {
  const initialState = {
    loading: false
  };

  const [state, dispatch] = useReducer(loadingReducer, initialState);

  // Show loading
  const showLoading = () => dispatch({ type: SHOW_LOADING })

  // Hide loading
  const hideLoading = () => {
    setTimeout(() => {
      dispatch({ type: HIDE_LOADING })
    }, 1000);
  }

  return (
    <LoadingContext.Provider
      value={{
        loading: state.loading,
        showLoading,
        hideLoading
      }}
    >
      {props.children}
    </LoadingContext.Provider>
  );
};

export default LoadingState;
