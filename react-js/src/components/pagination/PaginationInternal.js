import React from 'react';
import { Pagination } from 'react-bootstrap';
import { createUltimatePagination, ITEM_TYPES } from 'react-ultimate-pagination';

export default createUltimatePagination({
  WrapperComponent: Pagination,

  itemTypeToComponent: {
    [ITEM_TYPES.PAGE]: ({ value, isActive }) => (
      <Pagination.Item data-value={value} active={isActive}>{value}</Pagination.Item>
    ),
    [ITEM_TYPES.ELLIPSIS]: ({ value, isActive, onClick }) => (
      <Pagination.Ellipsis data-value={value} onClick={onClick} />
    ),
    [ITEM_TYPES.FIRST_PAGE_LINK]: ({ isActive, onClick }) => (
      <Pagination.First data-value={1} disabled={isActive} onClick={onClick} />
    ),
    [ITEM_TYPES.PREVIOUS_PAGE_LINK]: ({ value, isActive, onClick }) => (
      <Pagination.Prev data-value={value} disabled={isActive} onClick={onClick} />
    ),
    [ITEM_TYPES.NEXT_PAGE_LINK]: ({ value, isActive, onClick }) => (
      <Pagination.Next data-value={value} disabled={isActive} onClick={onClick} />
    ),
    [ITEM_TYPES.LAST_PAGE_LINK]: ({ value, isActive, onClick }) => (
      <Pagination.Last data-value={value} disabled={isActive} onClick={onClick} />
    ),
  },
});