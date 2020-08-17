import React, { useContext } from 'react'
import { Modal } from 'react-bootstrap';

import '../../css/loading.css';
import LoadingContext from '../../context/loading/loadingContext';

const Loading = () => {
    const loadingContext = useContext(LoadingContext);
    const { loading } = loadingContext;

    return (
        <Modal
            show={loading}
            backdrop="static"
            keyboard={false}
            animation={false}
            centered
            dialogClassName="loading-dialog"
            aria-labelledby='modal-loading'>
            <Modal.Body className="text-center">
                <i className="fa fa-spinner fa-spin" style={{ fontSize: "48px", color: "red" }}></i>
            </Modal.Body>
        </Modal>
    )
}

export default Loading
