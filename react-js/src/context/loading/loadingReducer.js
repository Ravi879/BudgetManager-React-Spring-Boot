import {
  SHOW_LOADING,
  HIDE_LOADING
} from '../types';

export default (state, action) => {
  switch (action.type) {

    case SHOW_LOADING:
      return {
        ...state,
        loading: true
      }

    case HIDE_LOADING:
      return {
        ...state,
        loading: false
      }

    default:
      return state;
  }
};
