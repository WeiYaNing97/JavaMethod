package com.example.config;

import com.example.entity.TPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

//@Component
public class ElasticsearchIndexInitializer implements CommandLineRunner {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void run(String... args) throws Exception {
        // 删除现有的索引
        if (elasticsearchRestTemplate.indexOps(TPost.class).exists()) {
            elasticsearchRestTemplate.indexOps(TPost.class).delete();
        }

        // 创建新的索引
        elasticsearchRestTemplate.indexOps(TPost.class).create();
        elasticsearchRestTemplate.indexOps(TPost.class).putMapping(TPost.class);
    }
}