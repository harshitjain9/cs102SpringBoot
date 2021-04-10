import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Header from "../../../frontend/src/components/Header/Header.css";
import Footer from "../../../frontend/src/components/Footer/Footer.css";
import VesselSchedules from "../../../frontend/src/components/VesselSchedules/VesselSchedules.css";
import AlertsConfigured from "../../../frontend/src/components/AlertsConfigured/AlertsConfigured.css";
import AlertsTriggered from "../../../frontend/src/components/AlertsTriggered/AlertsTriggered.css";
import Login from "../../../frontend/src/components/LoginPage/Login.css";
import Registration from "../../../frontend/src/components/Registration/Registration.css";
import ForgotPassword from "../../../frontend/src/components/ForgotPassword/ForgotPassword.css";
import WebServicesSettings from "../../../frontend/src/components/WebServicesSettings/WebServicesSettings.css";
// import EmailSettings from "../../../frontend/src/components/EmailSettings/EmailSettings.css";
import SingleVessel from "../../../frontend/src/components/SingleVessel/SingleVessel.css";

export class App extends Component() {
  const React = require('react'); (1)
  const ReactDOM = require('react-dom'); (2)
  const client = require('./client'); (3)
  render() {
    return (
      <Router>
        <Header />
        <Switch>
          <Route path='/vesselSchedules' exact component={VesselSchedules} />
          <Route path='/vesselSchedules/:fullVslM/:inVoyN' component={SingleVessel} />
          <Route path='/alertsConfigured' component={AlertsConfigured} />
          <Route path="/alertsTriggered" component={AlertsTriggered} />
          <Route path="/login" component={Login} />
          <Route path="/logout" component={Login} />
          <Route path="/webServicesSettings" component={WebServicesSettings} />
          <Route path="/forgotPassword" component={ForgotPassword} />
          <Route path="/createAccount" component={Registration} />
          <Route path="/" exact component={VesselSchedules} />
        </Switch>
        <Footer />
      </Router>
    )

  }
}

export default App;
