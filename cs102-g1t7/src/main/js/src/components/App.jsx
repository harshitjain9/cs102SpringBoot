import React from "react";
import { HashRouter as Router, Switch, Route } from "react-router-dom";
import Header from "./Header/Header.jsx";
import Footer from "./Footer/Footer.jsx";
import VesselSchedules from "./VesselSchedules/VesselSchedules.jsx";
import AlertsConfigured from "./AlertsConfigured/AlertsConfigured.jsx";
import AlertsTriggered from "./AlertsTriggered/AlertsTriggered.jsx";
import Login from "./LoginPage/Login.jsx";
import Registration from "./Registration/Registration.jsx";
import ForgotPassword from "./ForgotPassword/ForgotPassword.jsx";
import WebServicesSettings from "./WebServicesSettings/WebServicesSettings.jsx";
import EmailSettings from "./EmailSettings/EmailSettings.jsx";
import SingleVessel from "./SingleVessel/SingleVessel.jsx";

function App() {
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
  );
}

export default App;
