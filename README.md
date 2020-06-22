rabbitmq + 返回值封装 + swagger2
## 安装及配置
这里只说win10 使用docker安装的步骤，其他参考原文

### docker下的安装方法
- 下载rabbitmq 最新的Docker镜像
```
docker pull rabbitmq
```
- 使用Docker命令启动服务
```
docker run -p 5672:5672 -p 15672:15672 --name rabbitmq \
-d rabbitmq
```
- 进入容器并开启管理功能
```
docker exec -it rabbitmq /bin/bash
```
```

rabbitmq-plugins enable rabbitmq_management
```
### 访问及配置
- 访问RabbitMQ管理页面地址，查看是否安装成功（Linux下使用服务器IP访问即可）：http://localhost:15672/
- 输入账号密码并登录，这里使用默认账号密码登录：guest guest
- 创建帐号并设置其角色为管理员：mall mall
- 创建一个新的虚拟host为：/mall
- 点击mall用户进入用户配置页面
- 给mall用户配置该虚拟host的权限
- 至此，RabbitMQ的配置完成。
