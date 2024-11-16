import axios from 'axios'

export default (URL) => {
    console.log(`Api service URL: ${URL}`);

    const api = axios.create({
        baseURL: URL
    })

    return api
}