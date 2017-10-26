package com.xqsight.data.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author wangganggang
 * @date 2017年10月26日 14:17
 */
public class ElasticSearchClient {

    private volatile static TransportClient client;

    private ElasticSearchClient() {
    }

    public static TransportClient getInstanceClient() {
        if (client == null) {
            synchronized (TransportClient.class) {
                if (client == null) {
                    /**
                     * 1. client.transport.ignore_cluster_name  时候校验服务名字 true 校验  false 不校验
                     * 2. client.transport.ping_timeout   链接联通时间 Defaults 5s
                     * 3. client.transport.nodes_sampler_interval  节点调用时间   Defaults 5s
                     */
                    Settings settings = Settings.builder()
                            .put("cluster.name", "my-application").build();

                    client = new PreBuiltTransportClient(settings)
                            .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));

                }
            }
        }
        return client;
    }
}
