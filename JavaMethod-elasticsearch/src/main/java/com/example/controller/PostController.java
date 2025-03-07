package com.example.controller;

import com.example.dao.PostRepository;
import com.example.dto.PostResp;
import com.example.entity.TPost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author gooluke
 */
@Api(tags = "elasticsearch管理")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /**
     * 新增帖子
     */
    @ApiOperation("新增帖子")
    @PostMapping("/add")
    public String add() {/*@RequestBody TPost tPost*/
        TPost tPost = new TPost();
        tPost.setId(1L);
        tPost.setTitle("测试帖子");
        tPost.setContent("测试帖子内容");
        tPost.setCreateTime("2024-01-01 00:00:00");
        tPost.setUpdateTime("2025-01-01 00:00:00");
        tPost.setHighlights(new HashMap<>());
        postRepository.save(tPost);
        return "success";
    }

    /**
     * 根据关键字查询帖子
     */
    @ApiOperation("根据关键字查询帖子")
    @GetMapping("/search")
    public List<TPost> searchByName(@RequestParam String keywords) {
        //return postRepository.findByContent(keywords);
        SearchHits<TPost> searchHits = postRepository.searchByContentOrTitle(keywords);
        List<TPost> results  = new ArrayList<>();
        for (SearchHit<TPost> hit : searchHits) {
            TPost post = hit.getContent();
            // 处理高亮
            Map<String, List<String>> highlights = new HashMap<>(hit.getHighlightFields());
            post.setHighlights(highlights);
            results.add(post);
        }
        return results;
    }

    /**
     * 根据关键字查询帖子，并分页
     */
    @ApiOperation("根据关键字查询帖子，并分页")
    @GetMapping("/searchByPage")
    public PostResp searchByNameByPage(@RequestParam String keywords, @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        PostResp resp = new PostResp();
        // 创建 Pageable 对象
        Pageable pageable = PageRequest.of(page - 1, size);
        // 执行分页查询
        Page<TPost> pageData = postRepository.searchByContentOrTitleWithPage(keywords,pageable);
        //Page<TPost> pageData = postRepository.searchByContentOrTitleWithPageAndHighlights(keywords,pageable);
        List<TPost> posts = pageData.getContent();
        int totalPages = pageData.getTotalPages();
        long totalCount = pageData.getTotalElements();

        resp.setPosts(posts);
        resp.setTotalCount(totalCount);
        resp.setTotalPage(totalPages);
        resp.setCurrentPage(page);
        resp.setPageSize(size);
        return resp;
    }

    /**
     * 根据关键字查询帖子，并分页，并高亮
     */
    @ApiOperation("根据关键字查询帖子，并分页")
    @GetMapping("/searchByPageHighlight")
    public PostResp searchByPageHighlight(@RequestParam String keywords, @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        PostResp resp = new PostResp();
        // 创建 Pageable 对象
        Pageable pageable = PageRequest.of(page - 1, size);
        // 执行分页查询
        SearchHits<TPost> searchHits = postRepository.searchByContentOrTitleWithPageAndHighlights(keywords, pageable);
        Iterator<SearchHit<TPost>> iterator = searchHits.iterator();
        ArrayList<TPost> posts = new ArrayList<>();
        while (iterator.hasNext()) {
            SearchHit<TPost> hit = iterator.next();
            TPost post = hit.getContent();
            // 处理高亮
            Map<String, List<String>> highlights = new HashMap<>(hit.getHighlightFields());
            post.setHighlights(highlights);
            posts.add(post);
        }
        long totalCount = searchHits.getTotalHits();
        int totalPages = (int) (totalCount == 0 ? 0 : (totalCount / size + 1));

        resp.setPosts(posts);
        resp.setTotalCount(totalCount);
        resp.setTotalPage(totalPages);
        resp.setCurrentPage(page);
        resp.setPageSize(size);
        return resp;
    }

    /**
     * 获取所有帖子
     */
    @ApiOperation("获取所有帖子")
    @GetMapping("/all")
    public Iterable<TPost> getAll() {
        Iterable<TPost> posts = postRepository.findAll();
        ArrayList<TPost> tPosts = new ArrayList<>();
        for (TPost post : posts) {
            tPosts.add(post);
        }
        return tPosts;
    }
}
