package com.ddwj.kimgram.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
    private Integer code; // 1 : 성공 , -1 : 실패
    private String message;
    private T data;
}
