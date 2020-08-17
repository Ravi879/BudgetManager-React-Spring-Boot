import React from 'react'
import { Container, Row, Col } from 'react-bootstrap';

const BgDarkOverlay = (props) => {
    return (
        <div className='dark-overlay-container text-white'>
            <div className='dark-overlay'>
                <Container className="mx-auto">
                    <Row className="justify-content-center">
                        <Col className="col-10 col-sm-8 col-md-6 col-lg-5">
                            {props.children}
                        </Col>
                    </Row>
                </Container>
            </div>
        </div>
    )
}

export default BgDarkOverlay
