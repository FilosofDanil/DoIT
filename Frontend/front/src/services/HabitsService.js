import axios from "axios";

const HABITS_API_URL = "http://localhost:8080/api/habits"

class HabitsService {
    getAll() {
        return axios.get(HABITS_API_URL, {withCredentials: true})
    }

    create(HabitDTO) {
        return axios.post(HABITS_API_URL, HabitDTO, {withCredentials: true})
    }

    update(HabitDTO, id) {
        return axios.put(HABITS_API_URL + '/' + id, HabitDTO, {withCredentials: true})
    }

    delete(id) {
        return axios.delete(HABITS_API_URL + '/' + id, {withCredentials: true})
    }

    mark(id) {
        let body = true
        return axios.patch(HABITS_API_URL + '/mark/' + id, body, {withCredentials: true})
    }

    unmark(id) {
        let body = false
        return axios.patch(HABITS_API_URL + '/unmark/' + id, body, {withCredentials: true})
    }
}

export default new HabitsService()