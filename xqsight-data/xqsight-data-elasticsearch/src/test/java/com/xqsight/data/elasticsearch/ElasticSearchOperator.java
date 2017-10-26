package com.xqsight.data.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;

/**
 * @author wangganggang
 * @date 2017年10月26日 14:58
 */
public class ElasticSearchOperator {

    @Test
    public void test() {
        TransportClient client = ElasticSearchClient.getInstanceClient();
        for(int i=0;i<100;i++) {
            IndexResponse response = client.prepareIndex("users", "user", ("" + i))
                    .setSource(ElasticsearchIndex.buildIndex()).get();
            System.out.println(response.getIndex());
        }
    }
}
