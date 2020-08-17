import React, { Fragment, useEffect, useContext } from 'react';
import Header from '../homepage/Header';
import MidSection from '../homepage/MidSection';
import { getQueryStringParams } from '../../utils/stringUtils';
import MessageContext from '../../context/message/messageContext';
import { MSG_EMAIL_VERIFIED } from '../../utils/messages';

const Home = (props) => {
    const messageContext = useContext(MessageContext);
    const { showSuccessMsg } = messageContext;

    useEffect(() => {
        const params = getQueryStringParams(props.location.search);
        // eslint-disable-next-line
        if (params["emailVerification"] == "true") {
            props.history.push('/login');
            showSuccessMsg(MSG_EMAIL_VERIFIED);
        }

        if(localStorage.getItem('token'))
            props.history.push("/dashboard");

        // eslint-disable-next-line
    }, [])

    return (
        <Fragment>
            <Header />
            <MidSection />
        </Fragment>
    )
}

export default Home;
