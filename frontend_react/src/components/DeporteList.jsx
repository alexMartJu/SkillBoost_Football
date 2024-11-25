
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const DeporteList = () => {
    const [deportes, setDeportes] = useState([]); 
    const [loading, setLoading] = useState(true); 

    useEffect(() => {
        // Función para obtener deportes desde el backend
        const fetchDeportes = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/deportes');
                console.log(response);
                setDeportes(Array.isArray(response.data.deportes) ? response.data.deportes : []);
                setLoading(false); 
                console.log(deportes);
            } catch (error) {
                console.error('Error fetching deportes:', error);
                setLoading(false); 
            }
        };

        fetchDeportes();
    }, []);

    if (loading) return <p>Loading...</p>;

    return (
        <div>
            <h2>Deportes Disponibles</h2>
            <ul>
                {deportes.map((deporte) => (
                    <li key={deporte.id} style={{ borderBottom: '1px solid #ddd', padding: '8px 0' }}>
                        <h3>{deporte.nombre}</h3>
                        <p>Slug: {deporte.slug}</p>
                        <img  src={`/assets/deportes/${deporte.images[0].imageUrl}`}  alt={deporte.nombre} style={{ width: '100px', height: '100px' }} />
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default DeporteList;
