import React from 'react';
import { Modal } from 'react-bootstrap';

const About = (props) => {
    return (
        <Modal
            {...props} centered
            aria-labelledby='modal-add-item'>

            <Modal.Header closeButton className="bg-secondary text-white">
                <Modal.Title id='modal-add-item'>About</Modal.Title>
            </Modal.Header>

            <Modal.Body className="text-center">
                <h4>Budget Manager</h4>
                <div className="px-4">
                    <hr></hr>
                </div>
                <p> A simple budget manager web application to calculate
            income and expense. </p>
                <p>It's an open source project developed for learning purpose.</p>
            </Modal.Body>

            <Modal.Footer className="justify-content-center">
                <a href="https://www.github.com/Ravi879/BudgetManager-React-Spring-Boot" className="btn btn-primary" target=" blank">
                    Looking for source code &nbsp;&nbsp; <i className="fa fa-github"></i>
                </a>
            </Modal.Footer>
        </Modal>
    );
};

export default About;
