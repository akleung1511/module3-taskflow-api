const API_URL = "https://module3-taskflow-api-h255.onrender.com/api/tasks";

const taskList = document.getElementById("task-list");

async function loadTasks() {
    const response = await fetch(API_URL);
    const tasks = await response.json();

    console.log(tasks);
}

loadTasks();