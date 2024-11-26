// import axios from 'axios';

// const API_URL = 'http://localhost:8080/api';

// export const getDeportes = async () => {
//     try {
//         const response = await axios.get(`${API_URL}/deportes`);
//         return Array.isArray(response.data.deportes) ? response.data.deportes : [];
//     } catch (error) {
//         console.error('Error fetching deportes:', error);
//         throw error; 
//     }
// };


import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const getDeportes = async () => {
    try {
        const response = await axios.get(`${API_URL}/deportes`);
        const deportes = Array.isArray(response.data.deportes) ? response.data.deportes : [];
        return { data: deportes, loading: false }; 
    } catch (error) {
        console.error('Error fetching deportes:', error);
        return { data: [], loading: false };    
    }
};