package com.example.es.test;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
/*
    文档相关操作(document)
 */
public class Test2 {
    RestHighLevelClient restHighLevelClient;

    @Before
    public void init(){
        final ClientConfiguration clientConfiguration =ClientConfiguration.builder()
                .connectedTo("xx.xxx.xxx.xx:9200")
                .build();
        this.restHighLevelClient = RestClients.create(clientConfiguration).rest();
    }

    @Test  //创建文档
    public void createDoc() throws IOException {
        IndexRequest indexRequest = new IndexRequest("products");
        indexRequest.id("2") //手动指定文档的id，如果不指定则会用uuid
                .source("{\n" +
                        "  \"title\":\"瑞星咖啡\",\n" +
                        "  \"price\": 9.8,\n" +
                        "  \"created_at\":\"2023-11-18\",\n" +
                        "  \"description\":\"瑞星咖啡我最爱了，好喝\"\n" +
                        "}", XContentType.JSON);
        //参数1：索引请求对象，参数2：请求配置对象
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());
        restHighLevelClient.close();
    }

    @Test  //修改文档
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("products","1");
        updateRequest.doc("{\n" +
                "    \"title\":\"库迪咖啡非\"\n" +
                "  }",XContentType.JSON);
        //参数1：索引请求对象，参数2：请求配置对象
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
        restHighLevelClient.close();
    }

    @Test  //删除文档
    public void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("products","2");
        //参数1：索引请求对象，参数2：请求配置对象
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
        restHighLevelClient.close();
    }

    @Test  //基于id查询文档
    public void searchDocById() throws IOException {
        GetRequest getRequest = new GetRequest("products", "1");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getId());
        System.out.println(documentFields.getSourceAsString());
    }

}