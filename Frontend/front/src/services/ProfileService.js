import axios from "axios";

const PROFILE_API_URL = "http://localhost:8080/api/profile"

class ProfileService {
    getProfile() {
        return axios.get(PROFILE_API_URL, {withCredentials: true});
    }

    getCompletedTasksStats() {
        return axios.get(PROFILE_API_URL + '/calc_tasks', {withCredentials: true});
    }

    getUncompletedTasksStats() {
        return axios.get(PROFILE_API_URL + '/calc_un_tasks', {withCredentials: true});
    }

    getCompletedHabitsStats() {
        return axios.get(PROFILE_API_URL + '/calc_habits', {withCredentials: true});
    }

    getUncompletedHabitsStats() {
        return axios.get(PROFILE_API_URL + '/calc_un_habits', {withCredentials: true});
    }

    getAge(){
        return axios.get(PROFILE_API_URL + '/calc_age', {withCredentials: true});
    }

}

export default new ProfileService()