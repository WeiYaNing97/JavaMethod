package com.example.es.test;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;

/*
    索引相关操作
 */
public class Test1 {

    RestHighLevelClient restHighLevelClient;

    @Before()
    public void init(){
        final ClientConfiguration clientConfiguration =ClientConfiguration.builder()
                .connectedTo("123.57.51.130:9200")
                .build();
        this.restHighLevelClient = RestClients.create(clientConfiguration).rest();
    }

    @Test//创建索引
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
        //指定映射 参数1：指定映射json结构 参数2：指定数据类型
        createIndexRequest.mapping("{\n" +
                "    \"properties\": {\n" +
                "        \"id\":{\n" +
                "          \"type\":\"integer\"\n" +
                "        },\n" +
                "        \"title\":{\n" +
                "          \"type\":\"keyword\"\n" +
                "        },\n" +
                "        \"price\":{\n" +
                "          \"type\":\"double\"\n" +
                "        },\n" +
                "        \"created_at\":{\n" +
                "          \"type\":\"date\"\n" +
                "        },\n" +
                "        \"description\":{\n" +
                "          \"type\":\"text\",\n" +
                "          \"analyzer\": \"ik_max_word\"\n" +
                "        }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        //参数1：创建索引请求对象  参数2：请求配置对象
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("创建状态："+createIndexResponse.isAcknowledged()); //打印返回信息
        restHighLevelClient.close(); //关闭资源
    }

    @Test//删除索引
    public void deleteIndex() throws IOException {
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(new DeleteIndexRequest("products"), RequestOptions.DEFAULT);
        System.out.println("删除状态："+acknowledgedResponse.isAcknowledged()); //打印返回信息
        restHighLevelClient.close(); //关闭资源
    }
}
