import {
  GET_ITEMS,
  ADD_ITEM,
  DELETE_ITEM,
  SET_CURRENT,
  CLEAR_CURRENT,
  UPDATE_ITEM,
  CLEAR_ITEMS,
  SHOW_MODAL,
  HIDE_MODAL,
} from '../types';

export default (state, action) => {
  switch (action.type) {

    case GET_ITEMS:
      return {
        ...state,
        incomes: action.payload.incomes,
        expenses: action.payload.expenses
      };

    case ADD_ITEM:
      return {
        ...state,
        [action.category]: [...state[action.category], action.payload]
      };

    case UPDATE_ITEM:
      return {
        ...state,
        [action.category]: state[action.category].map(item =>
          item.itemId === action.payload.itemId ? action.payload : item
        )
      };

    case DELETE_ITEM:
      return {
        ...state,
        [action.category]: state[action.category].filter(item =>
          item.itemId !== action.payload
        )
      };

    case CLEAR_ITEMS:
      return {
        ...state,
        incomes: [],
        expenses: [],
        current: []
      };

    case SHOW_MODAL:
      return {
        ...state,
        isModalShowing: true
      }
    case HIDE_MODAL:
      return {
        ...state,
        isModalShowing: false
      }

    case SET_CURRENT:
      return {
        ...state,
        current: action.payload
      };
    case CLEAR_CURRENT:
      return {
        ...state,
        current: null
      };

    default:
      return state;
  }
};
