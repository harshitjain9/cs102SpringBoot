import React, { useState, useMemo, useEffect } from "react";
import FilteringTable from "../Table/FilteringTable";
import { COLUMNS } from "./columns";
import MOCK_DATA from "./MOCK_DATA.json";
import axios from "axios";
import {connect} from "react-redux";

function AlertsConfigured({auth}) {
    const [vessels, setVessels] = useState([]);

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
        setVessels([]);    
        axios.get(`http://localhost:8080/getAlertsAccordingToUser/${auth.user.username}`, config)
            .then(response =>  {
                return response.data;
            })
            .then(items => {
                items.forEach((alert)=> {
                    var fullVslM = alert.fullVslM;
                    console.log(fullVslM);
                    var inVoyN = alert.inVoyN;
                    console.log(inVoyN);
                    axios.get(`http://localhost:8080/vessels/${fullVslM}/${inVoyN}`, config)
                    .then(response => {
                        return response.data})
                    .then(item => {
                        setVessels(vessels => [...vessels, item])
                    });
                console.log("VESSELS ", vessels);
            })   
        })     
    }


    const columns = useMemo(() => COLUMNS, []);
    const data = useMemo(() => MOCK_DATA, []);

    return <div className="table-page-main-container">
        <center>
            <FilteringTable columns={columns} data={vessels} placeholder="Search for the details of the vessel." title="Alerts Configured" url="vesselSchedules" />
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
  
  export default connect(mapStateToProps, mapDispatchToProps)(AlertsConfigured);
