import React, { useContext, useEffect } from 'react';
import { Row, Form, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import FormInput from '../html/FormInput';
import FormBanner from '../html/FormBanner';
import AuthContext from '../../context/auth/authContext';
import { Formik } from 'formik';
import { validationSchema, initialValues } from '../../validation/loginValidation';
import BgDarkOverlay from '../layout/BgDarkOverlay';

const Login = (props) => {
    const authContext = useContext(AuthContext);
    const { login, isAuthenticated } = authContext;

    useEffect(() => {
        // redirect to dashboard
        if (isAuthenticated) {
            props.history.push('/dashboard');
        }

        // eslint-disable-next-line
    }, [isAuthenticated]);

    const onSubmit = (formData) => {
        const { email, password, rememberMe } = formData;

        login({
            email,
            password
        }, rememberMe);
    }

    return (
        <BgDarkOverlay>
            <FormBanner icon="fa-sign-in" />
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
                        < Form noValidate onSubmit={handleSubmit}>
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

                            <Row className="mb-4 px-3">
                                <div className="custom-control custom-checkbox">
                                    {/* must be in following sequence, first Form.Control than, Form.Label, if changed its not work */}
                                    {/* id="rememberMe" is compulsory */}
                                    <Form.Control
                                        id="rememberMe"
                                        name="rememberMe"
                                        type="checkbox"
                                        className="custom-control-input"
                                        value={values.chk}
                                        onChange={handleChange}>
                                    </Form.Control>
                                    <Form.Label htmlFor="rememberMe" className="custom-control-label text-sm">Remember me</Form.Label>
                                </div>
                                <Link className="ml-auto mb-0 font-weight-bold text-lg" style={{ color: "#55f178" }}
                                    to="/forgot-password" >Forgot Password?</Link>
                            </Row>

                            <Form.Group className="mb-3">
                                <Button
                                    variant="primary"
                                    disabled={!(isValid && dirty)}
                                    type="submit"
                                    block>
                                    SIGN IN
                                </Button>
                            </Form.Group>

                        </ Form>
                    )
                }
            </Formik>
            <Row className="px-3">
                <span
                    className="text-sm"
                    style={{ paddingTop: "2px" }}>
                    Don't have an account? &nbsp;
                    </span>
                <Link
                    className="font-weight-bold text-lg text-warning"
                    to="/register">
                    Sign up
                </Link>
            </Row>
        </BgDarkOverlay>
    )
}

export default Login

