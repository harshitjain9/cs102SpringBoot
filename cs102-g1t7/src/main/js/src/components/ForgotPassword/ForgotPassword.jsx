import React, { useState, useEffect } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { forgotPassword } from "../../actions/authActions";
import { connect } from "react-redux";
import { useHistory } from 'react-router-dom';
import "./forgotPassword.css";

function ForgotPassword({ auth, error, forgotPassword }) {
  const history = useHistory();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  function validateForm() {
    return email.length > 0 && password.length > 0 && password == newPassword && dateOfBirth != "";
  }

  function handleSubmit(event) {
    event.preventDefault();
    forgotPassword(email, password, dateOfBirth);
  }

  function resetForm() {
    setEmail("");
    setDateOfBirth("");
    setPassword("");
    setNewPassword("");
  }

  useEffect(() => {
    if (error.status != null) {
      resetForm();
      setErrorMsg("Invalid email.");
    }
  }, [error]);

  useEffect(() => {
    if (auth.passwordChanged == true) {
      history.push("/login");
    }
  }, [auth]);

  return (
    <div className="forgotPassword container">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="email">
          {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
          <Form.Label>Email</Form.Label>
          <Form.Control
            autoFocus
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="email">
          <Form.Label>Date Of Birth</Form.Label>
          <Form.Control
            autoFocus
            type="date"
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            autoFocus
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="newPassword">
          <Form.Label>New Password</Form.Label>
          <Form.Control
            autoFocus
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          Reset
        </Button>
      </Form>
    </div>
  );
}

const mapStateToProps = (state) => ({
  auth: state.auth,
  error: state.error
});

const mapDispatchToProps = dispatch => {
  return {
    forgotPassword: (email, password, dateOfBirth) => dispatch(forgotPassword(email, password, dateOfBirth))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(ForgotPassword);