import React from 'react';
import ListDeportesHome from '../../components/Shared/Lists/ListDeportesHome';
import PistaList from '../../components/Shared/Lists/ListPista';
import MiddleSection from '../../components/Home/MiddleSection/MiddleSection';
import HeroSection from '../../components/Home/HeroSection/HeroSection';
import Olympics from '../../components/Home/Olympics/Olympics';
import GraphicSection from '../../components/Home/GraphicSection/GraphicSection';
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