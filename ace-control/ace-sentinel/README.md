https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D

1.6.3版本 

启动命令

java -Dserver.port=8088 -Dcsp.sentinel.dashboard.server=localhost:8088 -Dsentinel.dashboard.auth.username=admin -Dsentinel.dashboard.auth.password=123456 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar

其中 -Dserver.port=8088 用于指定 Sentinel 控制台端口为 8088。


从 Sentinel 1.6.0 起，Sentinel 控制台引入基本的登录功能，默认用户名和密码都是 sentinel

以上配置登录账号为：admin/123456



后台启动
BUILD_ID=dontKillMe nohup java -Dserver.port=8088 -Dcsp.sentinel.dashboard.server=localhost:8088 -Dsentinel.dashboard.auth.username=admin -Dsentinel.dashboard.auth.password=123456 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar > log.log 2>&1 &
