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

