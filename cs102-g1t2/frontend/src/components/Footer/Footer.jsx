import React, { useState } from 'react';
import {Link} from "react-router-dom";
import "./Footer.css"

function Footer() {
    return (
        <footer>
            <div className="container">
                <div className="row">
                    <div className="col-md-8">
                        <p> <a href = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"> About Us </a></p>
                        <p><a href = "https://www.youtube.com/watch?v=dQw4w9WgXcQ">Get Help </a></p>
                        <p> <a href = "https://www.youtube.com/watch?v=dQw4w9WgXcQ">Privacy </a></p>
                    <div className="col-md-4">
                        <p className="get-in-touch"> Get In Touch</p>
                        <p>https://www.singaporepsa.com/</p>
                        <a href="https://www.facebook.com/singaporepsa/"><i class="fab fa-facebook"></i></a>
                        <a href="https://in.linkedin.com/company/psa-corporation-limited"><i class="fab fa-twitter"></i></a>
                        <a href="https://www.youtube.com/channel/UC_H-UldgNe8hN3Hh1JhG-QA"><i class="fab fa-linkedin"></i></a>
                    </div>
                </div>
            </div>
            </div>
        </footer>
    );
};

export default Footer;