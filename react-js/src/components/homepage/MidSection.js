import React from 'react'
import { Container, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const MidSection = () => {
    return (
        <section>
            <Container>
                <Row>
                    <Col className="text-center pb-4 pt-5">
                        <h2 className="text-h2 font-weight-bold">Get started with
                        <div className="font-weight-bold"> Budget Manager</div>
                        </h2>
                        <p className="sub-text" style={{ color: "#0000009a", fontWeight: "400" }}>Keep your daily financial income and
                        expense calculated, you'll discover the effortless way to manage your financial allocation</p>
                        <Link to="/register" className="btn btn-primary mb-1">Create an account, it's free</Link>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default MidSection
