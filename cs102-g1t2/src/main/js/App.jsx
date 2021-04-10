import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Header from "../../../frontend/src/components/Header/Header.jsx";
import Footer from "../../../frontend/src/components/Footer/Footer.jsx";
import VesselSchedules from "../../../frontend/src/components/VesselSchedules/VesselSchedules.jsx";
import AlertsConfigured from "../../../frontend/src/components/AlertsConfigured/AlertsConfigured.jsx";
import AlertsTriggered from "../../../frontend/src/components/AlertsTriggered/AlertsTriggered.jsx";
import Login from "../../../frontend/src/components/LoginPage/Login.jsx";
import Registration from "../../../frontend/src/components/Registration/Registration.jsx";
import ForgotPassword from "../../../frontend/src/components/ForgotPassword/ForgotPassword.jsx";
import WebServicesSettings from "../../../frontend/src/components/WebServicesSettings/WebServicesSettings.jsx";
// import EmailSettings from "../../../frontend/src/components/EmailSettings/EmailSettings.jsx";
import SingleVessel from "../../../frontend/src/components/SingleVessel/SingleVessel.jsx";

export class App extends Component() {
  // const React = require('react'); (1)
  // const ReactDOM = require('react-dom'); (2)
  // const client = require('./client'); (3)
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
