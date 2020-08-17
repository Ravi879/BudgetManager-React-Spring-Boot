import React, { Fragment } from 'react'
import { Row } from 'react-bootstrap';
import PropTypes from 'prop-types';
import '../../css/form-header.css';

const FormBanner = ({icon}) => {
    return (
        <Fragment>
            <Row className="justify-content-center">
                <div className={"fa fa-2x "+icon} style={{ color: "#eee" }}></div>
            </Row>
            <Row className="mb-4 justify-content-center">
                <div className="line mt-3"></div>
            </Row>
        </Fragment>
    )
}

FormBanner.propTypes = {
    icon: PropTypes.string.isRequired,
}

export default FormBanner;
