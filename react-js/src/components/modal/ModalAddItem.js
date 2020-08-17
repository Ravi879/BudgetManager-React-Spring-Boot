import React, { useState, useContext, useEffect } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import moment from 'moment';
import ItemContext from '../../context/item/itemContext';
import { Formik } from 'formik';
import { validationSchema } from '../../validation/itemValidation';

const ModalAddItem = () => {
    const itemContext = useContext(ItemContext);
    const { addItem, updateItem, clearCurrent, current, isModalShowing, hideModal } = itemContext;
    const [isItemUpdate, setIsItemUpdate] = useState(false);

    const [initialValues, setInitialValues] = useState({
        description: '',
        amount: '',
        date: moment().format('yyyy-MM-DD'),
        category: ''
    });

    useEffect(() => {
        if (current != null && current.itemId !== undefined) {
            setInitialValues(current);
            setIsItemUpdate(true);
        } else {
            setInitialValues({
                description: '',
                amount: '',
                date: moment().format('yyyy-MM-DD'),
                category: ''
            });
            setIsItemUpdate(false);
        }
        // eslint-disable-next-line
    }, [current]);

    const onSubmit = formData => {
        const { category, description, amount, date } = formData;
        if (isItemUpdate) {
            updateItem(category, current.itemId, { description, amount, date });
        } else {
            addItem(category, { description, amount, date });
        }
        onHide();
    };

    const onHide = () => {
        hideModal();
        clearCurrent();
    }

    return (
        <Modal
            centered
            show={isModalShowing}
            onHide={onHide}
            backdrop="static"
            keyboard={false}
            aria-labelledby='modal-add-item'>
            <Modal.Header closeButton className="bg-secondary text-white">
                <Modal.Title id='modal-add-item'>{isItemUpdate ? `Update Item` : `Add Item`}</Modal.Title>
            </Modal.Header>
            <Formik
                enableReinitialize
                validationSchema={validationSchema}
                onSubmit={onSubmit}
                initialValues={initialValues}>
                {({
                    handleSubmit,
                    handleChange,
                    values,
                    errors,
                    dirty,
                    isValid,
                    touched,
                    handleBlur
                }) => (
                        <Form noValidate onSubmit={handleSubmit}>
                            <Modal.Body>
                                <Form.Group controlId="ControlSelect2">
                                    <Form.Label>Choose category</Form.Label>
                                    <Form.Control
                                        as="select"
                                        custom
                                        name="category"
                                        disabled={isItemUpdate}
                                        value={values.category}
                                        error={errors.category}
                                        onChange={handleChange}
                                        onBlur={handleBlur}
                                        isInvalid={touched.category && !!errors.category}>
                                        <option>Select</option>
                                        <option value="income">Income</option>
                                        <option value="expense">Expense</option>
                                    </Form.Control>
                                    <Form.Control.Feedback type="invalid" className="my-0" style={{ color: "#ffa481" }}>
                                        {errors.category}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group controlId="ControlInput1">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Hose Rent"
                                        name="description"
                                        value={values.description}
                                        error={errors.description}
                                        onChange={handleChange}
                                        onBlur={handleBlur}
                                        isInvalid={touched.description && !!errors.description} />
                                    <Form.Control.Feedback type="invalid" className="my-0" style={{ color: "#ffa481" }}>
                                        {errors.description}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group controlId="ControlInput3">
                                    <Form.Label>Amount</Form.Label>
                                    <Form.Control
                                        type="number"
                                        placeholder="10000"
                                        name="amount"
                                        value={values.amount}
                                        error={errors.amount}
                                        onChange={handleChange}
                                        onBlur={handleBlur}
                                        isInvalid={touched.amount && !!errors.amount} />
                                    <Form.Control.Feedback type="invalid" className="my-0" style={{ color: "#ffa481" }}>
                                        {errors.amount}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group controlId="ControlInput4">
                                    <Form.Label>Date</Form.Label>
                                    {/* its a hack, defaultValue will not set if its not in formate, yyyy-MM-DD, ex, 2020-05-05     */}
                                    <Form.Control
                                        type="date"
                                        name="date"
                                        value={values.date}
                                        error={errors.date}
                                        onChange={handleChange}
                                        onBlur={handleBlur}
                                        isInvalid={touched.date && !!errors.date} />
                                    <Form.Control.Feedback type="invalid" className="my-0" style={{ color: "#ffa481" }}>
                                        {errors.date}
                                    </Form.Control.Feedback>
                                </Form.Group>
                            </Modal.Body>

                            <Modal.Footer className="justify-content-center">
                                <Button variant="primary" disabled={!(dirty)} type="submit" block>
                                    Submit
                                </Button>
                                <Button variant="outline-secondary" onClick={onHide} block>
                                    Cancel
                                </Button>
                            </Modal.Footer>
                        </Form>
                    )
                }
            </Formik>
        </Modal>
    );
};

export default ModalAddItem;
