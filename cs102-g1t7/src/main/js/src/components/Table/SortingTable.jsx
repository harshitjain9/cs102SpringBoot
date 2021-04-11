import React, { useMemo } from "react";
import { useTable, useSortBy } from "react-table";
// import MOCK_DATA from "../AllFirms/MOCK_DATA.json";
// import { COLUMNS } from "../AllFirms/columns"

function SortingTable(props) {
    const columns = useMemo(() => props.columns, []);
    const data = useMemo(() => props.mockdata, []);
    const tableInstance = useTable({
        columns,
        data
    }, useSortBy)

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;
    console.log(data);

    return (
        <table {...getTableProps()}>
            <thead>
                {headerGroups.map(headerGroup => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map(column => (
                            <th {...column.getHeaderProps(column.getSortByToggleProps())}>{column.render('Header')}<span>{" "}{column.isSorted ? (column.isSortedDesc ? <i class="fas fa-sort-down"></i> : <i class="fas fa-sort-up"></i>) : <i class="fas fa-sort"></i>}</span></th>
                        ))}
                    </tr>
                ))}
            </thead>
            <tbody {...getTableBodyProps()}>
                {rows.map((row, i) => {
                    prepareRow(row)
                    return (
                        <tr {...row.getRowProps()}>
                            {row.cells.map(cell => {
                                return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                            })}
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}

export default SortingTable;