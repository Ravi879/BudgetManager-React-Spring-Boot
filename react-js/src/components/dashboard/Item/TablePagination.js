import React from 'react';
import { Form, Row, Col } from 'react-bootstrap';
import PaginationInternal from '../../pagination/PaginationInternal';
import PropTypes from 'prop-types'

const TablePagination = ({ totalPages, currentPage, onClickPage, onSelectPageSize }) => {


  return (
    <div className="px-1 px-md-4 ">
      <Row className="mx-0">
        <Col className="col-12 col-md-6">
          <Form.Group className="form-inline">
            <Form.Label className="mr-2">Row per page</Form.Label>
            <Form.Control as="select" custom defaultValue="10" onChange={onSelectPageSize} style={{ width: "70px" }}>
              <option value={10}>10</option>
              <option value={20}>20</option>
              <option value={30}>30</option>
              <option value={40}>40</option>
            </Form.Control>
          </Form.Group>
        </Col>

          <Col className="col-12 col-md-6">
            <div className="float-md-right">
              <PaginationInternal
                currentPage={currentPage}
                totalPages={totalPages}
                onClick={onClickPage}
                hidePreviousAndNextPageLinks
              />
            </div>
          </Col>

      </Row>
    </div>
  );

}

TablePagination.propTypes = {
  totalPages: PropTypes.number.isRequired,
  currentPage: PropTypes.number.isRequired,
  onClickPage: PropTypes.func.isRequired,
  onSelectPageSize: PropTypes.func.isRequired

}

export default TablePagination