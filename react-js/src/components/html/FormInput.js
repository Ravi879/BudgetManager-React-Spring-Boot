import React from 'react'
import { Form } from 'react-bootstrap';
import PropTypes from 'prop-types';
import '../../css/form-input.css';

const FormInput = ({ className, name, label, type, placeHolder, error, value, onChange, isInvalid, onBlur }) => {
    return (
        <Form.Group className={className} controlId={"form-group-" + name}>
            <Form.Label className="mb-1 h6">{label}</Form.Label>
            <Form.Control type={type} className="input-lg" name={name} placeholder={placeHolder}
            isInvalid={isInvalid}
            value={value}
            onChange={onChange}
            onBlur={onBlur}>
            </Form.Control>
            <Form.Control.Feedback type="invalid" className="my-0" style={{ color: "#ffa481" }}>
                {error}
            </Form.Control.Feedback>
        </Form.Group>
    )
}

FormInput.defaultProps = {
    className: 'mb-3',
    isInvalid: false
};

FormInput.propTypes = {
    className: PropTypes.string,
    isInvalid: PropTypes.bool,
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    placeHolder: PropTypes.string.isRequired,
    error: PropTypes.string,
    value: PropTypes.string,
    onChange: PropTypes.func.isRequired,
    onBlur: PropTypes.func.isRequired,
};


export default FormInput
