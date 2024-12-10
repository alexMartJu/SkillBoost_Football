import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import { DeporteContextProvider } from './context/DeportesContext';
import { PistaContextProvider } from './context/PistasContext';
import { EntrenamientoContextProvider } from './context/EntrenamietosContext';

import Header from './components/Shared/Header/Header';
import Footer from './components/Shared/Footer/Footer';
import AppRouter from './routes/AppRouter';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <DeporteContextProvider>
          <PistaContextProvider>
            <EntrenamientoContextProvider>
              <Header />
              <AppRouter />
              <Footer />
            </EntrenamientoContextProvider>
          </PistaContextProvider>
        </DeporteContextProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;