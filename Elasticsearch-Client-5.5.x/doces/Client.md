所有的elasticsearch操作都是通过Client对象执行的, 所有的操作本质上都是异步执行的, 通过接受一个Listener或者返回一个Future。
客户端上的操作可以通过批量的方式执行。
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>5.5.3</version>
</dependency>
还需要添加Log4j2的依赖:
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.8.2</version>
</dependency>
### 客户端Client
可以使用多种方式使用Java客户端:
1对现有集群执行标准索引，获取，删除和搜索操作
2在正在运行的群集上执行管理任务
获取弹性搜索客户端很简单。 获取客户端的最常见方法是创建连接到集群的TransportClient。
#### Transport Client
TransportClient使用传输模块远程连接到Elasticsearch集群。
它不加入集群，而是简单地获取一个或多个初始传输地址，并以循环方式与每个动作进行通信。
Transport客户端具有群集嗅探功能，允许它动态添加新主机并删除旧主机。
传输客户端将连接到其内部节点列表中的节点，该节点是通过调用addTransportAddress构建的。
之后，客户端将调用这些节点上的内部集群状态API来发现可用的数据节点。
客户端的内部节点列表将仅替换为这些数据节点。默认情况下，此清单每五秒刷新一次。
请注意，嗅探器连接的IP地址是在那些节点的弹性搜索配置中被声明为发布(publish)地址的IP地址。
请记住，如果该节点不是数据节点，则该列表可能不包括其连接的原始节点。
例如，如果您最初连接到主节点，则在嗅探之后，不会再向该主节点提供进一步的请求，而是替换为任何数据节点。
传输客户端排除非数据节点的原因是避免将搜索流量发送到仅主节点。