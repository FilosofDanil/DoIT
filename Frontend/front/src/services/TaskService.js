import axios from "axios";

const TASK_API_URL = "http://localhost:8080/api/tasks"

class TaskService {
    getAllTodayTasks() {

        return axios.get(TASK_API_URL + '/today', {
            withCredentials: true
        });
    }

    getAllTasksByDate(date) {
        return axios.get(TASK_API_URL + '/date/' + date, {
            withCredentials: true
        });
    }

    markTask(id) {
        let body = true
        return axios.patch(TASK_API_URL + '/mark/' + id, body, {withCredentials: true});
    }

    unmarkTask(id) {
        let body = false
        return axios.patch(TASK_API_URL + '/unmark/' + id, body, {withCredentials: true});
    }

    markSubTask(id) {
        let body = true
        return axios.patch(TASK_API_URL + '/subtask/mark/' + id, body, {withCredentials: true});
    }

    unmarkSubTask(id) {
        let body = false
        return axios.patch(TASK_API_URL + '/subtask/unmark/' + id, body, {withCredentials: true});
    }

    createTask(TaskDTO) {
        return axios.post(TASK_API_URL, TaskDTO, {
            withCredentials: true
        });
    }

    updateTask(id, TaskDTO) {
        return axios.put(TASK_API_URL + '/' + id, TaskDTO, {
            withCredentials: true
        });
    }

    updateSubTask(subtask, id) {
        return axios.put(TASK_API_URL + '/subtask/' + id, subtask, {
            withCredentials: true
        });
    }

    createSubTask(subtask, id) {
        return axios.post(TASK_API_URL + '/subtask/' + id, subtask, {
            withCredentials: true
        });
    }

    deleteTask(id) {
        return axios.delete(TASK_API_URL + '/' + id, {
            withCredentials: true
        });
    }

    deleteSubTask(id) {
        return axios.delete(TASK_API_URL + '/subtask/' + id, {
            withCredentials: true
        });
    }
}

export default new TaskService()