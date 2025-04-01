# App后端管理系统

## 项目简介
这是一个基于Spring Boot的应用后台管理系统，主要用于管理应用程序的后端服务，包括用户管理、应用信息管理、版本控制等功能。

## 技术栈
- Spring Boot 2.3.12.RELEASE
- MyBatis
- MySQL
- Druid连接池
- JWT认证
- Knife4j接口文档
- Lombok
- PageHelper分页插件

## 主要功能
- 用户管理
  - 用户注册（支持普通用户和开发者两种角色）
  - 用户登录
  - 用户认证
- 应用管理
  - 应用信息维护
  - 应用分类管理
  - 版本控制
- 开发者管理
  - 开发者注册
  - 开发者信息管理

## 项目结构
```
src/main/java/com/justin/app_back/
├── controller/     # 控制器层
├── service/        # 服务层
├── mapper/         # 数据访问层
├── entity/         # 实体类
├── dto/            # 数据传输对象
├── vo/             # 视图对象
└── config/         # 配置类
```

## 快速开始

### 环境要求
- JDK 1.8
- Maven 3.x
- MySQL 5.7+

### 运行步骤
1. 克隆项目到本地
2. 配置数据库连接信息（application.properties）
3. 执行数据库脚本
4. 运行以下命令：
```bash
mvn spring-boot:run
```

### 接口文档
项目集成了Knife4j，启动后访问：http://localhost:8080/doc.html

## 开发者
- 作者：小杜
- 版本：1.0