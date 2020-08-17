import {
  CLEAR_MESSAGES,
  SUCCESS_MESSAGE,
  INFO_MESSAGE,
  ERROR_MESSAGE
} from '../types';

export default (state, action) => {
  switch (action.type) {

    case SUCCESS_MESSAGE:
      return {
        ...state,
        success: action.payload
      }

    case INFO_MESSAGE:
      return {
        ...state,
        info: action.payload
      }

    case ERROR_MESSAGE:
      return {
        ...state,
        error: action.payload
      }

    case CLEAR_MESSAGES:
      return {
        ...state,
        success: null,
        info: null,
        error: null
      };

    default:
      return state;
  }
};
