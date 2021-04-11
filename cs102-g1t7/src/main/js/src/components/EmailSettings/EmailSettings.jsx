import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./EmailSettings.css";

function EmailSettings() {
 function validateValues() {
    //need to work on this part
    return true;
 }
  function handleSubmit(event) {
    event.preventDefault();
  }
  return (
    <div className="EmailSettings">
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="apiKey">
          <Form.Label>Email Suffix</Form.Label>
          <Form.Control type="text"/>
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
}
export default EmailSettings;







