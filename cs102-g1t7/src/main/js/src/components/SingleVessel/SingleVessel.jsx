import React, { useState, useEffect } from "react";
import { Alert, Button } from "react-bootstrap";
import "./SingleVessel.css";
import axios from "axios";
import { connect } from "react-redux";
import CanvasJSReact from './canvasjs.react.js';
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

function SingleVessel({ match, auth }) {

    const [item, setItem] = useState({
        abbrVslM: null,
        avg_SPEED: 0,
        berthN: null,
        bthgDt: null,
        currentAvgSpeed: 0,
        current_PORT: null,
        current_PORT_COUNTRY: null,
        distance_TO_GO: null,
        fullInVoyN: null,
        fullVslM: null,
        inVoyN: null,
        is_PATCHING_ACTIVATED: null,
        last_PORT: null,
        last_PORT_COUNTRY: null,
        max_SPEED: null,
        next_PORT_COUNTRY: null,
        next_PORT_NAME: null,
        outVoyN: null,
        patching_PREDICTED_BTR: null,
        predicted_BTR: null,
        secondLastAvgSpeed: 0,
        shiftSeqN: null,
        status: null,
        thirdLastAvgSpeed: 0,
        unbthgDt: null,
        vessel_NAME: null,
        voyage_CODE_INBOUND: null,
        vsl_VOY: null,
    });
    const options = {
        title: {
            text: "History of average speeds"
        },
        data: [{
            type: "column",
            dataPoints: [
                { label: "Day Before", y: item.thirdLastAvgSpeed },
                { label: "Yesterday", y: item.secondLastAvgSpeed },
                { label: "Today", y: item.avg_SPEED }
            ]
        }]
    }
    const [errorMsg, setErrorMsg] = useState("");
    const [successMsg, setSuccessMsg] = useState("");

    useEffect(() => {
        fetchItems();
    }, [])

    function tokenConfig() {
        const token = auth.jwt;

        //Headers
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }

        //If token, then add to headers
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }

        return config;
    }

    function addAlert() {
        const body = JSON.stringify({
            "email": auth.user.username,
            "fullVslM": item.fullVslM,
            "inVoyN": item.inVoyN
        });
        axios.post("http://localhost:8080/saveAlertInDB/", body, tokenConfig())
            .then(res => {
                console.log(res.data);
                setSuccessMsg("Alert successfully added.")
                setErrorMsg("");
            })
            .catch(err => {
                setErrorMsg("Error adding the alert.");
                setSuccessMsg("");
            })
    }

    function deleteAlert() {
        const token = auth.jwt;
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        const data = {
            email: auth.user.username,
            fullVslM: item.fullVslM,
            inVoyN: item.inVoyN
        }
        axios.delete("http://localhost:8080/deleteAlert", { headers, data })
            .then(res => {
                setSuccessMsg("Alert successfully deleted.")
                setErrorMsg("");
            })
            .catch(err => {
                setErrorMsg("Error deleting the alert.");
                setSuccessMsg("");
            })
    }


    const fetchItems = async () => {
        const config = {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem('jwt')}`
            }
        };

        axios.get(`http://localhost:8080/vessels/${match.params.fullVslM}/${match.params.inVoyN}`, config)
            .then(response => response.data)
            .then(items => {
                setItem(items)
            })
    };

    //     abbrVslM: "A EXPLORER"
    // avg_SPEED: null
    // berthN: "S03"
    // bthgDt: "2021-04-10T08:45:00"
    // currentAvgSpeed: null
    // current_PORT: null
    // current_PORT_COUNTRY: null
    // distance_TO_GO: null
    // fullInVoyN: "AE080421"
    // fullVslM: "ASEAN EXPLORER"
    // inVoyN: "080421"
    // is_PATCHING_ACTIVATED: null
    // last_PORT: null
    // last_PORT_COUNTRY: null
    // max_SPEED: null
    // next_PORT_COUNTRY: null
    // next_PORT_NAME: null
    // outVoyN: "080421"
    // patching_PREDICTED_BTR: null
    // predicted_BTR: null
    // secondLastAvgSpeed: null
    // shiftSeqN: "1"
    // status: "ALONGSIDE"
    // thirdLastAvgSpeed: null
    // unbthgDt: "2021-04-12T12:00:00"
    // vessel_NAME: null
    // voyage_CODE_INBOUND: null
    // vsl_VOY: null

    return <div className="law-firm-main-container">
        <div className="container">
            <h1 className="heading">{item.fullVslM == null ? "" : item.fullVslM}</h1>
            <div class="row custom-row">
                <div class="col-2"></div>
                <div class="col-8">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Incoming Voyage Number</th>
                                <td>{item.inVoyN == null ? "N/A" : item.inVoyN}</td>
                            </tr>
                            <tr>
                                <th scope="row">Outgoing Voyage Number</th>
                                <td>{item.outVoyN == null ? "N/A" : item.outVoyN}</td>
                            </tr>
                            <tr>
                                <th scope="row">Average Speed</th>
                                <td>{item.avg_SPEED == null ? "N/A" : item.avg_SPEED}</td>
                            </tr>
                            <tr>
                                <th scope="row">Maximum Speed</th>
                                <td>{item.max_SPEED == null ? "N/A" : item.max_SPEED}</td>
                            </tr>
                            <tr>
                                <th scope="row">Berth Number</th>
                                <td>{item.berthN == null ? "N/A" : item.berthN}</td>
                            </tr>
                            <tr>
                                <th scope="row">Distance To Go</th>
                                <td>{item.distance_TO_GO == null ? "N/A" : item.distance_TO_GO}</td>
                            </tr>
                            {item.is_PATCHING_ACTIVATED == 0 ? <tr><th className="category">Berthing Time </th> <td>{item.predicted_BTR == null ? "N/A" : item.predicted_BTR} </td> </tr> : <tr><th className="category">Berthing Time</th> <td>{item.patching_PREDICTED_BTR == null ? "N/A" : item.patching_PREDICTED_BTR} </td></tr>}
                            <tr>
                                <th scope="row">Unberthing Time</th>
                                <td>{item.unbthgDt == null ? "N/A" : item.unbthgDt}</td>
                            </tr>
                            <tr>
                                <th scope="row">Last Port Country</th>
                                <td>{item.last_PORT_COUNTRY == null ? "N/A" : <img src={`https://www.countryflags.io/${item.last_PORT_COUNTRY}/flat/64.png`} />}</td>
                            </tr>
                            <tr>
                                <th scope="row">Last Port Name</th>
                                <td>{item.last_PORT == null ? "N/A" : item.last_PORT}</td>
                            </tr>
                            <tr>
                                <th scope="row">Current Port Country</th>
                                <td>{item.current_PORT_COUNTRY == null ? "N/A" : <img src={`https://www.countryflags.io/${item.current_PORT_COUNTRY}/flat/64.png`} />}</td>
                            </tr>
                            <tr>
                                <th scope="row">Current Port Name</th>
                                <td>{item.current_PORT == null ? "N/A" : item.current_PORT}</td>
                            </tr>
                            <tr>
                                <th scope="row">Next Port Country</th>
                                <td>{item.next_PORT_COUNTRY == null ? "N/A" : <img src={`https://www.countryflags.io/${item.next_PORT_COUNTRY}/flat/64.png`} />}</td>
                            </tr>
                            <tr>
                                <th scope="row">Next Port Name</th>
                                <td>{item.next_PORT_NAME == null ? "N/A" : item.next_PORT_NAME}</td>
                            </tr>
                            <tr>
                                <th scope="row">Status</th>
                                <td>{item.status == null ? "N/A" : item.status}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div>
                        <CanvasJSChart options={options}
                        /* onRef = {ref => this.chart = ref} */
                        />
                    </div>
                </div>
                <div class="col-2">
                    <br />
                    <Button variant="success" block onClick={addAlert}>Add Alert</Button>
                    <br />
                    <Button variant="danger" block onClick={deleteAlert}>Delete Alert</Button>
                    <br /><br />
                    {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
                    {successMsg && <Alert variant="success">{successMsg}</Alert>}
                </div>
            </div>

        </div>
    </div>
}


const mapStateToProps = (state) => ({
    auth: state.auth
});

const mapDispatchToProps = dispatch => {
    return {

    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SingleVessel);
