# 本地启动
1. 启动 Kafka
- 首先进入 Kafka 安装目录下
- 先启动 zookeeper: `bin\windows\zookeeper-server-start.bat config\zookeeper.properties`
- 再启动 Kafka: `bin\windows\kafka-server-start.bat config\server.properties`
3. 启动 ES
进入 ES 安装目录下，直接找 bin 下的 elasticsearch.bat 文件，点击启动即可。

# 会话管理
- cookie 
  - HTTP 是无状态的，无法处理连续性的业务，是有会话的，可以使用 HTTP 的头部扩展，
HTTP Cookies 解决这个问题来创建有状态（连贯的、有联系的）的会话。
  - HTTP Cookies 是服务器发送到用户浏览器并保存在本地的一小块数据，它会在浏览器下次访问同一服务器再发送请求
时被携带并发送到服务器上。Cookies 使得基于无状态的 HTTP 协议来记录稳定的状态信息成为了可能。 
  - 用途：会话状态管理（用户登录状态、购物车、游戏分数或其他需要记录的信息），个性化设置（用户自定义设置、主题），浏览器行为跟踪（跟踪分析用户行为） 
  - 数据存储在客户端，不安全
- session
  - 是JavaEE的标准，用于在服务端记录客户端信息
  - 数据存放在服务端更加安全，但是也会增加服务端的内存压力
  - 本质是服务端发送 cookie 存储 session 的唯一标识 id，注意这里并不暴露 session 实体，浏览器发送请求也会携带这份id，来实现和服务端之间有状态的会话
- 分布式部署session共享方案
  - 问题：分部署部署下，一个浏览器发送两次请求，两次请求所抵达的服务器不同，无法获取 session，就会导致业务功能错乱
  - 解决方案：
    - 粘性 session：同一个 ip 发送的请求，分配给固定的服务器，显然，这种解决方案会导致负载不均衡
    - 同步 session：请求抵达服务器并创建 session 后，将该 session 同步给其他服务器，该方案无法保证服务器集群间低耦合、同步对服务器性能有所消耗
    - 共享 session：单独设置一个服务器，提供存储 session、获取 session 的功能，但是如果该单体服务器宕机，整个项目可能会因此无法正常运行
    - 使用 Redis 管理 session：将 session 信息存储在高性能的 Redis 缓存数据库中