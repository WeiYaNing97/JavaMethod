package com.example.es.test;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.Map;

public class Test3 {
    RestHighLevelClient restHighLevelClient;

    @Before
    public void init(){
        final ClientConfiguration clientConfiguration =ClientConfiguration.builder()
                .connectedTo("xx.xxx.xxx.xx:9200")
                .build();
        this.restHighLevelClient = RestClients.create(clientConfiguration).rest();
    }

    @Test //查询一个索引中的所有文档
    public void searchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }


    @Test //基于关键词查询
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("description","好喝")));//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }

    /*
     * 分页查询
     * 排序
     * 字段
     */
    @Test //分页查询 与排序
    public void search2() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
                .from(1) //起始位置 start=(page-1)*size
                .size(1) //每页显示条数
                .sort("price", SortOrder.ASC) //字段排序
                .fetchSource(new String[]{},new String[]{"created_at"}); //参数1：包含字段数组  参数2：排除字段数组
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }

    @Test //高亮
    public void search3() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false).field("description").field("title").preTags("<span style='color:red;'>").postTags("</span>");
        searchSourceBuilder.query(QueryBuilders.termQuery("description","好喝"))
                .highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            System.out.println("id: "+hit.getId()+" source: "+hit.getSourceAsString());
            //获取高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields.containsKey("description")){
                System.out.println("description高亮结果： "+highlightFields.get("description").fragments()[0]);
            }
            if(highlightFields.containsKey("title")){
                System.out.println("title高亮结果： "+highlightFields.get("title").fragments()[0]);
            }
        }
    }


    @Test //过滤查询
    public void search4() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
                .postFilter(QueryBuilders.idsQuery().addIds("1")); //指定过滤条件
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }
}