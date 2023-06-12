import axios from "axios";

const PROFILE_API_URL = "http://localhost:8080/api/profile"

class ProfileService {
    getProfile() {
        return axios.get(PROFILE_API_URL, { withCredentials: true });
    }
}

export default new ProfileService()