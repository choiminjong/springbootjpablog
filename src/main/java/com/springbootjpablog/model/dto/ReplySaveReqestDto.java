package com.springbootjpablog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveReqestDto {
    private Long userId;
    private Long boardId;
    private String content;
}
