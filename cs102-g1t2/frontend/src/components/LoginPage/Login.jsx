import React, { useState, useEffect } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { connect } from "react-redux";
import { login } from "../../actions/authActions";
import { useHistory } from 'react-router-dom';
import "./Login.css";

const Login = ({ auth, login, error }) => {
  const history = useHistory();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  function validateForm() {
    return email.length > 0 && password.length > 0;
  }

  function resetLoginForm() {
    setEmail("");
    setPassword("");
  }

  function newAccount() {
    history.push('/createAccount');
  }

  function forgotPassword() {
    history.push('/forgotPassword');
  }

  function handleSubmit(event) {
    event.preventDefault();
    login(email, password);
  }

  useEffect(() => {
    if (auth.isAuthenticated === true) {
      if (auth.user.username == "admin@psa.com") {
        history.push("/webServicesSettings");
      } else {
        history.push("/");
      }
    }
  }, [auth]);

  useEffect(() => {
    if (error.status != null) {
      resetLoginForm();
      setErrorMsg("Invalid email and password");
    }
  }, [error]);



  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
        <Form.Group size="lg" controlId="email">
          <Form.Label>Email</Form.Label>
          <Form.Control
            autoFocus
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" variant="success" type="submit" disabled={!validateForm()}>
          Login
        </Button>
        <Button block size="lg" variant="info" onClick={newAccount}>
          Create New Account
        </Button>
        <Button block size="lg" variant="danger" onClick={forgotPassword}>
          Forgot Password
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
    login: (email, password) => dispatch(login(email, password))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);