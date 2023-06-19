import axios from "axios";

const TASK_API_URL = "http://localhost:8080/api/tasks"

class TaskService {
    getAllTodayTasks() {

        return axios.get(TASK_API_URL, {
            // headers: {
            //     'Content-Type': 'application/json',
            //     'Accept': 'application/json',
            //     'Access-Control-Allow-Origin': 'http://localhost:5173',
            //     'Access-Control-Allow-Credentials': true,
            //     'Access-Control-Allow-Methods': '*',
            //     'Access-Control-Allow-Headers': '*',
            //     'Origin': 'http://localhost:5173'
            // }
            withCredentials: true
        });
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
    createTask(TaskDTO) {
        return axios.post(TASK_API_URL, TaskDTO, {
            withCredentials: true
        });
    }
}

export default new TaskService()