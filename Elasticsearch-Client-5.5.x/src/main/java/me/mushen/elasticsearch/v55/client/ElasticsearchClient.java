package me.mushen.elasticsearch.v55.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-10-16
 */
public class ElasticsearchClient {

    public static TransportClient transportClient() throws UnknownHostException {
        // 配置
        Settings settings = Settings.builder()
                // 需要连接的集群名称, 默认是"elasticsearch"
                .put("cluster.name", "koumakan")
                // 启用集群群集嗅探功能, 默认为false
                .put("client.transport.sniff", true)
                // 如果设置为true, 将忽略连接节点的集群名称验证。
                .put("client.transport.ignore_cluster_name", false)
                // 等待来自节点的ping响应的时间, 默认5s(5秒)
                .put("client.transport.ping_timeout", "5s")
                // 对addTransportAddress添加的节点进行采样/ping的频率, 默认5s(5秒)
                .put("client.transport.nodes_sampler_interval", "5s")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        // 连接的节点, 必须是节点的发布地址(即elasticseach.yml中配置的publish_address)
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        return client;
    }
}
