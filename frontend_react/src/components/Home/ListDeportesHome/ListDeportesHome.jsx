import React, { useEffect, useState } from 'react';
import CardDeportes from './CardDeportes/CardDeportes'; 
import { getDeportes } from '../../../services/GetDeportes'; 
import './ListDeportesHome.css';

const ListDeportesHome = () => {
    const [deportes, setDeportes] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        
        const fetchDeportes = async () => {
            const { data, loading } = await getDeportes();
            setDeportes(data);
            setLoading(loading);
        };

        fetchDeportes();
    }, []);
    if (loading) return <p>Loading...</p>;

    return (
        <main className="body-deportes-home">
            <div className="test">
                <div className="test-2">
                    <p className="text-center text-white text-deportes fst-italic mb-0">
                        LOS DEPORTES DE LA ÉLITE
                    </p>
                </div>  
            </div>        
            <div className="row justify-content-center">
                {deportes.slice(0, 8).map((deporte) => (
                    <CardDeportes key={deporte.id} deporte={deporte} className="col-md-3" />
                ))}
            </div>
        </main>
    );
};

export default ListDeportesHome;
