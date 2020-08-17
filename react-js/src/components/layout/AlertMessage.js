import React, { useEffect, useContext } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from 'react-toastify';
import MessageContext from '../../context/message/messageContext';

const CloseButton = ({ closeToast }) => (
    <i
        className="fa fa-times fa-1x mt-3"
        onClick={closeToast}>
    </i>
);

const AlertMessage = (props) => {

    const messageContext = useContext(MessageContext);
    const { success, error, info, clearMessages } = messageContext;

    useEffect(() => {
        if (success) {
            toast.success(success, {
                autoClose: 10000
            });
        }
        if (error) {
            toast.error(error);
        }
        if (info) {
            toast.info(info, {
                autoClose: 3000
            });
        }

        setInterval(() => {
            clearMessages();
        }, 500);

        // eslint-disable-next-line
    }, [success, error, info])

    return (
        <ToastContainer
            position="top-center"
            autoClose={5000}
            hideProgressBar
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss={false}
            draggable={false}
            pauseOnHover={false}
            closeButton={CloseButton}
        />
    )
}

export default AlertMessage;
