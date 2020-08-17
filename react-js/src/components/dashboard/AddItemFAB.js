import React, { Fragment, useContext } from 'react'
import "../../css/fab-button.css";
import { Button } from 'react-bootstrap';
import ItemContext from '../../context/item/itemContext';

const AddItemFAB = () => {
    const itemContext = useContext(ItemContext);
    const { showModal } = itemContext;

    return (
        <Fragment>
            <div className="btn-fab-container">
                <Button
                    variant="primary"
                    size="lg"
                    className='btn-fab'
                    onClick={showModal}>
                    <i className='fa fa-1x fa-plus'></i>
                </Button>
            </div>
        </Fragment>
    )
}

export default AddItemFAB
