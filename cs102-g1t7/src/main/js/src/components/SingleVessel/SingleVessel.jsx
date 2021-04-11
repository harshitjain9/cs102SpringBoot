import React, { useState, useEffect } from "react";
import { Alert, Button } from "react-bootstrap";
import "./SingleVessel.css";
import axios from "axios";
import { connect } from "react-redux";

function SingleVessel({ match, auth }) {
    const [item, setItem] = useState({
        abbrVslM: "",
        avg_SPEED: "",
        berthN: "",
        bthgDt: "",
        count: "",
        displayColor: "",
        distance_TO_GO: "",
        firstBthgDt: "",
        fullInVoyN: "",
        fullVslM: "",
        inVoyN: "",
        is_PATCHING_ACTIVATED: "",
        max_SPEED: "",
        outVoyN: "",
        patching_PREDICTED_BTR: "",
        predicted_BTR: "",
        shiftSeqN: null,
        status: "",
        unbthgDt: "",
        vessel_NAME: "",
        voyage_CODE_INBOUND: "",
        vsl_VOY: ""
    });
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

    return <div className="law-firm-main-container">
        <div className="container">
            <h1 className="heading">{item.vessel_NAME}</h1>
            <div class="row custom-row">
                <div class="col-6">
                    <p><span className="category">Incoming Voyage Number:</span> {item.inVoyN == null ? "N/A" : item.inVoyN}</p>
                    <p><span className="category">Outgoing Voyage Number:</span> {item.outVoyN == null ? "N/A" : item.outVoyN}</p>
                    <p><span className="category">Average Speed:</span> {item.avg_SPEED == null ? "N/A" : item.avg_SPEED}</p>
                    <p><span className="category">Maximum Speed:</span> {item.max_SPEED == null ? "N/A" : item.max_SPEED}</p>
                    <p><span className="category">Berth Number:</span> {item.berthN == null ? "N/A" : item.berthN}</p>
                    <p><span className="category">Distance To Go:</span> {item.distance_TO_GO == null ? "N/A" : item.distance_TO_GO} </p>
                    {item.is_PATCHING_ACTIVATED == 0 ? <p><span className="category">Berthing Time: </span> {item.predicted_BTR == null ? "N/A" : item.predicted_BTR} </p> : <p><span className="category">Berthing Time</span> {item.patching_PREDICTED_BTR == null ? "N/A" : item.patching_PREDICTED_BTR} </p>}
                    <p><span className="category">Unberthing Time:</span> {item.unbthgDt == null ? "N/A" : item.unbthgDt} </p>
                    <p><span className="category">Status:</span> {item.status == null ? "N/A" : item.status} </p>

                </div>
                <div class="col-6">
                    <Button variant="success" onClick={addAlert}>
                        Add Alert
        </Button>
                    <br /> <br />
                    <Button variant="danger" onClick={deleteAlert}>
                        Delete Alert
        </Button>
                    <br /> <br />
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
