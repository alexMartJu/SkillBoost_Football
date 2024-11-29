import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import './Header.css';

const Header = () => {
    return (
        <header className="header fixed-top shadow-sm">
            <div className="container-fluid">
                <div className="d-flex justify-content-between align-items-center p-3">
                    
                    <Link to="/" className="text-color fw-bold h2 mb-0 ms-3">
                        <img src="/assets/logo_2.png" alt="Logo" className="logo" />
                    </Link>
                    <nav>
                        <ul className="nav me-5">
                            <li className="nav-item me-3">
                                <NavLink 
                                    to="/" 
                                    className={({ isActive }) => 
                                        isActive ? "nav-link text-color fw-bold fs-5 isActive" : "nav-link text-color fw-bold fs-5"
                                    }
                                >
                                    Home
                                </NavLink>
                            </li>
                            <li className="nav-item me-3">
                                <NavLink 
                                    to="/instalaciones" 
                                    className={({ isActive }) => 
                                        isActive ? "nav-link text-color fw-bold fs-5 isActive" : "nav-link text-color fw-bold fs-5"
                                    }
                                >
                                    Instalaciones
                                </NavLink>
                            </li>
                            <li className="nav-item me-3">
                                <NavLink 
                                    to="/servicios" 
                                    className={({ isActive }) => 
                                        isActive ? "nav-link text-color fw-bold fs-5 isActive" : "nav-link text-color fw-bold fs-5"
                                    }
                                >
                                    Servicios
                                </NavLink>
                            </li>
                            <li className="nav-item me-4">
                                <NavLink 
                                    to="/entrenadores" 
                                    className={({ isActive }) => 
                                        isActive ? "nav-link text-color fw-bold fs-5 isActive" : "nav-link text-color fw-bold fs-5"
                                    }
                                >
                                    Entrenadores
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink 
                                    to="/auth" 
                                    className={({ isActive }) => 
                                        isActive ? "nav-link auth fw-bold fs-5 isActive" : "nav-link auth fw-bold fs-5"
                                    }
                                >
                                    Unirse al club
                                </NavLink>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
    );
};

export default Header;
