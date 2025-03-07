package com.example.dao.impl;

import com.example.dao.CustomPostRepository;
import com.example.entity.TPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author gooluke
 */
@Component
public class PostRepositoryImpl implements CustomPostRepository {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public SearchHits<TPost> searchByContentOrTitleWithPageAndHighlights(String keyword, Pageable pageable) {
        // 构建多字段匹配查询
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<span style='color:red'>").postTags("</span>").numOfFragments(0),
                        new HighlightBuilder.Field("content").preTags("<span style='color:red'>").postTags("</span>").numOfFragments(0)
                )
                .withPageable(pageable)
                .build();

        // 执行查询
        return elasticsearchOperations.search(query, TPost.class);

        // 处理高亮结果
//        List<TPost> posts = searchHits.getSearchHits().stream()
//                .map(hit -> {
//                    TPost post = hit.getContent();
//                    Map<String, List<String>> highlightFields = hit.getHighlightFields();
//
//                    if (highlightFields.containsKey("title")) {
//                        post.setTitle(highlightFields.get("title").get(0));
//                    }
//
//                    if (highlightFields.containsKey("content")) {
//                        post.setContent(highlightFields.get("content").get(0));
//                    }
//
//                    return post;
//                })
//                .collect(Collectors.toList());
//
//        // 返回分页结果
//        return new PageImpl<>(posts, pageable, searchHits.getTotalHits());
    }
}
