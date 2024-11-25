import React from 'react';
import DeporteList from './deportelist';
import PistaList from './PistaList';

const Home = () => {
    return (
        <div>
            <h1>Bienvenido al Polideportivo</h1>
            <p>Aquí encontrarás información sobre todos los deportes disponibles.</p>
            <DeporteList /> {}
            <p>Disfrute de nuestras instalaciones</p>
            <PistaList/>{}
        </div>
    );
};

export default Home;