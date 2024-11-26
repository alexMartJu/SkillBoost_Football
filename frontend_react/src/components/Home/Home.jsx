import React from 'react';
import ListDeportesHome from './ListDeportesHome/ListDeportesHome';
import PistaList from './PistaList';
import MiddleSection from './MiddleSection/MiddleSection';
import HeroSection from './HeroSection/HeroSection';
import Olympics from './Olympics/Olympics';
import GraphicSection from './GraphicSection/GraphicSection';
import './Home.css';
const Home = () => {
    return (
        <> 
        <HeroSection />     
        <MiddleSection />
        <Olympics />
        <GraphicSection />
        <ListDeportesHome /> 
        </>
    );
};

export default Home;