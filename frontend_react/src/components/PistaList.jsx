
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PistaList = () => {
    const [pistas, setPistas] = useState([]); 
    const [loading, setLoading] = useState(true); 

    useEffect(() => {
        // Función para obtener deportes desde el backend
        const fetchPistas = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/pistas');
                setPistas(Array.isArray(response.data.pistas) ? response.data.pistas : []);
                setLoading(false); 
            } catch (error) {
                console.error('Error fetching pistas:', error);
                setLoading(false); 
            }
        };

        fetchPistas();
    }, []);

    if (loading) return <p>Loading...</p>;

    return (
        <div>
            <h2>Instalaciones Disponibles</h2>
            <ul>
                {pistas.map((pista) => (
                    <li key={pista.id} style={{ borderBottom: '1px solid #ddd', padding: '8px 0' }}>
                        <h3>{pista.nombre}</h3>
                        <p>Slug: {pista.slug}</p>
                        <img src={pista.image} alt={pista.nombre} style={{ width: '100px', height: '100px' }} />
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PistaList;
