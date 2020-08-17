import React, { Fragment } from 'react'
import Header from '../dashboard/Header';
import Items from '../dashboard/Items';
import AddItemFAB from '../dashboard/AddItemFAB';
import ModalAddItem from '../modal/ModalAddItem';


const Dashboard = (props) => {

    return (
        <Fragment>
            <Header />
            {/*
            TODO: complete filter
            <Filter />
           */}
            <Items />
            <AddItemFAB />
            <ModalAddItem />
        </Fragment >
    )
}

export default Dashboard;
