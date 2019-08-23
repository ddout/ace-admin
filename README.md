# Cloud-Platform
Cloud-Platform是国内首个基于`Spring 
Cloud`微`服务`化`开发平台`，具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API
管理等多个模块，支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。
核心技术采用`Spring Boot 
2.1.2`以及`Spring Cloud (Greenwich.RELEASE)
`相关核心组件，采用`Nacos`注册和配置中心，集成流量卫兵`Sentinel`，前端采用`vue-element-admin`组件，`Elastic Search`自行集成。







### 基础文档，[点击查看](https://gitee.com/geek_qi/cloud-platform/wikis/Home)

# 模块说明
![架构图](https://images.gitee.com/uploads/images/2019/0528/205306_9a8b8d83_1899222.png "1.png")

## 公众号
![输入图片说明](https://images.gitee.com/uploads/images/2019/0712/091503_c2618a08_547642.jpeg "qrcode_for_gh_e5a084d37c0d_258 (2).jpg")

### 架构摘要
#### 服务鉴权
通过`JWT`的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

#### 监控
利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

#### 负载均衡
将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和ribbon，可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的`Zuul限流插件`，方面进行限流。

#### 服务注册与调用
基于Nacos来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

#### 熔断机制
因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了`Hystrix`的作为熔断器，避免了服务之间的“雪崩”。

------

## 功能截图

### 基本功能
# 功能截图
![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205334_7a892d09_1899222.png "2.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205343_124202af_1899222.png "3.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205353_2fd7c93f_1899222.png "4.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205407_942a9540_1899222.png "5.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205420_01f6b933_1899222.png "6.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205429_5c7bb102_1899222.png "7.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205438_f1cd07c4_1899222.png "8.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205447_878d8a39_1899222.png "9.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205456_ec6fe29c_1899222.png "10.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205504_1ecaec42_1899222.png "11.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205514_27cd17dc_1899222.png "12.png")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0528/205521_7379cb06_1899222.png "13.png")
## License
Apache License Version 2.0


Cloud-Platform
Cloud-Platform是国内首个基于Spring Cloud微服务化开发平台，具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API 管理等多个模块，支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。 核心技术采用Spring Boot 2.1.2以及Spring Cloud (Greenwich.RELEASE) 相关核心组件，采用Nacos注册和配置中心，集成流量卫兵Sentinel，前端采用vue-element-admin组件，Elastic Search自行集成。

系统架构
image.png

架构详解
监控
利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

负载均衡
将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和rebbion，可以帮我们进行正常的网关管控和负载均衡。

服务注册与调用
基于Nacos来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

熔断机制
因为采取了服务的分布，为了避免服务之间的调用“雪蹦”，我采用了Hystrix的作为熔断器，避免了服务之间的“雪蹦”。

后端工程启动
项目结构
ace-security
  ace-modules--------------业务服务模块（基础系统、搜索、OSS）

  ace-auth-----------------服务鉴权中心

  ace-gate-----------------网关负载中心

  ace-common---------------通用脚手架

  ace-control--------------运维中心（配置、监控、链路）

  ace-web-ui--------------前端页面

  init-db-base---------数据库初始化脚本



configure-files----

环境须知!
mysql一个，redis一个，sentiel一个，nacos注册中心一个
jdk1.8
IDE插件一个，lombok插件，具体百度即可
搭建
须知
因为Cloud-Platform是一个前后端分离的项目，所以后端的服务必须先启动，在后端服务启动完成后，再启动前端的工程。

最多人问：代码有漏
下载完后端代码后，记得先安装lombok插件，否则你的IDE会报代码缺失。


环境搭建
node 版本：v6.11.2
npm 版本：3.10.10
开发（在UI目录下）
    

    # 安装依赖
    npm install
    
    ## 若上述不行则采取下面命令
    npm install --registry=https://registry.npm.taobao.org
    
    # 本地开发 开启服务
    npm run dev
浏览器访问 http://localhost:9527

发布
    # 构建生成环境
    npm run build:prod
目录结构
 build                      // 构建相关  
 config                     // 配置相关
 src                        // 源代码
    api                    // 所有请求
    assets                 // 主题 字体等静态资源
    components             // 全局公用组件
    directive              // 全局指令
    filtres                // 全局filter
    mock                   // mock数据
    router                 // 路由
    store                  // 全局store管理
    styles                 // 全局样式
    utils                  // 全局公用方法
    view                   // view
    App.vue                // 入口页面
    main.js                // 入口 加载组件 初始化等
 static                     // 第三方不打包资源
    Tinymce                // 富文本
 .babelrc                   // babel-loader 配置
 eslintrc.js                // eslint 配置项
 .gitignore                 // git 忽略项
 favicon.ico                // favicon图标
 index.html                 // html模板
 package.json               // package.json





1. 先启动redis、rabbitmq、mysql以及nacos注册中心

2. 运行数据库脚本：依次运行数据库：

   alibaba-base/biz-db/admin-init.sql

3. 依次配置统一配置文件并提交：

4. 按顺序运行main类：

#### 模块说明
|        模块名称 | 端口 | 说明             | 启动顺序 |
| --------------: | ---: | :--------------- | :------- |
|           redis | 6379 | redis  (123456)          | -1      |
|        rabbitmq | 5672 | rabbitmq  (guest/guest)       | -1      |
|           mysql | 3306 | mysql  (root/123456)          | -1      |
|    ace-sentinel | 8088 | 阿里流量控制 (admin/123456)    | 0        |
|       ace-nacos | 8848 | 阿里注册中心+配置中心 | 0        |
|     ace-monitor | 8764 | springboot-admin | 0      |
| configure-files |      | 应用配置文件。上线时需要在nacos中对应增加配置 | 0 |
| ace-web-ui | 9527 | web前端,npm run dev启动开发模式 | |
| | | | |
| ace-auth-server | 9777 | 授权中心-服务    | 1       |
|        ace-gate | 8765 | 统一网关-服务    | 2       |
|       ace-admin | 8762 | 权限管理-服务    | 3       |
|                 |      |                  |          |



#### 代码生成器说明：

修改数据库配置，启动后 访问 http://ip:7777；

注意数据库表需要填写备注信息，包括字典备注，否则生成的代码没有表头或标题。

1. 填写JAVA包名， 例如：com.github.wxiaoqi.security.pms

2. 填写前端前缀， 例如：pms/dict

3. 选择所需要生成代码的表，点击生成
4. 下载代码包，copy进对应代码目录
5. 前端route中增加对应配置
6. 系统中，菜单管理增加对应菜单配置(生成代码中有sql脚本，批量替换 {&替换为所属模块ID&} 后执行)
7. over。



待完善：

1. 代码生成器：  日期类型，数值类型、text类型，的页面控件自动匹配（列表影藏text的，日期格式显示和填写等）

