import axios from "axios";

const TASK_API_URL = "http://localhost:8080/api/tasks"

class TaskService {
    getAllTodayTasks() {
        return axios.get(TASK_API_URL, {withCredentials: true});
    }

    getAllTasksByDate() {

    }

    markTask(id) {
        let body = true
        return axios.post(TASK_API_URL + '/mark/' + id, body, {withCredentials: true});
    }

    unmarkTask(id) {
        let body = false
        return axios.post(TASK_API_URL + '/unmark/' + id, body, {withCredentials: true});
    }
    //
    // subtasks(id) {
    //     return axios.get(TASK_API_URL + '/subtasks/' + id,  {withCredentials: true});
    // }
}

export default new TaskService()