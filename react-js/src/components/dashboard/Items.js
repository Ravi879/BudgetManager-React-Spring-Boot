import React, { Fragment, useContext, useEffect } from 'react'
import Item from './Item/Item';
import ItemContext from '../../context/item/itemContext';

const Items = () => {
    const itemContext = useContext(ItemContext);
    const { incomes, expenses, getItems } = itemContext;

    useEffect(() => {
        getItems();
        // eslint-disable-next-line
    }, []);

    return (
        <Fragment>
            <Item
                bannerBackground="bg-success"
                bannerName="Income"
                items={incomes}
                category={"income"} />
            <Item
                bannerBackground="bg-danger"
                bannerName="Expense"
                items={expenses}
                category={"expense"} />
        </Fragment>
    )
}

export default Items
