# jspservlet
使用jsp，servlet和mysql开发的网上书店系统，前端使用bootstrap5框架进行页面设计。

## 文件结构
1. document：数据库相关模式设计文档
2. onlineBS：mysql相关脚本
3. lib：项目用到的一些库
4. src：java源代码，servlet主要负责前后端交接，entity主要负责实体定义，dao主要负责数据库操作（并提供相应的接口），util主要是辅助类
5. web：使用bootstrap5框架进行页面设计，使用jsp进行开发。（还使用了bootstrap5提供的图标库，实际开发中将图标库下载到了本地使用，但由于不方便上传，在提交的版本中换用了在线资源）

## 部署运行

### 环境
1. tomcat 9.0.83
2. jdk 1.8
3. mysql 8.0.32

### 配置
#### 数据库配置

1. 使用utf8mb4字符集
2. 创建数据库dbsc_lab3，用户dbsc_lab3，用户密码设为dbsc
3. 为用户赋予该数据库权限
4. 依次运行脚本文件onlineBS_schema.sql、onlineBS_data.sql

### 项目运行

### 源代码编译运行

1. 使用IDEA2023.3.1专业版打开项目
2. Project Structure ->Project中选择jdk1.8
3. Project Structure -> Facets中Add->web
4. Project Structure -> Artifacts -> Web Application: Exploded -> from Modules，选择相应模块即可
5. Run-> Edit Configurations
    1. Tomcat Server->Local
    2. Application server->configure，选择tomcat安装路径
    3. Deployment -> + -> Artifact

### 使用打包文件
（打包的文件可见于/out/artifacts/jspservlet/jspservlet.war）
1. 将打包的项目文件jspservlet.war放置于tomcat的webapps文件夹下
2. 运行tomcat
3. 访问http://localhost:8080/jspservlet/

## 效果图（随便截了几张图）
（从零开始自己现学、手敲的前端界面，虽然很烂但是还请体谅一下。）
![登录界面](\img\login.jpg)
![注册界面](\img\register.jpg)
![注册界面](\img\home.jpg)

