import axios from "axios";

const AUTH_API_URL = "http://localhost:8080/api/auth/"

class AuthService {
    signUp(user) {
        return axios.post(AUTH_API_URL + 'signup', user)
    }

    login(jwt_request) {
        return axios.post(AUTH_API_URL + 'login', jwt_request, {
            headers: {
                Authorization: "Bearer " + jwt_request.login,
            },
            withCredentials: true,
        });
    }
    activate(code) {
        return axios.get(AUTH_API_URL + 'activate/'+code)
    }
}

export default new AuthService()