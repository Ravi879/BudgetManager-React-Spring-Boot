import React, { useContext } from 'react'
import BgDarkOverlay from '../layout/BgDarkOverlay'
import FormBanner from '../html/FormBanner'
import { Formik } from 'formik';
import { Form, Button, Row } from 'react-bootstrap';
import { validationSchema, initialValues } from '../../validation/resetPasswordValidation';
import FormInput from '../html/FormInput';
import AuthContext from '../../context/auth/authContext';
import { Link } from 'react-router-dom';
import { getQueryStringParams } from '../../utils/stringUtils';
import MessageContext from '../../context/message/messageContext';

const ResetPassword = (props) => {
    const authContext = useContext(AuthContext);
    const { resetPassword } = authContext;
    const messageContext = useContext(MessageContext);
    const { showErrorMsg } = messageContext;


    const onSubmit = async (formData) => {
        const params = getQueryStringParams(props.location.search);
        if(!params["token"]){
            showErrorMsg("INVALID_RESET_PASSWORD_LINK", "Invalid reset password link.");
        }

        const token = params.token;
        const { password } = formData;
        const isSuccess = await resetPassword({
            token,
            password
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
                            <h2 className="mt-4 mb-4 text-center">Reset password</h2>
                            <FormInput
                                name="password"
                                label="New Password"
                                type="password"
                                placeHolder="Enter your new password"
                                value={values.password}
                                error={errors.password}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.password && !!errors.password}
                            />
                            <FormInput
                                className="mb-4"
                                name="password2"
                                label="Confirm new Password"
                                type="password"
                                placeHolder="Confirm new password"
                                value={values.password2}
                                error={errors.password2}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                isInvalid={touched.password2 && !!errors.password2}
                            />
                            <Form.Group className="mb-3">
                                <Button
                                    variant="primary"
                                    disabled={!(isValid && dirty)}
                                    type="submit"
                                    block>
                                    Submit
                                </Button>
                            </Form.Group>
                        </ Form>
                    )
                }
            </Formik>
            <Row className="px-3">
                <Link
                    className="font-weight-bold text-lg text-warning"
                    to="/login">
                    Back to sign in
                </Link>
            </Row>
        </BgDarkOverlay>
    )
}

export default ResetPassword
