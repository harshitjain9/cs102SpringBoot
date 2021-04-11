import React, { useMemo, useState, useEffect } from "react";
import { useTable, useSortBy, useGlobalFilter, useFilters, usePagination } from "react-table";
import { ColumnFilter } from "./ColumnFilter.jsx";
import { GlobalFilter } from "./GlobalFilter.jsx";
import { NoFilter } from "./NoFilter.jsx"
import { useHistory } from 'react-router-dom';
import "./FilteringTable.css";


function FilteringTable({ columns, data, mockdata, placeholder, title, url, filterValue = "" }) {

    const history = useHistory();
    function handleRowClick(row) {
        if (url=="alertsTriggered") {
            return;
        }
        if (row.original.fullVslM != null || row.original.fullVslM != "") {
            history.push(`/${url}/${row.original.fullVslM}/${row.original.inVoyN}`);
        }
    }

    const defaultColumn = useMemo(() => {
        return {
            Filter: NoFilter
        }
    }, [])

    const filterTypes = useMemo(() => ({
        multiSelect: (rows, id, filterValues) => {
            if (filterValues.length === 0) return rows;
            return rows.filter((r) => filterValues.includes(r.values[id]));
        }
    }),
        []
    );

    const { getTableProps, getTableBodyProps, headerGroups, page, nextPage, previousPage, canNextPage, canPreviousPage, pageOptions, gotoPage, pageCount, setPageSize, prepareRow, state, setGlobalFilter, setFilter } = useTable({
        columns, data, defaultColumn, initialState: { pageIndex: 0 }, filterTypes
    }, useFilters, useGlobalFilter, useSortBy, usePagination);


    const { pageIndex, globalFilter, pageSize } = state;
    const [fValue, setFValue] = useState(filterValue);
    useEffect(() => {
        setGlobalFilter(fValue);
    }, [globalFilter, fValue]);

    return (
        <>
            <div className="table-page-header">
                <h1 className="table-page-heading">{title}</h1>
                <GlobalFilter filter={fValue} setFilter={setFValue} placeholder={placeholder} />
            </div>

            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map(headerGroup => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map(column => (
                                <th {...column.getHeaderProps(column.getSortByToggleProps())}>{column.render('Header')}<span>{" "}{column.canSort ? (column.isSorted ? (column.isSortedDesc ? <i class="fas fa-sort-down"></i> : <i class="fas fa-sort-up"></i>) : <i class="fas fa-sort"></i>) : null}</span>
                                    <div>
                                        {column.canFilter ? column.render("Filter") : null}
                                    </div>
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                    {page.map((row, i) => {
                        prepareRow(row)
                        return (

                            <tr {...row.getRowProps()} onClick={() => handleRowClick(row)} >

                                {row.cells.map(cell => {
                                    return <td {...cell.getCellProps()}>{cell.render('Cell')} </td>
                                })}

                            </tr>

                        )
                    })}
                </tbody>
            </table>

            <div style={{ "position": "relative", "width": "90%", "paddingTop": "20px", "paddingBottom": "20px" }}>
                <span>
                    Page{" "}
                    <strong>
                        {pageIndex + 1} of {pageOptions.length}
                    </strong>{" "}
                </span>
                <span>
                    | Go to page: {' '}
                    <input type="number" defaultValue={pageIndex + 1} onChange={e => {
                        const pageNumber = e.target.value ? Number(e.target.value) - 1 : 0;
                        gotoPage(pageNumber);
                    }} style={{ width: "50px" }} />
                </span>
                <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}>{"<<"}</button>
                <button onClick={() => previousPage()} disabled={!canPreviousPage}>Previous</button>
                <button onClick={() => nextPage()} disabled={!canNextPage}>Next</button>
                <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>{">>"}</button>

                <select value={pageSize} onChange={e => setPageSize(Number(e.target.value))} style={{ width: "150px", position: "absolute", "right": "0px" }}>
                    {
                        [10, 25, 50].map(pageSize => (
                            <option key={pageSize} value={pageSize}>
                                Show {pageSize}
                            </option>
                        ))
                    }
                </select>
            </div>

        </>

    )
}


export default FilteringTable;
