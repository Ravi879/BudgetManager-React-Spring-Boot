import axios from 'axios';

const setErrorInterceptor = () => axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    if (error.response && error.response.data && error.response.data.message) {
        const message = error.response.data.message;
        return Promise.reject(message);
    }

    return Promise.reject(error.message);
});


export default setErrorInterceptor;