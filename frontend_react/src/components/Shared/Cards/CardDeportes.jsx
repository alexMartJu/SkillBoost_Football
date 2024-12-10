import React from 'react';
import './CardDeportes.css';

const CardDeportes = ({ deporte }) => {
    return (
        <div className="card-deportes col-md-3">
            <img
                src={`/assets/deportes/${deporte.images[0]?.imageUrl}`}
                alt={deporte.nombre}
                className="card-img-top"
            />
            <div className="card-img-overlay d-flex align-items-end justify-content-center text-white">
                <div className="deportes-name">
                    <h3 className="fw-bold">{deporte.nombre}</h3>
                </div>
            </div>
        </div>
    );
};

export default CardDeportes;