import React from 'react'
import { Container, Form, Row, Button } from 'react-bootstrap'
import { toast } from 'react-toastify';

const Filter = () => {

    return (
        <section className="bg-dark text-white pt-4">
            <Container>
                <Form>
                    <Row className="justify-content-center">
                        <Form.Group className="col-12 col-md-3 col-lg-2" controlId="formGridState">
                            <Form.Control as="select" custom defaultValue="Choose category">
                                <option>category</option>
                                <option value="income">income</option>
                                <option value="expense">expense</option>
                            </Form.Control>
                        </Form.Group>

                        <Form.Group className="col col-lg-6" controlId="formGridState1">
                            <Form.Control type="text" name="query" placeholder="Item description"></Form.Control>
                        </Form.Group>

                        <Form.Group className="col-12 col-md-3 col-lg-2">
                            <Button variant="primary" onClick={(e) => toast.error("Test") } block>
                                Filter
                            </Button>
                        </Form.Group>
                    </Row>
                </Form>
            </Container>
        </section>
    )
}

export default Filter
