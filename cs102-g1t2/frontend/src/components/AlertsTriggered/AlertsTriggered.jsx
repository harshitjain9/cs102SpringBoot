import React, { useState, useMemo, useEffect } from "react";
import FilteringTable from "../Table/FilteringTable";
import { COLUMNS } from "./columns";
import MOCK_DATA from "./MOCK_DATA.json";
import axios from "axios";
import {connect} from "react-redux";

function AlertsTriggered({auth}) {
    const [alertsTriggered, setAlertsTriggered] = useState([]);

    function modify(str) {
        var result = str.replace(/\s+/g, '');
        result = result.replaceAll("/", "");
        console.log(result);
        return result;
    }

    const config = {
        headers: {
            "Authorization": `Bearer ${localStorage.getItem('jwt')}`
        }
    };

    useEffect(() => {
        fetchItems();
    }, [])

    const fetchItems = async () => {  
        console.log("in the function");   
        setAlertsTriggered([]);    
        axios.get(`http://localhost:8080/alertsTriggered/${auth.user.username}`, config)
            .then(response =>  {
                return response.data;
            })
            .then(items => {
                console.log("items" ,items);
                setAlertsTriggered(items);   
        })     
        console.log("AT" , alertsTriggered);
    }


    const columns = useMemo(() => COLUMNS, []);
    const data = useMemo(() => MOCK_DATA, []);

    return <div className="table-page-main-container">
        <center>
            <FilteringTable columns={columns} data={alertsTriggered} placeholder="Search for the details of the vessel." title="Alerts Triggered" url="alertsTriggered" />
        </center>
    </div>
}

const mapStateToProps = (state) => ({
    auth: state.auth
  });
  
  const mapDispatchToProps = dispatch => {
    return {
      
    };
  };
  
  export default connect(mapStateToProps, mapDispatchToProps)(AlertsTriggered);
