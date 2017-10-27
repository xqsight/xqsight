package com.xqsight.data.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author wangganggang
 * @date 2017年10月26日 14:58
 */
public class ElasticSearchOperator {

    @Test
    public void signInsert() {
        TransportClient client = ElasticSearchClient.getInstanceClient();
        for(int i=0;i<100;i++) {
            IndexResponse response = client.prepareIndex("users", "user", ("" + i))
                    .setSource(ElasticsearchIndex.buildIndex()).get();
            System.out.println(response.getIndex());
        }
    }

    @Test
    public void signUpdate() {
        TransportClient client = ElasticSearchClient.getInstanceClient();
        UpdateRequest updateRequest = null;
        try {
            updateRequest = new UpdateRequest("users", "user", "2")
                    .doc(jsonBuilder()
                            .startObject()
                            .field("userName", "male")
                            .endObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            UpdateResponse response = client.update(updateRequest).get();
            System.out.println(response.getIndex());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signDel(){
        TransportClient client = ElasticSearchClient.getInstanceClient();
        DeleteResponse response = client.prepareDelete("users", "user", "3").get();
        System.out.println(response.getIndex());
    }

    @Test
    public void searchDel(){
        TransportClient client = ElasticSearchClient.getInstanceClient();

        BulkByScrollResponse response =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery("userName", "male"))
                        .source("users")
                        .get();

        long deleted = response.getDeleted();

        System.out.println(deleted);
    }

    @Test
    public void get(){
        TransportClient client = ElasticSearchClient.getInstanceClient();
        GetResponse response = client.prepareGet("users", "user", "1").get();
        if(response.isExists()){
            System.out.println(response.getSourceAsString());
        }
        System.out.println(response.getIndex());
    }

    @Test
    public void search(){
        TransportClient client = ElasticSearchClient.getInstanceClient();

        /**
         * index 可以多个
         * type 可以多个
         */
        SearchResponse response = client.prepareSearch("users")
                .setTypes("user")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("userName", "test"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("userAge").from(12).to(30))     // Filter
                .setFrom(0).setSize(20).setExplain(true)
                .get();

        System.out.println(response.getHits().getHits().length);
        System.out.println(response.getHits().getAt(0).getSourceAsString());
    }
}
