import React, { useReducer, useContext } from 'react';
import ItemContext from './itemContext';
import itemReducer from './itemReducer';
import LoadingContext from '../loading/loadingContext';
import MessageContext from '../message/messageContext';
import axios from 'axios';
import {
  GET_ITEMS,
  SET_CURRENT,
  CLEAR_CURRENT,
  UPDATE_ITEM,
  CLEAR_ITEMS,
  ADD_ITEM,
  DELETE_ITEM,
  HIDE_MODAL,
  SHOW_MODAL,
  ERROR_GET_ITEMS,
  ERROR_ADD_ITEM,
  ERROR_DELETE_ITEM,
  ERROR_UPDATE_ITEM
} from '../types';
import { capitalizeFirstLetter } from '../../utils/stringUtils';
import { baseURL } from '../baseUrl';

const ItemState = props => {
  const loadingContext = useContext(LoadingContext);
  const { showLoading, hideLoading } = loadingContext;

  const messageContext = useContext(MessageContext);
  const { showInfoMsg, showErrorMsg } = messageContext;

  const initialState = {
    incomes: [],
    expenses: [],
    current: null,
    isModalShowing: false
  };

  const [state, dispatch] = useReducer(itemReducer, initialState);

  // Get Items
  const getItems = async () => {
    showLoading();

    try {
      const res = await axios.get(baseURL + '/api/items');
      dispatch({
        type: GET_ITEMS,
        payload: res.data
      });

    } catch (err) {
      showErrorMsg(ERROR_GET_ITEMS, err);
    }

    hideLoading();
  };

  // create item
  const addItem = async (category, item) => {
    showLoading();
    try {
      const res = await axios.post(baseURL + `/api/items/${category}`, item);

      dispatch({
        type: ADD_ITEM,
        payload: res.data,
        category: category + 's'
      });

      showInfoMsg(`New ${capitalizeFirstLetter(category)} added.`);

    } catch (err) {
      showErrorMsg(ERROR_ADD_ITEM, err);
    }

    hideLoading();
  };

  // Update item
  const updateItem = async (category, itemId, item) => {
    showLoading();
    try {
      const res = await axios.put(baseURL + `/api/items/${category}/${itemId}`,item);
      item["itemId"] = res.data.itemId;
      dispatch({
        type: UPDATE_ITEM,
        payload: res.data,
        category: category + 's'
      });

      showInfoMsg(`${capitalizeFirstLetter(category)} updated.`);

    } catch (err) {
      showErrorMsg(ERROR_UPDATE_ITEM, err);
    }

    hideLoading();
  };

  // Delete item
  const deleteItem = async (category, itemId) => {
    try {
      showLoading();
      await axios.delete(baseURL + `/api/items/${category}/${itemId}`);
      dispatch({
        type: DELETE_ITEM,
        payload: itemId,
        category: category + 's'
      });

      showInfoMsg(`${capitalizeFirstLetter(category)} deleted.`);

    } catch (err) {
      showErrorMsg(ERROR_DELETE_ITEM, err);
    }

    hideLoading();
  };

  // Show add item modal
  const showModal = () => dispatch({ type: SHOW_MODAL });

  // Hide add item Modal
  const hideModal = () => dispatch({ type: HIDE_MODAL });

  // Clear Items
  const clearItems = () => dispatch({ type: CLEAR_ITEMS });

  // Set current item
  const setCurrent = item => dispatch({ type: SET_CURRENT, payload: item });

  // Clear current item
  const clearCurrent = () => dispatch({ type: CLEAR_CURRENT });

  return (
    <ItemContext.Provider
      value={{
        incomes: state.incomes,
        expenses: state.expenses,
        current: state.current,
        isModalShowing: state.isModalShowing,
        addItem,
        updateItem,
        deleteItem,
        showModal,
        hideModal,
        setCurrent,
        clearCurrent,
        getItems,
        clearItems
      }}
    >
      {props.children}
    </ItemContext.Provider>
  );
};

export default ItemState;