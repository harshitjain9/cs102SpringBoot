import React from "react";
// This is a custom filter UI for selecting
// a unique option from a list
export const SelectColumnFilter = ({
    column: { filterValue, setFilter, preFilteredRows, id },
  }) => {
    // Calculate the options for filtering
    // using the preFilteredRows
    const options = React.useMemo(() => {
      const options = new Set()
      preFilteredRows.forEach(row => {
        options.add(row.values[id])
      })
      return [...options.values()]
    }, [id, preFilteredRows])


    return <select
        value={filterValue}
        onChange={e => {
          setFilter(e.target.value || undefined)
        }}
      >
        <option value="">All</option>
        {options.map((option, i) => (
          <option key={i} value={option}>
            {option}
          </option>
        ))}
      </select>

    // function onChange(value, event) {
    //     if (event.action === "select-option") {
    //         setFilter(["Nullam", "Vivamus"]);
    //     } else {
    //         setFilter(undefined);
    //     }
        
    // }

    // const options = [
    //     { label: 'Vivamus', value: 1},
    //     { label: 'Nullam', value: 2},
    //   ];
  
    // // Render a multi-select box
    // return (
    //     <ReactMultiSelectCheckboxes options={options} onChange={onChange}/>
      
    // )
  }

