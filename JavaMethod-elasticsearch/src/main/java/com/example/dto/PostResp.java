package com.example.dto;

import com.example.entity.TPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gooluke
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResp {

    private List<TPost> posts;
    private long totalCount;
    private long totalPage;
    private long currentPage;
    private long pageSize;

}
