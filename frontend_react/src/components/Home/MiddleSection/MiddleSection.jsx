import React, { useEffect, useState } from 'react';
import { Splide, SplideSlide } from '@splidejs/react-splide';
import '@splidejs/splide/dist/css/splide.min.css';
import './MiddleSection.css';


const MiddleSection = () => {
    const [pistas, setPistas] = useState([]);

    useEffect(() => {
       
        const fetchPistas = async () => {   
            const data = [
                { id: 1, image: 'pista_atletismo.jpg' },
                { id: 2, image: 'piscina.jpg' },
                { id: 3, image: 'pista_ciclismo.jpg' },
                { id: 4, image: 'rocodromo.jpg' },
                { id: 5, image: 'pista_padel.jpg' },
            ];
            setPistas(data);
        };

        fetchPistas();
    }, []);

    const splideOptions = {
        type: 'loop',
        autoplay: true,
        interval: 3000,
        height: '100%',
        arrows: false,
        pagination: true,
    };
    return (
        <div className="landing-section d-flex">
            {/* Text Section */}
            <div className="text-section col-md-6 d-flex flex-column justify-content-center px-4">
                <div className="text-container">
                    <p className="fw-bold text-center">
                        LAS MEJORES INSTALACIONES
                    </p>
                </div>
            </div>

            {/* Carousel Section */}
            <div className="carousel-section col-md-6 p-0">
                <Splide options={splideOptions} className="h-100">
                    {pistas.length > 0 ? (
                        pistas.map((pista) => (
                            <SplideSlide key={pista.id}>
                                <img
                                    src={`/assets/home/${pista.image}`}
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt=""
                                />
                            </SplideSlide>
                        ))
                    ) : (
                        <>
                            <SplideSlide>
                                <img
                                    src="/assets/home/pista_atletismo.jpg"
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt="Pista de Atletismo"
                                />
                            </SplideSlide>
                            <SplideSlide>
                                <img
                                    src="/assets/home/piscina.jpg"
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt="Piscina"
                                />
                            </SplideSlide>
                            <SplideSlide>
                                <img
                                    src="/assets/home/pista_ciclismo.jpg"
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt="Pista de Ciclismo"
                                />
                            </SplideSlide>
                            <SplideSlide>
                                <img
                                    src="/assets/home/rocodromo.jpg"
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt="Rocódromo"
                                />
                            </SplideSlide>
                            <SplideSlide>
                                <img
                                    src="/assets/home/pista_padel.jpg"
                                    className="d-block w-100 h-100 object-fit-cover"
                                    alt="Pista de Pádel"
                                />
                            </SplideSlide>
                        </>
                    )}
                </Splide>
            </div>
        </div>
    );
};

export default MiddleSection;