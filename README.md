# Travel Randomizer 旅行随机器

## 项目简介
基于Spring Boot + Vue.js的智能旅行目的地推荐系统，包含地图交互、AI咨询和随机推荐核心功能。

## 功能特性
- 🌍 中国地图交互：点击选择目的地
- 🎲 随机城市推荐引擎
- 💬 AI旅行顾问（基于OpenAI API）
- 📱 响应式布局支持移动端访问
- 📊 ECharts数据可视化

## 技术栈
- **后端**：Spring Boot (Java 17+)
- **前端**：Vue.js 3.x + Bootstrap 5.1.3
- **地图**：ECharts 5.x + 中国城市GeoJSON数据
- **构建工具**：Maven 3.8+
- **数据库**：H2 Memory Database (开发环境)

## 快速启动
### 开发环境
```bash
# Windows系统
mvnw.cmd clean package
java -jar target/travel-randomizer-0.0.1-SNAPSHOT.jar
```

### 生产构建
```bash
mvnw.cmd -Pprod clean package
```

## 目录结构
```
├── src/main/java          # Java源代码
├── src/main/resources
│   ├── static             # 前端资源
│   │   ├── maps           # 地图数据
│   │   └── index.html     # 主页面
│   └── application.properties # 配置文件
├── pom.xml                # Maven项目配置
└── HELP.md                # 开发文档
```

## 贡献指南
1. Fork仓库
2. 创建feature分支
3. 提交PR并附带完整测试说明
4. 保持代码风格一致性

## 许可证
MIT License

## 联系
项目维护者：Tlh Team <https://github.com/woyaoxingfua>