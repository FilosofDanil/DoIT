import axios from "axios";

const AUTH_API_URL = "http://localhost:8080/api/auth/"

class AuthService{
    signUp(user)
    {
        return axios.post(AUTH_API_URL + 'signup', user)
    }
    login(jwt_request)
    {
        return axios.post(AUTH_API_URL + 'login', jwt_request)
    }
}

export default new AuthService()