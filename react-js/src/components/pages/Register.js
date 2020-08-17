import React, { useContext } from 'react'
import { Form, Button, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import FormInput from '../html/FormInput';
import FormBanner from '../html/FormBanner';
import { Formik } from 'formik';
import { validationSchema, initialValues } from '../../validation/registerValidation';
import AuthContext from '../../context/auth/authContext';
import BgDarkOverlay from '../layout/BgDarkOverlay';

const Register = (props) => {
    const authContext = useContext(AuthContext);
    const { register } = authContext;

    const onSubmit = async (formData) => {
        const { firstName, lastName, email, password } = formData;
        const isSuccess = await register({
            firstName,
            lastName,
            email,
            password
        });

        if (isSuccess)
            props.history.push('/login');

    }

    return (
        <BgDarkOverlay>
            <FormBanner icon="fa-user-plus" />
            <Formik
                validationSchema={validationSchema}
                onSubmit={onSubmit}
                initialValues={initialValues}>
                {({
                    handleSubmit,
                    handleChange,
                    handleBlur,
                    values,
                    errors,
                    touched,
                    isValid,
                    dirty
                }) => (
                        <Form noValidate onSubmit={handleSubmit}>
                            <Row>
                                <FormInput
                                    className="col-12 col-md-6"
                                    name="firstName"
                                    label="First name"
                                    type="text"
                                    placeHolder="Enter first Name"
                                    value={values.firstName}
                                    error={errors.firstName}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    isInvalid={touched.firstName && !!errors.firstName}
                                />
                                <FormInput
                                    className="col-12 col-md-6"
                                    name="lastName"
                                    label="Last name"
                                    type="text"
                                    placeHolder="Enter last Name"
                                    value={values.lastName}
                                    error={errors.lastName}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    isInvalid={touched.lastName && !!errors.lastName}
                                />
                            </Row>
                            <FormInput
                                name="email"
                                label="Email address"
                                type="email"
                                placeHolder="Enter email address"
                                value={values.email}
                                error={errors.email}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.email && !!errors.email}
                            />
                            <FormInput
                                name="password"
                                label="Password"
                                type="password"
                                placeHolder="Enter password"
                                value={values.password}
                                error={errors.password}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.password && !!errors.password}
                            />
                            <FormInput
                                className="mb-4"
                                name="password2"
                                label="Confirm Password"
                                type="password"
                                placeHolder="Enter confirm password"
                                value={values.password2}
                                error={errors.password2}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.password2 && !!errors.password2}
                            />
                            <Form.Group className="mb-3" controlId="validationFormik05">
                                <Button
                                    variant="primary"
                                    disabled={!(isValid && dirty)}
                                    type="submit"
                                    block>
                                    SIGN UP
                                </Button>
                            </Form.Group>
                            <Form.Group controlId="validationFormik06">
                                <span className="text-sm" style={{ paddingTop: "2px" }}>
                                    Already have an account? &nbsp;
                                </span>
                                <Link
                                    className="font-weight-bold text-lg text-warning"
                                    to="/login">
                                    Sign In
                                </Link>
                            </Form.Group>
                        </Form>
                    )
                }
            </Formik>
        </BgDarkOverlay>
    )
}

export default Register
