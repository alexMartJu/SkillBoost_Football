import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const getPistas = async () => {
    try {
        const response = await axios.get(`${API_URL}/pistas`);
        return Array.isArray(response.data.pistas) ? response.data.pistas : [];
    } catch (error) {
        console.error('Error fetching pistas:', error);
        throw error; 
    }
};