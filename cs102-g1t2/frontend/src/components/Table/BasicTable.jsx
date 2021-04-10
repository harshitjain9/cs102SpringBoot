import React, { useMemo } from "react";
import { useTable } from "react-table";
import { useHistory } from 'react-router-dom';

function BasicTable({columns, data, url}) {
  
  // const columns = useMemo(() => props.columns, []);
  // const data = useMemo(() => props.data, []);
  const tableInstance = useTable({
    columns,
    data
  })

  const history = useHistory();
    function handleRowClick(row) {
        history.push(`/${url}/${row.original._id}`);
    }

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;

  return (
    <table {...getTableProps()}>
      <thead>
        {headerGroups.map(headerGroup => (
          <tr {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map(column => (
              <th {...column.getHeaderProps()}>{column.render('Header')}</th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()}>
        {rows.map((row, i) => {
          prepareRow(row)
          return (
            <tr {...row.getRowProps()} onClick={() => handleRowClick(row)} >
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

export default BasicTable;