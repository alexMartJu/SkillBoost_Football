import React from 'react';
import './Footer.css'; // Import CSS file for styles

const Footer = () => {
    return (
        <footer className="footer text-white py-5">
            <div className="container text-center">
                {/* Copyright text */}
                <p className="mb-0">
                    &copy; 2024 ZenithZone. Todos los derechos reservados.
                </p>
                {/* Social links */}
                <div className="social-links mt-3">
                    <a href="#" className="text-white mx-2">
                        <i className="fab fa-facebook"></i> Facebook
                    </a>
                    <a href="#" className="text-white mx-2">
                        <i className="fab fa-twitter"></i> Twitter
                    </a>
                    <a href="#" className="text-white mx-2">
                        <i className="fab fa-instagram"></i> Instagram
                    </a>
                </div>
            </div>
        </footer>
    );
};

export default Footer;