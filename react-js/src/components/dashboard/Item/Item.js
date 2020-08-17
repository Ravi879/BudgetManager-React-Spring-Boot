import React, { useState, useEffect } from 'react'
import ItemBanner from './ItemBanner';
import ItemTable from './ItemTable';
import TablePagination from './TablePagination';
import PropTypes from 'prop-types'

const Item = ({ bannerBackground, bannerName, items, category }) => {
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [tableItems, setTableItems] = useState([])
    const [pageSize, setPageSize] = useState(10)

    useEffect(() => {
        if (items.length > 10) {
            setPageSize(10);
            const pages = Math.ceil(items.length / 10);
            setTotalPages(pages);
            setCurrentPage(1);
        }
        setTableItems(items.slice(0, 10));
        // eslint-disable-next-line
    }, [items])

    const onClickPage = (event) => {
        const target = event.target;
        const pageNumber = target.dataset.value ? parseInt(target.dataset.value, 10) : parseInt(target.parentNode.dataset.value, 10);

        if (pageNumber) {
            const startIndex = parseInt((pageSize * (pageNumber - 1)));
            setTableItems(items.slice(startIndex, startIndex + pageSize));
            setCurrentPage(pageNumber);
        }
    }

    const onSelectPageSize = (event) => {
        const size = event.target.value;
        setPageSize(size);
        const pages = Math.ceil(items.length / size);
        setTotalPages(pages);
        setTableItems(items.slice(0, size));
        setCurrentPage(1);
    }

    return (
        <div>
            <ItemBanner
                background={bannerBackground}
                bannerName={bannerName} />
            <ItemTable
                category={category}
                items={tableItems}
                initialItemNo={(currentPage - 1) * pageSize} />
            {items.length > 10 &&
                <TablePagination
                    totalPages={totalPages}
                    currentPage={currentPage}
                    onClickPage={onClickPage}
                    onSelectPageSize={onSelectPageSize} />}
        </div>
    )

}

Item.propTypes = {
    bannerBackground: PropTypes.string.isRequired,
    bannerName: PropTypes.string.isRequired,
    category: PropTypes.string.isRequired,
    items: PropTypes.array.isRequired

}


export default Item;
