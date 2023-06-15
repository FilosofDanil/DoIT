import axios from "axios";

const HABITS_API_URL = "http://localhost:8080/api/habits"

class HabitsService {
    getAll() {
        return axios.get(HABITS_API_URL, {withCredentials: true})
    }

    getAllTracks(id) {
        return axios.get(HABITS_API_URL + '/tracks/' + id, {withCredentials: true})
    }

    mark(id) {
        let body = true
        return axios.post(HABITS_API_URL + '/mark/' + id, body,  {withCredentials: true})
    }

    unmark(id) {
        let body = false
        return axios.post(HABITS_API_URL + '/unmark/' + id, body, {withCredentials: true})
    }
}

export default new HabitsService()