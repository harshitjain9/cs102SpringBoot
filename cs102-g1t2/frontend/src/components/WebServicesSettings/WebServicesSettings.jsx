import React, { useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import "./WebServicesSettings.css";
import axios from "axios";
import { connect } from "react-redux";

function WebServicesSettings({ auth }) {
  const [apiKey, setApiKey] = useState("");
  const [hours, setHours] = useState("");
  const [minutes, setMinutes] = useState("");
  const [dailyTime, setDailyTime] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [emailSuffix1, setEmailSuffix1] = useState("");
  const [emailSuffix2, setEmailSuffix2] = useState("");
  const [firstApi, setFirstApi] = useState("");
  const [secondApi, setSecondApi] = useState("");

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

  const updateInterval = () => {
    if (hours != "" && minutes != "") {
      var currentDayUpdate = ((parseInt(hours) * 60) + parseInt(minutes)) * 60 * 60;
      const body = JSON.stringify({ currentDayUpdate });
      axios.put("http://localhost:8080/webservice/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("Interval successfully updated.");
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error updating the interval.")
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the hours and minutes.");
      setSuccessMsg("");
    }
  }

  function calculateDailyUpdate() {
    var dailyHours = dailyTime.substring(0, 2);
    if (dailyHours.substring(0, 1) == "0") {
      dailyHours = dailyHours.substring(1);
    }
    var dailyMinutes = dailyTime.substring(3);
    if (dailyMinutes.substring(0, 1) == "0") {
      dailyMinutes = dailyMinutes.substring(1);
    }
    console.log(dailyHours, dailyMinutes);
    var dailyUpdate = "0 " + dailyMinutes + " " + dailyHours + " * * *";
    return dailyUpdate;
  }

  const updateDailyTime = () => {
    if (dailyTime != "") {
      var dailyUpdate = calculateDailyUpdate();
      console.log("DAILY UPDATE", dailyUpdate);
      const body = JSON.stringify({ dailyUpdate });
      axios.put("http://localhost:8080/webservice/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("Daily update successfully updated.");
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error updating the daily time.")
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the daily time.");
      setSuccessMsg("");
    }
  }

  const updateApiKey = () => {
    if (apiKey != "") {

      const body = JSON.stringify({ apiKey });
      axios.put("http://localhost:8080/webservice/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("API Key successfully updated.")
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error updating the API Key.");
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the new API Key.");
      setSuccessMsg("");
    }
  }

  const addEmailSuffix = () => {
    if (emailSuffix1 != "") {
      const body = JSON.stringify({
        "emailSuffix": emailSuffix1
      });
      axios.post("http://localhost:8080/emailSuffix/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("Email Suffix successfully added.")
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error adding the email suffix.");
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the email suffix.");
      setSuccessMsg("");
    }
  }

  const deleteEmailSuffix = () => {
    if (emailSuffix2 != "") {
      axios.delete(`http://localhost:8080/emailSuffix/${emailSuffix2}`, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("Email Suffix successfully deleted.")
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error deleting the email suffix.");
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the email suffix.");
      setSuccessMsg("");
    }
  }

  const updateFirstApi = () => {
    if (firstApi != "") {
      const body = JSON.stringify({ "firstApiServerName": firstApi });
      axios.put("http://localhost:8080/webservice/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("First API Server Name successfully updated.")
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error updating the server name for the first API.");
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the server name for the first API.");
      setSuccessMsg("");
    }
  }

  const updateSecondApi = () => {
    if (secondApi != "") {
      const body = JSON.stringify({ "secondApiServerName": secondApi });
      axios.put("http://localhost:8080/webservice/", body, tokenConfig())
        .then(res => {
          console.log(res.data);
          setSuccessMsg("Second API Server Name successfully updated.")
          setErrorMsg("");
        })
        .catch(err => {
          setErrorMsg("Error updating the server name for the second API.");
          setSuccessMsg("");
        })
    } else {
      setErrorMsg("Please fill in the server name for the second API.");
      setSuccessMsg("");
    }
  }

  return (
    <div className="Login">
      <Form>
        <Form.Group controlId="apiKey">
          {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
          {successMsg && <Alert variant="success">{successMsg}</Alert>}
          <Form.Label>API Key</Form.Label>
          <Form.Control
            autoFocus
            type="varchar"
            value={apiKey}
            onChange={(e) => setApiKey(e.target.value)} />
        </Form.Group>

        <Button variant="primary" onClick={updateApiKey}>
          Update API Key
        </Button>
        <br /><br />
        <Form.Group controlId="time">
          <Form.Label>Time</Form.Label>
          <Form.Control
            autoFocus
            type="time"
            value={dailyTime}
            onChange={(e) => {
              setDailyTime(e.target.value)
            }} />
        </Form.Group>
        <Button variant="info" onClick={updateDailyTime}>
          Update Daily Time for First API Call
        </Button>
        <br /><br />
        <Form.Group controlId="hours">
          <Form.Label>Hours</Form.Label>
          <Form.Control
            autoFocus
            type="number"
            value={hours}
            onChange={(e) => setHours(e.target.value)} />
        </Form.Group>
        <Form.Group controlId="minutes">
          <Form.Label>Minutes</Form.Label>
          <Form.Control
            autoFocus
            type="number"
            value={minutes}
            onChange={(e) => setMinutes(e.target.value)} />
        </Form.Group>

        <Button variant="dark" onClick={updateInterval}>
          Update Interval for Second API Call
        </Button>
        <br /><br />
        <Form.Group controlId="emailSuffix1">
          <Form.Label>Email Suffix (with @)</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={emailSuffix1}
            onChange={(e) => setEmailSuffix1(e.target.value)} />
        </Form.Group>

        <Button variant="success" onClick={addEmailSuffix}>
          Add Email Suffix
        </Button>
        <br /><br />
        <Form.Group controlId="emailSuffix1">
          <Form.Label>Email Suffix (with @)</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={emailSuffix2}
            onChange={(e) => setEmailSuffix2(e.target.value)} />
        </Form.Group>

        <Button variant="danger" onClick={deleteEmailSuffix}>
          Delete Email Suffix
        </Button>

        <br /><br />
        <Form.Group controlId="firstApi">
          <Form.Label>First API Server Name</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={firstApi}
            onChange={(e) => setFirstApi(e.target.value)} />
        </Form.Group>

        <Button variant="warning" onClick={updateFirstApi}>
            Update Server Name for API that gets list of vessels
        </Button>

        <br /><br />
        <Form.Group controlId="secondApi">
          <Form.Label>Second API Server Name</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={secondApi}
            onChange={(e) => setSecondApi(e.target.value)} />
        </Form.Group>

        <Button variant="secondary" onClick={updateSecondApi}>
        Update Server Name for API that gets details of a specific vessel
        </Button>



      </Form>
    </div>
  );
}
const mapStateToProps = (state) => ({
  auth: state.auth
});

const mapDispatchToProps = dispatch => {
  return {

  };
};

export default connect(mapStateToProps, mapDispatchToProps)(WebServicesSettings);







