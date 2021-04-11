import React, { useState } from 'react';
import { text } from '@fortawesome/fontawesome-svg-core';
import { Link } from "react-router-dom";
import "./Footer.css"

function Footer() {
    return (
        <footer class="page-footer font-small blue pt-4" style={{ background: '#043c54', color: 'white', paddingTop: '100px' }}>

            <div class="container-fluid text-center text-md-left">


                <div class="row">

                    <div class="col-md-4 mt-md-0 mt-3">
                        {/* <h5 class="text-uppercase">Footer Content</h5> */}
                        <h3 style={{ paddingLeft: '100px' }}>Contact Us</h3>
                        <hr style={{ lineColor: 'white' }} />

                        <ul style={{ paddingLeft: '100px' }} class="list-unstyled">
                            <li>
                                <a href="mailto:psac_corpcomms@globalpsa.com" title="Email - opens in a mailbox" style={{ color: 'white' }}>
                                    <i class="fa fa-envelope" aria-hidden="true"></i> psac_corpcomms@globalpsa.com
                    </a>
                            </li>
                            <br class="seprator"></br>
                            <li>
                                <a href="tel:(65) 6274 7111" title="Call this no" style={{ color: 'white' }}>
                                    <i class="fa fa-phone" aria-hidden="true"></i> (65) 6274 7111
                        </a>
                                <p> </p>
                                <a style={{ color: 'white' }} title="Fax">
                                    <i class="fa fa-fax" aria-hidden="true"></i> (65) 6274 4261
                        </a>
                            </li>
                            <li class="seprator" />
                        </ul>

                    </div>

                    <hr class="clearfix w-100 d-md-none pb-3" />

                    <div class="col-md-4 mb-md-0 mb-3">
                        <img src="https://www.globalpsa.com/wp-content/uploads/PSA-Singapore-Achieves-Record-Breaking-Moves-in-a-Single-Call-scaled.jpg"
                            class="rounded img-fluid" />
                    </div>

                    <div class="col-md-4 mb-md-0 mb-3">
                        <div class="row">
                            <div class="col-md-2" />
                            <div class="col-md-10">
                                <h3 class="text text-align-center"> Connect With Us</h3>
                            </div>
                            <div class="col-md-3" />
                        </div>
                        <hr />
                        <div class="row" style={{ paddingLeft: '100px' }}>
                            <div class="social-bar-bottom" >
                                <a href='https://www.facebook.com/singaporepsa/' class="fb-ic" style={{ color: 'white' }}>
                                    <i class="fab fa-facebook-f fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                                </a>

                                <a href='https://in.linkedin.com/company/psa-corporation-limited' class="li-ic" style={{ color: 'white' }}>
                                    <i class="fab fa-linkedin-in fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                                </a>
                                <a href='https://www.youtube.com/channel/UC_H-UldgNe8hN3Hh1JhG-QA' class="yt-ic" style={{ color: 'white' }}>
                                    <i class="fab fa-youtube fa-lg white-text fa-2x"> </i>
                                </a>
                            </div>

                        </div>
                    </div>

                </div>

            </div>
            <br />
            <div class="footer-copyright text-center py-3">© 2021 Copyright:
            <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"> findMyShip.com</a>
            </div>
        </footer>
    );
};

export default Footer;