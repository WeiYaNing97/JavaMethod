package com.example.dao;

import com.example.entity.TPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;

/**
 * @author gooluke
 * 自定义查询
 */
public interface CustomPostRepository {

    //分页查询，并高亮
    SearchHits<TPost> searchByContentOrTitleWithPageAndHighlights(String keyword, Pageable pageable);

}
