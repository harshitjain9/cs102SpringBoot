import React, { useState } from "react";
import { useAsyncDebounce } from "react-table";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

export const GlobalFilter = ({ filter, setFilter, placeholder }) => {
    const [value, setValue] = useState(filter);
    const onClick = () => {};
    const onChange = useAsyncDebounce(value => {
        setFilter(value || undefined)
    }, 1000)
    return (
        <div className="search-bar-section">
            <div className="container">
                <div style={{"display": "flex"}}>
                    <input value={value || ""} onChange={e => {
                        setValue(e.target.value)
                        onChange(e.target.value)
                    }} className="form-control col-11 ml-sm-4 mr-sm-2" type="text" placeholder={placeholder} aria-label="Search" />
                    <button className="btn search-button" onClick={onClick}><FontAwesomeIcon icon={faSearch} /></button>
                </div>
            </div>
        </div>
    )
}