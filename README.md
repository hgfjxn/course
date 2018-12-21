# course
course microservice

## 消息服务

port: 9090

## 用户服务

port: 8080

## 用户edge Service

port: 8888

## 课程服务

port: 9999

## 课程Edge Service

port: 9966

## api gateway

port: 10000


## thrift client auto config

`application.yml`定义了thrift的客户端参数：

```yml
thrift:
  type: win.hgfdodo.course.user.UserService.Client
  address: localhost
  port: 8080
```

> 默认的protocol 使用的是`TBinaryProtocol`, 默认的transport 使用的是 `TFramedTraspoort`

- [ ] 支持任意的trasport
- [ ] 支持任意的protocol  
