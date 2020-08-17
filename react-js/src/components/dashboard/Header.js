import React, { useContext, useEffect, useState } from 'react'
import { Container, Row, Col } from 'react-bootstrap';
import ItemContext from '../../context/item/itemContext';
import { getItemTotal, getTotalExpensePercentage } from '../../utils/budget';
import '../../css/header-dashboard.css';

const Header = () => {
    const [budget, setBudget] = useState(0)
    const [totalIncome, setTotalIncome] = useState(0)
    const [totalExpense, setTotalExpense] = useState(0)
    const [expensePercentage, setExpensePercentage] = useState(0)

    const itemContext = useContext(ItemContext);
    const { incomes, expenses } = itemContext;


    useEffect(() => {
        const income = getItemTotal(incomes);
        const expense = getItemTotal(expenses);
        const percentage = getTotalExpensePercentage(income, expense);

        setTotalIncome(income);
        setTotalExpense(expense);
        setExpensePercentage(percentage);
        setBudget(income - expense);

    }, [incomes, expenses]);

    return (
        <div className='home-section text-white'>
            <div className='dark-over' style={{ paddingTop: "140px" }}>
                <Container>
                    <h2 className="text-center mb-0"> Available Budget in <span> Month</span> </h2>
                    <h1 className="display-5 text-center">{budget}</h1>
                    <Row className="py-2 px-3 mx-3 mx-sm-auto banner-green">
                        <Col className="text-left">INCOME</Col>
                        <Col className="text-left">{totalIncome}</Col>
                        <Col className="col-2"></Col>
                    </Row>
                    <Row className="py-2 px-3 my-2 mx-3 mx-sm-auto banner-red">
                        <Col className="text-left">EXPENSE</Col>
                        <Col className="text-left">{totalExpense}</Col>
                        <Col className="col-2 px-0 text-center" style={{ backgroundColor: "rgba(255, 255, 255, 0.2)" }} >
                            <span className="">{expensePercentage}</span> %
                        </Col>
                    </Row>
                </Container>
            </div>
        </div>
    )
}

export default Header
