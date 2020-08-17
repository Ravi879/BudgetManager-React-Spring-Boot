import React, { useContext } from 'react'
import { Table } from 'react-bootstrap'
import PropTypes from 'prop-types'
import '../../../css/itemTable.css'
import ItemContext from '../../../context/item/itemContext';

const ItemTable = ({ category, items, initialItemNo }) => {
    const itemContext = useContext(ItemContext);
    const { deleteItem, setCurrent, clearCurrent, showModal } = itemContext;

    const onUpdateItem = (category, item) => {
        setCurrent({ ...item, category: category });
        showModal(true);
    }

    const onDeleteItem = (category, item) => {
        deleteItem(category, item.itemId);
        clearCurrent();
    }

    return (
        <div className="px-1 px-md-4 mt-2 text-center" style={{ minHeight: "250px" }}>
            {
            // eslint-disable-next-line
            items.length == 0 &&
                <div>
                    <p className="mt-4" style={{color:"#0000009a"}}> No {category} item available.</p>
                </div>}

            {items.length > 0 &&
                <Table striped bordered responsive style={{ overflow: "auto" }}>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th style={{ minWidth: "300px" }}>Description</th>
                            <th style={{ minWidth: "110px" }}>Date</th>
                            <th>Amount</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items.map((item, index) =>
                            <tr key={item.itemId}>
                                <td>{initialItemNo + index + 1}</td>
                                <td style={{ minWidth: "300px" }}>{item.description}</td>
                                <td>{item.date}</td>
                                <td>{item.amount}</td>
                                <td><img role="button" alt="edit-icon" className="btn-edit-item" onClick={(e) => onUpdateItem(category, item)} /></td>
                                <td><img role="button" alt="delete-icon" className="btn-delete-item" onClick={(e) => onDeleteItem(category, item)} /></td>
                            </tr>
                        )}
                    </tbody>
                </Table>
            }
        </div>
    )
}

ItemTable.propTypes = {
    category: PropTypes.string.isRequired,
    items: PropTypes.array.isRequired,
    initialItemNo: PropTypes.number.isRequired
}


export default ItemTable
