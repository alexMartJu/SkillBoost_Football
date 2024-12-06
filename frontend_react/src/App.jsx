import React, { Suspense } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import { DeporteContextProvider } from './context/DeportesContext';
import { PistaContextProvider } from './context/PistasContext';
import { EntrenamientoContextProvider } from './context/EntrenamietosContext';

import Header from './components/Shared/Header/Header';
import Footer from './components/Shared/Footer/Footer';
// import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'

const Home = React.lazy(() => import('./pages/Home/Home'))

function App() {
  return (
    <div className='App'>
      <Suspense>
        <BrowserRouter>
          <DeporteContextProvider>
            <PistaContextProvider>
              <Header />
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/home" element={<Home />} />
              </Routes>
              <Footer />
            </PistaContextProvider>
          </DeporteContextProvider>
        </BrowserRouter>
      </Suspense>

    </div>


  )
}

export default App
