## 示例项目 1：前后端分离的简单任务管理应用（后端使用Node.js + Express）

在这个示例项目中，我们将创建一个简单的前后端分离的任务管理应用。后端使用Node.js和Express框架，前端使用React框架。后端提供RESTful API，前端通过HTTP请求与后端交互。

#### 1. 后端项目（Node.js + Express）

**步骤 1: 初始化项目**

```bash
mkdir task-manager-backend
cd task-manager-backend
npm init -y
```

**步骤 2: 安装依赖**

```bash
npm install express cors body-parser
```

- `express`: Node.js的Web框架。
- `cors`: 处理跨域请求。
- `body-parser`: 解析请求体。

**步骤 3: 创建后端代码**

在项目根目录下创建 `index.js` 文件：

```javascript
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const PORT = 5000;

// 使用中间件
app.use(cors());
app.use(bodyParser.json());

// 模拟数据
let tasks = [
  { id: 1, title: 'Learn React', completed: false },
  { id: 2, title: 'Build a Node.js API', completed: false },
];

// 获取所有任务
app.get('/api/tasks', (req, res) => {
  res.json(tasks);
});

// 添加新任务
app.post('/api/tasks', (req, res) => {
  const newTask = {
    id: tasks.length + 1,
    title: req.body.title,
    completed: false,
  };
  tasks.push(newTask);
  res.status(201).json(newTask);
});

// 启动服务器
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
```

**步骤 4: 启动后端服务器**

```bash
node index.js
```

后端服务器将在 `http://localhost:5000` 上运行。

#### 2. 前端项目（React）

**步骤 1: 初始化项目**

```bash
npx create-react-app task-manager-frontend
cd task-manager-frontend
```

**步骤 2: 安装依赖**

```bash
npm install axios
```

- `axios`: 用于发送HTTP请求。

**步骤 3: 创建前端代码**

在 `src/App.js` 中编写前端代码：

```javascript
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const App = () => {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');

  // 获取任务列表
  useEffect(() => {
    axios.get('http://localhost:5000/api/tasks')
      .then(response => setTasks(response.data))
      .catch(error => console.error('Error fetching tasks:', error));
  }, []);

  // 添加新任务
  const addTask = () => {
    if (newTask.trim() === '') return;

    axios.post('http://localhost:5000/api/tasks', { title: newTask })
      .then(response => {
        setTasks([...tasks, response.data]);
        setNewTask('');
      })
      .catch(error => console.error('Error adding task:', error));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Task Manager</h1>
      <div>
        <input
          type="text"
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
          placeholder="Enter a new task"
        />
        <button onClick={addTask}>Add Task</button>
      </div>
      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            {task.title} - {task.completed ? 'Completed' : 'Pending'}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default App;
```

**步骤 4: 启动前端开发服务器**

```bash
npm start
```

前端应用将在 `http://localhost:3000` 上运行。

#### 3. 运行项目

1. 启动后端服务器：`node index.js`（在 `task-manager-backend` 目录下）。
2. 启动前端开发服务器：`npm start`（在 `task-manager-frontend` 目录下）。
3. 打开浏览器，访问 `http://localhost:3000`，你将看到一个简单的任务管理应用。

#### 4. 项目结构

- **后端项目 (`task-manager-backend`)**
  - `index.js`: 后端主文件。
  - `package.json`: 项目依赖和脚本。

- **前端项目 (`task-manager-frontend`)**
  - `src/App.js`: 前端主组件。
  - `public/index.html`: HTML模板。
  - `package.json`: 项目依赖和脚本。

#### 5. 总结

这个示例项目展示了如何创建一个前后端分离的简单任务管理应用。后端使用Node.js和Express提供RESTful API，前端使用React构建用户界面并通过HTTP请求与后端交互。你可以在此基础上进一步扩展功能，例如添加任务删除、标记完成等功能。