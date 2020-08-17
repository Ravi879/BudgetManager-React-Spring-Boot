import React, { useContext } from 'react'
import BgDarkOverlay from '../layout/BgDarkOverlay'
import FormBanner from '../html/FormBanner'
import { Formik } from 'formik';
import { Form, Button, Row } from 'react-bootstrap';
import { validationSchema, initialValues } from '../../validation/forgotPasswordValidation';
import FormInput from '../html/FormInput';
import AuthContext from '../../context/auth/authContext';
import { Link } from 'react-router-dom';

const ForgotPassword = (props) => {
    const authContext = useContext(AuthContext);
    const { sendPasswordResetEmail } = authContext;

    const onSubmit = async (formData) => {
        const { email } = formData;
        const isSuccess = await sendPasswordResetEmail({
            email
        });

        if (isSuccess)
            props.history.push('/login');

    }

    return (
        <BgDarkOverlay>
            <FormBanner icon="fa-lock" />
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
                            <h2 className="mt-4 mb-4 text-center">Forgot your password?</h2>
                            <FormInput
                                name="email"
                                label="Please enter your registered email address to get reset password link."
                                type="email"
                                placeHolder="Email address"
                                value={values.email}
                                error={errors.email}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.email && !!errors.email}

                            />
                            <Form.Group className="mb-3">
                                <Button
                                    variant="primary"
                                    disabled={!(isValid && dirty)}
                                    type="submit"
                                    block>
                                    Send
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
                    to="/login">
                    Back to sign in
                </Link>
            </Row>
        </BgDarkOverlay>

    )
}

export default ForgotPassword
