package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSearchSupportRes {
    private Long support_idx;
    private String support_policy;
    private String support_name;
    private String support_institution;
    private String support_content;
    private String support_image;
    private int support_view;
    private int support_comment;
}
